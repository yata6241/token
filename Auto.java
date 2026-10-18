package business;

public class Auto {
	
    private String kennzeichen;
    private String typ;
    private String modell;
    private float tagespreis;
    private String[] vermietetVonBis;
    
    public Auto(String kennzeichen, String typ, String modell,
       	float tagespreis, String[] vermietetVonBis){
    	this.kennzeichen = kennzeichen;
      	this.typ = typ;
       	this.modell = modell;
       	this.tagespreis = tagespreis;
       	this.vermietetVonBis = vermietetVonBis;
    }

	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public float getTagespreis() {
		return tagespreis;
	}

	public void setTagespreis(float tagespreis) {
		this.tagespreis = tagespreis;
	}

	public String[] getVermietetVonBis() {
		return vermietetVonBis;
	}

	public void setVermietetVonBis(String[] vermietetVonBis) {
		this.vermietetVonBis = vermietetVonBis;
	}
	
 	public String getVermietetVonBisAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getVermietetVonBis().length - 1; i++) {
			ergebnis = ergebnis + this.getVermietetVonBis()[i] + trenner; 
		}
		return ergebnis	+ this.getVermietetVonBis()[i];
	}
	
	public String gibAutoZurueck(char trenner){
  		return this.getKennzeichen() + trenner 
  			+ this.getTyp() + trenner
  			+ this.getModell() + trenner
  		    + this.getTagespreis() + trenner + "\n"
  		    + this.getVermietetVonBisAlsString(trenner) + "\n";
  	}
}

