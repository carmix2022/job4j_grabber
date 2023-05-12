package ru.job4j.grabber;

import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("jdbc.url"),
                    cfg.getProperty("jdbc.username"),
                    cfg.getProperty("jdbc.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement =
                     cnn.prepareStatement("INSERT INTO post(name, text, link, created) "
                                     + "VALUES (?, ?, ?, ?) ON CONFLICT (link) DO NOTHING",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Post createPost(ResultSet rs) throws SQLException {
        Post post = new Post(
                rs.getString("name"),
                rs.getString("link"),
                rs.getString("text"),
                rs.getTimestamp("created").toLocalDateTime()
        );
        post.setId(rs.getInt("id"));
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> gottenPosts = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from post;");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                gottenPosts.add(createPost(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gottenPosts;
    }

    @Override
    public Post findById(int id) {
        Post foundPost = null;
        try (PreparedStatement statement = cnn.prepareStatement("SELECT * FROM post WHERE id = ? LIMIT 1")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundPost = createPost(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundPost;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config.load(in);
        }
        try (PsqlStore ps = new PsqlStore(config)) {
            HabrCareerParse hcp = new HabrCareerParse(new HabrCareerDateTimeParser());
            List<Post> postList = hcp.list("https://career.habr.com/vacancies/java_developer");
            for (Post post : postList) {
                ps.save(post);
            }
            System.out.println(ps.findById(1) + "\n");
            System.out.println(ps.getAll());
        }
    }
}