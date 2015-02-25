package calcultor;

public class CalculatorImpl implements Calculator {

	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a + b;
	}
	
	@Override
	public double add(double a, double b){
		return a + b;
	}

	@Override
	public int sub(int a, int b) {
		// TODO Auto-generated method stub
		return a - b;
	}
	
	@Override
	public double sub(double a, double b){
		return a - b;
	}

	@Override
	public int mul(int a, int b) {
		// TODO Auto-generated method stub
		return a * b;
	}
	
	@Override
	public double mul(double a, double b){
		return a * b;
	}

	@Override
	public int div(int a, int b) {
		// TODO Auto-generated method stub
		return a/b;
	}
	
	@Override
	public double div(double a, double b){
		return a / b;
	}
	
	@Override
	public boolean greater(int a, int b){
		return (a > b) ? true : false;
	}
	
	@Override
	public boolean greater(double a, double b){
		return (a > b) ? true : false;
	}

}
