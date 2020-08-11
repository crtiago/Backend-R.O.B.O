package br.com.crtiago.apirest.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crtiago.apirest.models.Arm;
import br.com.crtiago.apirest.models.Head;
import br.com.crtiago.apirest.props.head.EInclination;
import br.com.crtiago.apirest.resources.rest.ResponseBase;

@SpringBootTest
@AutoConfigureMockMvc
public class RoboApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RoboApi roboApi;

	@Test
	public void changeInclinationHeadTest() throws JsonProcessingException, Exception {

		int idInclination = 1;

		/*mockMvc.perform(put("/head-inclination/{idInclination}").contentType("application/json")
				.content(objectMapper.writeValueAsString(idInclination))).andExpect(status().isOk());*/

		ResponseEntity<ResponseBase<Head>> response = roboApi.changeInclinationHead(idInclination);

		assertEquals(response.getBody().getData().getIdInclination(), 1);

	}

}
