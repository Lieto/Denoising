package model;



public class Function implements IFunction {
	
	// Value of input for function
	public double input = 0.0;
	
	//Value of output for function
	public double output = 0;

	@Override
	public void setInput(double input) {
		
		this.input = input;
	}

	@Override
	public double getOutput() {
		
		return output;
	}

	@Override
	public  double solve() {
		
		return 0;
		
	}

}
