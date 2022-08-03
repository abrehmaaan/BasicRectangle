import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Rectangle {
	private double x, y, width, height;
	private int decPlaces;
	public Rectangle(){
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        decPlaces = 2;
    }
	public Rectangle(double x1, double y1, double x2, double y2){
		this.x = x1;
        this.y = y1;
        this.width = Math.abs(x2-x1);
        this.height = Math.abs(y2-y1);
        decPlaces = 2;
        if(x1>x2) {
        	this.x = x2;
        }
        if(y1>y2) {
        	this.y = y2;
        }
    }
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getWidth() {
		return String.format("%."+decPlaces+"f", round(width, decPlaces));
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public String getHeight() {
		return String.format("%."+decPlaces+"f", round(height, decPlaces));
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	public int getDecPlaces() {
		return decPlaces;
	}
	public void setDecPlaces(int decPlaces) {
		this.decPlaces = decPlaces;
	}
	public String area(){
		return String.format("%."+decPlaces+"f", round(height*width, decPlaces));
    }
    public String perimeter(){
    	return String.format("%."+decPlaces+"f", round(2*(width + height), decPlaces));
    }
    public String diagonalLength() {
    	return String.format("%."+decPlaces+"f", round(Math.sqrt(height*height + width*width), decPlaces));
    }
    public void deltaMove(double x, double y) {
    	this.x+=x;
    	this.y+=y;
    }
    public void scale(double scaleFactor) {
    	width*=scaleFactor;
    	height*=scaleFactor;
    }
    public String toString() {
    	return String.format("Rectangle(%."+decPlaces+"f, %."+decPlaces+"f, %."+decPlaces+"f, %."+decPlaces+"f)", x,y,width,height);
    }
    public String showLeftCorner() {
    	return String.format("(%."+decPlaces+"f, %."+decPlaces+"f)", x,y);
    }
}
public class TestRectangle{
    public static void main(String[] args){
    	Map<String, Rectangle> n = new HashMap<String, Rectangle>();
        Scanner scanner = new Scanner(System.in);
        int dec = 2;
        
        while (scanner.hasNextLine()){
            List<String> tokens = new ArrayList<String>();
            Scanner lineScanner = new Scanner(scanner.nextLine());
 
            while (lineScanner.hasNext()) {
                tokens.add(lineScanner.next());
            }
            if(tokens.isEmpty()) {
            	break;
            }
            else if(tokens.get(0).equals("createWithTwoPoints")) {
            	n.put(tokens.get(1), new Rectangle(Double.parseDouble(tokens.get(2)),Double.parseDouble(tokens.get(3)),Double.parseDouble(tokens.get(4)),Double.parseDouble(tokens.get(5))));
            	n.get(tokens.get(1)).setDecPlaces(dec);
            }
            else if(tokens.get(0).equals("createWithLowerLeft")) {
            	n.put(tokens.get(1), new Rectangle(Double.parseDouble(tokens.get(2)),Double.parseDouble(tokens.get(3)),Double.parseDouble(tokens.get(2))+Double.parseDouble(tokens.get(4)),Double.parseDouble(tokens.get(3))+Double.parseDouble(tokens.get(5))));
            	n.get(tokens.get(1)).setDecPlaces(dec);
            }
            else if(tokens.get(0).equals("show")) {
            	System.out.println(tokens.get(1)+" = "+n.get(tokens.get(1)).toString());
            }
            else if(tokens.get(0).equals("showWidth")) {
            	System.out.println("width("+tokens.get(1)+") = "+n.get(tokens.get(1)).getWidth());
            }
            else if(tokens.get(0).equals("showHeight")) {
            	System.out.println("height("+tokens.get(1)+") = "+n.get(tokens.get(1)).getHeight());
            }
            else if(tokens.get(0).equals("showArea")) {
            	System.out.println("area("+tokens.get(1)+") = "+n.get(tokens.get(1)).area());
            }
            else if(tokens.get(0).equals("showPerimeter")) {
            	System.out.println("perimeter("+tokens.get(1)+") = "+n.get(tokens.get(1)).perimeter());
            }
            else if(tokens.get(0).equals("showDiagonalLength")) {
            	System.out.println("diagonalLength("+tokens.get(1)+") = "+n.get(tokens.get(1)).diagonalLength());
            }
            else if(tokens.get(0).equals("showLowerLeftCorner")) {
            	System.out.println("lowerLeftCorner("+tokens.get(1)+") = "+n.get(tokens.get(1)).showLeftCorner());
            }
            else if(tokens.get(0).equals("deltaMove")) {
            	n.get(tokens.get(1)).deltaMove(Double.parseDouble(tokens.get(2)),Double.parseDouble(tokens.get(3)));
            }
            else if(tokens.get(0).equals("scale")) {
            	n.get(tokens.get(1)).scale(Double.parseDouble(tokens.get(2)));
            }
            else if(tokens.get(0).equals("decimal")) {
            	for (Map.Entry<String,Rectangle> entry : n.entrySet())
            		entry.getValue().setDecPlaces(Integer.parseInt(tokens.get(1)));
            	dec = Integer.parseInt(tokens.get(1));
            }
            lineScanner.close();
            
        }
        scanner.close();
    }
}
