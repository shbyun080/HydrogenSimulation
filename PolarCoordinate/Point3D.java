import javafx.scene.shape.Sphere;

public class Point3D extends Sphere{

	double x;
	double y;
	double z;
	double prob;
	
	public Point3D(double x, double y, double z, double prob){
		super(1);
		this.x = x;
		this.y = y;
		this.z = z;
		this.prob = prob;
	}
	
}