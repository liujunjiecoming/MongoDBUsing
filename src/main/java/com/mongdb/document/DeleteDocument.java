package com.mongdb.document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import static com.mongodb.client.model.Filters.eq;

/**
 * @ClassName DeleteDocument
 * @Description
 * @Author JJLiu
 * @Date 19-7-1 下午5:03
 * @Version 1.0
 **/
public class DeleteDocument {

    @Test
    public void delete() {
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase db = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = db.getCollection("testcol");

        collection.find().forEach(printBlock);
        System.out.println("======================分割线==========================");

        collection.deleteOne(eq("_id", 888));

        collection.find().forEach(printBlock);


    }

    static Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };


}
