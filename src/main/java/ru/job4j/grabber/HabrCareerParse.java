package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) throws IOException {
        List<Post> postList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            String linkPage = "%s%s%d".formatted(link, "?page=", i);
            pageParsing(linkPage, postList);
        }
        return postList;
    }

    private void pageParsing(String anotherPage, List<Post> postList) throws IOException {
        Connection connection = Jsoup.connect(anotherPage);
        Document document = connection.get();
        Elements rows = document.select(".vacancy-card__inner");
        rows.forEach(row -> {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element dateElement = row.select(".vacancy-card__date").first().child(0);
            Element linkElement = titleElement.child(0);
            String vacancyName = titleElement.text();
            LocalDateTime date = dateTimeParser.parse(dateElement.attr("datetime"));
            String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            String description = null;
            try {
                description = retrieveDescription(link);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            postList.add(new Post(vacancyName, link, description, date));
        });
    }

    private String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements description = document.select(".vacancy-description__text");
        return String.format("%s%n", description.text());
    }
}