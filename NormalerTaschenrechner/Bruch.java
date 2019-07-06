package NormalerTaschenrechner;

public class Bruch {

	private double zaehler;
	private double nenner;
	int n = 1;

	public Bruch(double zaehler, double nenner) {
		this.zaehler = zaehler;
		this.nenner = nenner;
	}

	public Bruch(int zahl) {
		this(zahl, 1);
	}

	public double getZaehler() {
		return zaehler;
	}

	public double getNenner() {
		return nenner;
	}

	public double toDouble() {
		return (double) zaehler / nenner;
	}

	public Bruch add(Bruch b) {
		double z = this.zaehler * b.nenner + b.zaehler * this.nenner;
		double n = this.nenner * b.nenner;
		return new Bruch(z, n);
	}

	public Bruch sub(Bruch b) {
		double z = this.zaehler * b.nenner - b.zaehler * this.nenner;
		double n = this.nenner * b.nenner;
		return new Bruch(z, n);
	}

	public Bruch multi(Bruch b) {
		double z = this.zaehler * b.zaehler;
		double n = this.nenner * b.nenner;
		return new Bruch(z, n);
	}

	public Bruch div(Bruch b) {
		double z = this.zaehler * b.nenner;
		double n = this.nenner * b.zaehler;
		return new Bruch(z, n);
	}
}