package br.com.crtiago.apirest.props.head;

public enum EInclination {

	PARA_CIMA(0, "Para Cima"), EM_REPOUSO(1, "Em Repouso"), PARA_BAIXO(2, "Para Baixo");

	private EInclination(int aId, String aState) {
		this.id = aId;
		this.state = aState;
	}

	public int id;
	public String state;

	public static int getEnum(int aId) {
		for (EInclination lInclination : EInclination.values()) {
			if (lInclination.id == aId) {
				return lInclination.id;
			}
		}
		return EM_REPOUSO.id;
	}

	public static int getEnum(String aState) {
		for (EInclination lInclination : EInclination.values()) {
			if (lInclination.state.equalsIgnoreCase(aState)) {
				return lInclination.id;
			}
		}
		return EM_REPOUSO.id;
	}

	public static int getMaxId() {
		return PARA_BAIXO.id;
	}
}
