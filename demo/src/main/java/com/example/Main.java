package com.example;
import java.io.IOException;

import javax.swing.text.html.HTMLEditorKit.Parser;

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

        Elements allfullreviews = doc.getElementsByClass("w_DHV_ pv3 mv0");
        //Elements reviews = doc.getElementsByClass("tl-m mb3 db-m");
;
        //System.out.println(doc);
        // System.out.println("Outer HTML: " + link.outerHtml());
        // System.out.println("Inner HTML: " + link.html());

       // System.out.println(fullreviews);

        // Object[] idk = allfullreviews.toArray();

        // Element[] fullreviews = new Element[idk.length];
        Review[] reviews = new Review[allfullreviews.size()];

        for (int i =0; i < allfullreviews.size(); i++){

            //System.out.println(idk[i]);
            // System.out.println(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first());
            // System.out.println(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first());

            reviews[i] = new Review(Cleaner.cleanReview(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first().toString())
                                    , Cleaner.cleanName(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first().toString()));

            System.out.println(reviews[i].name);



            //System.out.println("NEXT ###########################################################################################################");
        }

        //System.out.println(allfullreviews.get(0).getElementsByClass("tl-m mb3 db-m").first());
    }
}

class Cleaner{
    static String cleanReview(String review){
        review = review.substring(35,review.length()-7);
        review = review.replaceAll("[-+.^:,]","");
        return review;
    }
    static String cleanName(String name){
        name = name.substring(31,name.length()-7);
        name = name.replaceAll("[-+.^:,]","");
        return name;
    }
}

class Review{
    public String name;
    public String review;
    public boolean isGoodMatch = false;

    public Review(String review, String name){
        this.name = name;
        this.review = review;
    }
}
