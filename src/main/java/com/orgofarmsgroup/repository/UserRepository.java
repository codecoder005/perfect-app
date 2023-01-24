package com.orgofarmsgroup.repository;

import com.orgofarmsgroup.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(
            value = "select u.email from users u where u.uid = :uid",
            nativeQuery = true
    )
    String findEmailByUid(Long uid);

    @Query(
            value = "select u.uid from users u where u.email = :email",
            nativeQuery = true
    )
    Long findUidByEmail(String email);

    @Query(
            value = "select u.name from users u where u.uid = :uid",
            nativeQuery = true
    )
    String findNameByUid(Long uid);

    @Query(
            value = "select u.name from users u where u.email = :email",
            nativeQuery = true
    )
    String findNameByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
