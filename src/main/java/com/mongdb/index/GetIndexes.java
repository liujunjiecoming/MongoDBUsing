package com.mongdb.index;

import com.mongdb.utils.CommonUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * @ClassName GetIndexes
 * @Description 从集合仲获取索引
 * @Author JJLiu
 * @Date 19-7-3 上午11:04
 * @Version 1.0
 **/
public class GetIndexes {

    /**
     * 获取索引列表
     */
    @Test
    public void getIndex() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("inventory");

        for (Document index : collection.listIndexes()) {
            System.out.println(index.toJson());
        }

    }


}
