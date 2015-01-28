import java.util.Scanner;

public class StraightFlush {
	
	private static boolean flushExists = false;
	
	private static Card[] flush = new Card[5];
	private static int position = 0;

	public static void main(String[] args) {
		
		String[] cards;
		
		Scanner consoleReader = new Scanner(System.in);
		
		cards = consoleReader.nextLine().split("\\,\\s*");
		
		consoleReader.close();
		
		Card[] playerCards = new Card[cards.length];
		
		for (int i = 0; i < playerCards.length; i++) {
			
			Card newCard = new Card(cards[i]);
			
			playerCards[i] = newCard;
			
		}
		
		for (int a = 0; a < playerCards.length; a++) {
			
			for (int b = a + 1; b < playerCards.length; b++) {
				
				Card cardA = playerCards[a];
				Card cardB = playerCards[b];
				
				if (cardB.cardNumber < cardA.cardNumber) {
					
					playerCards[a] = cardB;
					playerCards[b] = cardA;
					
				}
				
			}
			
		}
		
		for (int a = 0; a < playerCards.length; a++) {
			
			position = 0;
			flush[position] = playerCards[a];
			
			for (int b = a + 1; b < playerCards.length; b++) {
				
				if (flush[position].cardSuit == playerCards[b].cardSuit) {
					
					if (playerCards[b].cardNumber - flush[position].cardNumber == 1) {
						
						if (position < 4) {
							
							position += 1;
							flush[position] = playerCards[b];
							
						}
						
						if (position == 4) {
							
							System.out.println(String.format("[%s, %s, %s, %s, %s]", flush[0].toString(), 
									flush[1].toString(),flush[2].toString(), flush[3].toString(), 
									flush[4].toString()));
							
							position = 0;
							
							flushExists = true;
							
							break;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		if (!flushExists) {
			
			System.out.println("No Straight Flushes");
			
		}
						
	}
	
}
	
		

class Card {
	
	public int cardNumber;
	public char cardSuit;
	
	public Card(String card) {
		
		String cardNumberAsString = card.substring(0, card.length() - 1);
		
		char suit = card.charAt(card.length() - 1);
		
		cardNumber = stringToInt(cardNumberAsString);
		
		cardSuit = suit;
		
	}
	
	private static int stringToInt(String value) {
		
		switch (value) {
		
		case "J":
			return 11;
		case "Q":
			return 12;
		case "K":
			return 13;
		case "A":
			return 14;
		default:
			int number = Integer.parseInt(value);
			return number;

		}
		
	}
	
	private static String cardNumberToString(int number) {
		
		if (number > 1 && number < 11) {
			
			return Integer.toString(number);
			
		}
		else {
			
			switch (number) {
			
			case 11:
				return "J";
			case 12:
				return "Q";
			case 13:
				return "K";
			default:
				return "A";
			}
			
		}
		
	}
	
	@Override public String toString() {
		
		return cardNumberToString(cardNumber) + cardSuit;
	}
	
}

