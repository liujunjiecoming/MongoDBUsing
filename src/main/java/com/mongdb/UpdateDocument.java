package com.mongdb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import static com.mongodb.client.model.Filters.eq;

/**
 * @ClassName UpdateDocument
 * @Description
 * @Author JJLiu
 * @Date 19-7-1 下午4:41
 * @Version 1.0
 **/
public class UpdateDocument {


    @Test
    public void update() {
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase db = mongoClient.getDatabase("test");

        MongoCollection<Document> mycolCollection = db.getCollection("testcol");
        mycolCollection.find().forEach(printBlock);
        System.out.println("====================这是一条分割线===============================");

        mycolCollection.updateOne(eq("_id", 888), new Document("$set", new Document("title", "更新了标题2")));

        mycolCollection.find().forEach(printBlock);


    }


    static Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };


}
