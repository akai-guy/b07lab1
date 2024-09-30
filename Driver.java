
import java.io.FileWriter;
import java.io.FileNotFoundException;  
import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) {
		try {
			File myObj = new File("polystring.txt");
		      
		    if (myObj.createNewFile()) {
		    	System.out.println("File created: " + myObj.getName());
		    } 
		    else {
		   	    System.out.println("File already exists.");		      
		    }
		    try {
		    	FileWriter myWriter = new FileWriter("polystring.txt");
				myWriter.write("5+x+5x3-x4");
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
				Polynomial p = new Polynomial(myObj);
				System.out.println(p.non_zero_coefficients[0]);
				System.out.println(p.non_zero_coefficients[1]);
				System.out.println(p.non_zero_coefficients[2]);
				System.out.println(p.non_zero_coefficients[3]);
				if(p.evaluate(2)==31){
					System.out.println("File constructor and eval fcn are accurate");
				}
				else{
					System.out.println("File constructor and eval fcn are inaccurate");
				}
		    } 
		    catch (IOException e) {
		    	System.out.println("An error occurred in writing file.");
				e.printStackTrace();
				      
			}
		}
		catch (IOException e) {
			System.out.println("An error occurred when creating file.");
		    e.printStackTrace();
		}
		
		
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,9,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,6};
		Polynomial p2 = new Polynomial(c2);

		Polynomial s = p1.add(p2);
		
		System.out.println(s.non_zero_coefficients[0]);
		System.out.println(s.non_zero_coefficients[1]);
		System.out.println(s.non_zero_coefficients[2]);
		
		System.out.println("s(0) = " + s.evaluate(0));
		if(s.hasRoot(0)){
			System.out.println("1 is a root of s");
		}
		else{
			System.out.println("1 is not a root of s");
		}

		double [] c3 = {1,2,1};
		Polynomial p3 = new Polynomial(c3);
		double [] c4 = {1,4};
		Polynomial p4 = new Polynomial(c4);
		Polynomial p5 = p3.multiply(p4);
		
		if(p5.non_zero_coefficients[0]==1 && 
		   p5.non_zero_coefficients[1]==6 &&
		   p5.non_zero_coefficients[2]==9 && 
		   p5.non_zero_coefficients[3]==4){
			System.out.println("Poly multiplication is accurate");
		}
		else{
			System.out.println("Poly multiplication is inaccurate");
		}
		
		try {
			File myObj2 = new File("polystring2.txt");
		    
		    if (myObj2.createNewFile()) {
		    	System.out.println("File created: " + myObj2.getName());
		    } 
		    else {
		   	    System.out.println("File already exists.");		      
		    }
		    System.out.println(myObj2.getAbsolutePath());
		    double [] c_save = {1,-2.5,3, 4, 5, -6.7, -8, 9.1};
			Polynomial p_save = new Polynomial(c_save);
			p_save.saveToFile("polystring2.txt");
			
			Polynomial p_get = new Polynomial(myObj2);
			System.out.println(p_get.non_zero_coefficients[0]);
			System.out.println(p_get.non_zero_coefficients[1]);
			System.out.println(p_get.non_zero_coefficients[2]);
			System.out.println(p_get.non_zero_coefficients[3]);
			System.out.println(p_get.non_zero_coefficients[4]);
			System.out.println(p_get.non_zero_coefficients[5]);
			System.out.println(p_get.non_zero_coefficients[6]);
			System.out.println(p_get.non_zero_coefficients[7]);
			

		    double [] c_save2 = {-4, 1, -1};
			Polynomial p_save2 = new Polynomial(c_save2);
			p_save2.saveToFile("polystring2.txt");
			
		    
		}
		catch (IOException e) {
			System.out.println("An error occurred when creating file.");
		    e.printStackTrace();
		}
		
	}
}