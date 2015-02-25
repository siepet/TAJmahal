package calcultor;

public interface Calculator {
	
	public int add(int a, int b);
	public double add(double a, double b);
	
	public int sub(int a, int b);
	public double sub(double a, double b);
	
	public int mul(int a, int b);
	public double mul(double a, double b);
	
	public int div(int a, int b);
	public double div(double a, double b);
	
	boolean greater(int a, int b);
	boolean greater(double a, double b);
}
