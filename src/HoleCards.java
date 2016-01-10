
public class HoleCards {
	private static HoleCards[] allHoleCards = new HoleCards[1326];
	
	public Card[] cards = new Card[2];
	public final int index;
	
	private HoleCards(Card c1, Card c2, int index) {		
		if (c2.index <= c1.index) {
			throw new IllegalArgumentException("second card cant be bigger or equal to first card");
		}
		
		cards[0] = c1;
		cards[1] = c2;
		this.index = index;
	}
	
	static {
		int counter = 0;
		for (int i = 0; i < 52; i++) {
			for (int j = i + 1; j < 52; j++) {				
				allHoleCards[counter] = new HoleCards(Card.getCard(i), Card.getCard(j), counter);
				counter++;
			}
		}
	}
		
	public static int getIndex(Card c1, Card c2) {
		if (c1.index == c2.index) {
			throw new IllegalArgumentException("cant be the same card! card with index " + c1.index + " is used twice");
		}
				
		//bigger card always goes second!		
		int smallCard = Math.min(c1.index, c2.index);
		int bigCard = Math.max(c1.index, c2.index);
		
		//triangular numbers!
		int cardsBefore = 0;		
		for (int i = 0; i < smallCard; i++) {
			cardsBefore += 51 - i;
		}
		cardsBefore += bigCard - smallCard - 1;	
		
		return cardsBefore;			
	}
	
	public static HoleCards getHoleCards(Card c1, Card c2) {
		if (c1.index == c2.index) {
			throw new IllegalArgumentException("cant be the same card! card with index " + c1.index + " is used twice");
		}
		
		return allHoleCards[getIndex(c1, c2)];		
	}
	
	public static HoleCards getHoleCards(int index) {	
		return allHoleCards[index];		
	}
	
	public String toString() {
		return cards[0].toString() + cards[1].toString();
	}	
}