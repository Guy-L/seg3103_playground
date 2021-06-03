public class Date {

	/* Data fields */
	private int year;
	private int month;
	private int day;
	private boolean isLeapYear;
	private boolean isThirtyDayMonth;

	/* String correspondent used for displaying months */
	String[] monthNames = {
		"January", "February", "March",
		"April", "May", "June",
		"July", "August", "September",
		"October", "November", "December"
	};

	//
	// CONSTRUCTOR
	//
	public Date(int year, int month, int day) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}
	
	
	

	/**
	 * Check validity of the year when creating a new Date. year must be greater than 0
	 */
	private void setYear(int year) {
		if (year < 0) throw new IllegalArgumentException("Year must be greater or equal to 0.");
		this.year = year;
		
		isLeapYear = (year % 100 == 0) ? (year % 400 == 0) : (year % 4 == 0);
	}
	
	/**
	 * Check validity of the month when creating a new Date. month must be between 1 and 12.
	 */
	private void setMonth(int month) {
		if (month < 1 || month > 12) throw new IllegalArgumentException("Month must be between 1 and 12.");
		this.month = month;
		
		isThirtyDayMonth = month == 4 || month == 6 || month == 9 || month == 11;
	}
	
	/**
	 * Check validity of the day when creating a new Date.
	 * day must be greater or equal to 1 and
	 *   - less or equal to 31 for months with 31 days
	 *   - less or equal to 30 for months with 30 days,
	 *   - less or equal to 29 for February if year is leap
	 *   - less or equal to 30 for February if year is non-leap
	 */
	private void setDay(int day) {
		if (day < 1) throw new IllegalArgumentException("Day must greater or equal to 1.");
		if (day > 31) throw new IllegalArgumentException("Day must less or equal to 31.");
		
		if (isThirtyDayMonth && day > 30) 
			throw new IllegalArgumentException("Day must less than 30 for month " + monthNames[month-1]);
		
		if (month == 2 && isLeapYear && day > 29) 
			throw new IllegalArgumentException("Day must less than 29 for month " + monthNames[month-1] + " on a leap year.");
		
		if (month == 2 && !isLeapYear && day > 28) 
			throw new IllegalArgumentException("Day must less than 28 for month " + monthNames[month-1] + " on a non leap year.");
		
		this.day = day;
	}
	
	
	
	// Class methods
	/**
	 * Returns the date of the day following that date.
	 */
	public Date nextDate() {
		int nextYear = year, nextMonth = month, nextDay = day + 1;
		if (isEndOfMonth()) {
			nextDay = 1;
			if (month == 12) {
				nextYear++;
				nextMonth = 1;
			} else nextMonth++;
		}
		
		return new Date(nextYear, nextMonth, nextDay);
	}

	/**
	 * Check if the date is a end of a month.
	 */
	private boolean isEndOfMonth() {
		return day == 31 || (day == 30 && isThirtyDayMonth) || (month == 2 && (isLeapYear ? day == 29 : day == 28));
	}
	
	
	

	public String toString() {
		return year + "/" + monthNames[month-1] + "/" + day;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Date)) return false;
		
		Date od = (Date)obj;
		return year == od.getYear() && month == od.getMonth() && day == od.getDay();
	}
}
