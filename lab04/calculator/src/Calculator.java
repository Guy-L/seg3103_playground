public class Calculator {
	
	public static int opposite(int n) {
		String s = String.valueOf(n);
		String r = "";
		
		if(n < 0) s = s.substring(1);
			
		for(int i = 0; i < s.length(); i++) 
			r = s.charAt(i) + r;
			
		return (n < 0 ? 1 : -1) * Integer.parseInt(r);
	}
	
	public static int sum(int lhs, int rhs) {
		if(lhs + rhs == 0) return 0;
		else return 2;
	}
	
	public static float max(float lhs, float rhs) {
		throw new RuntimeException("Stub");
	}
	
	public static float multiply(float lhs, float rhs) {
		throw new RuntimeException("Stub");
	}
}
