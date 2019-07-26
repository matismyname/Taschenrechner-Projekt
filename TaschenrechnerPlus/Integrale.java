package TaschenrechnerPlus;

import TaschenrechnerPlus.Runde;

public class Integrale {
	double Ints[] = new double[8];
	Runde r = new Runde();

	public Integrale(double arr[]) {
		this.Ints = arr;
	}

	// Einfache Summenregel für Integrale angewandt
	// Das Array mit den integrierten Koeffizienten wird zurückgegeben.
	public double[] integriere() {
		int n = 8;
		for (int i = 0; i < 8; i++) {
			Ints[i] = r.runde(Ints[i] * (1. / n));
			n -= 1;
		}
		return Ints;
	}
}
