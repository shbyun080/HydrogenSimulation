
public class RadialWaveFunction {

	//bohr radius in meters
	private static final double a = 5.29177*Math.pow(10, -11);
	
	public static ComplexNumber R(int n, int l, double r, int Z){
		
		double p = 2*Z*r/(a*n);
		
		switch(n) {
		
		case 1:
			return f_1(l, r, Z, p);
		case 2:
			return f_2(l, r, Z, p);
		case 3:
			return f_3(l, r, Z, p);
		case 4:
			return f_4(l, r, Z, p);
		case 5:	
			return f_5(l, r, Z, p);
			
		}
		
		return null;
		
	}
	
	private static ComplexNumber f_1(int l, double r, int Z, double p){
		double ans = Math.pow(Math.E, -p/2);
		ans *= 2;
		return new ComplexNumber(ans, 0);
	}
	
	private static ComplexNumber f_2(int l, double r, int Z, double p){
		double ans = Math.pow(Math.E, -p/2);
		if(l==0)
			ans *= 1/(2*Math.sqrt(2))*(2-p);
		else if(l==1)
			ans *= 1/(2*Math.sqrt(6))*p;
		return new ComplexNumber(ans, 0);
	}
	
	private static ComplexNumber f_3(int l, double r, int Z, double p){
		double ans = Math.pow(Math.E, -p/2);
		if(l==0)
			ans *= 1/(9*Math.sqrt(3))*(6-6*p+p*p);
		else if(l==1)
			ans *= 1/(9*Math.sqrt(6))*(4-p)*p;
		else if(l==2)
			ans *= 1/(9*Math.sqrt(30))*p*p;
		return new ComplexNumber(ans, 0);
	}
	
	private static ComplexNumber f_4(int l, double r, int Z, double p){
		double ans = Math.pow(Math.E, -p/2);
		if(l==0)
			ans *= 1/96.0*(24-36*p+12*p*p-p*p*p);
		else if(l==1)
			ans *= 1/(32*Math.sqrt(15))*(20-10*p+p*p)*p;
		else if(l==2)
			ans *= 1/(96*Math.sqrt(5))*(6-p)*p*p;
		else if(l==3)
			ans *= 1/(96*Math.sqrt(35))*p*p*p;
		return new ComplexNumber(ans, 0);
	}
	
	private static ComplexNumber f_5(int l, double r, int Z, double p){
		double ans = Math.pow(Math.E, -p/2);
		if(l==0)
			ans *= 1/(300*Math.sqrt(5))*(120-240*p+120*p*p-20*p*p*p+p*p*p*p);
		else if(l==1)
			ans *= 1/(150*Math.sqrt(30))*(120-90*p+18*p*p-p*p*p)*p;
		else if(l==2)
			ans *= 1/(150)*(42-14*p+p*p)*p*p;
		else if(l==3)
			ans *= 1/(300*Math.sqrt(70))*(8-p)*p*p*p;
		else if(l==4)
			ans *= 1/(900*Math.sqrt(70))*p*p*p*p;
		return new ComplexNumber(ans, 0);
	}
	
}
