package com.example.demo.demo.dao;

import com.example.demo.demo.entity.WebsiteUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<WebsiteUser, String> {

    List<WebsiteUser> findByLastName(@Param("name") String name);

}
