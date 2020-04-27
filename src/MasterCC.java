

public class MasterCC implements CreditCard {

	@Override
	public String run() {
		// TODO Auto-generated method stub
		System.out.println("Im MasterCredit card");
		return "MasterCard";
		
	}
	
	
	 // Return true if the digit d is a prefix for number 
   public static boolean prefixMatched(long number, int d) 
   { 
       return getPrefix(number, getSize(d)) == d; 
   } 
 
   // Return the number of digits in d 
   public static int getSize(long d) 
   { 
       String num = d + ""; 
       return num.length(); 
   } 
 
   // Return the first k number of digits from  
   // number. If the number of digits in number 
   // is less than k, return number. 
   public static long getPrefix(long number, int k) 
   { 
       if (getSize(number) > k) { 
           String num = number + ""; 
           return Long.parseLong(num.substring(0, k)); 
       } 
       return number; 
   }
   
	public static boolean isValidNumber(long number) {
		// TODO Auto-generated method stub
		try {
		if((prefixMatched(number, 51) || prefixMatched(number, 52) || prefixMatched(number, 53) || prefixMatched(number, 54) || prefixMatched(number, 55)) && getSize(number) == 15) {
			return true;
		}
		//else System.out.println("Not a MasterCard");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	} 

}

