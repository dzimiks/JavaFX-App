package model;

public class Knjiga {

	private String autor;
	private String naslov;
	private String izdavac;
	private int    godinaIzdanja;
	private float  cena;
	private int    stanjeNaLageru;
	private short  popust;
	
	public Knjiga(String autor, String naslov, String izdavac, int godinaIzdanja, float cena, int stanjeNaLageru, short popust) {
		this.autor = autor;
		this.naslov = naslov;
		this.izdavac = izdavac;
		this.godinaIzdanja = godinaIzdanja;
		this.cena = cena;
		this.stanjeNaLageru = stanjeNaLageru;
		this.popust = popust;
	}
	
	@Override
	public String toString() {
		return naslov + ", " + autor + ", " + godinaIzdanja + ", " + izdavac;
	}
	
	public short getPopust() {
		return popust;
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getIzdavac() {
		return izdavac;
	}

	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}

	public int getGodinaIzdanja() {
		return godinaIzdanja;
	}

	public void setGodinaIzdanja(int godinaIzdanja) {
		this.godinaIzdanja = godinaIzdanja;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public int getStanjeNaLageru() {
		return stanjeNaLageru;
	}

	public void setStanjeNaLageru(int stanjeNaLageru) {
		this.stanjeNaLageru = stanjeNaLageru;
	}
}
