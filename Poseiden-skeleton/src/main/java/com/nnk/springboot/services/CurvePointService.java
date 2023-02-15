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
	
	/**
	 * Get a CurvePoint object from database by ID
	 * @param Id the Id of the object to find
	 * @return Optional<CurvePoint> the curvePoint with correct Id, if any.
	 */
	public Optional<CurvePoint> getCurvePoint (final int Id) {
		return curvePointRepository.findById(Id);
	}
	
	/**
	 * Get all CurvePoint objects from database
	 * @return List<CurvePoint> list of all entities found in database
	 */
	public List<CurvePoint> getAllCurvePoint() {
		return curvePointRepository.findAll();
	}
	
	/**
	 * Delete a CurvePoint object from database by ID
	 * @param Id the Id of the object to delete
	 */
	public void deleteCurvePoint (final int Id) {
		curvePointRepository.deleteById(Id);
	}
	
	/**
	 * Save or update a CurvePoint object into database.
	 * @param curvePoint the CurvePoint to save or update into database
	 * @return curvePoint the saved CurvePoint
	 */
	public CurvePoint saveCurvePoint (CurvePoint curvePoint) {
		CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
		return savedCurvePoint;
	}
}
