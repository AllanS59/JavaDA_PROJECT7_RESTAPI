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
	
	/**
	 * Get a BidList object from database by ID
	 * @param BidListId the Id of the object to find
	 * @return Optional<BidList> the bidList with correct Id, if any.
	 */
	public Optional<BidList> getBidList (final int BidListId) {
		return bidListRepository.findById(BidListId);
	}
	
	/**
	 * Get all BidList objects from database
	 * @return List<BidList> list of all entities found in database
	 */
	public List<BidList> getAllBidList() {
		return bidListRepository.findAll();
	}
	
	/**
	 * Delete a BidList object from database by ID
	 * @param BidListId the Id of the object to delete
	 */
	public void deleteBidList (final int BidListId) {
		bidListRepository.deleteById(BidListId);
	}
	
	/**
	 * Save or update a BidList object into database.
	 * @param bidList the BidList to save or update into database
	 * @return bidList the saved BidList
	 */
	public BidList saveBidList (BidList bidList) {
		BidList savedBidList = bidListRepository.save(bidList);
		return savedBidList;
	}
	
	
}
