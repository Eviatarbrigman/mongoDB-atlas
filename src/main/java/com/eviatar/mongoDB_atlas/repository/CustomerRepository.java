package com.eviatar.mongoDB_atlas.repository;

import com.eviatar.mongoDB_atlas.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
