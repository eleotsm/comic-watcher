package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException {
        boolean found = false;

       try{
           for(int page=1;page<=3;page++){
               Document doc = Jsoup.connect("url"+page).get();  //chamge url and of course credentials
               for (Element title : doc.select("a")) {
                   if (title.text().equalsIgnoreCase("Comic name")) {
                       found = true;
                       break;
                   }
               }
               if (found) break;
           }
           if (found) {
               EmailSender.sendEmail("yourgmail@gmail.com", "Comic Alert", "Comic name is updated!!");
           } else {
               System.out.println("Comic not found in first 3 pages.");
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
