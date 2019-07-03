package com.jj;

import com.mongdb.utils.CommonUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName InsertDocumentExampleTest
 * @Description 一些文档用例的写入
 * @Author JJLiu
 * @Date 19-7-3 下午2:38
 * @Version 1.0
 **/
public class InsertDocumentExampleTest {

    /**
     * 插入一条文档
     */
    @Test
    public void insertOne() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("restaurants");

        Document document = new Document("name", "Café Con Leche")
                .append("contact", new Document("phone", "228-555-0149")
                        .append("email", "cafeconleche@example.com")
                        .append("location", Arrays.asList(-73.92502, 40.8279556)))
                .append("stars", 3)
                .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));

        collection.insertOne(document);

    }

    /**
     * 插入多条文档
     */
    @Test
    public void insertMany() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("restaurants");

        Document doc1 = new Document("name", "Amarcord Pizzeria")
                .append("contact", new Document("phone", "14379823011")
                        .append("email", "amarcord.pizzeria@example.net")
                        .append("location", Arrays.asList(-73.88502, 40.749556)))
                .append("stars", 2)
                .append("categories", Arrays.asList("Pizzeria", "Italian", "Pasta"));


        Document doc2 = new Document("name", "Blue Coffee Bar")
                .append("contact", new Document("phone", "2353490893")
                        .append("email", "bluecoffeebar@example.com")
                        .append("location", Arrays.asList(-73.97902, 40.8479556)))
                .append("stars", 2)
                .append("categories", Arrays.asList("Coffee", "Pastries"));

        Document doc3 = new Document("name", "Blue Coffee Bar")
                .append("contact", new Document("phone", "459385302430")
                        .append("email", "bluecoffeebar@example.com")
                        .append("location", Arrays.asList(-73.97902, 40.8479556)))
                .append("stars", 2)
                .append("categories", Arrays.asList("Coffee", "Pastries"));


        List<Document> documents = new ArrayList<Document>();
        documents.add(doc1);
        documents.add(doc2);
        documents.add(doc3);

        collection.insertMany(documents);
    }


}
