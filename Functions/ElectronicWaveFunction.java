
public class ElectronicWaveFunction {

	public static ComplexNumber psi(int n, int l, int ml, int Z, double r, double theta, double phi){
		
		ComplexNumber R = RadialWaveFunction.R(n, l, r, Z);
		
		ComplexNumber Y = AngularWaveFunction.Y(l, ml, theta, phi);
		
		return ComplexNumber.multiply(R, Y);
		
	}
	
}
