package com.mongodb.pc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;

import static com.mongodb.pc.InsertTest.printJson;

/**
 * Created by Srilatha on 1/21/2017.
 */
public class FindTest {
    public static void main(String... args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("courses");
        MongoCollection<Document> coll = db.getCollection("findTest");
        coll.drop();
        for(int i = 0; i < 10; i++)
            coll.insertOne(new Document("x",i));

        System.out.println("find one");
        Document first = coll.find().first();
        printJson(first);
        System.out.println("find all into");
        Collection<Document> into = coll.find().into(new ArrayList<>());
        for(Document doc:into){
            printJson(doc);
        }
        System.out.println("find all cursor");
        MongoCursor<Document> cursor = coll.find().iterator();
        try {
            while(cursor.hasNext()) {
                printJson(cursor.next());
            }
        } finally {
            cursor.close();
        }
        System.out.println("count");
        System.out.println("count "+coll.count());
    }
}
