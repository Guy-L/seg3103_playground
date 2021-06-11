public class Calculator {	
	
	//Note: as "final" is a reserved keyword in Java, the value was renamed finterm. 
	public static int percentageGrade(float[] homework, float[] labs, float midterm, float finterm) {
		float avgHomework = (homework.length == 0) ? 0 : (arraySum(homework) / homework.length); 
		float avgLabs = (labs.length == 0) ? 0 : (arraySum(labs) / labs.length); 
		
		return (int) Math.round((100)*(0.2 * avgLabs + 0.3 * avgHomework + 0.2 * midterm + 0.3 * finterm));
	}
	
	public static String letterGrade(float[] homework, float[] labs, float midterm, float finterm) {
		float avgHomework = (homework.length == 0) ? 0 : (arraySum(homework) / homework.length); 
		float avgLabs = (labs.length == 0) ? 0 : (arraySum(labs) / labs.length); 
		float avgExams = (midterm + finterm) / 2;
		
		int numLabs = 0;
		for(float mark : labs) if(mark >= 0.25) numLabs++;
		
		if(avgHomework < 0.4 || avgExams < 0.4 || numLabs < 3) return "EIN";
		else {
			float mark = 0.2f * avgLabs + 0.3f * avgHomework + 0.2f * midterm + 0.3f * finterm;
			
			//Unfortunately, cannot use a switch statement for floats...
			if(mark > 0.895) return "A+";
			if(mark > 0.845) return "A";
			if(mark > 0.795) return "A-";
			if(mark > 0.745) return "B+";
			if(mark > 0.695) return "B";
			if(mark > 0.645) return "C+";
			if(mark > 0.595) return "C";
			if(mark > 0.545) return "D+";
			if(mark > 0.495) return "D";
			if(mark > 0.395) return "E";
			return "F";
		}
	}
	
	public static int numericGrade(float[] homework, float[] labs, float midterm, float finterm) {
		float avgHomework = (homework.length == 0) ? 0 : (arraySum(homework) / homework.length); 
		float avgLabs = (labs.length == 0) ? 0 : (arraySum(labs) / labs.length); 
		float avgExams = (midterm + finterm) / 2;
		
		int numLabs = 0;
		for(float mark : labs) if(mark >= 0.25) numLabs++;
		
		if(avgHomework < 0.4 || avgExams < 0.4 || numLabs < 3) return 0;
		else {
			float mark = 0.2f * avgLabs + 0.3f * avgHomework + 0.2f * midterm + 0.3f * finterm;
			
			//Unfortunately, cannot use a switch statement for floats...
			if(mark > 0.895) return 10;
			if(mark > 0.845) return 9;
			if(mark > 0.795) return 8;
			if(mark > 0.745) return 7;
			if(mark > 0.695) return 6;
			if(mark > 0.645) return 5;
			if(mark > 0.595) return 4;
			if(mark > 0.545) return 3;
			if(mark > 0.495) return 2;
			if(mark > 0.395) return 1;
			return 0;
		}
	}
	
	//Replacement for Elexir's "Enum.sum" method
	private static float arraySum(float[] arr) {
		float sum = 0;
		for(float n : arr) sum += n;
		return sum;
	}
}
