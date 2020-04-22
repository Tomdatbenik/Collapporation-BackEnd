package com.collapperation.userservice.repo;

import com.collapperation.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, String> {
    @Modifying
    @Query("UPDATE user u set u.picture = :newPfp, u.username = :newUserName where u.id = :id")
    public void updateBasicUserInfo(@Param("newPfp") String pfp, @Param("newUserName") String userName, @Param("id") String id);
}
