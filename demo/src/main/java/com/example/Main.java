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
        Document doc = Jsoup.connect(URL).get(); //grabs the html file ig from the url

        Elements allfullreviews = doc.getElementsByClass("w_DHV_ pv3 mv0"); //grabbing all reviews which includes all the info about the stars, name, and other stuff

        Review[] reviews = new Review[allfullreviews.size()]; //creating a list of reviews to store the reviews fromt he site

        for (int i =0; i < allfullreviews.size(); i++){ //itterating through all the reviews

            // System.out.println(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first());
            // System.out.println(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first());

            reviews[i] = new Review(Cleaner.cleanReview(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first().toString()) //cleans the html stuff and creates a new review object
                                    , Cleaner.cleanName(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first().toString()));

            System.out.println(reviews[i].name);

            //System.out.println("NEXT ###########################################################################################################");
        }
    }
}

class Cleaner{
    static String cleanReview(String review){
        review = review.substring(35,review.length()-7); //just gets rid  of the beginnnign html/xml stuff
        review = review.replaceAll("[-+.^:,]",""); //removing all puntuations to make compartison easy
        return review;
    }
    static String cleanName(String name){ //same as above
        name = name.substring(31,name.length()-7);
        name = name.replaceAll("[-+.^:,]","");
        return name;
    }
}

class Review{ //self explanatory
    public String name;
    public String review;
    public boolean isGoodMatch = false;

    public Review(String review, String name){
        this.name = name;
        this.review = review;
    }
}

class Tester{
    //WRITE the word detector here plz and return a T/F thx
}