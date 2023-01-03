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
	
	
	public Optional<Trade> getTrade (final int TradeId) {
		return tradeRepository.findById(TradeId);
	}
	
	public List<Trade> getAllTrade() {
		return tradeRepository.findAll();
	}
	
	public void deleteTrade (final int TradeId) {
		tradeRepository.deleteById(TradeId);
	}
	
	public Trade saveTrade (Trade trade) {
		Trade savedTrade = tradeRepository.save(trade);
		return savedTrade;
	}
}
