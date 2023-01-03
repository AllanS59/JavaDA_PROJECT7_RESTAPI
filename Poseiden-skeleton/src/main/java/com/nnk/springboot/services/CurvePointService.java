package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	private CurvePointRepository curvePointRepository;
	
	
	public Optional<CurvePoint> getCurvePoint (final int Id) {
		return curvePointRepository.findById(Id);
	}
	
	public List<CurvePoint> getAllCurvePoint() {
		return curvePointRepository.findAll();
	}
	
	public void deleteCurvePoint (final int Id) {
		curvePointRepository.deleteById(Id);
	}
	
	public CurvePoint saveCurvePoint (CurvePoint curvePoint) {
		CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
		return savedCurvePoint;
	}
}
