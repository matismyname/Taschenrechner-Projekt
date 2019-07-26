package NormalerTaschenrechner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Taschenrechner.OHK;
import TaschenrechnerPlus.Runde;
import jserver.Board;
import jserver.BoardClickEvent;
import jserver.BoardClickListener;
import jserver.XSendAdapter;
import plotter.Graphic;

public class Hauptklasse implements BoardClickListener {
	// Deklaration aller wichtigen Variablen etc. wie z.B. die Zahlenfelder zum
	// Eingeben der Zahlen
	private String ROP = "leer"; // Speichert die Rechenoperationen Plus/Minus/Mal/Geteilt, da BoardClickListener
									// nur void kann
	private Board board;
	private Graphic graphic;
	private int[] ROP_int_feld = { 1, 2, 3 }; // 1 = Dezimal 2 = Bruch 3 = Komplex
												// Für die JRadioButtons, damit wir wissen, welcher gedrückt wurde
	private int ROP_int_aktuell = 1; // Aktuell ist Dezimal eingestellt und mit Dezimal.setSelected() ausgewählt
	private String[] Rechenoperationen = { "Dezimal", "Bruch", "Komplex" };
	JRadioButton RechenWaehler;
	JTextField zahlenFeld1, zahlenFeld2;
	JLabel label1, label2;
	private XSendAdapter xsend;

	private double dezimalErgebnis;
	Bruch bruchErgebnis;
	Komplex kompErgebnis;

	Runde r = new Runde();

	public void starten() {
		// Die Rahmenbedingungen des Taschenrechners schaffen wie die Größe
		xsend = new XSendAdapter();
		board = xsend.getBoard();
		xsend.groesse(4, 1);
		xsend = new XSendAdapter(board);
		graphic = board.getGraphic();
		JTextField zahlenFeld1 = new JTextField();
		JTextField zahlenFeld2 = new JTextField();
		xsend.farben(XSendAdapter.LIGHTGRAY);
		board.addClickListener(this);
		graphic.setLocation(230, 30);

		// Mache die Symbole +, -, *, -|- größer
		board.receiveMessage(">>fontsize 30");

		// Fuegt die zwei Zahlenfelder hinzu, von denen die Zahlen rausgelesen werden
		// sollen
		graphic.addBottomComponent(new JLabel("Zahl 1: "));
		graphic.addBottomComponent(zahlenFeld1);

		graphic.addBottomComponent(new JLabel("Zahl 2: "));
		graphic.addBottomComponent(zahlenFeld2);

		// Fuegt den Berechnenbutton hinzu; wichtig ist, dass der Button erst nach den
		// Zahlenfeldern hinzugefügt wird,
		// damit er auch rechts hinter dem zweiten Zahlenfeld erscheint
		JButton berechnenButton = new JButton("Berechnen");
		JButton zrk_zu_OHK = new JButton("Zurück zum Hauptmenü");
		graphic.addBottomComponent(berechnenButton);
		graphic.addWestComponent(zrk_zu_OHK);

		zrk_zu_OHK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				graphic.setVisible(false);
				graphic.dispose();
				OHK.starten();
			}

		});

		// Buttongroup, damit nicht gleichzeitig Dezimal und Bruch o.ä. ausgewählt
		// werden kann
		graphic.addEastComponent(new JLabel("Rechenoperationen"));
		ButtonGroup operationenGruppe = new ButtonGroup();

		// Füge die RadioButtons hinzu und speichere den Wert des gedrückten
		// RadioButtons in ROP_int_aktuell
		for (int f = 0; f < Rechenoperationen.length; f++) {
			RechenWaehler = new JRadioButton(Rechenoperationen[f]);
			RechenWaehler.setActionCommand("" + ROP_int_feld[f]);
			operationenGruppe.add(RechenWaehler);
			RechenWaehler.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ROP_int_aktuell = Integer.parseInt(e.getActionCommand());
				}
			});
			graphic.addEastComponent(RechenWaehler);

			// Dezimal ist beim Start immer ausgewählt
			if (f == 0) {
				RechenWaehler.setSelected(true);
			}
		}

		// Füge +, -, *, -|- hinzu
		xsend.text(0, "+");
		xsend.text(1, "-");
		xsend.text(2, "*");
		xsend.zeichen(3, '\u00F7'); // Unicodezeichen für die Division

		berechnenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text1 = zahlenFeld1.getText();
				String text2 = zahlenFeld2.getText();

				// Ein kleiner Easter Egg
				if (text1.equals("Frohe") || text2.equals("Weihnachten")) {
					Weihnachten weihnachten = new Weihnachten();
					weihnachten.start();
					return;
				}

				// Error Behandlung
				if (text1.isEmpty() || text2.isEmpty()) {
					JOptionPane.showMessageDialog(graphic, "Bitte Zahlenwerte eingeben", "Leeres Feld",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				else if (ROP.equals("leer")) {
					JOptionPane.showMessageDialog(graphic,
							"Bitte Rechenoperation auswählen! Klicke auf eines der Felder!", "Rechenoperation",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				else {
					try {
						// Wenn der User Dezimalzahlen berechnen möchte
						if (e.getSource() == berechnenButton && ROP_int_aktuell == 1) {
							double zahl1 = Double.parseDouble(text1);
							double zahl2 = Double.parseDouble(text2);

							if (zahl2 == 0 && ROP.equals("geteilt")) {
								JOptionPane.showMessageDialog(graphic, "Nullteilung ist nicht definiert!",
										"Nullteilung!", JOptionPane.ERROR_MESSAGE);
								return;
							}

							Dezimal dez = new Dezimal(zahl1, zahl2, ROP);
							dezimalErgebnis = dez.berechne();
							JOptionPane.showMessageDialog(graphic, "Das Ergebnis ist: " + r.runde(dezimalErgebnis),
									"Ergebnisfenster", JOptionPane.INFORMATION_MESSAGE);

							// Wenn der User Brüche errechnen möchte
						} else if (e.getSource() == berechnenButton && ROP_int_aktuell == 2) {
							double zaehler1 = Double.parseDouble(text1.split("/")[0]);
							double nenner1 = Double.parseDouble(text1.split("/")[1]);

							double zaehler2 = Double.parseDouble(text2.split("/")[0]);
							double nenner2 = Double.parseDouble(text2.split("/")[1]);

							if ((nenner1 == 0 || nenner2 == 0) || (zaehler2 == 0 && ROP.equals("geteilt"))) {
								JOptionPane.showMessageDialog(graphic, "Nullteilung ist nicht definiert!",
										"Nullteilung!", JOptionPane.ERROR_MESSAGE);
								return;
							}

							if ((zaehler1 == 0 || zaehler2 == 0) && (ROP.equals("mal"))) {
								JOptionPane.showMessageDialog(graphic, "Das Ergebnis ist: 0", "Ergebnisfenster",
										JOptionPane.INFORMATION_MESSAGE);
								return;
							}

							Bruch b1 = new Bruch(zaehler1, nenner1);
							Bruch b2 = new Bruch(zaehler2, nenner2);

							switch (ROP) {
							case "plus":
								bruchErgebnis = b1.add(b2);
								break;
							case "minus":
								bruchErgebnis = b1.sub(b2);
								break;
							case "mal":
								bruchErgebnis = b1.multi(b2);
								break;
							case "geteilt":
								bruchErgebnis = b1.div(b2);
								break;
							}

							// Das Ergebnis wird gerundet
							JOptionPane.showMessageDialog(graphic,
									"Das Ergebnis ist: " + r.runde(bruchErgebnis.getZaehler()) + " / "
											+ r.runde(bruchErgebnis.getNenner()),
									"Ergebnisfenster", JOptionPane.INFORMATION_MESSAGE);

							// Wenn der User komplexe Zahlen berechnen möchte
						} else if (e.getSource() == berechnenButton && ROP_int_aktuell == 3) {
							String komplex_zahl1 = text1;
							String komplex_zahl2 = text2;

							double realteil1 = 0;
							double imaginaerteil1 = 0;
							double realteil2 = 0;
							double imaginaerteil2 = 0;

							// Der User muss jede komplexe Zahl als a+ib eingeben, damit regex greifen kann
							Pattern p = Pattern.compile("([+-]?[0-9]+)([+-][0-9]+)");

							Matcher m1 = p.matcher(komplex_zahl1);
							Matcher m2 = p.matcher(komplex_zahl2);

							if (m1.find()) {

								realteil1 = Double.parseDouble(m1.group(1));
								imaginaerteil1 = Double.parseDouble(m1.group(2));
							}

							if (m2.find()) {
								realteil2 = Double.parseDouble(m2.group(1));
								imaginaerteil2 = Double.parseDouble(m2.group(2));
							}

							if (ROP.equals("geteilt") && realteil2 == 0 && imaginaerteil2 == 0) {
								JOptionPane.showMessageDialog(graphic, "Nullteilung ist nicht definiert!",
										"Nullteilung!", JOptionPane.ERROR_MESSAGE);
								return;
							} else {
								Komplex komp1 = new Komplex(realteil1, imaginaerteil1);
								Komplex komp2 = new Komplex(realteil2, imaginaerteil2);

								switch (ROP) {
								case "plus":
									kompErgebnis = komp1.plus(komp2);
									break;
								case "minus":
									kompErgebnis = komp1.minus(komp2);
									break;
								case "mal":
									kompErgebnis = komp1.mal(komp2);
									break;
								case "geteilt":
									kompErgebnis = komp1.geteilt(komp2);
									break;
								}

								JOptionPane.showMessageDialog(graphic,
										"Das Ergebnis ist: (" + r.runde(kompErgebnis.getKomplexRealteil()) + ") + ("
												+ r.runde(kompErgebnis.getKomplexImaginaerteil()) + ")i",
										"Ergebnisfenster", JOptionPane.INFORMATION_MESSAGE);
							}
						}

					} catch (Exception exep) {
						System.out.println(exep);
						JOptionPane.showMessageDialog(graphic,
								"Etwas ist falsch gelaufen! Klicke auf 'Bedienung' oben rechts, um Hilfe zu erhalten!",
								"Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

			}

		});

		// Titel des Taschenrechners oben links
		xsend.statusText("Taschenrechner 1.0");

		JMenu hilfeMenu = new JMenu("Info");
		graphic.addExternMenu(hilfeMenu);
		JMenuItem ueber = new JMenuItem("Ueber");
		ueber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(graphic,
						"Taschenrechner 1.0, programmiert von Metin Bozkurt und Benita Heid", "Ueber",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Bedienungshilfen
		JMenu bedienungsMenu = new JMenu("Bedienung");
		graphic.addExternMenu(bedienungsMenu);
		JMenuItem bedienung1 = new JMenuItem("Dezimal");
		JMenuItem bedienung2 = new JMenuItem("Bruch");
		JMenuItem bedienung3 = new JMenuItem("Komplex");

		bedienung1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(graphic,
						"Einfach eine Zahl in Zahlenfeld Zahl1 und Zahl2 eingeben, eine Rechenoperation auswählen und auf berechnen drücken!",
						"Bedienung", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		bedienung2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(graphic,
						"Beispiel für die Eingabe:  Zahl1: 7/3   Zahl2: 1/2   jetzt einfach eine Rechenoperation auswählen und auf berechnen drücken!",
						"Bedienung", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		bedienung3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(graphic,
						"Beispiel für die Eingabe:  Zahl1: 3+2i   Zahl2: 4-1i   jetzt einfach eine Rechenoperation auswählen und auf berechnen drücken!",
						"Bedienung", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		bedienungsMenu.add(bedienung1);
		bedienungsMenu.add(bedienung2);
		bedienungsMenu.add(bedienung3);
		hilfeMenu.add(ueber);

		graphic.pack();
		graphic.repaint();
	}

	// Wenn z.B. plus ausgewählt wird, wird alles auf grau gesetzt und plus auf grün
	@Override
	public void boardClick(BoardClickEvent info) {

		// Plus
		if (info.getX() == 0) {
			ROP = "plus";
			xsend.farben(XSendAdapter.LIGHTGRAY);
			xsend.farbe2(info.getX(), 0, XSendAdapter.GREEN);
		}

		// Minus
		else if (info.getX() == 1) {
			ROP = "minus";
			xsend.farben(XSendAdapter.LIGHTGRAY);
			xsend.farbe2(info.getX(), 0, XSendAdapter.GREEN);
		}

		// Mal
		else if (info.getX() == 2) {
			ROP = "mal";
			xsend.farben(XSendAdapter.LIGHTGRAY);
			xsend.farbe2(info.getX(), 0, XSendAdapter.GREEN);
		}

		// Geteilt
		else if (info.getX() == 3) {
			ROP = "geteilt";
			xsend.farben(XSendAdapter.LIGHTGRAY);
			xsend.farbe2(info.getX(), 0, XSendAdapter.GREEN);
		}

	}
}
