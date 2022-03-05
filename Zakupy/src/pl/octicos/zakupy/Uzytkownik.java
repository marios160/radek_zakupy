package pl.octicos.zakupy;

public class Uzytkownik {
	private String email;
	private boolean polaczony;
	public Uzytkownik(String email, boolean polaczony) {
		this.email = email;
		this.polaczony = polaczony;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isPolaczony() {
		return polaczony;
	}
	public void setPolaczony(boolean polaczony) {
		this.polaczony = polaczony;
	}
	
	

}
