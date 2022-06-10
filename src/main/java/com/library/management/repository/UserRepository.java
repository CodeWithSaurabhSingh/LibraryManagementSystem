package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.management.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByLibCardNo(String libCardNo);

	UserEntity findByUserEmailId(String userEmailId);

	UserEntity findByUserName(String userName);

	UserEntity findByUserContactNo(Long userContactNo);
}
