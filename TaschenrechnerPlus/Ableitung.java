package TaschenrechnerPlus;
import TaschenrechnerPlus.Runde;

public class Ableitung{
	double Abs[] = new double[8];
	Runde r = new Runde();
	public Ableitung(double arr[]) {
		this.Abs = arr;
	}
    
	//Einfache Summenregel für Ableitungen angewandt
	//Das Array mit den abgeleiteten Koeffizienten wird zurückgegeben
    public double[] ableite() {
    	int n = 7;
    	for(int i = 0; i<8; i++) {
    		Abs[i] = r.runde((Abs[i] * n));
    		n -= 1;
    	}
		return Abs;
    }
}