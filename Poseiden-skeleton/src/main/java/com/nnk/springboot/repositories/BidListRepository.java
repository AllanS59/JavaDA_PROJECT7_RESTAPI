package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

	public List<BidList> findByAccount (String account);
	
	public void deleteByAccount (String account);
}
