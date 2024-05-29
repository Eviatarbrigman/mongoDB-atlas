package com.eviatar.mongoDB_atlas.controller;

import com.eviatar.mongoDB_atlas.service.AggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregate")
public class AggregateContraller {

    @Autowired
    private AggregateService aggregateService;

    @GetMapping("")
    public ResponseEntity<List<Document>> aggregateCustomersAndOrders() {
        return ResponseEntity.ok(aggregateService.getOrdersWithCustomerDetails());
    }
}
