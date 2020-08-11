package br.com.crtiago.apirest.props.arm;

public enum EPulse {

	ROTACAO_MENOS_90(0, "Rotação para -90º"), ROTACAO_MENOS_45(1, "Rotação para -45º"), EM_REPOUSO(2, "Em Repouso"),
	ROTACAO_PARA_45(3, "Rotação para 45º"), ROTACAO_PARA_90(4, "Rotação para 90º"),
	ROTACAO_PARA_135(5, "Rotação para 135º"), ROTACAO_PARA_180(6, "Rotação para 180º");

	EPulse(int aId, String aState) {
		this.id = aId;
		this.state = aState;
	}

	public int id;
	public String state;

	public static int getMaxId() {
		return ROTACAO_PARA_180.getId();
	}

	public int getId() {
		return id;
	}

}
