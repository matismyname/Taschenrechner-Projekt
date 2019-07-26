package TaschenrechnerPlus;

import TaschenrechnerPlus.Runde;

public class Nullstellen2 {
	double a;
	double b;
	double c;
	double zeta, zetaabs;
	Runde r = new Runde();

	public Nullstellen2(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;

		this.zeta = b * b - 4 * a * c; // Diskriminante
		this.zetaabs = Math.abs(b * b - 4 * a * c); // Absolutwert der Diskriminante, da sqrt(-x) = i*sqrt(x)
	}

	public double getZeta() {
		return zeta;
	}

	// Erste Nullstelle
	public double berech1() {
		double z = (-this.b + Math.sqrt(this.zetaabs)) / (2 * this.a);
		return r.runde(z);
	}

	// Zweite Nullstelle
	public double berech2() {
		double z = (-this.b - Math.sqrt(this.zetaabs)) / (2 * this.a);
		return r.runde(z);
	}

	// Der Realteil der komplexen Nullstelle
	public double getR() {
		double z = -this.b / (2 * this.a);
		return r.runde(z);
	}

	// Der Imaginärteil der komplexen Nullstelle
	public double getK() {
		double z = Math.sqrt(this.zetaabs) / (2 * this.a);
		return r.runde(z);
	}
}
