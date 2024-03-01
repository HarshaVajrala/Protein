package com.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String html = "<html><head><title>Sample Title</title></head>"
        + "<body><p>Sample Content</p></body></html>";
        Document document = Jsoup.parse(html);
        System.out.println(document.title());
        Elements paragraphs = document.getElementsByTag("p");
        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }
    }
}