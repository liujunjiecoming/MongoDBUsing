package com.mongdb.changstream;

import com.mongdb.utils.CommonUtil;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.junit.Test;

/**
 * @ClassName ChangeStreamUse
 * @Description 转化流使用
 * @Author JJLiu
 * @Date 19-7-4 上午9:11
 * @Version 1.0
 **/
public class ChangeStreamUse {

    /**
     * watch the collection
     */
    @Test
    public void testChangeStream() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("restaurants");

        collection.watch().forEach(printBlock);

    }


    /**
     * 打印转化流的方法
     */
    Block<ChangeStreamDocument<Document>> printBlock = new Block<ChangeStreamDocument<Document>>() {

        @Override
        public void apply(final ChangeStreamDocument<Document> changeStreamDocument) {
            System.out.println(changeStreamDocument);
        }
    };


}
