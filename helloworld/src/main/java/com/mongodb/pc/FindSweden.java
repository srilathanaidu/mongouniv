package com.mongodb.pc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

/**
 * Created by Srilatha on 1/21/2017.
 */
public class FindSweden {
    public static void main(String... args) {
        MongoClient client = new MongoClient();
        //Validate client object values 
        MongoDatabase db = client.getDatabase("video");
        MongoCollection<Document> collection = db.getCollection("movieDetails");
        MongoCursor<Document> cursor = coll.find().iterator();
        int count = 0;
        try {
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                List<String> countries = (List<String>) doc.get("countries");
                if(countries != null && countries.size() >= 2 && countries.get(1).equalsIgnoreCase("sweden")) {
                    count++;
                }
                System.out.println(countries);
            }
        } finally {
            cursor.close();
        }
        System.out.println(count);
    }
}
