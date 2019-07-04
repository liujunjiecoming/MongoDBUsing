package com.mongdb.commands;

import com.mongdb.utils.CommonUtil;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * @ClassName RunCommandsUse
 * @Description 运行命令
 * @Author JJLiu
 * @Date 19-7-4 上午10:41
 * @Version 1.0
 **/
public class RunCommandsUse {

    @Test
    public void testRun() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");

        Document buildInfo = db.runCommand(new Document("buildInfo", 1));

        System.out.println(buildInfo.toJson());


        Document collStatsResults = db.runCommand(new Document("collStats", "restaurants"));
        System.out.println(collStatsResults.toJson());

    }


}
