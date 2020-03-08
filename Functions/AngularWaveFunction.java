public class AngularWaveFunction {
	
	public static ComplexNumber Y(int l, int ml, double theta, double phi){
		
		switch(l){
		
		case 0:
			return f_0(ml, theta, phi);
		case 1:
			return f_1(ml, theta, phi);
		case 2:
			return f_2(ml, theta, phi);
		case 3:
			return f_3(ml, theta, phi);
		
		}
		
		return null;
		
	}
	
	private static ComplexNumber f_0(int ml, double theta, double phi){
		ComplexNumber ans = new ComplexNumber(1, 0);
		
		ans.multiply(new ComplexNumber(1/2.0*Math.sqrt(1/Math.PI), 0));
		
		return ans;
	}
	
	private static ComplexNumber f_1(int ml, double theta, double phi){
		ComplexNumber ans = new ComplexNumber(1, 0);
		
		if(ml==-1){
			ans.multiply(new ComplexNumber(1/2.0*Math.sqrt(3/Math.PI)*Math.sin(theta), 0));
			ans.multiply(e_ix(-phi));
		}else if(ml==0){
			ans.multiply(new ComplexNumber(1/2.0*Math.sqrt(3/Math.PI)*Math.cos(theta), 0));
		}else if(ml==1){
			ans.multiply(new ComplexNumber(-1/2.0*Math.sqrt(3/Math.PI)*Math.sin(theta), 0));
			ans.multiply(e_ix(phi));
		}
		
		return ans;
	}
	
	private static ComplexNumber f_2(int ml, double theta, double phi){
		ComplexNumber ans = new ComplexNumber(1, 0);
		
		if(ml==-2){
			ans.multiply(new ComplexNumber(1/4.0*Math.sqrt(15/(2*Math.PI))*Math.pow(Math.sin(theta),2), 0));
			ans.multiply(e_ix(-2*phi));
		}else if(ml==-1){
			ans.multiply(new ComplexNumber(1/2.0*Math.sqrt(15/(2*Math.PI))*Math.sin(theta)*Math.cos(theta), 0));
			ans.multiply(e_ix(-phi));
		}else if(ml==0){
			ans.multiply(new ComplexNumber(1/4.0*Math.sqrt(5/Math.PI)*(3*Math.pow(Math.cos(theta), 2) - 1), 0));
		}else if(ml==1){
			ans.multiply(new ComplexNumber(-1/2.0*Math.sqrt(15/(2*Math.PI))*Math.sin(theta)*Math.cos(theta), 0));
			ans.multiply(e_ix(phi));
		}else if(ml==2){
			ans.multiply(new ComplexNumber(1/4.0*Math.sqrt(15/(2*Math.PI))*Math.pow(Math.sin(theta),2), 0));
			ans.multiply(e_ix(2*phi));
		}
		
		return ans;
	}
	
	private static ComplexNumber f_3(int ml, double theta, double phi){
		ComplexNumber ans = new ComplexNumber(1, 0);
		
		if(ml==3){
			ans.multiply(new ComplexNumber(1/8.0*Math.sqrt(35/Math.PI)*Math.pow(Math.sin(theta),3), 0));
			ans.multiply(e_ix(-3*phi));
		}else if(ml==-2){
			ans.multiply(new ComplexNumber(1/4.0*Math.sqrt(105/(2*Math.PI))*Math.pow(Math.sin(theta),2)*Math.cos(theta), 0));
			ans.multiply(e_ix(-2*phi));
		}else if(ml==-1){
			ans.multiply(new ComplexNumber(1/8.0*Math.sqrt(21/Math.PI)*Math.sin(theta)*(5*Math.pow(Math.cos(theta),2)-1), 0));
			ans.multiply(e_ix(-phi));
		}else if(ml==0){
			ans.multiply(new ComplexNumber(1/4.0*Math.sqrt(7/Math.PI)*(5*Math.pow(Math.cos(theta), 3) - 3*Math.cos(theta)), 0));
		}else if(ml==1){
			ans.multiply(new ComplexNumber(-1/8.0*Math.sqrt(21/Math.PI)*Math.sin(theta)*(5*Math.pow(Math.cos(theta),2)-1), 0));
			ans.multiply(e_ix(phi));
		}else if(ml==2){
			ans.multiply(new ComplexNumber(1/4.0*Math.sqrt(105/(2*Math.PI))*Math.pow(Math.sin(theta),2)*Math.cos(theta), 0));
			ans.multiply(e_ix(2*phi));
		}else if(ml==3){
			ans.multiply(new ComplexNumber(-1/8.0*Math.sqrt(35/Math.PI)*Math.pow(Math.sin(theta),3), 0));
			ans.multiply(e_ix(3*phi));
		}
		
		return ans;
	}
	
	private static ComplexNumber e_ix(double x){
		return new ComplexNumber(Math.cos(x), Math.sin(x));
	}
	
}
