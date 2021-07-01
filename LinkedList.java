import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST IDENTICAL TO OTHER
	public LinkedList( LinkedList<T> other )
	{
		head = other.head; // YOU ABSOLUTLEY MUST CHANGE THIS. THIS IS A SHALLOW COPY :(
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	@SuppressWarnings("unchecked") 
	public LinkedList( String fileName ) 
	{
		try 
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{  
				insertAtTail( (T)infile.readLine() );  
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}	
	
	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node curr = head; curr != null; curr = curr.getNext())
		{
			toString += curr.getData();		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " -> ";
		}

		return toString + "\n";
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{
		if ( head == null )
			insertAtFront(data);	
		else 
		{	
			Node<T> curr = head;
			while ( true )
			{
				if ( curr.getNext() == null ) 
				{
					curr.setNext( new Node<T>(data) );
					break;
				} else {
					curr = curr.getNext();
				}
			}	
		}	
	}
	public int size()
	{	
		int count = 0;
		for ( Node<T> curr = head; curr != null; curr = curr.getNext() )
			count++;
		return count;
	}
	public boolean contains( T key )
	{
		return ( search(key) != null ); 
	}
	public Node<T> search( T key )
	{
		// start at 
		Node<T> curr = head;
		while ( curr != null )
		{
			if ( curr.getData().equals(key) )
				return curr;
			else
				curr = curr.getNext();
		}
		return null;
	}
}