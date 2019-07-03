package com.mongdb.document;

import com.mongdb.utils.CommonUtil;
import com.mongodb.Block;
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
    public void deleteOne() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");

        MongoCollection<Document> collection = db.getCollection("testcol");

        collection.find().forEach(printBlock);
        System.out.println("======================分割线==========================");

        collection.deleteOne(eq("_id", 888));

        collection.find().forEach(printBlock);


    }

    @Test
    public void deleteMany() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("restaurants");

        collection.find().forEach(printBlock);
        System.out.println("======================分割线==========================");

        collection.deleteMany(eq("stars", 10));

        collection.find().forEach(printBlock);
    }


    static Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };


}
