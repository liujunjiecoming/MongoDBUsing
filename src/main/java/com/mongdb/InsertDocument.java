package com.mongdb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName InsertDocument
 * @Description
 * @Author JJLiu
 * @Date 19-7-1 下午4:30
 * @Version 1.0
 **/
public class InsertDocument {

    @Test
    public void insertOne() {

        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase db = mongoClient.getDatabase("test");

        MongoCollection<Document> mycolCollection = db.getCollection("mycol");

        mycolCollection.find().forEach(printBlock);
        System.out.println("====================这是一条分割线===============================");

        //新增一条记录
        Document document = new Document("_id", 999).append("title", "MongoDB Insert Demo")
                                                    .append("desciption", "database")
                                                    .append("likes", 30)
                                                    .append("by", "yibai point")
                                                    .append("url", "http://www.yiibai.com/mongodb/");

        mycolCollection.insertOne(document);
        mycolCollection.find().forEach(printBlock);


    }

    @Test
    public void insertMany() {
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase db = mongoClient.getDatabase("test");

        MongoCollection<Document> mycolCollection = db.getCollection("mycol");
        mycolCollection.find().forEach(printBlock);
        System.out.println("====================这是一条分割线===============================");


        Document doc1 = new Document("name", "Amarcord Pizzeria")
                .append("contact", new Document("phone", "264-555-0193")
                        .append("email", "amarcord.pizzeria@example.net")
                        .append("location", Arrays.asList(-73.88502, 40.749556)))
                .append("stars", 2)
                .append("categories", Arrays.asList("Pizzeria", "Italian", "Pasta"));


        Document doc2 = new Document("name", "Blue Coffee Bar")
                .append("contact", new Document("phone", "604-555-0102")
                        .append("email", "bluecoffeebar@example.com")
                        .append("location", Arrays.asList(-73.97902, 40.8479556)))
                .append("stars", 5)
                .append("categories", Arrays.asList("Coffee", "Pastries"));

        List<Document> documents = new ArrayList<Document>();
        documents.add(doc1);
        documents.add(doc2);

        mycolCollection.insertMany(documents);
        mycolCollection.find().forEach(printBlock);

    }


    static Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };


}
