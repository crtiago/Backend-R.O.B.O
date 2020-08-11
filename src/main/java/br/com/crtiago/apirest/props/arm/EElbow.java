package br.com.crtiago.apirest.props.arm;

public enum EElbow {

	EM_REPOUSO(0, "Em Repouso"), LEVEMENTE_CONTRAIDO(1, "Levemente Contraído"), CONTRAIDO(2, "Contraído"),
	FORTEMENTE_CONTRAIDO(3, "Fortemente Contraído");

	EElbow(int aId, String aState) {
		this.id = aId;
		this.state = aState;
	}

	public int id;
	public String state;

	public static int getEnum(int aId) {
		for (EElbow lElbow : EElbow.values()) {
			if (lElbow.id == aId) {
				return lElbow.id;
			}
		}
		return EM_REPOUSO.id;
	}

	public static int getEnum(String aState) {
		for (EElbow lElbow : EElbow.values()) {
			if (lElbow.state.equalsIgnoreCase(aState)) {
				return lElbow.id;
			}
		}
		return EM_REPOUSO.id;
	}
	
	public static int getMaxId() {
		return FORTEMENTE_CONTRAIDO.id;
	}

}
