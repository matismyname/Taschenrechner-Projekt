package NormalerTaschenrechner;

public class Dezimal {
	public double ersteZahl, zweiteZahl, Ergebnis;
	String ROP;

	public Dezimal(double ersteZahl, double zweiteZahl, String ROP) {
		this.ersteZahl = ersteZahl;
		this.zweiteZahl = zweiteZahl;
		this.ROP = ROP;
	}

	public double berechne() {

		switch (ROP) {
		case "plus":
			Ergebnis = plus();
			break;
		case "minus":
			Ergebnis = minus();
			break;
		case "mal":
			Ergebnis = mal();
			break;
		case "geteilt":
			Ergebnis = geteilt();
			break;
		}
		return Ergebnis;
	}

	public double plus() {
		return ersteZahl + zweiteZahl;
	}

	public double minus() {
		return ersteZahl - zweiteZahl;
	}

	public double geteilt() {
		return ersteZahl / zweiteZahl;
	}

	public double mal() {
		return ersteZahl * zweiteZahl;
	}
}