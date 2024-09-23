public class Polynomial{
	double[] coefficients;
	
	//constructors, method overloading (2 constructors, only requires diff parameters)
	public Polynomial(){
		coefficients = new double[]{0};
	}
	
	public Polynomial(double[] values){
		coefficients = values;
	}
	
	// method declaration has return type (String) before method name
	public Polynomial add(Polynomial compare){
		int length = coefficients.length;
		if(compare.coefficients.length > length){
			length = compare.coefficients.length;
		}
		double[] sum_coefficients= new double[length];

		for (int i = 0;i<length;i++){
			if(coefficients.length <= i){
				sum_coefficients[i] = compare.coefficients[i];
			}
			else if(compare.coefficients.length <= i){
				sum_coefficients[i] = coefficients[i];
			}
			else{
				sum_coefficients[i] = coefficients[i] + compare.coefficients[i];
			}
		}
		Polynomial sum = new Polynomial(sum_coefficients);
		return sum;
	}
	
	public double evaluate(double value){
		double sum = 0;
		for (int i = 0;i<coefficients.length;i++){
			sum += Math.pow(value, i) * coefficients[i];
		}

		return sum;
	}
	
	public boolean hasRoot(double value){
		return (evaluate(value) == 0);
	}
}
