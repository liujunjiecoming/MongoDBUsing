package com.mongdb.collection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * @ClassName DeleteCollection
 * @Description
 * @Author JJLiu
 * @Date 19-7-1 下午5:39
 * @Version 1.0
 **/
public class DeleteCollection {


    @Test
    public void drop() {
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase db = mongoClient.getDatabase("test");

        db.createCollection("testcol");

        MongoCollection<Document> mycolCollection = db.getCollection("testcol");

        mycolCollection.drop();

    }


}
