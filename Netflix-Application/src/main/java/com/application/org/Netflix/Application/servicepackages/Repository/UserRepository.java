package com.application.org.Netflix.Application.servicepackages.Repository;

import com.application.org.Netflix.Application.ui.model.Entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);


    @Transactional
    @Modifying
    @Query("Delete from Users u where u.userId=:userId")
    void deleteByUserId(String userId);
}
