package com.mongdb.collection;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.bson.Document;
import org.junit.Test;

/**
 * @ClassName CreateCollection
 * @Description
 * @Author JJLiu
 * @Date 19-7-1 下午4:58
 * @Version 1.0
 **/
public class CreateCollection {


    /**
     * 新建一个普通的集合
     */
    @Test
    public void create() {
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase db = mongoClient.getDatabase("test");

        db.createCollection("testcol");

        MongoCollection<Document> mycolCollection = db.getCollection("testcol");

        mycolCollection.find().forEach(printBlock);
        System.out.println("====================这是一条分割线===============================");

        //新增一条记录
        Document document = new Document("_id", 888).append("title", "MongoDB Insert Demo")
                                                    .append("desciption", "database")
                                                    .append("likes", 30)
                                                    .append("by", "yibai point")
                                                    .append("url", "http://www.yiibai.com/mongodb/");

        mycolCollection.insertOne(document);
        mycolCollection.find().forEach(printBlock);

    }

    /**
     * 创建固定集合
     */
    @Test
    public void createCappedCol() {
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase db = mongoClient.getDatabase("test");

        for (String name : db.listCollectionNames()) {
            System.out.println(name);
        }
        System.out.println("==========================分割线========================");
        db.createCollection("cappedCol", new CreateCollectionOptions().capped(true).sizeInBytes(0x100000));

        for (String name : db.listCollectionNames()) {
            System.out.println(name);
        }
    }


    static Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };


}
