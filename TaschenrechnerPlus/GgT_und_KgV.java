package TaschenrechnerPlus;

public class GgT_und_KgV {
	int a, b, GgT, KgV, r, m;

	public GgT_und_KgV(int a, int b) {
		this.a = a;
		this.b = b;
		this.m = Math.abs(a * b);
	}

	public void berech() {
		if (a == 0) {
			GgT = b;
			KgV = 0;
		} else if (b == 0) {
			GgT = a;
			KgV = 0;
		} else {
			do {
				r = a % b;
				a = b;
				b = r;
			} while (r != 0);
			GgT = a;
			KgV = m / GgT;
		}
	}

	public int getGgT() {
		return GgT;
	}

	public int getKgV() {
		return KgV;
	}

}
