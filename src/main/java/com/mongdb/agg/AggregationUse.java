package com.mongdb.agg;

import com.mongdb.utils.CommonUtil;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName AggregationUse
 * @Description 聚合框架(类似sql语句的group by)
 * @Author JJLiu
 * @Date 19-7-3 下午3:35
 * @Version 1.0
 **/
public class AggregationUse {

    /**
     * 一个简单的聚合查询
     */
    @Test
    public void testSimple() {
        MongoDatabase db = CommonUtil.connectMongoDB("test");
        MongoCollection<Document> collection = db.getCollection("restaurants");

        collection.aggregate(Arrays.asList(
                // Aggregates.match(Filters.eq("categories", "Bakery")),
                Aggregates.group("$stars", Accumulators.sum("count", 1))
        )).forEach(printBlock);

        System.out.println("===============================分割线==================================");

        collection.aggregate(
                Arrays.asList(
                        Aggregates.project(
                                Projections.fields(
                                        Projections.excludeId(),
                                        Projections.include("name"),
                                        Projections.computed(
                                                "secondCategory",
                                                new Document("$arrayElemAt", Arrays.asList("$categories", 1))
                                        )
                                )
                        )
                )
        ).forEach(printBlock);


    }


    Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };

}
