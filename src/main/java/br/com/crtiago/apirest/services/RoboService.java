package br.com.crtiago.apirest.services;

import org.springframework.stereotype.Service;

@Service
public class RoboService {

	public boolean checkLimit(int max, int idFront) {
		if (idFront > max || idFront < 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPermission(int idObject, int idFront) {
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

	public boolean checkPermissionRotation(int idInclination, int idEnum) {
		if (idInclination == idEnum) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPermissionPulse(int idElbow, int idEnum) {
		if (idElbow != idEnum) {
			return true;
		} else {
			return false;
		}
	}

}
