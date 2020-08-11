package br.com.crtiago.apirest.models;

import br.com.crtiago.apirest.props.arm.EElbow;
import br.com.crtiago.apirest.props.arm.EPulse;

public class Arm {

	private int idElbow = EElbow.EM_REPOUSO.getId();
	private int idPulse = EPulse.EM_REPOUSO.getId();

	public Arm() {
		super();
	}

	public Arm(int idElbow, int idPulse) {
		super();
		this.idElbow = idElbow;
		this.idPulse = idPulse;
	}

	public int getIdElbow() {
		return idElbow;
	}

	public void setIdElbow(int idElbow) {
		this.idElbow = idElbow;
	}

	public int getIdPulse() {
		return idPulse;
	}

	public void setIdPulse(int idPulse) {
		this.idPulse = idPulse;
	}

}
