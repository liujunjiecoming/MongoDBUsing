package com.mongdb.readAndWrite;

import com.mongdb.utils.CommonUtil;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.junit.Test;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

/**
 * @ClassName ReadOptions
 * @Description 读取选项
 * @Author JJLiu
 * @Date 19-7-3 上午11:14
 * @Version 1.0
 **/
public class ReadOptions {


    /**
     * 查询集合，打印集合
     */
    @Test
    public void printCollection() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");

        collection.find().forEach(printBlock);

        System.out.println("========================分割线==============================");

        collection.find(eq("description", "MongoDB is no sql database")).forEach(printBlock);

    }

    /**
     * 过滤查询
     */
    @Test
    public void filterHelper() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");

        //普通的写法
        collection.find(new Document("likes", new Document("$gte", 50)
                .append("$lt", 500))
                .append("description", "NoSQL database doesn't have tables")).forEach(printBlock);

        System.out.println("===============分割线===============");

        //filter helper写法
        collection.find(and(gte("likes", 50),
                lt("likes", 500),
                eq("description", "NoSQL database doesn't have tables")))
                  .forEach(printBlock);

    }

    /**
     * 查询返回指定的值
     */
    @Test
    public void projections() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");


        //1，true    0，false
        collection.find(and(gte("likes", 50),
                lt("likes", 500)))
                  .projection(new Document("title", 1)
                          .append("tags", 1)
                          .append("likes", 1)
                          //不限制id怎样都会打印出来
                          .append("_id", 0))
                  .forEach(printBlock);

        System.out.println("===================分界线==================");

        //使用projections方法
        collection.find(and(gte("likes", 50), lt("likes", 500)))
                  //include(field)包括， exclude(field)不包括
                  .projection(fields(include("title", "tags", "likes"), excludeId()))
                  .forEach(printBlock);
    }

    /**
     * sort排序
     */
    @Test
    public void sort() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");
        collection.find(and(gte("likes", 0), lt("likes", 1000)))
                  .sort(Sorts.ascending("likes"))
                  .forEach(printBlock);

        System.out.println("=============分割线===============");

        //sorts with projections
        collection.find(and(gte("likes", 0), lt("likes", 10000)))
                  .sort(Sorts.ascending("likes"))
                  .projection(fields(include("title", "likes", "tags"), excludeId()))
                  .forEach(printBlock);
    }


    /**
     * 打印结果
     */
    Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };


}
