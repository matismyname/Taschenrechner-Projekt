package TaschenrechnerPlus;

public class Lineare_Regression {
	double x_Werte[] = new double[10];
	double y_Werte[] = new double[10];
	double a, b; // Koeffizienten für ydach = a+bx
	int n; // Variable, die die Anzahl der vollen Felder speichert, damit das arithmetische
			// Mittel richtig berechnet wird
	Runde r = new Runde();

	public Lineare_Regression(double arr1[], double arr2[], int leer_zaehler) {
		this.x_Werte = arr1;
		this.y_Werte = arr2;
		this.n = 10 - leer_zaehler;
	}

	public double[] berech() {
		double xd = ArMi(x_Werte, n); // Arithmetisches Mittel
		double yd = ArMi(y_Werte, n);
		double sxy = Kovarianz(x_Werte, y_Werte, xd, yd);
		double sx = Varianz(x_Werte, xd);
		double b = r.runde(sxy / sx); // Formel zur Berechnung von b, gerundet
		double a = r.runde(yd - b * xd); // Formel zur Berechnung von a, gerundet
		double ergs[] = { a, b };
		return ergs;
	}

	private double Kovarianz(double[] xW, double[] yW, double xd, double yd) {
		double erg = 0;
		double summe = 0;
		for (int i = 0; i < 10; i++) {
			summe += xW[i] * yW[i];
		}
		erg = (1. / n) * summe - (xd * yd);
		return erg;
	}

	private double Varianz(double[] xW, double xd) {
		double erg = 0;
		double summe = 0;
		for (double i : xW) {
			summe += i * i;
		}
		erg = (1. / n) * summe - (xd * xd);
		return erg;
	}

	public double ArMi(double[] z, double n) {
		double k = 0;
		for (double i : z) {
			k += i;
		}
		return k / n;
	}
}
