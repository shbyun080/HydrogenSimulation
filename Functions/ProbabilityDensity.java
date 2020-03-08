import java.io.IOException;

public class ProbabilityDensity {
	
	public static double probability(int n, int l, int ml, int Z, double r, double theta, double phi)throws IOException{
		
		ComplexNumber psi = ElectronicWaveFunction.psi(n, l, ml, Z, r, theta, phi);
		
		return ComplexNumber.multiply(psi, psi.conjugate()).getRe();
		
	}
	
}
