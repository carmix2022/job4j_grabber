package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE1_LINK = String.format("%s/vacancies/java_developer?page=1", SOURCE_LINK);

    private static void pageParsing(String anotherPage) throws IOException {
        Connection connection = Jsoup.connect(anotherPage);
        Document document = connection.get();
        Elements rows = document.select(".vacancy-card__inner");
        rows.forEach(row -> {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element dateElement = row.select(".vacancy-card__date").first().child(0);
            Element linkElement = titleElement.child(0);
            String vacancyName = titleElement.text();
            String date = dateElement.attr("datetime").split("T")[0];
            String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            System.out.printf("%s %s %s%n", vacancyName, date, link);
        });
    }

    public static void main(String[] args) throws IOException {
        StringBuilder anotherPage = new StringBuilder(PAGE1_LINK);
        for (int i = 1; i <= 5; i++, anotherPage.setCharAt(anotherPage.length() - 1, (char) (i + 48))) {
            pageParsing(anotherPage.toString());
        }
    }
}