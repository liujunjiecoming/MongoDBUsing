package com.mongdb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.Test;

/**
 * @ClassName ConnectTest
 * @Description
 * @Author JJLiu
 * @Date 19-7-1 下午4:24
 * @Version 1.0
 **/
public class ConnectTest {

    /**
     * 简单的测试
     */
    @Test
    public void testConnect() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase db = mongoClient.getDatabase("test");

        System.out.println(db.getCollection("mycol").find());

        for (String name : db.listCollectionNames()) {
            System.out.println(name);
        }


    }


}
