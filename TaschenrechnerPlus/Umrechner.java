package TaschenrechnerPlus;

public class Umrechner {
	double a;
	String von, zu;
	double erg;
	Runde r = new Runde();

	public Umrechner(double a, String von, String zu) {
		this.a = a;
		this.von = von;
		this.zu = zu;
	}

	public void berech() {
		if ((von.equals("Radiant") && zu.equals("Winkelgrad")) || (von.equals("Winkelgrad") && zu.equals("Radiant"))) {
			radwink();
		} else if ((von.equals("Miles") && zu.equals("Kilometer")) || (von.equals("Kilometer") && zu.equals("Miles"))) {
			milkil();
		} else if ((von.equals("Inches") && zu.equals("Zentimeter"))
				|| (von.equals("Zentimeter") && zu.equals("Inches"))) {
			inze();
		} else if ((von.equals("Celcius") && zu.equals("Fahrenheit"))
				|| (von.equals("Fahrenheit") && zu.equals("Celcius"))) {
			celfah();
		} else if ((von.equals("Mph") && zu.equals("Kmh")) || (von.equals("Kmh") && zu.equals("Mph"))) {
			mphkmh();
		}
	}

	private void mphkmh() {
		if (von.equals("Mph")) {
			erg = a * 1.609344;
		} else {
			erg = a / 1.609344;
		}
	}

	private void celfah() {
		if (von.equals("Celcius")) {
			erg = a * 1.8 + 32;
		} else {
			erg = (a - 32) / 1.8;
		}
	}

	private void inze() {
		if (von.equals("Inches")) {
			erg = a * 2.54;
		} else {
			erg = a / 2.54;
		}
	}

	// Gleiche Formel wie bei mphkmh
	private void milkil() {
		if (von.equals("Miles")) {
			erg = a * 1.609344;
		} else {
			erg = a / 1.609344;
		}
	}

	private void radwink() {
		if (von.equals("Radiant")) {
			erg = a * (180 / Math.PI);
		} else {
			erg = a * (Math.PI / 180);
		}
	}

	public double getErg() {
		return r.runde(erg);
	}
}
