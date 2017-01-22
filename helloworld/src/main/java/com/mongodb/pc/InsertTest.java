package com.mongodb.pc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static java.util.Arrays.asList;

/**
 * Created by Srilatha on 1/21/2017.
 */
public class InsertTest {
    public static void main(String... args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("courses");
        MongoCollection<Document> coll = db.getCollection("insertTest");
        coll.drop();

        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");
        Document Jones = new Document("name", "Jones")
                .append("age", 30)
                .append("profession", "hacker");
        Document gigi = new Document("name", "gigi")
                .append("age", 30)
                .append("profession", "model");
        printJson(smith);
        //coll.insertOne(smith);

        coll.insertMany(asList(smith, Jones));
        coll.insertOne(gigi);
        gigi.remove("_id");
        coll.insertOne(gigi);
        printJson(smith);

    }

    static void printJson(Document smith) {
        System.out.println(smith.toJson());
    }
}


