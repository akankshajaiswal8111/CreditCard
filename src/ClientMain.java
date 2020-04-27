import java.util.ArrayList;
import java.util.List;

public class ClientMain {
		
		//protected static long number = 4011081888500645L;
		
		protected static long number; 
		public static CCBaseStrategy ccb ;
		public static StrategyPicker sp;
		static List<Long> listNumbers = new ArrayList<Long>();
		static List<CardDetails> listCardDetails = new ArrayList<CardDetails>();
		public static void main(String[] args) throws Exception 
	    { 
			if(args.length != 2) {
	        	 System.out.println("Either input file or output file is missing. Please pass both files");
	        	 System.out.println("Usage: java ClientMain /path/<file1>.ext /path/<file2>.ext ");
	        	 System.exit(0);
	         }
	         String args0 = args[0];
	         String args1 = args[1];
	         
	         //match Extensions of input and output file
	         if(matchExtensions(args0, args1) == true) {
	        	 System.out.println("Extensions are same. Proceed");

	        	 ccb = StrategyPicker.select(args[0]);
	        	 try {
	        	 listNumbers= ccb.readFile(args0);
	        	 }
	        	 catch(Exception e) {
	        		 System.out.println("Something went wrong. Suspending..");
	        		 System.exit(0);
	        	 }
//	        	 for(int i =0; i < listNumbers.size(); i++) {
//	        		 System.out.println(listNumbers.get(i));
//	        	
//	        	 }
	        	 processCardNumbers();
	        	 ccb.writeFile(args1, listCardDetails);
	         }
	    
	         else System.out.println("Extensions mismatch. Filetypes should be same");
	        
	         //System.out.println(number + " is " +  
	         //(isValid(number) ? "valid" : "invalid")); 


	    } 

		// Validating card length is no more than 19 digits
		public static boolean isValid(long number) 
	    { 
	       return (getSize(number) <= 19); 
	    } 
	  

	    public static int getSize(long d) 
	    { 
	        String num = d + ""; 
	        return num.length(); 
	    } 
	    
	    public static boolean matchExtensions(String args0, String args1) {
	    	String extension1 = args0.substring(args0.lastIndexOf(".") + 1, args0.length());
	    	String extension2 = args1.substring(args1.lastIndexOf(".") + 1, args1.length());
	    	System.out.println(extension1);
	    	System.out.println(extension2);
	    	if(extension1.equals(extension2)) 
	    		return true;
	    	
	    	return false;
	    	
	    }
	    
	    public static void processCardNumbers() throws Exception {
	         for(int i =0; i < listNumbers.size(); i++) {
	        	 
	        	 number = listNumbers.get(i);
	        	 if((number!=0L) && isValid(number)) {
	
	        		 CreditCard cc =CreditCardFactory.createCreditCard(number);
	        		 try {
	        			 String cardType = cc.run();
	        			 CardDetails newCard = new CardDetails(number, cardType);
	        			 listCardDetails.add(newCard);
	        		 }
	        		 catch(Exception e) {
	        			 CardDetails newCard = new CardDetails(number, "Invalid");
	        			 listCardDetails.add(newCard);
	        			 System.out.println("Invalid Card");
	        			 //e.printStackTrace();
	        		 }
	        		 
	        	 }
	        	 else { 
	        		 CardDetails newCard = new CardDetails(number, "Invalid");
        			 listCardDetails.add(newCard);
	        		 System.out.println("Invalid number");
	        	 }
	         }
			
	    	
	    	
	    }


}
