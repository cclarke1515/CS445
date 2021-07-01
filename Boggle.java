import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle
{	
	static String[][] board;
	static long startTime,endTime; // for timing
	static final long MILLISEC_PER_SEC = 1000;

	static TreeSet<String> newD = new TreeSet<String>(); 
	static TreeSet<String> hits = new TreeSet<String>();	// TreeSet for automatic sorting
	static HashSet<Integer> cut = new HashSet<Integer>();
	public static void main( String args[] ) throws Exception
	{	startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );
		BufferedReader dictionary = new BufferedReader( new FileReader( args[0] ) );
		String line = dictionary.readLine();
		while ( dictionary.ready() )
		{
			if ( line.length() >= 3 && line.length() <= ( board.length*board.length ) )
			{
				newD.add(line);
				cut.add(line.length());
			}
			line = dictionary.readLine();
		}
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  );
		Iterator<String> itr = hits.iterator();
		while ( itr.hasNext() )
			System.out.println(itr.next());
		endTime =  System.currentTimeMillis(); // for timing
		
	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{	
		word += board[r][c];
		if ( search(word) )
			hits.add(word);
		else if ( !newD.higher(word).startsWith(word) )
			return;
		if ( ( r-1 >= 0  && r-1 < board.length ) && c >= 0  && board[r-1][c] != null )   // THE r-1 WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r-1, c, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}
		// NE IS [r-1][c+1]  YOU WILL NEED TO TEST BOTH r-1 AND c+1 FOR OUT OF BOUNDS
		if ( ( r-1 >= 0 && r-1 < board.length ) && ( c+1 >= 0  && c+1 < board[r-1].length ) && board[r-1][c+1] != null )
		{
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r-1, c+1, word );
			board[r][c] = unMarked;
		}
		// E IS [r][c+1]
		if ( r >= 0  && ( c+1 >= 0 && c+1 < board[r].length ) && board[r][c+1] != null )
		{
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r, c+1, word );
			board[r][c] = unMarked;
		}
		// SE IS ...
		if ( ( r+1 >= 0 && r+1 < board.length ) && ( c+1 >= 0  && c+1 < board.length ) && board[r+1][c+1] != null )
		{
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r+1, c+1, word );
			board[r][c] = unMarked;
		}
		// S IS ...
		if ( ( r+1 >= 0 && r+1 < board.length ) && c >= 0  && board[r+1][c] != null )
		{
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r+1, c, word );
			board[r][c] = unMarked;
		}
		// SW IS ...
		if ( ( r+1 >= 0 && r+1 < board.length ) && ( c-1 >= 0  && c-1 < board[r+1].length ) && board[r+1][c-1] != null )
		{
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r+1, c-1, word );
			board[r][c] = unMarked;
		}
		// W IS ...
		if ( r >= 0 && ( c-1 >= 0 && c-1 < board[r].length ) && board[r][c-1] != null )
		{
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r, c-1, word );
			board[r][c] = unMarked;
		}
		// NW IS ...
		if ( ( r-1 >= 0 && r-1 < board.length ) && ( c-1 >= 0  && c-1 < board[r-1].length ) && board[r-1][c-1] != null)
		{
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r-1, c-1, word );
			board[r][c] = unMarked;
		}
	} // END DFS ----------------------------------------------------------------------------

	//=======================================================================================
	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD 
	static boolean search( String word )
	{
		if ( word.length() >= 3 )
			if ( newD.contains(word) && cut.contains(word.length()) ) // cut skips words of invalid length
				return true;
		return false;
	}
} // END BOGGLE CLASS