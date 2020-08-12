package br.com.crtiago.apirest.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.crtiago.apirest.models.Head;
import br.com.crtiago.apirest.props.utils.EMessages;
import br.com.crtiago.apirest.resources.rest.ResponseBase;

@SpringBootTest
@AutoConfigureMockMvc
public class RoboApiTest {

	@Autowired
	private RoboApi roboApi;

	@Test
	public void changeInclinationHeadTest1() throws JsonProcessingException, Exception {
		int idInclination = 2;
		ResponseEntity<ResponseBase<Head>> response = roboApi.changeInclinationHead(idInclination);
		assertEquals(response.getBody().getData().getIdInclination(), idInclination);
	}

	@Test
	public void changeInclinationHeadTest2() throws JsonProcessingException, Exception {
		int idInclination = 3;
		ResponseEntity<ResponseBase<Head>> response = roboApi.changeInclinationHead(idInclination);
		assertEquals(response.getBody().isSuccess(), false);
	}

	@Test
	public void changeInclinationHeadTest3() throws JsonProcessingException, Exception {
		int idInclination = 0;
		ResponseEntity<ResponseBase<Head>> response = roboApi.changeInclinationHead(idInclination);
		assertEquals(response.getBody().isSuccess(), true);
	}

	@Test
	public void changeInclinationHeadTest4() throws JsonProcessingException, Exception {
		int idInclination = 1;
		ResponseEntity<ResponseBase<Head>> response = roboApi.changeInclinationHead(idInclination);
		assertEquals(response.getBody().getMessage(), EMessages.PERMISSION.getMessage());
	}

	/*@Test
	public void changeRotationHeadTest() throws JsonProcessingException, Exception {

		int idRotation = 1;

		ResponseEntity<ResponseBase<Head>> response = roboApi.changeInclinationHead(idRotation);

		assertEquals(response.getBody().getData().getIdRotation(), idRotation);
		assertEquals(response.getBody().isSuccess(), true);
		assertEquals(response.getBody().getMessage(), EMessages.SUCCESS.getMessage());

	}*/

}
