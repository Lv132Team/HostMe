package com.softserve.edu.service;

import java.util.Set;

import com.softserve.edu.model.Sightseeing;

public interface SightseeingService {

	public boolean haveSight(int id);

	Set<Sightseeing> findAll();

	Sightseeing findOne(Integer id);

	void deleteSightseeing(Sightseeing sightseeing);

	void saveSightseeing(Sightseeing sightseeing);
}
