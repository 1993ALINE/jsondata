package com.atiqur_rahman.login_example.repository;

import com.atiqur_rahman.login_example.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by salem on 23/10/2018.
 */

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Query(value = "select * from users where email=:email",nativeQuery = true)
    User findByEmail(@Param("email")String email);

    @Query(value = "select * from users where email=:email and password=:password and disabled=false ",nativeQuery = true)
    User findByEmailAndPassword(@Param("email")String email,@Param("password")String password);

}
