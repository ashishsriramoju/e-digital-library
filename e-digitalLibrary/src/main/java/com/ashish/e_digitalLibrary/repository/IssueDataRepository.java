package com.ashish.e_digitalLibrary.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashish.e_digitalLibrary.entity.IssueData;

@Repository
public interface IssueDataRepository extends JpaRepository<IssueData, UUID> {
	
	@Query("select i from IssueData i where i.member.id = ?1")
	public List<IssueData> findByMemberId(UUID id);

}
