
public class CardDetails {
	
	public long cardNumber;
	public String cardType;
	
	public CardDetails(long cardNumber, String cardType) {
		this.setCardNumber(cardNumber);
		this.setCardType(cardType);
		
	}
	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


}
