package com.example.demo.controller;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	@Query("select u from Admin u where u.emailId = ?1 and u.password = ?2")
	Admin findByEmailAddress(String emailAddress,String id);
	
	@Query("select u from Admin u where u.id = ?1 ")
	Admin findByAdminCode(long code);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value ="update login_details u set u.password=?1 where u.id = ?2",nativeQuery = true)
	int resetPasswrd(String pwd,long code);
}