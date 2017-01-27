package com.mongodb.pc;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.pc.InsertTest.printJson;

/**
 * Created by Srilatha on 1/25/2017.
 */
public class HomeWork3_1 {
    public static void main(String... args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> coll = db.getCollection("students");

        MongoCursor<Document> cursor = coll.find(eq("scores.type","homework"))
                .iterator();
        while(cursor.hasNext()) {
            Document doc = cursor.next();
            List<Document> scores = (List<Document>) doc.get("scores");
            List<Document> updatedScores = new ArrayList<>();
            List<Document> hwscores = new ArrayList<>();

            scores.stream()
            .forEach( data -> {
                if(data.getString("type").equalsIgnoreCase("homework")) {
                    hwscores.add(data);
                } else {
                    updatedScores.add(data);
                }
            });
            hwscores.sort(Comparator.comparing(data -> data.getDouble("score")));
            hwscores.remove(0);
            updatedScores.addAll(hwscores);
            doc.put("scores",updatedScores);
            coll.updateOne(eq("_id",doc.get("_id")),new Document("$set",doc));
            printJson(doc);
        }
    }
}
