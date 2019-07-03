package com.mongdb.readAndWrite;

import com.mongdb.utils.CommonUtil;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

/**
 * @ClassName WriteOptions
 * @Description 写入数据
 * @Author JJLiu
 * @Date 19-7-3 下午2:35
 * @Version 1.0
 **/
public class WriteOptions {

    /**
     * 更新文档
     */
    @Test
    public void updateDoc() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("restaurants");

        /**
         * Updates.set和sql语句中set一样  update from db set stars = 1 and phone = 13431967860
         * Updates.currentDate 将lastModified修改为当前时间，如果lastModified这个字段不存在，就会添加这个字段去到文档中
         */
        collection.updateOne(eq("_id", new ObjectId("5d1c4dc9f96b0c79d99c7ee3")),
                combine(set("stars", 1), set("contact.phone", "13431967860"), currentDate("lastModified")));

    }

    /**
     * 更新多个
     */
    @Test
    public void updateMul() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("restaurants");

        collection.updateMany(eq("stars", 2), combine(set("stars", 10), currentDate("lastModified")));

    }

    /**
     * 通过一个新的文档直接替换旧的文档
     */
    @Test
    public void replaceOne() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("restaurants");

        Document document = new Document("name", "JJLiu")
                .append("contact", new Document("phone", "14379823011")
                        .append("email", "amarcord.pizzeria@example.net")
                        .append("location", Arrays.asList(-73.88502, 40.749556)));

        collection.replaceOne(eq("contact.phone", "264-555-0193"), document);

    }


    Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };
}
