package br.com.sensedia.users.repositories;

import br.com.sensedia.users.entities.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {

    public List<UserEntity> findAll(String firstName, String lastName, String email);

}
