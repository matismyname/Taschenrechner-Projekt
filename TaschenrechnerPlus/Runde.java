package TaschenrechnerPlus;

public class Runde {
	
	public double runde(double r) {
		// Runde auf 5 Stellen genau
		return Math.round(r * 100000d) / 100000d;
	}
}