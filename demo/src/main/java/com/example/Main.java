package com.example;
import java.io.File;
import java.io.IOException;

import javax.swing.text.html.HTMLEditorKit.Parser;
import java.util.ArrayList;
import java.lang.Thread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Scanner;

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

    
    private static String[] urls = {"https://www.walmart.com/reviews/product/35192854","https://www.walmart.com/reviews/product/35192854?page=2","https://www.walmart.com/reviews/product/35192854?page=3","https://www.walmart.com/reviews/product/35192854?page=4"};
    public static void main(String[] args) throws IOException {

        ArrayList<Review> reviews = new ArrayList<Review>(); //creating a list of reviews to store the reviews fromt he site

        String addon = "?page=";
        String newURL;
        for (int x = 1; x <= 1; x++){

            newURL = URL + addon + Integer.toString(x);
        
            if (x == 1){
                newURL = URL;
            }

            System.out.println(newURL);

            Document doc = Jsoup.connect(newURL)
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36")
            .referrer("http://www.google.com")
            .get(); //grabs the html file ig from the url

            Elements allfullreviews = doc.getElementsByClass("w_DHV_ pv3 mv0"); //grabbing all reviews which includes all the info about the stars, name, and other stuff

            for (int i =0; i < allfullreviews.size(); i++){ //itterating through all the reviews

                // System.out.println(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first());
                // System.out.println(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first());

                reviews.add(new Review(Cleaner.cleanReview(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first().toString()) //cleans the html stuff and creates a new review object
                                        , Cleaner.cleanName(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first().toString())));

                System.out.println(reviews.get(i).name);

                try{
                Thread.sleep(1000);
                } catch(Exception e){
                    
                }
                //System.out.println("NEXT ###########################################################################################################");
            }

            doc = null;
            
        }

        reviews.add(new Review("There are so many different trail mix is out there. But this great value trail mix has all the best things. It has proteins it has calcium it has all the nutrients in different minerals that your body is going to need in one pack. And it also has all the great savings that youreThere are so many different trail mixes out there. But this great value trail mix has all the best things. It has proteins it has calcium it has all the nutrients in different minerals that your body is going to need in one pack. And it also has all the great savings that your pocket is going to need and love","joe"));

        String[] targetNames = Comparator.testRevs(reviews);
        System.out.println("Potential targets :)");
        for(String i : targetNames){
            System.out.println(i);
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

class Comparator{

    static String[] testRevs(ArrayList<Review> reviews){
        File file = new File("C:\\Users\\meow1\\OneDrive\\Desktop\\hell\\sell\\demo\\src\\main\\java\\com\\example\\keywords.txt");
        Scanner sc;
        String[] words;

        ArrayList<String> names = new ArrayList<String>();
        try{
            sc = new Scanner(file);
            String line = sc.nextLine();

            words = line.split(",");

            for (Review r: reviews){
                String[] rwords = r.review.split(" ");
    
                for (String rw : rwords){
                    for (String w: words){
                        System.out.println(rw);
                        if (w.equals(rw)){
                            if (!names.contains(r.name)){
                                names.add(r.name);
                            }
                            break;
                        }
                    }
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }

        return Arrays.copyOf(names.toArray(), names.toArray().length, String[].class);
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