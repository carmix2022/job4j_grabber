package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);
    private static final HabrCareerDateTimeParser DATE_TIME_PARSER = new HabrCareerDateTimeParser();

    private static void pageParsing(String anotherPage) throws IOException {
        Connection connection = Jsoup.connect(anotherPage);
        Document document = connection.get();
        Elements rows = document.select(".vacancy-card__inner");
        rows.forEach(row -> {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element dateElement = row.select(".vacancy-card__date").first().child(0);
            Element linkElement = titleElement.child(0);
            String vacancyName = titleElement.text();
            LocalDateTime date = DATE_TIME_PARSER.parse(dateElement.attr("datetime"));
            String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            System.out.printf("%s %s %s%n", vacancyName, date, link);
        });
    }

    private static String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements description = document.select(".vacancy-description__text");
        return String.format("%s%n", description.text());
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 5; i++) {
            String link = "%s%d".formatted(PAGE_LINK, i);
            pageParsing(link);
        }
    }
}