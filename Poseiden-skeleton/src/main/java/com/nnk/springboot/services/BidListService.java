package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	private BidListRepository bidListRepository;
	
	
	public Optional<BidList> getBidList (final int BidListId) {
		return bidListRepository.findById(BidListId);
	}
	
	public List<BidList> getAllBidList() {
		return bidListRepository.findAll();
	}
	
	public void deleteBidList (final int BidListId) {
		bidListRepository.deleteById(BidListId);
	}
	
	public BidList saveBidList (BidList bidList) {
		BidList savedBidList = bidListRepository.save(bidList);
		return savedBidList;
	}
	
	
}
