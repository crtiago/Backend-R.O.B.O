package br.com.crtiago.apirest.resources;

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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class RoboApi {

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

		if (checkLimit(EInclination.getMaxId(), idInclination)) {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		} else if (checkObject(idInclination, "head", 0)) {
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

		if (checkLimit(ERotation.getMaxId(), idRotation)) {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		} else if (head.getIdInclination() == EInclination.PARA_BAIXO.id) {
			baseResponse = new ResponseBase<>(false, "Não é permitido rotacionar a cabeça com a inclinação para baixo",
					head);
		} else {
			if (checkObject(idRotation, "head", 1)) {
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

		if (checkLimit(EElbow.getMaxId(), idFront)) {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		} else if (side.toLowerCase().equals("esquerdo")) {
			if (checkObject(idFront, "leftarm", 0)) {
				leftArm.setIdElbow(EElbow.getEnum(idFront));
				baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), leftArm);
			} else {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
			}
		} else if (side.toLowerCase().equals("direito")) {
			if (checkObject(idFront, "rightarm", 0)) {
				rightArm.setIdElbow(EElbow.getEnum(idFront));
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

		if (checkLimit(EPulse.getMaxId(), idFront)) {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		} else if (side.toLowerCase().equals("esquerdo")) {
			if (leftArm.getIdElbow() != EElbow.FORTEMENTE_CONTRAIDO.id) {
				baseResponse = new ResponseBase<>(false,
						"Só pode movimentar o Pulso caso o Cotovelo esteja Fortemente Contraído", null);
			} else if (checkObject(idFront, "leftarm", 1)) {
				leftArm.setIdPulse(EPulse.getEnum(idFront));
				baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), leftArm);
			} else {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
			}

		} else if (side.toLowerCase().equals("direito")) {
			if (rightArm.getIdElbow() != EElbow.FORTEMENTE_CONTRAIDO.id) {
				baseResponse = new ResponseBase<>(false,
						"Só pode movimentar o Pulso caso o Cotovelo esteja Fortemente Contraído", null);
			}else if (checkObject(idFront, "rightarm", 1)) {
				rightArm.setIdPulse(EPulse.getEnum(idFront));
				baseResponse = new ResponseBase<>(true, EMessages.SUCCESS.getMessage(), rightArm);
			} else {
				baseResponse = new ResponseBase<>(false, EMessages.PERMISSION.getMessage(), null);
			}
		} else {
			baseResponse = new ResponseBase<>(false, EMessages.ERROR.getMessage(), null);
		}

		return new ResponseEntity<ResponseBase<Arm>>(baseResponse, HttpStatus.OK);

	}

	private boolean checkObject(int idFront, String partRobot, int controlPart) {

		switch (partRobot.toLowerCase()) {
		case "head":
			if (controlPart == 0) {
				return checkPermission(head.getIdInclination(), idFront);
			} else if (controlPart == 1) {
				return checkPermission(head.getIdRotation(), idFront);
			}
		case "leftarm":
			if (controlPart == 0) {
				return checkPermission(leftArm.getIdElbow(), idFront);
			} else if (controlPart == 1) {
				return checkPermission(leftArm.getIdPulse(), idFront);
			}
		case "rightarm":
			if (controlPart == 0) {
				return checkPermission(rightArm.getIdElbow(), idFront);
			} else if (controlPart == 1) {
				return checkPermission(rightArm.getIdPulse(), idFront);
			}
		default:
			return false;
		}

	}

	private boolean checkPermission(int idObject, int idFront) {
		if (idObject == idFront) {
			return false;
		} else if (idObject == idFront + 1) {
			return true;
		} else if (idObject == idFront - 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkLimit(int max, int idFront) {
		if (idFront > max || idFront < 0) {
			return true;
		} else {
			return false;
		}
	}
}
