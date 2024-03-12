package com.example;
import java.io.File;
import java.io.IOException;

import javax.swing.event.PopupMenuEvent;
import javax.swing.text.html.HTMLEditorKit.Parser;
import java.util.ArrayList;
import java.lang.Thread;
import java.security.Permissions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
import java.util.random.*;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;



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

    //private static final String URL = "https://www.walmart.com/reviews/product/35192854";
    private static final String URL = "https://www.walmart.com/ip/Great-Value-Mountain-Trail-Mix-26-oz/39205511?athbdg=L1200&from=/search";

    
    private static String[] urls = {"https://www.walmart.com/reviews/product/35192854","https://www.walmart.com/reviews/product/35192854?page=2","https://www.walmart.com/reviews/product/35192854?page=3","https://www.walmart.com/reviews/product/35192854?page=4"};
    public static void main(String[] args) throws IOException {

        // ArrayList<Review> reviews = new ArrayList<Review>(); //creating a list of reviews to store the reviews fromt he site

        // String addon = "?page=";
        // String newURL;
        // for (int x = 1; x <= 4; x++){

        //     newURL = URL + addon + Integer.toString(x);
        
        //     if (x == 1){
        //         newURL = URL;
        //     }

        //     //newURL = urls[x-1];
        //     System.out.println(newURL);

        //     Document doc = Jsoup.connect(newURL)
        //     .get(); //grabs the html file ig from the url

        //     Elements allfullreviews = doc.getElementsByClass("w_DHV_ pv3 mv0"); //grabbing all reviews which includes all the info about the stars, name, and other stuff

        //     for (int i =0; i < allfullreviews.size(); i++){ //itterating through all the reviews

        //         // System.out.println(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first());
        //         // System.out.println(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first());

        //         reviews.add(new Review(Cleaner.cleanReview(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first().toString()) //cleans the html stuff and creates a new review object
        //                                 , Cleaner.cleanName(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first().toString())));

        //         System.out.println(reviews.get(i).name);

        //         //System.out.println("NEXT ###########################################################################################################");
        //     }

        //     doc = null;
            
        //     try{
        //     Thread.sleep(1000);
        //     } catch(Exception e){
                
        //     }
        // }

        //reviews.add(new Review(Cleaner.cleanReview("There are so many different trail mix is out there. But this great value trail mix has all the best things. It has proteins it has calcium it has all the nutrients in different minerals that your body is going to need in one pack. And it also has all the great savings that youreThere are so many different trail mixes out there. But this great value trail mix has all the best things. It has proteins it has calcium it has all the nutrients in different minerals that your body is going to need in one pack. And it also has all the great savings that your pocket is going to need and love"),"joe"));

        ArrayList<Product> allProducts = new ArrayList<Product>();

        String startingProduct = "https://www.walmart.com/ip/Great-Value-Mountain-Trail-Mix-26-oz/39205511?athbdg=L1200&from=/search";

        Document startdoc = Jsoup.connect(startingProduct)
        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36")
        .referrer("http://www.google.com")
        .header("Accept-Encoding", "gzip, deflate")
        .header("Accept-Language", "en-US,en;q=0.9")
        .header("Connection", "keep-alive")
        .get();

        //System.out.println(startdoc);

        Elements relatedProducts = startdoc.getElementsByAttribute("data-item-id");

        
        System.out.println(relatedProducts.size());
        System.out.println(relatedProducts.first().attr("data-item-id"));

        for (int i = 0; i < relatedProducts.size(); i++){
            //allProducts.add(new Product(Integer.valueOf(relatedProducts.get(i).attr("data-item-id"))));
        }

        // <div role="group" data-item-id="189071348" class="sans-serif mid-gray relative flex flex-column w-100 h-100 hide-child-opacity" 
        Product mounttrail =  new Product(35192854);
        allProducts.add(mounttrail);

        ArrayList<Review> targetNames = new ArrayList<Review>();

        for(Product p : allProducts){
            for(Review r: Comparator.testRevs(p.reviews)){
                targetNames.add(r);
            }
        }

        System.out.println("Potential targets :)");
        FileWriter myWriter = new FileWriter("demo\\src\\main\\java\\com\\example\\targetmarket.txt");

        for(Review i : targetNames){
            System.out.print(i.name);
            System.out.println(" <----- " + i.getHighestCat());
            myWriter.write(i + "\n");

        }


        



    }
}


class Cleaner{
    static String cleanReview(String review){
        review = review.substring(35,review.length()-7); //just gets rid  of the beginnnign html/xml stuff
        review = review.replaceAll("[-+.^:,]",""); //removing all puntuations to make compartison easy
        return review.toLowerCase();
    }
    static String cleanName(String name){ //same as above
        name = name.substring(31,name.length()-7);
        name = name.replaceAll("[-+.^:,]","");
        return name;
    }
}

class Comparator{

    static ArrayList<ArrayList<String>> records = new ArrayList<>();

    static ArrayList<Review> testRevs(ArrayList<Review> reviews){
        File file = new File("demo\\src\\main\\java\\com\\example\\keywords.csv");
        Scanner sc;
        String[] words;



        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch(Exception e){
            System.out.println("keywords file not found!");
        }

        System.out.println(records.get(0).get(0));

        /*
         * private List<String> getRecordFromLine(String line) {
                List<String> values = new ArrayList<String>();
                try (Scanner rowScanner = new Scanner(line)) {
                    rowScanner.useDelimiter(COMMA_DELIMITER);
                    while (rowScanner.hasNext()) {
                        values.add(rowScanner.next());
                    }
                }
                return values;
            }
         */

        ArrayList<Review> names = new ArrayList<Review>();
        try{
            sc = new Scanner(file);
            String line = sc.nextLine();

            words = line.split(",");
                

            for (Review r: reviews){
                String[] rwords = r.review.split(" ");
                names.add(r);
    
                for (String rw : rwords){
                    for (ArrayList<String> l: records){
                        //System.out.println(rw);
                        //System.out.println(l.get(0));
                        if (l.get(0).equals(rw)){
                            r.healtyRating += Integer.parseInt(l.get(2));
                            r.hikingRating += Integer.parseInt(l.get(1));
                            r.protienRating += Integer.parseInt(l.get(3));
                        }
                    }
                }
                System.out.println(r.getHighestCat());
            }
        } catch(Exception e){
            System.out.println(e);
        }

        return names;
    }

    static private ArrayList<String> getRecordFromLine(String line) {
        ArrayList<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

}

class Review{ //self explanatory
    public String name;
    public String review;
    public boolean isGoodMatch = false;

    public int healtyRating = 0;
    public int hikingRating = 0;
    public int protienRating = 0;

    public Review(String review, String name){
        this.name = name;
        this.review = review;
    }

    public String getHighestCat(){
        if(healtyRating > hikingRating &&  healtyRating > protienRating){
            return "healty";
        } else if(hikingRating > healtyRating &&  hikingRating > protienRating){
            return "hiking";
        } else{
            return "protien";
        }
    }
}

class Product{

    Random rand = new Random();
    public int id;

    ArrayList<Review> reviews = new ArrayList<Review>(); //creating a list of reviews to store the reviews fromt he site
    
    String URL = "https://www.walmart.com/reviews/product/";
    String addon = "?page=";


    public Product(int id){
        
        String newURL;

        for (int x = 1; x <= 1; x++){

            newURL = URL + id + addon + Integer.toString(x);
        
            if (x == 1){
                newURL = URL + id;
            }

            //newURL = urls[x-1];
            System.out.println(newURL);

            try{
                
                Document doc = Jsoup.connect(newURL)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36")
                .referrer("http://www.google.com")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Connection", "keep-alive")
                .get(); //grabs the html file ig from the url

                if (doc.getElementsByClass("w_DHV_ pv3 mv0").isEmpty()){
                    break;
                }

                addReviewsFromPage(doc);
                doc = null;
            } catch(Exception e) {
                System.out.println(e);
                break;
            }

            try{
            Thread.sleep(2000 + rand.nextInt());
            } catch(Exception e){
                
            }
        }
    }

    public void addReviewsFromPage(Document doc){

        Elements allfullreviews = doc.getElementsByClass("w_DHV_ pv3 mv0"); //grabbing all reviews which includes all the info about the stars, name, and other stuff

        for (int i =0; i < allfullreviews.size(); i++){ //itterating through all the reviews
            try{

            // System.out.println(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first());
            // System.out.println(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first());

            reviews.add(new Review(Cleaner.cleanReview(allfullreviews.get(i).getElementsByClass("tl-m mb3 db-m").first().toString()) //cleans the html stuff and creates a new review object
                                    , Cleaner.cleanName(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first().toString())));

            //System.out.println(reviews.get(i).name);
            System.out.println( Cleaner.cleanName(allfullreviews.get(i).getElementsByClass("f6 gray pr2 mb2").first().toString()));

            //System.out.println("NEXT ###########################################################################################################");
            }
            catch (Exception e){
                continue;
            }
        }
    }
}
