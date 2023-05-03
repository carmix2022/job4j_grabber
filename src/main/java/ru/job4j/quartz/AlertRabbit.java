package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    public static void main(String[] args) throws IOException {
        Properties loadedRabbitProperties = loadRabbitProperties();
        try (Connection connection = initConnection(loadedRabbitProperties)) {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            data.put("connection", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(loadedRabbitProperties.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
            System.out.println(store);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
            Long createdDate = System.currentTimeMillis();
            store.add(createdDate);
            Connection cn = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            try (PreparedStatement statement =
                         cn.prepareStatement("INSERT INTO rabbit(created_date) VALUES (?)")) {
                statement.setTimestamp(1, new Timestamp(createdDate));
                statement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Properties loadRabbitProperties() throws IOException {
        Properties rabbitProperties = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
             rabbitProperties.load(in);
        }
        return rabbitProperties;
    }

    private static Connection initConnection(Properties p) throws ClassNotFoundException, SQLException {
        Class.forName(p.getProperty("jdbc.driver"));
        String url = p.getProperty("jdbc.url");
        String login = p.getProperty("jdbc.username");
        String password = p.getProperty("jdbc.password");
        return DriverManager.getConnection(url, login, password);
    }
}