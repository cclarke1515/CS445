/*
	Deck class
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];// YOU DO THIS => init deck to be exactly numCards long
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i;
		}// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
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
		int newArr[] = new int[deck.length];
		int mid = deck.length / 2;
		int top = 0;
		int count = 0;
		for ( int i = 0; i < deck.length / 2; i++ ) {
			newArr[count] = deck[mid];
			mid++;
			count++;
			newArr[count] = deck[top];
			top++;
			count++;
		}
		deck = newArr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int newArr[] = new int[deck.length];
		int mid = deck.length / 2;
		int top = 0;
		int count = 0;
		for ( int i = 0; i < deck.length / 2; i++ ) {
			newArr[count] = deck[top];
			top++;
			count++;
			newArr[count] = deck[mid];
			mid++;
			count++;
		}
		deck = newArr;
	}
	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		for (int i = 0; i < deck.length - 1; i++) {
			if (deck[i] >= deck[i+1]) {
				return false;
			}
		}
		return true; // JUST HERE TO COMPILE
	}
}	// END DECK CLASS