package com.example;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    // public static void main(String[] args) {
    //     String html = "<html><head><title>Sample Title</title></head>"
    //     + "<body>"
    //     + "<p>Sample Content</p>"
    //     + "<div id='sampleDiv'><a href='www.google.com'>Google</a>"
    //     + "<h3><a>Sample</a><h3>"
    //     +"</div>"
    //     +"</body></html>";
    //  Document document = Jsoup.parse(html);

    //  //a with href
    //  Element link = document.select("a").first();         

    //  System.out.println("Outer HTML: " + link.outerHtml());
    //  System.out.println("Inner HTML: " + link.html());

    // }

    private static final String URL = "https://www.walmart.com/reviews/product/35192854";


    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(URL).get();

        Elements fullreviews = doc.getElementsByClass("w_DHV_ pv3 mv0");
        Elements reviews = doc.getElementsByClass("tl-m mb3 db-m");
;
        //System.out.println(doc);
        // System.out.println("Outer HTML: " + link.outerHtml());
        // System.out.println("Inner HTML: " + link.html());

       // System.out.println(fullreviews);

        Object[] idk = fullreviews.toArray();

        String a = idk[0].toString();
        for (int i =0; i < idk.length; i++){

            System.out.println(idk[i]);

            System.out.println("NEXT ###########################################################################################################");
        }
    }
}
