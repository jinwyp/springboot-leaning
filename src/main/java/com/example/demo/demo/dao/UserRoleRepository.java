package com.example.demo.demo.dao;

import com.example.demo.demo.entity.UserRole;
import com.example.demo.demo.entity.WebsiteUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "userroles", path = "userroles")
public interface UserRoleRepository extends MongoRepository<UserRole, String> {

    List<UserRole> findByRoleName(@Param("roleName") String roleName);

}
