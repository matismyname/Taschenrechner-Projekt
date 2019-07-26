package TaschenrechnerPlus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Taschenrechner.OHK;

public class Menu_TRP {
	Boolean b; // Boolean, die bei Wert = false das Fenster des JFrame zentriert
	static boolean schalter = true; // Variable für das Erkennen von fehlerhaften Eingaben des Users

	// Farben für die Fenster und panel
	public static Color azure2 = new Color(224, 238, 238);
	public static Color azure3 = new Color(193, 205, 205);

	private static JTextArea[] Textfelder_Ableitungen = new JTextArea[8]; // Für das Ableitungsmenü
	private static JTextArea[] Textfelder_Integrale = new JTextArea[8]; // Für das Integrationsmenü
	private static JTextArea[] Textfelder_Nullstellen2 = new JTextArea[3]; // Für das Nullstellenmenü2
	private static JTextArea[] Textfelder_Nullstellen1 = new JTextArea[2]; // Für das Nullstellenmenü1
	private static JTextArea[] Textfelder_Plotter = new JTextArea[8]; // Für das Plottermenü
	private static JTextArea[] Textfelder_linregx = new JTextArea[10]; // Für die x-Werte der linearen Regression
	private static JTextArea[] Textfelder_linregy = new JTextArea[10]; // Für die y-Werte der linearen Regression
	private static JTextArea[] Textfelder_Umrechner = new JTextArea[3]; // Für die Umrechnerklasse

	// Globale JFrame und JPanel, wenn ein Fenster
	// geschlossen und ein anderes aufgemacht werden soll.
	// Wenn mehr als zwei Fenster auf einmal gezeigt werden sollen,
	// werden lokale JFrame und JPanel Variablen verwendet
	// JFrame entspricht dem Fenster, JPanel entspricht der Leinwand, auf der
	// "gemalt" wird
	static JFrame f = new JFrame();
	static JPanel p = new JPanel(new GridBagLayout());

	// Variablen der Ableitung, grad_7_Ableitung entspricht x^7 usw.
	static double grad_7_Ableitung, grad_6_Ableitung, grad_5_Ableitung, grad_4_Ableitung, grad_3_Ableitung,
			grad_2_Ableitung, grad_1_Ableitung, grad_0_Ableitung;
	private static double[] Die_Grade_Ableitung = new double[] { grad_7_Ableitung, grad_6_Ableitung, grad_5_Ableitung,
			grad_4_Ableitung, grad_3_Ableitung, grad_2_Ableitung, grad_1_Ableitung, grad_0_Ableitung };

	// Variablen der Integrale
	static double grad_7_Integrale, grad_6_Integrale, grad_5_Integrale, grad_4_Integrale, grad_3_Integrale,
			grad_2_Integrale, grad_1_Integrale, grad_0_Integrale;
	private static double[] Die_Grade_Integrale = new double[] { grad_7_Ableitung, grad_6_Ableitung, grad_5_Ableitung,
			grad_4_Ableitung, grad_3_Ableitung, grad_2_Ableitung, grad_1_Ableitung, grad_0_Ableitung };

	private static double[] Nullstellen2 = new double[3];
	private static double[] Nullstellen1 = new double[2];

	// Variablen für den Plotter
	static double plot_7, plot_6, plot_5, plot_4, plot_3, plot_2, plot_1, plot_0;
	private static double[] Die_Plotter = new double[] { plot_7, plot_6, plot_5, plot_4, plot_3, plot_2, plot_1,
			plot_0 };

	// Variablen für die lineare Regression, x-Werte
	static double lrx0, lrx1, lrx2, lrx3, lrx4, lrx5, lrx6, lrx7, lrx8, lrx9, lrx10;
	private static double[] Die_linregx = new double[] { lrx0, lrx1, lrx2, lrx3, lrx4, lrx5, lrx6, lrx7, lrx8, lrx9,
			lrx10 };

	// Variablen für die lineare Regression, y-Werte
	static double lry0, lry1, lry2, lry3, lry4, lry5, lry6, lry7, lry8, lry9, lry10;
	private static double[] Die_linregy = new double[] { lry0, lry1, lry2, lry3, lry4, lry5, lry6, lry7, lry8, lry9,
			lry10 };

	// Variablen für den Umrechner
	static String ur1, ur2;
	static double ur3;
	private static double[] Die_Umrechner = new double[] { ur3 };
	private static String[] Die_Umrechner_string = new String[] { ur1, ur2 };

	// Variablen für den GgT und KgV
	static int gka, gkb;
	private static int[] Die_Teiler = new int[] { gka, gkb };

	public static void starten() {
		// Erstelle Fenster mit globalem f und p, setze Größe und Farbe usw.
		fenster(f, 300, 350, "Auswahlmenü", azure3, p, false, 0, 0);
		p.setBackground(azure3);

		// Knöpfe des Auswahlmenüs
		JButton knopf1 = new JButton("GgT und KgV");
		JButton knopf2 = new JButton("Ableitungen von Polynomen");
		JButton knopf3 = new JButton("Umrechner");
		JButton knopf4 = new JButton("Integrale von Polynomen");
		JButton knopf5 = new JButton("Lineare Regression");
		JButton knopf6 = new JButton("Nullstellenberechnung");
		JButton knopf7 = new JButton("Plotten");
		JButton knopf8 = new JButton("Zurück zum Hauptmenü");

		JButton[] knopf = new JButton[] { knopf1, knopf2, knopf3, knopf4, knopf5, knopf6, knopf7, knopf8 };

		// GridBagConstraints ist eine Bibliothek, die es erlaubt,
		// die einzelnen Komponenten an exakte Stellen im Panel zu setzen
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; // Alle Button haben die gleiche Länge
		c.gridx = 0; // 1. Spalte
		c.gridy = 0; // 1. Zeile

		for (int i = 0; i < 7; i++) {
			p.add(knopf[i], c);
			c.gridy += 1;
		}

		// Der letzte Knopf wird manuell hinzugefügt wegen der Insets
		// Was den Abstand zu den anderen Knöpfen erzeugt
		c.insets = new Insets(16, 16, 16, 16);
		c.gridy = 9;
		p.add(knopf8, c);

		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// schließen(f); // Schließt das Fenster, d.h. f ist wieder verfügbar
				// saub(p); // Macht den Panel sauber
			}

		});

		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				GgTKgV();
			}
		});

		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				ableiten();
			}
		});

		knopf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				umrechner();
			}

		});

		knopf4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				integriere();
			}

		});

		knopf5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				linreg();
			}

		});

		knopf6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				nullstelle();
			}

		});

		knopf7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				plotte();
			}

		});

		knopf8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				OHK.starten(); // Zurück zum Hauptmenü
			}

		});
	}

	// Größter gemeinsamer Teiler und Kleinstes gemeinsames Vielfaches
	public static void GgTKgV() {
		saub(p); // Als Sicherheit nochmals den Panel säubern
		fenster(f, 350, 230, "GgT und KgV", azure3, p, false, 0, 0);
		p.setBackground(azure3);

		// Deklariere alle wichtigen Variablen usw. und füge die Textfelder und JLabel
		// an die richtigen Stellen
		JPanel p1 = new JPanel();
		JFrame f1 = new JFrame();
		JPanel p11 = new JPanel();
		JFrame f11 = new JFrame();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);

		JButton knopf1 = new JButton("GgT und KgV");
		JButton knopf2 = new JButton("Zurück zum Hauptmenü");

		JLabel l1 = new JLabel("Größere Zahl: ");
		JLabel l2 = new JLabel("Kleinere Zahl: ");

		JTextArea txt1 = new JTextArea(1, 8);
		txt1.setMinimumSize(new Dimension(40, 15));
		JTextArea txt2 = new JTextArea(1, 8);
		txt2.setMinimumSize(new Dimension(40, 15));
		p.add(l1, c);
		c.gridx = 1;
		p.add(txt1, c);

		c.gridx = 0;
		c.gridy = 1;
		p.add(l2, c);
		c.gridx = 1;
		p.add(txt2, c);

		c.gridx = 0;
		c.gridy = 2;
		p.add(knopf1, c);
		c.gridx = 1;
		p.add(knopf2, c);

		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Säubern und schließen der Fenster und panel, damit keine Duplikate entstehen
				// bei mehrfachem Klicken des Berechne Buttons
				saub(p1);
				saub(p11);
				schließen(f1);
				schließen(f11);
				try {
					Die_Teiler[0] = Integer.parseInt(txt1.getText());
					Die_Teiler[1] = Integer.parseInt(txt2.getText());
					// Die Berechnung der GgT ist einfacher, wenn die erste Zahl größer ist als die
					// zweite
					if (Die_Teiler[0] < Die_Teiler[1]) {
						throw new Exception();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
							JOptionPane.ERROR_MESSAGE);
					schalter = false;
				}

				if (schalter) {
					GgT_und_KgV gk = new GgT_und_KgV(Die_Teiler[0], Die_Teiler[1]);
					gk.berech();

					fenster(f1, 350, 100, "Größter gemeinsamer Teiler", azure2, p1, true, 300, 423);
					JLabel l11 = new JLabel("GgT: " + Integer.toString(gk.getGgT()));
					l11.setFont(l11.getFont().deriveFont(18.0f));
					p1.add(l11);

					fenster(f11, 350, 100, "Kleinstes gemeinsames Vielfaches", azure2, p11, true, 636, 423);
					JLabel l12 = new JLabel("KgV: " + Integer.toString(gk.getKgV()));
					l12.setFont(l12.getFont().deriveFont(18.0f));
					p11.add(l12);
				} else {
					schließen(f);
					saub(p);
					schalter = true;
					GgTKgV();
				}

			}

		});

		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				saub(p1);
				saub(p11);
				schließen(f1);
				schließen(f11);
				starten();
			}

		});
	}

	// Plotterklasse
	public static void plotte() {
		saub(p); // Als Sicherheit nochmals den Panel säubern
		fenster(f, 1000, 180, "Plotter", azure3, p, false, 0, 0);
		p.setBackground(azure3);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 5); // Alle Komponenten haben einen Abstand von 5 Einheiten von einander
		int n = 0;
		int exp = 7;

		// Fügt die einzelnen TextAreas und x^7 usw. dem panel hinzu
		for (int i = 0; i < 7; i++) {
			JTextArea txt = new JTextArea(1, 8);
			txt.setMinimumSize(new Dimension(40, 15)); // Damit die Text Felder nicht nur ein dünner Strich sind auf dem
														// panel
			Textfelder_Plotter[i] = txt;
			p.add(txt, c);
			n += 1;
			c.gridx = n;
			JLabel l = new JLabel("x" + "^" + exp + " +");
			p.add(l, c);
			n += 1;
			c.gridx = n;
			exp -= 1;
		}

		// Fügt die Konstante txt als TextArea hinzu
		c.gridx = 15;
		JTextArea txt = new JTextArea(1, 8);
		txt.setMinimumSize(new Dimension(40, 15));
		Textfelder_Plotter[7] = txt;
		p.add(txt, c);

		// Die Buttons Zurück zum Auswahlemenü und Plotten werden dem panel hinzugefügt
		JButton knopf1 = new JButton("Zurück zum Auswahlmenü");
		JButton knopf2 = new JButton("Plotten");

		c.gridx = 19;
		c.gridy = 0;
		p.add(knopf1, c);

		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 19;
		c.gridy = 3;
		p.add(knopf2, c);

		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				starten();
			}

		});

		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Liest die Zahlen aus dem Textfeld raus
				// Wenn keine Zahlen eingegeben wurden, dann wird der Koeffizient 0 gesetzt
				for (int i = 0; i < 8; i++) {
					try {
						if (Textfelder_Plotter[i].getText().isEmpty()) {
							Die_Plotter[i] = 0;
						} else {
							Die_Plotter[i] = Double.parseDouble(Textfelder_Plotter[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}

				// Wenn schalter = true, dann entstand kein Fehler, andernfalls wird die
				// Ausführung des Codes abgebrochen
				if (schalter) {
					Plotten p = new Plotten(Die_Plotter, "Normal");
					p.plot();
				} else {
					schließen(f);
					saub(p);
					schalter = true;
					plotte();
				}
			}
		});
	}

	// Klasse für die lineare Regression
	public static void linreg() {
		saub(p);
		fenster(f, 400, 400, "Lineare Regression", azure3, p, true, 550, 70);
		p.setBackground(azure3);

		JFrame fst = new JFrame();
		JPanel pn = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));

		JButton knopf1 = new JButton("Zurück zum Auswahlmenü");
		JButton knopf2 = new JButton("Berechnen und Plotten");

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		JLabel lx = new JLabel("x");
		c.gridx = 1;
		p.add(lx, c);
		JLabel ly = new JLabel("y");
		c.gridx = 2;
		p.add(ly, c);

		c.gridx = 1;
		c.gridy = 1;

		// Die x-Felder und y-Felder werden erstellt und dem panel hinzugefügt
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 10; i++) {
				JTextArea txt = new JTextArea(1, 6);
				txt.setMinimumSize(new Dimension(40, 15));
				p.add(txt, c);
				if (c.gridx == 1) {
					Textfelder_linregx[i] = txt;
				} else {
					Textfelder_linregy[i] = txt;
				}
				c.gridy += 1;
			}
			c.gridy = 1;
			c.gridx += 1;
		}

		c.gridx = 1;
		c.gridy = 12;
		p.add(knopf1, c);

		c.gridx = 2;
		p.add(knopf2, c);

		// Schließt das lineare Regressionsfenster und das Ergebnisfenster, wenn der
		// User zurück will
		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				schließen(fst);
				saub(pn);
				starten();
			}

		});

		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int leer_zaehler = 0; // Zählt die leeren Felder
				for (int i = 0; i < 10; i++) {
					try {
						if (Textfelder_linregx[i].getText().isEmpty() && Textfelder_linregy[i].getText().isEmpty()) {
							leer_zaehler += 1;
							Die_linregx[i] = 0;
							Die_linregy[i] = 0;
						} else {
							Die_linregx[i] = Double.parseDouble(Textfelder_linregx[i].getText());
							Die_linregy[i] = Double.parseDouble(Textfelder_linregy[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}

				if (schalter) {
					Lineare_Regression reg = new Lineare_Regression(Die_linregx, Die_linregy, leer_zaehler);
					double[] ergs = reg.berech();

					schließen(fst);
					saub(pn);
					fenster(fst, 300, 110, "Regressionsgerade", azure2, pn, true, 550, 463);

					JLabel l1 = new JLabel("y = " + Double.toString(ergs[0]) + " + " + Double.toString(ergs[1]) + "x");
					l1.setFont(l1.getFont().deriveFont(15.0f)); // Macht das Ergebnis größer

					pn.add(l1);

					Plotten p = new Plotten(ergs, "Linear");
					p.plot();
				} else {
					schließen(fst);
					saub(pn);
					schließen(f);
					saub(p);
					schalter = true;
					linreg();
				}
			}

		});
	}

	// Klasse für die Nullstellen
	public static void nullstelle() {
		// Nullstellenfenster
		saub(p);
		fenster(f, 380, 200, "Nullstellenrechner", azure3, p, false, 0, 0);
		p.setBackground(azure3);

		// Nullstellenberechnungsfenster
		JFrame fst = new JFrame();
		JPanel pn = new JPanel();

		// Ergebnisfenster
		JFrame fstt = new JFrame();
		JPanel pnn = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20)); // FlowLayout ist eine Bibliothek,
																			// die es ermöglicht, die JLabel zu //
																			// zentrieren

		JButton knopf1 = new JButton("Polynom 1. Grades");
		JButton knopf2 = new JButton("Polynom 2. Grades");
		JButton knopf3 = new JButton("Zurück zum Auswahlmenü");
		JButton knopf4 = new JButton("Berechne!");
		JButton knopf5 = new JButton("Berechne!");
		JButton knopf6 = new JButton("Plotten");
		JButton knopf7 = new JButton("Plotten");
		JButton knopf8 = new JButton("Zurück zum Nullstellenrechner");

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		p.add(knopf1, c);

		c.gridy = 1;
		p.add(knopf2, c);

		c.insets = new Insets(16, 16, 16, 16);
		c.gridy = 3;
		p.add(knopf3, c);

		// Erzeugt das Fenster für das Polynom 1. Grades, d.h. die Felder für die
		// Koeffizienten usw..
		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saub(pn);
				schließen(fst);
				saub(pnn);
				schließen(fstt);
				saub(p);
				schließen(f);
				fenster(fst, 650, 110, "Polynom 1. Grades", azure3, pn, true, 530, 290);

				JLabel l1 = new JLabel(" *x + ");
				JTextArea txt1 = new JTextArea(1, 8);
				JTextArea txt2 = new JTextArea(1, 8);

				txt1.setMinimumSize(new Dimension(40, 15));
				txt2.setMinimumSize(new Dimension(40, 15));

				Textfelder_Nullstellen1[0] = txt1;
				Textfelder_Nullstellen1[1] = txt2;

				GridBagConstraints d = new GridBagConstraints();
				d.fill = GridBagConstraints.HORIZONTAL;

				d.gridx = 0;
				d.gridy = 0;

				pn.add(txt1, d);

				d.gridx = 1;
				pn.add(l1, d);

				d.gridx = 2;
				pn.add(txt2, d);

				d.gridx = 3;
				pn.add(knopf4, d);

				d.gridx = 4;
				pn.add(knopf6, d);

				d.gridx = 5;
				pn.add(knopf8, d);
			}

		});

		// Erzeugt das Fenster für das Polynom 2. Grades, d.h. die Felder für die
		// Koeffizienten usw..
		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saub(pn);
				schließen(fst);
				saub(pnn);
				schließen(fstt);
				saub(p);
				schließen(f);
				fenster(fst, 750, 110, "Polynom 2. Grades", azure3, pn, true, 530, 290);

				JLabel l1 = new JLabel(" *x^2 + ");
				JLabel l2 = new JLabel(" *x + ");
				JTextArea txt1 = new JTextArea(1, 8);
				JTextArea txt2 = new JTextArea(1, 8);
				JTextArea txt3 = new JTextArea(1, 8);

				txt1.setMinimumSize(new Dimension(40, 15));
				txt2.setMinimumSize(new Dimension(40, 15));
				txt3.setMinimumSize(new Dimension(40, 15));

				Textfelder_Nullstellen2[0] = txt1;
				Textfelder_Nullstellen2[1] = txt2;
				Textfelder_Nullstellen2[2] = txt3;

				GridBagConstraints d = new GridBagConstraints();
				d.fill = GridBagConstraints.HORIZONTAL;

				d.gridx = 0;
				d.gridy = 0;

				pn.add(txt1, d);

				d.gridx = 1;
				pn.add(l1, d);

				d.gridx = 2;
				pn.add(txt2, d);

				d.gridx = 3;
				pn.add(l2, d);

				d.gridx = 4;
				pn.add(txt3, d);

				d.gridx = 5;
				pn.add(knopf5, d);

				d.gridx = 6;
				pn.add(knopf7, d);

				d.gridx = 7;
				pn.add(knopf8, d);
			}

		});

		knopf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				schließen(fst);
				saub(pn);
				schließen(fstt);
				saub(pnn);
				starten();
			}

		});

		// Berechnenknopf für das Polynom 1.Grades
		knopf4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < 2; i++) {
					try {
						if (Textfelder_Nullstellen1[i].getText().isEmpty()) {
							Nullstellen1[i] = 0;
						} else {
							Nullstellen1[i] = Double.parseDouble(Textfelder_Nullstellen1[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}

				if (schalter) {
					saub(pnn);
					schließen(fstt);
					fenster(fstt, 600, 110, "Nullstellenergebnis", azure2, pnn, true, 530, 392);

					Nullstellen1 N = new Nullstellen1(Nullstellen1[0], Nullstellen1[1]);
					double n1 = N.berech1();

					JLabel l1 = new JLabel("x1: " + Double.toString(n1));

					l1.setFont(l1.getFont().deriveFont(15.0f));

					pnn.add(l1);
				} else {
					saub(p);
					schließen(f);
					saub(pn);
					schließen(fst);
					saub(pnn);
					schließen(fstt);
					schalter = true;
					nullstelle();
				}

			}

		});

		knopf5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Lies die Werte aus den Textfeldern heraus, wenn ein Feld leer ist, wird es 0
				// gesetzt
				for (int i = 0; i < 3; i++) {
					try {
						if (Textfelder_Nullstellen2[i].getText().isEmpty()) {
							Nullstellen2[i] = 0;
						} else {
							Nullstellen2[i] = Double.parseDouble(Textfelder_Nullstellen2[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}

				if (schalter) {
					saub(pnn);
					schließen(fstt);
					fenster(fstt, 600, 110, "Nullstellenergebnisse", azure2, pnn, true, 530, 392);

					Nullstellen2 N = new Nullstellen2(Nullstellen2[0], Nullstellen2[1], Nullstellen2[2]);

					// Entweder sind die Nullstellen komplex oder nicht
					// Hier werden diese Fälle überprüft
					if (N.getZeta() < 0) {
						double r1 = N.getR(); // Realteil
						double k1 = N.getK(); // Imaginärteil
						JLabel l1 = new JLabel("x1: " + Double.toString(r1) + " + " + k1 + "i");
						JLabel l2 = new JLabel("x2: " + Double.toString(r1) + " - " + k1 + "i");
						l1.setFont(l1.getFont().deriveFont(15.0f)); // deriveFont vergrößert den Text im JLabel
						l2.setFont(l2.getFont().deriveFont(15.0f));
						pnn.add(l1);
						pnn.add(l2);
					} else {
						double n1 = N.berech1();
						double n2 = N.berech2();
						JLabel l1 = new JLabel("x1: " + Double.toString(n1));
						JLabel l2 = new JLabel("x2: " + Double.toString(n2));
						l1.setFont(l1.getFont().deriveFont(15.0f));
						l2.setFont(l2.getFont().deriveFont(15.0f));
						pnn.add(l1);
						pnn.add(l2);
					}
				} else {
					saub(p);
					schließen(f);
					saub(pn);
					schließen(fst);
					saub(pnn);
					schließen(fstt);
					schalter = true;
					nullstelle();
				}
			}
		});

		// Plotter für das Polynom 1. Grades
		knopf6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 2; i++) {
					try {
						if (Textfelder_Nullstellen1[i].getText().isEmpty()) {
							Nullstellen1[i] = 0;
						} else {
							Nullstellen1[i] = Double.parseDouble(Textfelder_Nullstellen1[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}

				if (schalter) {
					Plotten p = new Plotten(Nullstellen1, "Linear");
					p.plot();
				} else {
					saub(p);
					schließen(f);
					saub(pn);
					schließen(fst);
					saub(pnn);
					schließen(fstt);
					schalter = true;
					nullstelle();
				}

			}
		});

		// Plotter für das Polynom 2. Grades
		knopf7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 3; i++) {
					try {
						if (Textfelder_Nullstellen2[i].getText().isEmpty()) {
							Nullstellen2[i] = 0;
						} else {
							Nullstellen2[i] = Double.parseDouble(Textfelder_Nullstellen2[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}

				if (schalter) {
					Plotten p = new Plotten(Nullstellen2, "Quadratisch");
					p.plot();
				} else {
					saub(p);
					schließen(f);
					saub(pn);
					schließen(fst);
					saub(pnn);
					schließen(fstt);
					schalter = true;
					nullstelle();
				}
			}
		});

		// Zurück zum Nullstellenrechner
		knopf8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saub(pn);
				schließen(fst);
				saub(pnn);
				schließen(fstt);

				nullstelle();
			}

		});
	}

	// Umrechnerklasse
	public static void umrechner() {
		saub(p);
		fenster(f, 400, 250, "Umrechner", azure3, p, false, 0, 0);
		p.setBackground(azure3);

		// Deklariere alles wichtige, setzte alles an seinen richtigen Platz im JPanel
		JPanel p1 = new JPanel();
		JFrame f1 = new JFrame();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);

		JLabel l1 = new JLabel("Von");
		JLabel l2 = new JLabel("Zu");
		JLabel l3 = new JLabel("Eingabe");

		JButton knopf1 = new JButton("Zurück zum Hauptmenü");
		JButton knopf2 = new JButton("Berechne");

		// Feld zur Überprüfung, ob der User auch die richtigen Einheiten eingegeben hat
		String[] umr = new String[] { "Radiant", "Winkelgrad", "Miles", "Kilometer", "Inches", "Zentimeter", "Celcius",
				"Fahrenheit", "Mph", "Kmh" };

		c.gridx = 0;
		c.gridy = 0;
		p.add(l1, c);
		c.gridx = 1;
		p.add(l2, c);

		c.gridx = 0;
		c.gridy = 1;

		JTextArea txt_von = new JTextArea(1, 8);
		txt_von.setMinimumSize(new Dimension(40, 15));
		p.add(txt_von, c);
		Textfelder_Umrechner[0] = txt_von;

		c.gridx = 1;

		JTextArea txt_zu = new JTextArea(1, 8);
		txt_zu.setMinimumSize(new Dimension(40, 15));
		p.add(txt_zu, c);
		Textfelder_Umrechner[1] = txt_zu;

		c.gridx = 0;
		c.gridy = 2;
		p.add(l3, c);

		c.gridy = 4;
		c.gridx = 0;
		JTextArea txt_eingabe = new JTextArea(1, 8);
		txt_eingabe.setMinimumSize(new Dimension(40, 15));
		p.add(txt_eingabe, c);
		Textfelder_Umrechner[2] = txt_eingabe;

		c.gridy = 5;
		p.add(knopf1, c);

		c.gridx = 1;
		p.add(knopf2, c);

		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saub(p);
				schließen(f);
				saub(p1);
				schließen(f1);
				starten();
			}

		});

		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saub(p1);
				schließen(f1);
				// Ist Von und Zu in umr, also sind Von und Zu Einheiten
				boolean check1 = Arrays.asList(umr).contains(Textfelder_Umrechner[0].getText());
				boolean check2 = Arrays.asList(umr).contains(Textfelder_Umrechner[1].getText());
				try {
					if (check1 && check2) {
						Die_Umrechner_string[0] = Textfelder_Umrechner[0].getText();
						Die_Umrechner_string[1] = Textfelder_Umrechner[1].getText();
						Die_Umrechner[0] = Double.parseDouble(Textfelder_Umrechner[2].getText());
					} else {
						throw new Exception();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
							JOptionPane.ERROR_MESSAGE);
					schalter = false;
				}

				if (schalter) {
					Umrechner U = new Umrechner(Die_Umrechner[0], Die_Umrechner_string[0], Die_Umrechner_string[1]);
					U.berech();
					fenster(f1, 500, 100, "Ergebnis", azure2, p1, true, 440, 447);
					JLabel l1 = new JLabel(Double.toString(U.getErg()));
					l1.setFont(l1.getFont().deriveFont(18.0f));
					p1.add(l1);
				} else {
					schalter = true;
					saub(p);
					schließen(f);
					umrechner();
				}
			}

		});
	}

	// Ableitungsklasse
	public static void ableiten() {
		saub(p);
		fenster(f, 1000, 200, "Ableitungsrechner", azure3, p, false, 0, 0);
		p.setBackground(azure3);
		JPanel p_abl = new JPanel(); // Lokales JPanel
		JFrame f1 = new JFrame(); // Lokales JFrame

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		int n = 0;
		int exp = 7;

		// Fügt die einzelnen TextAreas und x^7 usw dem panel hinzu
		for (int i = 0; i < 7; i++) {
			JTextArea txt = new JTextArea(1, 8);
			txt.setMinimumSize(new Dimension(40, 15));
			Textfelder_Ableitungen[i] = txt;
			p.add(txt, c);
			n += 1;
			c.gridx = n;
			JLabel l = new JLabel("x" + "^" + exp + " +");
			p.add(l, c);
			n += 1;
			c.gridx = n;
			exp -= 1;
		}

		// Fügt die Konstante txt als TextArea hinzu
		c.gridx = 15;
		JTextArea txt = new JTextArea(1, 8);
		txt.setMinimumSize(new Dimension(40, 15));
		Textfelder_Ableitungen[7] = txt;
		p.add(txt, c);

		// Die Buttons Zurück zum Auswahlemenü und Ableiten werden dem panel hinzugefügt
		JButton knopf1 = new JButton("Zurück zum Auswahlmenü");
		JButton knopf2 = new JButton("Ableiten");
		JButton knopf3 = new JButton("Plotten");

		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 19;
		c.gridy = 2;
		p.add(knopf1, c);

		c.gridx = 19;
		c.gridy = 1;
		p.add(knopf2, c);

		c.gridx = 19;
		c.gridy = 0;
		p.add(knopf3, c);

		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				schließen(f1);
				saub(p_abl);
				starten();
			}

		});

		// Liest die Koeffizienten ab
		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 8; i++) {
					try {
						if (Textfelder_Ableitungen[i].getText().isEmpty()) {
							Die_Grade_Ableitung[i] = 0;
						} else {
							Die_Grade_Ableitung[i] = Double.parseDouble(Textfelder_Ableitungen[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}

				if (schalter) {
					Ableitung A = new Ableitung(Die_Grade_Ableitung);
					Die_Grade_Ableitung = A.ableite();

					schließen(f1);
					saub(p_abl);
					p_abl.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
					fenster(f1, 1000, 180, "Ergebnis", azure2, p_abl, true, 140, 423);

					JLabel l1 = new JLabel(Double.toString(Die_Grade_Ableitung[6]));
					JLabel l2 = new JLabel(Double.toString(Die_Grade_Ableitung[5]) + "*x^1 +");
					JLabel l3 = new JLabel(Double.toString(Die_Grade_Ableitung[4]) + "*x^2 +");
					JLabel l4 = new JLabel(Double.toString(Die_Grade_Ableitung[3]) + "*x^3 +");
					JLabel l5 = new JLabel(Double.toString(Die_Grade_Ableitung[2]) + "*x^4 +");
					JLabel l6 = new JLabel(Double.toString(Die_Grade_Ableitung[1]) + "*x^5 +");
					JLabel l7 = new JLabel(Double.toString(Die_Grade_Ableitung[0]) + "*x^6 +");

					JLabel[] lbl = new JLabel[] { l7, l6, l5, l4, l3, l2, l1 };
					// Ergebnislabel werden auf das Panel "gedruckt"
					// Der Font wird größer gemacht, damit das Ergebnis einfacher zu lesen ist
					c.insets = new Insets(10, 10, 10, 10);
					c.gridx = 0;
					c.gridy = 3;

					for (int i = 0; i < 7; i++) {
						lbl[i].setFont(lbl[i].getFont().deriveFont(18.0f));
						p_abl.add(lbl[i], c);
					}
				} else {
					schalter = true;
					saub(p_abl);
					saub(p);
					schließen(f);
					schließen(f1);
					ableiten();
				}
			}
		});

		// Plotter für den Ableitungsrechner
		knopf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 8; i++) {
					try {
						if (Textfelder_Ableitungen[i].getText().isEmpty()) {
							Die_Grade_Ableitung[i] = 0;
						} else {
							Die_Grade_Ableitung[i] = Double.parseDouble(Textfelder_Ableitungen[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}
				if (schalter) {
					Plotten p = new Plotten(Die_Grade_Ableitung, "Normal");
					p.plot();
				} else {
					schalter = true;
					saub(p_abl);
					saub(p);
					schließen(f);
					schließen(f1);
				}

			}

		});
	}

	// Integralklasse
	protected static void integriere() {
		saub(p);
		fenster(f, 1000, 200, "Integralrechner", azure3, p, false, 0, 0);
		p.setBackground(azure3);
		JPanel p_intg = new JPanel();
		JFrame f1 = new JFrame();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		int n = 0;
		int exp = 7;

		// Die Textfelder und x^7 usw. werden dem panel hinzugefügt
		for (int i = 0; i < 7; i++) {
			JTextArea txt = new JTextArea(1, 8);
			txt.setMinimumSize(new Dimension(40, 15));
			Textfelder_Integrale[i] = txt;
			p.add(txt, c);
			n += 1;
			c.gridx = n;
			JLabel l = new JLabel("x" + "^" + exp + " +");
			p.add(l, c);
			n += 1;
			c.gridx = n;
			exp -= 1;
		}

		// Fügt die Konstante c als TextArea hinzu
		c.gridx = 15;
		JTextArea txt = new JTextArea(1, 8);
		txt.setMinimumSize(new Dimension(40, 15));
		Textfelder_Integrale[7] = txt;
		p.add(txt, c);

		// Die Buttons Zurück zum Auswahlemenü und Ableiten werden dem panel hinzugefügt
		JButton knopf1 = new JButton("Zurück zum Auswahlmenü");
		JButton knopf2 = new JButton("Integrieren");
		JButton knopf3 = new JButton("Plotten");

		c.gridx = 19;
		c.gridy = 2;
		p.add(knopf1, c);

		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 19;
		c.gridy = 1;
		p.add(knopf2, c);

		c.gridx = 19;
		c.gridy = 0;
		p.add(knopf3, c);

		knopf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				schließen(f);
				saub(p);
				schließen(f1);
				saub(p_intg);
				starten();
			}

		});

		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 8; i++) {
					try {
						if (Textfelder_Integrale[i].getText().isEmpty()) {
							Die_Grade_Integrale[i] = 0;
						} else {
							Die_Grade_Integrale[i] = Double.parseDouble(Textfelder_Integrale[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						schalter = false;
					}
				}

				if (schalter) {
					Integrale I = new Integrale(Die_Grade_Integrale);
					Die_Grade_Integrale = I.integriere();

					schließen(f1);
					saub(p_intg);
					p_intg.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
					fenster(f1, 1000, 180, "Ergebnis", azure2, p_intg, true, 140, 423);

					// Ergebnislabel
					JLabel lC = new JLabel("+ C");
					JLabel l0 = new JLabel(Double.toString(Die_Grade_Integrale[7]) + "x");
					JLabel l1 = new JLabel(Double.toString(Die_Grade_Integrale[6]) + "x^2 +");
					JLabel l2 = new JLabel(Double.toString(Die_Grade_Integrale[5]) + "*x^3 +");
					JLabel l3 = new JLabel(Double.toString(Die_Grade_Integrale[4]) + "*x^4 +");
					JLabel l4 = new JLabel(Double.toString(Die_Grade_Integrale[3]) + "*x^5 +");
					JLabel l5 = new JLabel(Double.toString(Die_Grade_Integrale[2]) + "*x^6 +");
					JLabel l6 = new JLabel(Double.toString(Die_Grade_Integrale[1]) + "*x^7 +");
					JLabel l7 = new JLabel(Double.toString(Die_Grade_Integrale[0]) + "*x^8 +");

					c.insets = new Insets(10, 10, 10, 10);
					c.gridx = 0;
					c.gridy = 3;

					JLabel[] lbl = new JLabel[] { l7, l6, l5, l4, l3, l2, l1, l0, lC };

					for (int i = 0; i < 9; i++) {
						lbl[i].setFont(lbl[i].getFont().deriveFont(18.0f));
						p_intg.add(lbl[i], c);
					}
				} else {
					schließen(f);
					saub(p);
					schließen(f1);
					saub(p_intg);
					integriere();
				}
			}
		});

		knopf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 8; i++) {
					try {
						if (Textfelder_Integrale[i].getText().isEmpty()) {
							Die_Grade_Integrale[i] = 0;
						} else {
							Die_Grade_Integrale[i] = Double.parseDouble(Textfelder_Integrale[i].getText());
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f, "Irgendetwas ist schief gelaufen!", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				Plotten p = new Plotten(Die_Grade_Integrale, "Normal");
				p.plot();
			}

		});

	}

	// Funktion zum Setzen der Größe, des Namens, der Farbe etc. des Fensters
	// Fügt einen Panel pn dem Fenster hinzu
	// Die Farbe muss dem Fenster und dem Panel hinzugefügt werden.
	private static void fenster(JFrame fst, int breite, int höhe, String name, Color farbe, JPanel pn, Boolean b,
			int posx, int posy) {
		fst.setSize(breite, höhe); // Größe des Fensters
		fst.setResizable(false); // Fenstergröße kann nicht vom User verändert werden
		fst.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Schließt das Fenster, wenn man auf X klickt
		fst.setTitle(name); // Überschrift des Fensters
		fst.setVisible(true); // Das Fenster wird sichtbar
		fst.getContentPane().setBackground(farbe); // Farbe des Fensters wird gesetzt
		pn.setBackground(farbe); // Farbe des panels wird gesetzt
		fst.add(pn); // Dem Fenster wird der panel hinzugefügt

		if (!b) {
			fst.setLocationRelativeTo(null); // Zentriert des Fenster
		} else {
			fst.setLocation(posx, posy); // Spezielle Angabe der Position des Fensters
		}
	}

	// Funktion, die das Fenster fst schließt
	public static void schließen(JFrame fst) {
		fst.setVisible(false);
		fst.dispose();
	}

	// Funktion, die das Panel pn säubert
	public static void saub(JPanel pn) {
		pn.removeAll();
		pn.updateUI();
	}
}
