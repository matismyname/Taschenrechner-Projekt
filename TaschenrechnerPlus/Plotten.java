package TaschenrechnerPlus;

import java.awt.Dimension;

import javax.swing.JFrame;

import plotter.Graphic;
import plotter.Plotter;

public class Plotten {
	double plts1[] = new double[8];
	double plts2[] = new double[2];
	double plts3[] = new double[3];
	boolean N1 = false;
	boolean N2 = false;

	Graphic graphic = new Graphic("Funktionenplot");
	Plotter plotter = graphic.getPlotter();

	public Plotten(double arr[], String s) {
		// Für Ableitungen und Integrale
		if (s.equals("Normal")) {
			this.plts1 = arr;
			// Falls das Polynom 1. Grades bei Nullstellenberechnung geplottet werden soll
		} else if (s.equals("Linear")) {
			this.plts2 = arr;
			this.N1 = true;
			// Falls das Polynom 2. Grades bei Nullstellenberechnung geplottet werden soll
		} else if (s.equals("Quadratisch")) {
			this.plts3 = arr;
			this.N2 = true;
		}
	}

	public void plot() {
		// X-Achse und Y-Achse werden gesetzt
		plotter.setXLine(0);
		plotter.setYLine(0);
		plotter.setPreferredSize(new Dimension(500, 500));

		if (!N1 & !N2) {
			plotter.setXLabelFormat("%.2f");

			for (double x = -10; x < 10; x += 0.1) {
				plotter.add(x, fktwerte(x, plts1));
			}
		} else if (N1 & !N2) {
			plotter.setXLabelFormat("%.2f");
			plotter.setAutoXgrid(5); // X-Achse wird in 5-er Abständen beschriftet

			// Y-Achse wird beschriftet je nachdem, wie groß die Koeffizienten sind
			if (plts2[0] < 15 & plts2[1] < 15) {
				plotter.setAutoYgrid(20);
			} else if (plts2[0] < 50 & plts2[1] < 50) {
				plotter.setAutoYgrid(50);
			} else {
				plotter.setAutoYgrid(100);
			}

			for (double x = -10; x < 10; x += 0.1) {
				plotter.add(x, fktwerte(x, plts2));
			}
		} else if (!N1 & N2) {
			plotter.setXLabelFormat("%.2f");
			plotter.setAutoXgrid(5);

			if (plts3[0] < 15 & plts3[1] < 15 & plts3[2] < 15) {
				plotter.setAutoYgrid(100);
			} else if (plts3[0] < 50 & plts3[1] < 50 & plts3[2] < 50) {
				plotter.setAutoYgrid(100);
			} else {
				plotter.setAutoYgrid(250);
			}

			for (double x = -10; x < 10; x += 0.1) {
				plotter.add(x, fktwerte(x, plts3));
			}
		}

		graphic.pack();
		graphic.repaint();
	}

	// Horner Schema
	public double fktwerte(double x, double plts[]) {
		double erg = plts[0] * x + plts[1];
		for (int i = 0; i < plts.length - 2; i++) {
			erg = erg * x + plts[i + 2];
		}
		return erg;
	}

	// Funktion, die das Fenster fst schließt
	public static void schließen(JFrame fst) {
		fst.setVisible(false);
		fst.dispose();
	}
}