package br.com.crtiago.apirest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Robo {

	@JsonProperty(value="Head")
	private Head head;
	@JsonProperty(value="LeftArm")
	private Arm leftArm;
	@JsonProperty(value="RightArm")
	private Arm rightArm;

	public Robo() {
		super();
	}

	public Robo(Head head, Arm leftArm, Arm rightArm) {
		super();
		this.head = head;
		this.leftArm = leftArm;
		this.rightArm = rightArm;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Arm getLeftArm() {
		return leftArm;
	}

	public void setLeftArm(Arm leftArm) {
		this.leftArm = leftArm;
	}

	public Arm getRightArm() {
		return rightArm;
	}

	public void setRightArm(Arm rightArm) {
		this.rightArm = rightArm;
	}

}
