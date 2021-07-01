/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		int newDeck[] = new int[deck.length];
		int mid = deck.length / 2;
		int top = 0;
		int count = 0;
		for ( int i = 0; i < deck.length / 2; i++ ) {
			newDeck[count] = deck[mid];
			count++;
			mid++;
			newDeck[count] = deck[top];
			count++;
			top++;
		}
		deck = newDeck;// YOUR CODE HERE
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int newDeck[] = new int[deck.length];
		int mid = deck.length / 2;
		int top = 0;
		int count = 0;
		for ( int i = 0; i < deck.length / 2; i++ ) {
			newDeck[count] = deck[top];
			count++;
			top++;
			newDeck[count] = deck[mid];
			count++;
			mid++;
		}
		deck = newDeck;// YOUR CODE HERE
	}
	
	public String toBitString( int n ) 
	{
		String bitstr = "";
		while ( n > 0 )
			if ( (n % 2) == 0 ) {
				bitstr = 0 + bitstr;
				n /= 2;
			} else {
				bitstr = 1 + bitstr;
				n /= 2;
			}
		return bitstr;
	}
}	// END DECK CLASS