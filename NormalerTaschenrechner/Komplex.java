package NormalerTaschenrechner;

//Es existieren algebraische Rechenregeln für komplexe Zahlen,
//z.B. (a+ib)*(a-ib) = a^2+b^2. Diese sind hier in Code umgesetzt.

public class Komplex {

	double realteil, imaginaerteil;

	public Komplex(double realteil, double imaginaerteil) {
		this.realteil = realteil;
		this.imaginaerteil = imaginaerteil;
	}

	public Komplex plus(Komplex k) {
		double realErgebnis = this.realteil + k.realteil;
		double imaginaerErgebnis = this.imaginaerteil + k.imaginaerteil;
		return new Komplex(realErgebnis, imaginaerErgebnis);
	}

	public Komplex minus(Komplex k) {
		double realErgebnis = this.realteil + (-1 * k.realteil);
		double imaginaerErgebnis = this.imaginaerteil + (-1 * k.imaginaerteil);
		return new Komplex(realErgebnis, imaginaerErgebnis);
	}

	public Komplex mal(Komplex k) {
		double realErgebnis = 0;
		double imaginaerErgebnis = 0;

		realErgebnis = (this.realteil * k.realteil) + ((-1) * this.imaginaerteil * k.imaginaerteil);
		imaginaerErgebnis = (this.realteil * k.imaginaerteil) + (k.realteil * this.imaginaerteil);

		return new Komplex(realErgebnis, imaginaerErgebnis);
	}

	public Komplex geteilt(Komplex k) {
		double nenner = k.realteil * k.realteil + k.imaginaerteil * k.imaginaerteil;
		double zaehler1 = k.realteil * this.realteil + k.imaginaerteil * this.imaginaerteil;
		double zaehler2 = this.imaginaerteil * k.realteil + (-1) * k.imaginaerteil * this.realteil;

		double realErgebnis = zaehler1 / nenner;
		double imaginaerErgebnis = zaehler2 / nenner;

		return new Komplex(realErgebnis, imaginaerErgebnis);

	}

	public double getKomplexRealteil() {
		return realteil;
	}

	public double getKomplexImaginaerteil() {
		return imaginaerteil;
	}
}