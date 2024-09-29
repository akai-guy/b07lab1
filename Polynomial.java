import java.util.Scanner;
import java.io.FileNotFoundException;  
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Polynomial{
	double[] non_zero_coefficients;
	int[] exponents;
	
	public Polynomial(){
		non_zero_coefficients = new double[]{};
		exponents = new int[]{};
	}
	
	
	public Polynomial(double[] values){
		int non_zero_count = 0;
		for(int i = 0;i<values.length;i++) {
			if(values[i]!=0) {
				non_zero_count++;
			}
		}

		non_zero_coefficients = new double[non_zero_count];
		exponents = new int[non_zero_count];
		int k=0;
		for(int j=0;j<values.length;j++){
			if(values[j]!=0) {
				non_zero_coefficients[k] = values[j];
				exponents[k] = j;
				k++;
			}
		}
		
	}
	
	public Polynomial(File input_file) {
		try {
			Scanner input = new Scanner(input_file);
			String poly_string = input.nextLine();
			String[] poly_split_string = poly_string.split("-");

			String[] split_x = poly_string.split("x");
			int length = Integer.parseInt(split_x[split_x.length - 1])+1;
			double[] all_coefficients = new double[length];
					
			for(int i=0;i<poly_split_string.length;i++) {
				if(i>0) {
					String new_subtraction = "+-".concat(poly_split_string[i]);
					poly_split_string[i] = new_subtraction;
				}
			}

			poly_string = String.join("",poly_split_string);
			poly_split_string = poly_string.split("\\+");
			
			//for(int k=0;k<poly_split_string.length;k++) {
			//	System.out.println(poly_split_string[k]);
			//}
			String[] term;
			
			for(int j=0;j<poly_split_string.length;j++) {
				term = poly_split_string[j].split("x");
				double coefficient = Double.parseDouble(term[0]);
				int power;
				if(poly_split_string[j].charAt(poly_split_string[j].length()-1)=='x') {
					power = 1;
				}
				else if (term.length==1) {
					power = 0;
				}
				else {
					power = Integer.parseInt(term[1]);
				}
				all_coefficients[power] = coefficient;
				//System.out.println(j+", "+ power+", "+ coefficient);
			}
			
			Polynomial placeholder = new Polynomial(all_coefficients);
			non_zero_coefficients = placeholder.non_zero_coefficients;
			exponents = placeholder.exponents;
		}
		catch(FileNotFoundException e) {
			System.out.println("Error: File not found!");
			return;
		}
		
	}
	
	public Polynomial add(Polynomial operand){
		int length = Math.max(exponents[exponents.length - 1], operand.exponents[operand.exponents.length - 1])+1;
		double[] sum_coefficients= new double[length];
		for (int i = 0;i<length;i++){
			for(int j = 0;j<exponents.length;j++) {
				if(exponents[j] == i){
					sum_coefficients[i] = sum_coefficients[i] + non_zero_coefficients[j];

				}
			}
			for(int k = 0;k<operand.exponents.length;k++) {
				if(operand.exponents[k] == i){
					sum_coefficients[i] = sum_coefficients[i] + operand.non_zero_coefficients[k];
				}
			}
		}
		Polynomial sum = new Polynomial(sum_coefficients);
		return sum;
	}
	
	public double evaluate(double value){
		double sum = 0;
		for (int i = 0;i<exponents.length;i++){
			sum += Math.pow(value, exponents[i]) * non_zero_coefficients[i];
		}

		return sum;
	}
	
	public boolean hasRoot(double value){
		return (evaluate(value) == 0);
	}
	
	public Polynomial multiply(Polynomial operand) {
		int length = exponents[exponents.length - 1] + operand.exponents[operand.exponents.length - 1] + 1;
		double[] product_coefficients= new double[length];
		for (int i = 0;i<exponents.length;i++){
			for(int j = 0;j<operand.exponents.length;j++) {
				int prod_exponent = exponents[i]+operand.exponents[j];
				product_coefficients[prod_exponent] = product_coefficients[prod_exponent] + non_zero_coefficients[i]*operand.non_zero_coefficients[j];
			}
		}
		Polynomial product = new Polynomial(product_coefficients);
		return product;
	}
	
	public void saveToFile(String poly_string) {
		String store_poly = "";
		if(non_zero_coefficients.length >= 1) {
			if (non_zero_coefficients[0]%1==0) {
				store_poly = String.valueOf((int)Math.round(non_zero_coefficients[0]));
			}
			else {
				store_poly = String.valueOf(non_zero_coefficients[0]);
			}
		}
		
		for (int i = 1;i<non_zero_coefficients.length;i++) {
			int exponent = exponents[i];
			if (non_zero_coefficients[i]%1==0) {
				int coefficient = (int)Math.round(non_zero_coefficients[i]);
				
				if(exponent==1) {
					if(non_zero_coefficients[i]<0 ) {
						store_poly = store_poly + String.valueOf(coefficient)+"x";
					}
					else{
						store_poly = store_poly + "+" + String.valueOf(coefficient)+"x";
					}
				}
				else {
					if(non_zero_coefficients[i]<0 ) {
						store_poly = store_poly + String.valueOf(coefficient)+"x"+String.valueOf(exponent);
					}
					else{
						store_poly = store_poly + "+" + String.valueOf(coefficient)+"x"+String.valueOf(exponent);
					}
				}
			}
			else{ 
				double coefficient = non_zero_coefficients[i];
				
				if(exponent==1) {
					if(non_zero_coefficients[i]<0 ) {
						store_poly = store_poly + String.valueOf(coefficient)+"x";
					}
					else{
						store_poly = store_poly + "+" + String.valueOf(coefficient)+"x";
					}
				}
				else {
					if(non_zero_coefficients[i]<0 ) {
						store_poly = store_poly + String.valueOf(coefficient)+"x"+String.valueOf(exponent);
					}
					else{
						store_poly = store_poly + "+" + String.valueOf(coefficient)+"x"+String.valueOf(exponent);
					}
				}
			}
		}
		
		try {
	    	FileWriter myWriter = new FileWriter(poly_string);
			myWriter.write(store_poly);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
	    } 
	    catch (IOException e) {
	    	System.out.println("An error occurred in writing file.");
			e.printStackTrace();
			      
		}
		
	}
	
	
}