package com.mongodb.pc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.pc.InsertTest.printJson;

/**
 * Created by Srilatha on 1/22/2017.
 */
public class ProjectionTest {
    public static void main(String... args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("courses");
        MongoCollection<Document> coll = db.getCollection("insertTest");
        Document doc = coll.find().first();
        List<Document> list = coll.find().projection(new Document("name",1).append("_id",0)).into(new ArrayList<>());
        list = coll.find().projection(Projections.fields(include("name"),excludeId())).into(new ArrayList<>());
        //list = coll.find(new Document("name",0).append("_id",0)).into(new ArrayList<>());
        for(Document doc1:list) {
            printJson(doc1);
        }
    }
}
