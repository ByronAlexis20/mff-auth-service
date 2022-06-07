package mff.auth.seguridad.springSecurity.entity;

public class MensajeRespuesta {
	
	private String message;

	public MensajeRespuesta(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
