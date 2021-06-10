public class Calculator {
	
	public static int opposite(int n) {
		String s = String.valueOf(n);
		String r = "";
		
		for(int i = 0; i < s.length(); i++) 
			r = s.charAt(i) + r;
		
		return -Integer.parseInt(r);
	}
	
	public static float sum(float lhs, float rhs) {
		throw new RuntimeException("Stub");
	}
	
	public static float max(float lhs, float rhs) {
		throw new RuntimeException("Stub");
	}
	
	public static float multiply(float lhs, float rhs) {
		throw new RuntimeException("Stub");
	}
}
