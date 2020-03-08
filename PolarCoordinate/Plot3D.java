import java.util.ArrayList;

import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Plot3D{

	private ArrayList<Point3D> points;
	
	double max = 0;
	double min = 1;
	
	double maxRad = 0;
	
	public Plot3D(){
		points = new ArrayList<Point3D>();
	}
	
	public void add(double r, double theta, double phi, double prob){
		double x = r*Math.sin(phi)*Math.cos(theta);
		double y = r*Math.sin(phi)*Math.sin(theta);
		double z = r*Math.cos(phi);
		points.add(new Point3D(x, y, z, prob));
		if(prob>max)
			max = prob;
		if(prob<min)
			min = prob;
//		if(r>maxRad)
//			maxRad = r;
	}
	
	public void addcart(double x, double y, double z, double prob){
		points.add(new Point3D(x, y, z, prob));
		if(prob>max)
			max = prob;
		if(prob<min)
			min = prob;
//		if(r>maxRad)
//			maxRad = r;
	}
	
	public void reset(){
		points = new ArrayList<Point3D>();
	}
	
	public double plot(Group group, int scale){
		for(Point3D p: points){
			if(p.prob<max/50)
				continue;
			double r = Math.sqrt(p.x*p.x+p.y*p.y+p.z*p.z);
			if(r>maxRad){
				maxRad = r;
			}
		}
		for(Point3D p: points){
			if(p.prob<max/50)
				continue;
			PhongMaterial material = new PhongMaterial(getColor(p.prob));
			p.setMaterial(material);
			p.setTranslateX(p.x/maxRad*scale);
			p.setTranslateY(p.y/maxRad*scale);
			p.setTranslateZ(p.z/maxRad*scale);
		}
		AmbientLight light = new AmbientLight(Color.WHITE);
		light.setTranslateZ(100);
		light.setTranslateX(400);
		light.setVisible(false);
		group.getChildren().addAll(points);
		//group.getChildren().add(light);
		
		//Remove Later and Switch method to void
		return maxRad;
	}
	
	private Color getColor(double prob){
		double x = 1-(prob-min)/(max-min);
		return Color.hsb(200*x, 0.5, 0.5, (x>0.2) ? 1.2-x:1);
	}
	
}
