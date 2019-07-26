package NormalerTaschenrechner;

import jserver.Board;
import jserver.XSendAdapter;
import plotter.Graphic;

//Easter Egg Klasse, die Frohe Weihnachten in BoS ausgibt
public class Weihnachten {
	Board board;
	Graphic graphic;
	XSendAdapter xsend;

	private String[] str_feld = { "F", "R", "O", "H", "E", "W", "E", "I", "H", "N", "A", "C", "H", "T", "E", "N" };

	int x = 7;
	int x_zaehl = x;
	int y = 10;

	public Weihnachten() {

	}

	public void start() {
		xsend = new XSendAdapter();
		board = xsend.getBoard();
		xsend.groesse(15, 12);
		xsend = new XSendAdapter(board);
		graphic = board.getGraphic();

		for (int i = 0; i < 7; i++) {
			while (x_zaehl < 8) {
				xsend.farbe2(x_zaehl, y, XSendAdapter.GREEN);
				x_zaehl += 1;
			}

			y -= 1;
			x -= 1;
			x_zaehl = x;
		}

		x_zaehl = 8;
		y = 9;

		for (int i = 0; i < 6; i++) {
			while (x_zaehl > 7) {
				xsend.farbe2(x_zaehl, y, XSendAdapter.GREEN);
				x_zaehl -= 1;
			}

			y -= 1;
			x_zaehl = 9 + i;
		}

		xsend.farbe2(7, 2, XSendAdapter.BROWN);
		xsend.farbe2(7, 3, XSendAdapter.BROWN);

		for (int i = 0; i < str_feld.length; i++) {
			if (i < 5) {
				xsend.text2(i, 1, str_feld[i]);
			} else {
				xsend.text2(i - 1, 0, str_feld[i]);
			}
		}
	}
}
