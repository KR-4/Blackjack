package FinalExam;

public class Card 
{
	private String rank;
	private String suit;
	private int cardValue;
		
	public Card(String suit, String rank, int cardValue)
	{
		this.suit = suit;
		this.rank = rank;
		if(cardValue > 10)
		{
			cardValue = 10;
		}
		this.cardValue = cardValue;
	}
	
	public String getRank()
	{
		return this.rank;
	}
	
	public String getSuit()
	{
		return this.suit;
	}
	
	public int getCardValue()
	{
		return this.cardValue;
	}
}
