package Taschenrechner;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import NormalerTaschenrechner.Hauptklasse;
import TaschenrechnerPlus.Menu_TRP;

public class OHK {

	public static Color azure2 = new Color(224, 238, 238); // Farbe für das Fenster
	static Hauptklasse hpt = new Hauptklasse();
	static Menu_TRP trp = new Menu_TRP();
	static JFrame f = new JFrame();

	static ImageIcon logo = new ImageIcon(OHK.class.getResource("/Logo.png")); // Selbstkreirtes Logo auf dem
										// Startbildschirm

	public static void main(String[] args) {
		OHK.starten();
	}

	public static void starten() {
		f.setSize(680, 650); // Größe des Fensters
		f.setLocationRelativeTo(null); // Zentriert des Fenster
		f.setResizable(false); // Fenstergröße kann nicht vom User verändert werden
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Schließt das Fenster, wenn man auf X klickt
		f.setTitle("Willkommen zum Taschenrechner 2.0!");

		JPanel p = new JPanel(new GridBagLayout());
		JButton knopf = new JButton("Taschenrechner");
		JButton knopf2 = new JButton("Taschenrechner +");
		JLabel label = new JLabel(logo); // Logo in der Mitte des Fenster

		// GridBagLayout wird benutzt, um Komponenten an passende Stellen
		// einfügen zu können, z.b. Knöpfe unten
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.SOUTH;
		c.gridx = 0;
		c.gridy = 0;

		p.add(knopf, c);

		c.gridx = 3;
		c.gridy = 0;
		p.add(knopf2, c);

		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 40;
		c.weighty = 20;
		c.gridx = 1;
		c.gridy = 0;

		p.add(label, c);

		// Wenn Taschenrechner angeklickt wird, wird das Hauptmenü geschlossen
		knopf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hpt.starten();
				schließen();
			}

		});

		knopf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu_TRP.starten(); // Menü Taschenrechner +
				schließen();
			}

		});

		p.setBackground(azure2);
		f.getContentPane().setBackground(azure2);
		f.add(p);
		f.setVisible(true);
	}

	public static void schließen() {
		f.setVisible(false);
		f.dispose();
	}
}
