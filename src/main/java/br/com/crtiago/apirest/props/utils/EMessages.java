package br.com.crtiago.apirest.props.utils;

public enum EMessages {

	SUCCESS("Mudança de estado realizada com sucesso"), ERROR("Mudança de estado não permitida"),
	PERMISSION("Mudança de estado não realizada. A progressão de estados deve seguir a ordem crescente ou decrescente, "
			+ "por exemplo, a partir do estado 4, pode-se ir para os estados 3 ou 5, nunca pulando um estado");

	private String message;

	EMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
