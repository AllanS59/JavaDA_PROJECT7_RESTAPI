package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

	@Autowired
	private TradeRepository tradeRepository;
	
	/**
	 * Get a Trade object from database by ID
	 * @param TradeId the Id of the object to find
	 * @return Optional<Trade> the trade with correct Id, if any.
	 */
	public Optional<Trade> getTrade (final int TradeId) {
		return tradeRepository.findById(TradeId);
	}
	
	/**
	 * Get all Trade objects from database
	 * @return List<Trade> list of all entities found in database
	 */
	public List<Trade> getAllTrade() {
		return tradeRepository.findAll();
	}
	
	/**
	 * Delete a Trade object from database by ID
	 * @param TradeId the Id of the object to delete
	 */
	public void deleteTrade (final int TradeId) {
		tradeRepository.deleteById(TradeId);
	}
	
	/**
	 * Save or update a Trade object into database.
	 * @param trade the Trade to save or update into database
	 * @return Trade the saved Trade
	 */
	public Trade saveTrade (Trade trade) {
		Trade savedTrade = tradeRepository.save(trade);
		return savedTrade;
	}
}
