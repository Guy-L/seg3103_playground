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
		if(lhs < 0) return 0;
		
		int sum = 0;
		
		for(int i = lhs; i <= rhs; i++) {
			if(i == 1) continue;
			
			boolean isPrime = true;
			for(int j = 2; j < i; j++) 
				if(i % j == 0) isPrime = false;
			
			if(isPrime) sum += i;
		}
		
		return sum;
	}
	
	public static float max(float lhs, float rhs) {
		throw new RuntimeException("Stub");
	}
	
	public static float multiply(float lhs, float rhs) {
		throw new RuntimeException("Stub");
	}
}
