package com.jj;

import com.mongdb.utils.CommonUtil;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.junit.Test;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

/**
 * @ClassName MongoIterableTest
 * @Description MongoIterable接口测试类
 * @Author JJLiu
 * @Date 19-7-3 下午1:53
 * @Version 1.0
 **/
public class MongoIterableTest {

    @Test
    public void test() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("mycol");

        //sorts with projections
        collection.find(and(gte("likes", 0), lt("likes", 10000)))
                  .sort(Sorts.ascending("likes"))
                  .projection(fields(include("title", "likes", "tags"), excludeId()))
                  .forEach(printBlock);

        System.out.println("=============分割线===============");

        //返回集合中第一个文档或者null
        Document first = collection.find(and(gte("likes", 0), lt("likes", 10000)))
                                   .sort(Sorts.ascending("likes"))
                                   .projection(fields(include("title", "likes", "tags"), excludeId()))
                                   .first();
        System.out.println(first.toJson());

        System.out.println("=============分割线===============");

        //迭代
        MongoCursor<Document> iterator = collection.find(and(gte("likes", 0), lt("likes", 10000)))
                                                   .sort(Sorts.ascending("likes"))
                                                   .projection(fields(include("title", "likes", "tags"), excludeId()))
                                                   .iterator();
        System.out.println(iterator);

        System.out.println("=============分割线===============");

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
