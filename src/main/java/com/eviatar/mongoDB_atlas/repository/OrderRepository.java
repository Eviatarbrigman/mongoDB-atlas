package com.eviatar.mongoDB_atlas.repository;

import com.eviatar.mongoDB_atlas.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByProductId(int productId);

    @Query("{productName: ?0}")
    List<Order> getOrdersByProductName(String productName);
}
