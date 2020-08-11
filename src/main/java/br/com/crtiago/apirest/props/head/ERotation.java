package br.com.crtiago.apirest.props.head;

public enum ERotation {

	ROTACAO_MENOS_90(0, "Rotação -90º"), ROTACAO_MENOS_45(1, "Rotação -45º"), EM_REPOUSO(2, "Em Repouso"),
	ROTACAO_45(3, "Rotação 45º"), ROTACAO_90(4, "Rotação 90º");

	ERotation(int aId, String aState) {
		this.id = aId;
		this.state = aState;
	}

	public int id;
	public String state;

	public static int getMaxId() {
		return ROTACAO_90.getId();
	}

	public int getId() {
		return id;
	}

}
