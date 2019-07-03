package com.mongdb.index;

import com.mongdb.utils.CommonUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.junit.Test;

/**
 * @ClassName IndexOptions
 * @Description 索引选项
 * @Author JJLiu
 * @Date 19-7-3 上午10:40
 * @Version 1.0
 **/
public class IndexOption {

    /**
     * 创建唯一索引
     */
    @Test
    public void createUniqueIndex() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");

        IndexOptions indexOptions = new IndexOptions().unique(true);

        //id+title只能是唯一的值
        collection.createIndex(Indexes.ascending("id", "title"), indexOptions);

    }


    /**
     * 创建部分索引
     */
    @Test
    public void createPartialIndex() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");

        IndexOptions indexOptions = new IndexOptions().partialFilterExpression(Filters.exists("tags.mongodb"));

        collection.createIndex(Indexes.descending("id", "title"), indexOptions);


    }


}
