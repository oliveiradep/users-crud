package br.com.sensedia.users.repositories;

import br.com.sensedia.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<UserEntity> findAll(String firstName, String lastName, String email) {
        Query query = new Query();

        if (Objects.nonNull(firstName))
            query.addCriteria(Criteria.where("firstName").is(firstName));

        if (Objects.nonNull(lastName))
            query.addCriteria(Criteria.where("lastName").is(lastName));

        if (Objects.nonNull(email))
            query.addCriteria(Criteria.where("email").is(email));

        List<UserEntity> users = mongoTemplate.find(query, UserEntity.class);
        return users;
    }
}