package com.krishna.brewery.service.v2;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.krishna.brewery.web.model.v2.BeerDtoV2;

@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
	@Override
	public BeerDtoV2 getBeerById(UUID beerId) {
		return null;
	}

	@Override
	public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
		return null;
	}

	@Override
	public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
		// TODO write logic to update
	}

	@Override
	public void deleteById(UUID beerId) {
		// TODO write logic to delete
	}
}