package TaschenrechnerPlus;
import TaschenrechnerPlus.Runde;

public class Nullstellen1 {
	double a;
	double c;
	Runde r = new Runde();
	
	public Nullstellen1(double a, double c) {
		this.a = a;
		this.c = c;
	}
	
	public double berech1() {
		double erg = -c/a;
		return r.runde(erg);
	}
}
