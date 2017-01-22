package com.mongodb.pc;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.pc.InsertTest.printJson;

/**
 * Created by Srilatha on 1/21/2017.
 */
public class DeleteTest {
    public static void main(String... args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> coll = db.getCollection("grades");
        MongoCursor<Document> cursor = coll.find(eq("type","homework"))
                .sort(new BasicDBObject("student_id",1).append("score",1)).iterator();

        try {
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                printJson(doc);
                coll.deleteOne(eq("_id",doc.get("_id")));
                cursor.next();
            }
        } finally {
            cursor.close();
        }

    }
}
