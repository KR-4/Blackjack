package FinalExam;

//New Concepts used:Classes, ArrayLists, Encapsulation, Constructors, Object Oriented Programming 

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackJack 
{
	//create random number generator
	static Random rng = new Random();
	//create scanner system
	static Scanner input = new Scanner(System.in);
	
	private static String[] suits = {null, "Clubs", "Diamonds", "Hearts", "Spades"};
	private static String[] ranks = {null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	private static Card[] card = new Card[53];
	private static int numCardsDealer = 0, numCardsPlayer = 0, scoreDealer = 0, scorePlayer = 0, dealerCard = 0, playerCard = 0;
	private static boolean stay = false, playAgain = true;
	private static ArrayList<Integer> cardsDealt = new ArrayList<Integer>();
	private static String runAgain = "yes";
	
	//initializes a deck of 52 cards
	public static void initializeDeck()
	{
		int y = 1;
		for(int i = 1; i <= 13;i++) 
		{
			for(int x = 1; x <= 4; x++)
			{
				//assign a suit and a rank to each card
				card[y] = new Card(suits[x], ranks[i], i);
				y++;
			}
		}
	}
	
	//return a random integer between 1 and 52, which represents the cards in the deck
	public static int deal()
	{
		return (int) (rng.nextDouble() * (52 - 1) + 1);
	}
	
	//print score of a hand
	public static void printScore()
	{
		System.out.println("\nPlayer's Score: " + scorePlayer);
		System.out.println("Dealer's Score: " + scoreDealer + "\n");
	}
	
	//deal a card for the player
	public static void playerDeal()
	{
		//deal a random card to the player
		boolean isSameCard = false;
		int x = 0;
		playerCard = deal();
		
		while(x == 0)
		{	
			//check if the card has already been dealt
			isSameCard = cardsDealt.contains(playerCard); 
			
			//if the card has already been dealt, change the card
			if(isSameCard)
			{
				playerCard = deal();
			}else
			{
				x = 1;
			}
		}
		//add the card to the list of cards that have been dealt
		cardsDealt.add(playerCard);
		System.out.println(card[playerCard].getRank() + " of " + card[playerCard].getSuit() + "\n");
		scorePlayer += card[playerCard].getCardValue();
		numCardsPlayer++;
	}
	
	//deal a card for the dealer
	public static void dealerDeal()
	{
		//deal a random card to the dealer
		dealerCard = deal();
		boolean isSameCard = false;
		int x = 0;
		
		while(x == 0)
		{	
			//check if the card has already been dealt
			isSameCard = cardsDealt.contains(dealerCard);
			
			//if the card has already been dealt, change the card
			if(isSameCard)
			{
				dealerCard = deal();
			}else
			{
				x = 1;
			}
		}
		cardsDealt.add(dealerCard);
		scoreDealer += card[dealerCard].getCardValue();
		numCardsDealer++;
	}
	
	//checks if the user wants to hit or stay
	public static void HitOrStay()
	{
		String hitOrStay;
		//asks the user if they want to hit or stay
		hitOrStay = input.nextLine();
		
	    if (hitOrStay.equalsIgnoreCase("hit"))
	    {
			System.out.println("\nPlayer:");
			playerDeal();
	    }
	    else if (hitOrStay.equalsIgnoreCase("stay"))
	    {
	    	System.out.println("Your turn is over! \n");
	    	stay = true;
	    }
	    //if they enter something other than hit or stay:
	    else
	    {
	    	int x = 0;

	    	while (x == 0)
	    	{
	    		System.out.println("Please enter Hit or Stay!");
	    		hitOrStay = input.nextLine();
	    		
	    		if(hitOrStay.equalsIgnoreCase("hit"))
	    		{
	    			System.out.println("Player:");
	    			playerDeal();
	    			x++;
	    		}else if(hitOrStay.equalsIgnoreCase("stay"))
	    		{
	    	    	System.out.println("Your turn is over!");
	    	    	if(scoreDealer > 16)
	    	    	{
						stay = true;
	    	    	}
	    		}
	    	}		    		
	    }
	}
	
	//decides if the player or dealer won, or if it was a tie
	public static void WinOrLose()
	{
		System.out.println("Game Over! \n");
		if(scoreDealer > 21 && scorePlayer <= 21)
		{
			System.out.println("Player Wins and Dealer Loses!");
		}else if (scorePlayer > 21 && scoreDealer <= 21)
		{
			System.out.println("Dealer Wins and Player Loses!");
		}else if (scorePlayer > scoreDealer && scoreDealer <= 21 && scoreDealer <= 21)
		{
			System.out.println("Player Wins and Dealer Loses!");
		}else if(scoreDealer > scorePlayer && scoreDealer <= 21 && scoreDealer <= 21) 
		{
			System.out.println("Dealer Wins and Player Loses!");
		}else if(scorePlayer == scoreDealer)
		{
			System.out.println("It's a tie!");
		}
		System.out.println("***************************************");
	}
	
	//contains the game itself
	public static void play()
	{
		System.out.println("***************************************");
		System.out.println("               BlackJack");
		System.out.println("            (Kaven Rajakumar)");
		System.out.println("*************************************** \n");
	//first round
		//player
		System.out.println("Player:");
		playerDeal();
		playerDeal();
    	System.out.println("Your turn is over! \n");
		
		//dealer
		System.out.println("Dealer:");
		dealerDeal();
		System.out.println(card[dealerCard].getRank() + " of " + card[dealerCard].getSuit() + "\n");
		dealerDeal();
		System.out.println("Face Down Card \n");
    	System.out.println("Dealer's turn is over! ");
    	
    	printScore();

	//rest of the rounds	
		while (scoreDealer < 21 && scorePlayer < 21)
		{
			if(!stay)
			{
				if(numCardsPlayer <= 11)
				{
					System.out.println("Player, do you want to hit or stay?");
					HitOrStay();
					if(stay == true && scoreDealer > 16)
					{
						break;
					}
				}else
				{
					System.out.println("Sorry, Player already has 11 cards! \n Dealer, it's your turn again!");
				}
			}else 
			{
				System.out.println("Player Stays!");
			}
			if(scorePlayer > 21)
			{
				System.out.println("BUST! \n");
				break;
			}
			
			if(numCardsDealer <= 11)
			{
				if(scoreDealer <= 16)
				{
					System.out.println("Dealer");
					dealerDeal();
					System.out.println(card[dealerCard].getRank() + " of " + card[dealerCard].getSuit() + "\n");
				}else if(scoreDealer > 16)
				{
					System.out.println("Dealer stays!");
				}
			}else
			{
				System.out.println("Sorry, Dealer already has 11 cards! \n Player, it's your turn again!");
			}
			
			if(scoreDealer > 21)
			{
				System.out.println("BUST! \n");
				break;
			}
			
			if(stay == true && scoreDealer > 16)
			{
				break;
			}
			
			printScore();
		}
		
		System.out.println("\nFinal Score:");
		System.out.println("Player Score: " + scorePlayer);
		System.out.println("Dealer Score: " + scoreDealer + "\n");
		
		WinOrLose();
	}
	
	//checks if the user wants to play again
	public static void playAgain()
	{
		System.out.println("\nDo you want to play again?");
	    runAgain = input.nextLine();
	    //if the user enters yes, run the whole program again
	    if (runAgain.equalsIgnoreCase("yes"))
	    {
	    	System.out.println("Ok, thank you!\n");
	    	resetValues();
	    }
	    //if user enters no, exit the program
	    else if (runAgain.equalsIgnoreCase("no"))
	    {
	    	System.out.println("Thank you for playing!");
	    	playAgain = false;
	    }
	    //if they enter something other than yes or no:
	    else
	    {
	    	int x = 0;
	    	
	    	while (x == 0)
	    	{
	    		System.out.println("Please enter Yes or No!");
	    		runAgain = input.nextLine();
	    		
	    		//if they enter yes, exit this loop and run the whole program again
	    		if(runAgain.equalsIgnoreCase("yes"))
	    		{
	    			System.out.println("Ok, thank you!\n");
	    			resetValues();
	    			x++;
	    		}
	    		//if user enters no, exit the program
	    		else if(runAgain.equalsIgnoreCase("no"))
	    		{
			    	System.out.println("Thank you for playing!");
			    	playAgain = false;
			    	x++;
	    		}
	    	}		    		
	    }
	}
	
	//if the user chooses to play again, reset all values
	public static void resetValues()
	{
		numCardsDealer = 0; 
		numCardsPlayer = 0;
    	scoreDealer = 0;
    	scorePlayer = 0;
    	dealerCard = 0;
    	playerCard = 0;
    	stay = false;
    	playAgain = true;
    	runAgain = "yes";
    	cardsDealt.clear();
	}
	
	public static void main(String[] args) 
		{	
			initializeDeck();
			
		    while(playAgain)
		    {	
		    	play();
		    	playAgain();
		    }
		}
}
