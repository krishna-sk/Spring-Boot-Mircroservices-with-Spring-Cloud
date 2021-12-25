package com.krishna.brewery.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.brewery.service.BeerService;
import com.krishna.brewery.web.model.BeerDto;

/**
 * @author krishna
 * 
 * @deprecated
 */
@Deprecated(since = "v2", forRemoval = false)
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

	private final BeerService beerService;

	@Autowired
	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> handlePost(@RequestBody BeerDto beerDto) {

		BeerDto savedBeer = beerService.saveNewBeer(beerDto);
		HttpHeaders header = new HttpHeaders();

		// TODO add hostname to url

		header.add("Location", "/api/v1/beer" + savedBeer.getId().toString());
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}

	@PutMapping({ "/{beerId}" })
	public ResponseEntity<Object> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {

		beerService.updateBeer(beerId, beerDto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping({ "/{beerId}" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteById(beerId);
	}
}
