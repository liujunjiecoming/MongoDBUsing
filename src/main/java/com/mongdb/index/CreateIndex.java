package com.mongdb.index;

import com.mongdb.utils.CommonUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.junit.Test;

/**
 * @ClassName CreateIndex
 * @Description 创建不同索引的方法
 * @Author JJLiu
 * @Date 19-7-2 下午5:03
 * @Version 1.0
 **/
public class CreateIndex {


    /**
     * 创建升序索引
     */
    @Test
    public void createAscendingIndex() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");

        MongoCollection<Document> collection = db.getCollection("mycol");

        //创建单个升序索引
        collection.createIndex(Indexes.ascending("id"));

        //创建组合索引
        collection.createIndex(Indexes.ascending("id", "title"));

    }


    /**
     * 创建降序索引
     */
    @Test
    public void createDescendingIndex() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");

        MongoCollection<Document> collection = db.getCollection("inventory");

        //创建单个索引
        collection.createIndex(Indexes.descending("id"));

        //创建复合索引
        collection.createIndex(Indexes.descending("id", "item"));


    }

    /**
     * 升降序都有的索引，符合最左前缀原则
     */
    @Test
    public void createCompoundIndex() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");

        MongoCollection<Document> collection = db.getCollection("inventory");

        //创建混合复合索引
        collection.createIndex(Indexes.compoundIndex(Indexes.ascending("id"), Indexes.descending("title")));

    }

    /**
     * 创建文本索引，为了在String内容中支持文本查询
     */
    @Test
    public void createTextIndex() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");

        collection.createIndex(Indexes.text("title"));
    }

    /**
     * 创建hash索引
     */
    @Test
    public void createHashIndex() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");

        collection.createIndex(Indexes.hashed("_id"));

    }


}
