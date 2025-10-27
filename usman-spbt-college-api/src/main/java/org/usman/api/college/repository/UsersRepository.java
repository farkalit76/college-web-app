package org.usman.api.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.usman.api.college.repository.entities.Users;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.userName LIKE %:userName%")
    List<Users> findByUserNameLike(@Param("userName") String userName);

    @Modifying
    @Transactional
    @Query("Delete from Users where userId = :userId")
    void deleteByUserId(@Param("userId") Long userId);

    //for login validation
    @Query("SELECT u FROM Users u WHERE u.userName = :userName")
    Users findByUserName(@Param("userName") String userName);
}
