import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class PolarOrbitalSimulationApp extends Application{
	
	//Rotation Transformation Variables
	Rotate rx;
	Rotate ry;
	boolean rotate = false;
	double x;
	double y;
	
	//Quantum Number Variables
	int d = 1; // 1 is 2D, 2 is 3D, 3 is 3D detailed
	int n = 1;
	int l = 0;
	int m = 0;
	int phi = 0;
	
	//Main Pane
	private BorderPane pane;
	
	//Error Message
	private Label errorMessage;
	
	private StackPane currpane;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Orbital Simulation");
		
		pane = new BorderPane();
		Scene scene = new Scene(pane, 800, 600);
		stage.setScene(scene);
		stage.show();
		
		errorMessage = new Label("Input Error!");
		errorMessage.textFillProperty().set(Color.RED);
		errorMessage.setPadding(new Insets(10,10,0,10));
		
		VBox optionPane = new VBox();
		optionPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		ToggleGroup dimensionGroup = new ToggleGroup();
		ToggleButton d2_Button = new ToggleButton("2D");
		ToggleButton d3_Button = new ToggleButton("3D");
		ToggleButton d3d_Button = new ToggleButton("3D Detailed");
		dimensionGroup.getToggles().addAll(d2_Button, d3_Button, d3d_Button);
		HBox buttonPane = new HBox();
		buttonPane.getChildren().addAll(d2_Button, d3d_Button);
//		buttonPane.getChildren().addAll(d2_Button, d3_Button, d3d_Button);
		optionPane.getChildren().addAll(buttonPane);
		buttonPane.setAlignment(Pos.TOP_CENTER);
		buttonPane.setPadding(new Insets(10,10,0,10));
		
		HBox nBox = new HBox();
		Label nLabel = new Label("n:");
		TextField nField = new TextField(String.valueOf(n));
		nBox.getChildren().addAll(nLabel, nField);
		optionPane.getChildren().add(nBox);
		nBox.setPadding(new Insets(20,10,0,10));
		nField.setAlignment(Pos.CENTER_RIGHT);
		
		HBox lBox = new HBox();
		Label lLabel = new Label("l: ");
		TextField lField = new TextField(String.valueOf(l));
		lBox.getChildren().addAll(lLabel, lField);
		optionPane.getChildren().add(lBox);
		lBox.setPadding(new Insets(30,10,0,10));
		lField.setAlignment(Pos.CENTER_RIGHT);
		
		HBox mBox = new HBox();
		Label mLabel = new Label("m:");
		TextField mField = new TextField(String.valueOf(m));
		mBox.getChildren().addAll(mLabel, mField);
		optionPane.getChildren().add(mBox);
		mBox.setPadding(new Insets(30,10,0,10));
		mField.setAlignment(Pos.CENTER_RIGHT);
		
		HBox phiBox = new HBox();
		Label phiLabel = new Label("φ:");
		TextField phiField = new TextField(String.valueOf(m));
		phiBox.getChildren().addAll(phiLabel, phiField);
		phiBox.setPadding(new Insets(30,10,0,10));
		phiField.setAlignment(Pos.CENTER_RIGHT);
		if(d == 1) {
			optionPane.getChildren().add(phiBox);
		}
		
		HBox graphButtonBox = new HBox();
		Button graphButton = new Button("Graph");
		graphButtonBox.getChildren().add(graphButton);
		optionPane.getChildren().add(graphButtonBox);
		graphButtonBox.setAlignment(Pos.BOTTOM_CENTER);
		graphButtonBox.setPadding(new Insets(50, 10, 0, 10));
		graphButton.setOnAction(e -> {
			if(optionPane.getChildren().contains(errorMessage))
				optionPane.getChildren().remove(errorMessage);
			try {
				int nt = Integer.parseInt(nField.getText());
				int lt = Integer.parseInt(lField.getText());
				int mt = Integer.parseInt(mField.getText());
				if(!(nt<5 && (lt>=0 && lt<nt && l<4) && (mt>=-lt && mt<=lt)))
					throw(new Exception());
				n = nt;
				l = lt;
				m = mt;
				if(d == 1) {
					plot2D();
				}else if(d == 2) {
					
				}else if(d == 3) {
					plot3DDetailed();
				}
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
				optionPane.getChildren().add(errorMessage);
				return;
			}
		});
	
		Label warningMessaged3d = new Label("Warning:\n3D Detailed might take\nsignificantly longer time to\ncalculate and hard to interpret.\nIn case of crash, please\nrestart the program.");
		warningMessaged3d.setTextFill(Color.RED);
		warningMessaged3d.setPadding(new Insets(0,0,0,5));
		
		d2_Button.setOnAction(e -> {
			d = 1;
			if(optionPane.getChildren().contains(warningMessaged3d))
				optionPane.getChildren().remove(warningMessaged3d);
			if(!optionPane.getChildren().contains(phiBox))
				optionPane.getChildren().add(optionPane.getChildren().indexOf(graphButtonBox), phiBox);
		});
		d3_Button.setOnAction(e -> {
			d = 2;
			if(optionPane.getChildren().contains(phiBox))
				optionPane.getChildren().remove(phiBox);
			if(optionPane.getChildren().contains(warningMessaged3d))
				optionPane.getChildren().remove(warningMessaged3d);
		});
		d3d_Button.setOnAction(e -> {
			d = 3;
			if(optionPane.getChildren().contains(phiBox))
				optionPane.getChildren().remove(phiBox);
			if(!optionPane.getChildren().contains(warningMessaged3d))
				optionPane.getChildren().add(warningMessaged3d);
		});
		
		pane.setRight(optionPane);
		
	}
	
	private void plot2D() throws Exception{
		currpane = null;
		System.gc();
		Group g = new Group();
		g.setLayoutX(600);
		g.setLayoutY(600);
		
		Plot3D p = new Plot3D();
		
		//Cartesian Basis
		for(double x = -1.5*Math.pow(10, -9); x<1.5*Math.pow(10, -9); x+=Math.pow(10, -11)){
			for(double y = -1.5*Math.pow(10, -9); y<1.5*Math.pow(10, -9); y+=Math.pow(10, -11)){
				double r = Math.sqrt(x*x+y*y);
				double theta = Math.atan(y/x);
				if(x<0)
					theta += Math.PI;
				//p.add(r, theta, Math.PI/2, ProbabilityDensity.probability(n, l, m, 1, r, theta, phi));
				p.addcart(x, y, 0, ProbabilityDensity.probability(n, l, m, 1, r, theta, phi));
			}
		}
		
		//Generate spheres
		double maxRad = p.plot(g, 200);
		//For Data collection - Delete Later
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ReferenceDatas/BoundaryDataCollection")));
			out.println(n+", "+l+", "+m+": "+maxRad);
			out.close();
		}catch(IOException e) {
			System.exit(0);
		}
		
		StackPane simulPane = new StackPane();
		simulPane.getChildren().add(g);
		simulPane.setAlignment(Pos.CENTER);
		
		currpane = simulPane;
		
		pane.setCenter(currpane);
	}
	
	//Unstable and long calculations
	private void plot3DDetailed() throws Exception{
		currpane=null;
		System.gc();
		//Start Plotting
		
		Group g = new Group();
		g.setLayoutX(600);
		g.setLayoutY(600);
		
		Plot3D p = new Plot3D();
		
		System.out.println(n+" "+l+" "+m);
		
		//Cartesian Basis
		for(double z = -1.5*Math.pow(10, -9); z<1.5*Math.pow(10, -9); z+=5*Math.pow(10, -10)){
			for(double x = -1.5*Math.pow(10, -9); x<1.5*Math.pow(10, -9); x+=Math.pow(10, -11)){
				for(double y = -1.5*Math.pow(10, -9); y<1.5*Math.pow(10, -9); y+=Math.pow(10, -11)){
					double r = Math.sqrt(x*x+y*y);
					double theta = Math.atan(y/x);
					double phi = Math.atan(Math.sqrt(x*x+y*y)/z);
					if(z<0)
						phi+= Math.PI;
					if(x<0)
						theta += Math.PI;
					p.add(r, theta, phi, ProbabilityDensity.probability(n, l, m, 1, r, theta, phi));
					//p.add(r, theta, Math.PI/2, ProbabilityDensity.probability(n, l, m, 1, r, theta, Math.PI/2));
				}
			}
		}
		
		//Generate spheres
		p.plot(g, 200);
		
		/* START DRAG ROTATE EVENT */
		
//		rx = new Rotate();
//		rx.setAxis(Rotate.X_AXIS);
//		ry = new Rotate();
//		ry.setAxis(Rotate.Y_AXIS);
//		x = 0;
//		y = 0;
//		
//		g.getTransforms().addAll(rx, ry);
//		
//		g.setOnMouseDragged((e) -> {
//			double dx = e.getX()-x;
//			double dy = e.getY()-y;
//			rx.setAngle(rx.getAngle()-dy);
//			ry.setAngle(ry.getAngle()-dx);
//			System.out.println(dy);
//			System.out.println(dx);
//			x = e.getX();
//			y = e.getY();
//		});
//		
//		g.setOnMousePressed((e) -> {
//			rotate = true;
//			x = e.getX();
//			y = e.getY();
//		});
//		
//		g.setOnMouseReleased((e) -> {
//			rotate = false;
//		});
//		
		/* END DRAG ROTATE EVENT */
		
		StackPane simulPane = new StackPane();
		simulPane.getChildren().add(g);
		simulPane.setAlignment(Pos.CENTER);
		
		currpane = simulPane;
		
		pane.setCenter(currpane);
		
	}
	
	public void launchApp(String[] args){
		launch(args);
	}
	
}
