package com.krishna.brewery;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krishna.brewery.service.BeerService;
import com.krishna.brewery.web.controller.BeerController;
import com.krishna.brewery.web.model.BeerDto;

//@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BeerControllerTest {

	@MockBean
	BeerService beerService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	BeerDto validBeer;

	@BeforeAll
	public void setUp() {
		validBeer = BeerDto.builder().id(UUID.randomUUID()).beerName("Beer1").beerStyle("PALE_ALE").upc(123456789012L)
				.build();
	}

	@Test
	void getBeer() throws Exception {
		given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

		mockMvc.perform(get("/api/v1/beer/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
				.andExpect(jsonPath("$.beerName", is("Beer1")));
	}

	@Test
	void handlePost() throws Exception {
		// given
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		given(beerService.saveNewBeer(any())).willReturn(savedDto);

		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
				.andExpect(status().isCreated());

	}

	@Test
	void handleUpdate() throws Exception {
		// given
		BeerDto beerDto = validBeer;
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		// when
		mockMvc.perform(
				put("/api/v1/beer/" + validBeer.getId()).contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
				.andExpect(status().isNoContent());

		then(beerService).should().updateBeer(any(), any());

	}
}