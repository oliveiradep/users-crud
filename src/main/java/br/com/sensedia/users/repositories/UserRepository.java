package br.com.sensedia.users.repositories;

import br.com.sensedia.users.entities.UserEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity, String> {}
