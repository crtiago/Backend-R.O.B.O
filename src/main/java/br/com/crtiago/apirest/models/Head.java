package br.com.crtiago.apirest.models;

import br.com.crtiago.apirest.props.head.EInclination;
import br.com.crtiago.apirest.props.head.ERotation;

public class Head {

	private int idRotation = ERotation.EM_REPOUSO.getId();
	private int idInclination = EInclination.EM_REPOUSO.getId();

	public Head() {
		super();
	}

	public Head(int idRotation, int idInclination) {
		super();
		this.idRotation = idRotation;
		this.idInclination = idInclination;
	}

	public int getIdRotation() {
		return idRotation;
	}

	public void setIdRotation(int idRotation) {
		this.idRotation = idRotation;
	}

	public int getIdInclination() {
		return idInclination;
	}

	public void setIdInclination(int idInclination) {
		this.idInclination = idInclination;
	}

}
