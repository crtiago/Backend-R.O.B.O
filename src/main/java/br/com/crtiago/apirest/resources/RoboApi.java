package br.com.crtiago.apirest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crtiago.apirest.models.Arm;
import br.com.crtiago.apirest.models.Head;
import br.com.crtiago.apirest.models.Robo;
import br.com.crtiago.apirest.props.arm.EElbow;
import br.com.crtiago.apirest.props.arm.EPulse;
import br.com.crtiago.apirest.props.head.EInclination;
import br.com.crtiago.apirest.props.head.ERotation;
import br.com.crtiago.apirest.props.utils.EMessages;
import br.com.crtiago.apirest.resources.rest.ResponseBase;
import br.com.crtiago.apirest.services.RoboService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class RoboApi {

	@Autowired(required = true)
	private RoboService roboService;

	private Head head = new Head();
	private Arm leftArm = new Arm();
	private Arm rightArm = new Arm();

	private Robo robo = new Robo(head, leftArm, rightArm);

	@GetMapping("/robo")
	public ResponseEntity<ResponseBase<Robo>> getRobo() {
		ResponseBase<Robo> baseResponse = new ResponseBase<>();
		try {
			baseResponse = new ResponseBase<>(true, "Informações carregadas com sucesso", robo);
		} catch (Exception e) {
			baseResponse = new ResponseBase<>(false, "Informações não carregadas", robo);
		}
		return new ResponseEntity<ResponseBase<Robo>>(baseResponse, HttpStatus.OK);
	}

	@PutMapping("/head-inclination/{idInclination}")
	public ResponseEntity<ResponseBase<Head>> changeInclinationHead(@PathVariable Integer idInclination) {
		ResponseBase<Head> baseResponse = new ResponseBase<>();

		if (roboService.checkLimit(EInclination.getMaxId(), idInclination)) {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		} else if (roboService.checkPermission(head.getIdInclination(), idInclination)) {
			head.setIdInclination(idInclination);
			baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), head);
		} else {
			baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
		}
		return new ResponseEntity<ResponseBase<Head>>(baseResponse, HttpStatus.OK);
	}

	@PutMapping("/head-rotation/{idRotation}")
	public ResponseEntity<ResponseBase<Head>> changeRotationHead(@PathVariable Integer idRotation) {
		ResponseBase<Head> baseResponse = new ResponseBase<>();

		if (roboService.checkLimit(ERotation.getMaxId(), idRotation)) {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		} else if (head.getIdInclination() == EInclination.PARA_BAIXO.getId()) {
			baseResponse = new ResponseBase<>(false, EMessages.PERMISSION_HEAD.getMessage(), head);
		} else {
			if (roboService.checkPermission(head.getIdRotation(), idRotation)) {
				head.setIdRotation(idRotation);
				baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), head);
			} else {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
			}
		}
		return new ResponseEntity<ResponseBase<Head>>(baseResponse, HttpStatus.OK);
	}

	@PutMapping("/arm-elbow/{idFront}/{side}")
	public ResponseEntity<ResponseBase<Arm>> changeStateElbow(@PathVariable Integer idFront,
			@PathVariable String side) {

		ResponseBase<Arm> baseResponse = new ResponseBase<>();

		if (roboService.checkLimit(EElbow.getMaxId(), idFront)) {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		} else if (side.toLowerCase().equals("esquerdo")) {
			if (roboService.checkPermission(leftArm.getIdElbow(), idFront)) {
				leftArm.setIdElbow(idFront);
				baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), leftArm);
			} else {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
			}
		} else if (side.toLowerCase().equals("direito")) {
			if (roboService.checkPermission(rightArm.getIdElbow(), idFront)) {
				rightArm.setIdElbow(idFront);
				baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), rightArm);
			} else {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
			}
		} else {
			baseResponse = new ResponseBase<>(false, "Opção inválida", null);
		}
		return new ResponseEntity<ResponseBase<Arm>>(baseResponse, HttpStatus.OK);
	}

	@PutMapping("/arm-pulse/{idFront}/{side}")
	public ResponseEntity<ResponseBase<Arm>> changeStatePulse(@PathVariable Integer idFront,
			@PathVariable String side) {

		ResponseBase<Arm> baseResponse = new ResponseBase<>();

		if (roboService.checkLimit(EPulse.getMaxId(), idFront)) {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		} else if (side.toLowerCase().equals("esquerdo")) {
			if (roboService.getPermissionPulse(leftArm.getIdElbow(), EElbow.FORTEMENTE_CONTRAIDO.getId())) {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION_PULSE.getMessage(), null);
			} else if (roboService.checkPermission(leftArm.getIdPulse(), idFront)) {
				leftArm.setIdPulse(idFront);
				baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), leftArm);
			} else {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
			}

		} else if (side.toLowerCase().equals("direito")) {
			if (roboService.getPermissionPulse(rightArm.getIdElbow(), EElbow.FORTEMENTE_CONTRAIDO.getId())) {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION_PULSE.getMessage(), null);
			} else if (roboService.checkPermission(rightArm.getIdPulse(), idFront)) {
				rightArm.setIdPulse(idFront);
				baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), rightArm);
			} else {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
			}
		} else {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		}

		return new ResponseEntity<ResponseBase<Arm>>(baseResponse, HttpStatus.OK);

	}
}
