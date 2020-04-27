public class CreditCardFactory {
	
	public static CreditCard createCreditCard(long number) throws Exception {
		CreditCard cc = null;
		if(AmExCC.isValidNumber(number)) {
				cc = new AmExCC();
		}
		else if(MasterCC.isValidNumber(number)) {
			cc = new MasterCC();
		}
		else if(VisaCC.isValidNumber(number)) {
			cc = new VisaCC();
		}
		else if(DiscoverCC.isValidNumber(number)) {
			cc = new DiscoverCC();
		}
//		else {
//			//throw new NullPointerException("Undefined Credit Card");
//			
//			//System.out.println("Undefined credit card");
//			cc = null;
//		}
		return cc;
	}
}
