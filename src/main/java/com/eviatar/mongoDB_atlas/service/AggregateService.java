package com.eviatar.mongoDB_atlas.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Service
public class AggregateService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Document> getOrdersWithCustomerDetails() {
        Aggregation aggregation = Aggregation.newAggregation(
                lookup("customers", "customerId", "_id", "customerDetails"),
                project("productName", "productId", "customerId")
                        .and("customerDetails.fullName").as("customerDetails.fullName")
                        .and("customerDetails.email").as("customerDetails.email")
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "orders", Document.class);
        return results.getMappedResults();
    }

}
