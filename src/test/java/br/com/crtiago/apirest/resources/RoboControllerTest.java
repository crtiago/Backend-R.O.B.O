package br.com.crtiago.apirest.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.crtiago.apirest.services.RoboService;

@SpringBootTest
public class RoboControllerTest {

	@Autowired
	private RoboService roboService;

	@Test
	public void checkLimitTest1() {
		int max = 4;
		int idFront = 5;

		boolean response = roboService.checkLimit(max, idFront);

		assertEquals(response, true);
	}

	@Test
	public void checkLimitTest2() {
		int max = 4;
		int idFront = -2;

		boolean response = roboService.checkLimit(max, idFront);

		assertEquals(response, true);
	}

	@Test
	public void checkLimitTest3() {
		int max = 4;
		int idFront = 0;

		boolean response = roboService.checkLimit(max, idFront);

		assertEquals(response, false);
	}

	@Test
	public void checkLimitTest4() {
		int max = 4;
		int idFront = 1;

		boolean response = roboService.checkLimit(max, idFront);

		assertEquals(response, false);
	}

	@Test
	public void checkLimitTest5() {
		int max = 4;
		int idFront = 4;

		boolean response = roboService.checkLimit(max, idFront);

		assertEquals(response, false);
	}

	@Test
	public void checkPermissionTest1() {
		int idObject = 1;
		int idFront = 1;

		boolean response = roboService.checkPermission(idObject, idFront);

		assertEquals(response, false);
	}

	@Test
	public void checkPermissionTest2() {
		int idObject = 1;
		int idFront = 2;

		boolean response = roboService.checkPermission(idObject, idFront);

		assertEquals(response, true);
	}

	@Test
	public void checkPermissionTest3() {
		int idObject = 2;
		int idFront = 1;

		boolean response = roboService.checkPermission(idObject, idFront);

		assertEquals(response, true);
	}

	@Test
	public void checkPermissionTest4() {
		int idObject = 3;
		int idFront = 1;

		boolean response = roboService.checkPermission(idObject, idFront);

		assertEquals(response, false);
	}

	@Test
	public void checkPermissionRotationTest1() {
		int idInclination = 1;
		int idEnum = 1;

		boolean response = roboService.checkPermissionRotation(idInclination, idEnum);

		assertEquals(response, true);
	}

	@Test
	public void checkPermissionRotationTest2() {
		int idInclination = 2;
		int idEnum = 1;

		boolean response = roboService.checkPermissionRotation(idInclination, idEnum);

		assertEquals(response, false);
	}

	@Test
	public void checkPermissionPulseTest1() {
		int idElbow = 1;
		int idEnum = 1;

		boolean response = roboService.checkPermissionPulse(idElbow, idEnum);

		assertEquals(response, false);
	}

	@Test
	public void checkPermissionPulseTest2() {
		int idElbow = 2;
		int idEnum = 1;

		boolean response = roboService.checkPermissionPulse(idElbow, idEnum);

		assertEquals(response, true);
	}

}
