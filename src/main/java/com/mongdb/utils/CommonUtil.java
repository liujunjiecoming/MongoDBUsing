package com.mongdb.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @ClassName ConnectDBUtil
 * @Description
 * @Author JJLiu
 * @Date 19-7-3 上午10:14
 * @Version 1.0
 **/
public class CommonUtil {

    /**
     * 连接Mongo数据库的工具类
     *
     * @param database 数据库名
     * @return
     */
    public static MongoDatabase connectMongoDB(String database) {
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase db = mongoClient.getDatabase(database);

        return db;
    }


}
