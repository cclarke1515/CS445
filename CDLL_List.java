import java.io.*;
import java.util.*;

public class CDLL_List<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	
	public CDLL_List()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_List( String fileName, String insertionMode ) throws Exception
	{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );	
			while ( infile.ready() )
			{	@SuppressWarnings("unchecked") 
				T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
				if ( insertionMode.equals("atFront") )
					insertAtFront( data ); 	
				else if ( insertionMode.equals( "atTail" ) )
					insertAtTail( data ); 
				else
					die( "FATAL ERROR: Unrecognized insertion mode <" + insertionMode + ">. Aborting program" );
			}
			infile.close();
	}	
	
	private void die( String errMsg )
	{
		System.out.println( errMsg );
		System.exit(0);
	}
		
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT YOU COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	@SuppressWarnings("unchecked")
	public int size()
	{
		if ( head == null )
			return 0;
		CDLL_Node<T> curr = head;
		do
		{
			++count;
			curr = curr.getNext();
		} 
		while ( curr != head );
		return count;
	}


	// TACK A NEW NODE ONTO THE FRONT OF THE LIST
	@SuppressWarnings("unchecked")
	public void insertAtFront(T data)
	{
		// BASE CASE WRITTEN FOR YOU
		CDLL_Node<T> newNode = new CDLL_Node( data,null,null);
		if (head==null)
		{
			newNode.setNext( newNode );
			newNode.setPrev( newNode );
			head = newNode;
			return;
		}
		CDLL_Node<T> last = head.getPrev();
		newNode.setNext( head );
		head.setPrev( newNode );
		newNode.setPrev( last );
		last.setNext( newNode );	
		head = newNode;
	}
	
	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		// BASE CASE WRITTEN FOR YOU
		CDLL_Node<T> newNode = new CDLL_Node( data,null,null);
		if (head==null)
		{
			newNode.setNext( newNode );
			newNode.setPrev( newNode );
			head = newNode;
			return;
		}
		CDLL_Node<T> last = head.getPrev();
		last.setNext( newNode );
		newNode.setPrev( last );
		newNode.setNext( head );
		head.setPrev( newNode );
		
		// NOT EMPTY. INSERT NEW NODE AFTER THE LAST/TAIL NODE
	}
	
	// RETURN TRUE/FALSE THIS LIST CONTAINS A NODE WITH DATA EQUALS KEY
	public boolean contains( T key )
	{
		return ( search(key) != null ) ? true:false;
	}

	// RETURN REF TO THE FIRST NODE (SEARCH CLOCKWISE FOLLOWING next) THAT CONTAINS THIS KEY. DO -NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
	public CDLL_Node<T> search( T key )
	{
		CDLL_Node<T> curr = head;
		do
		{
			if ( curr.getData().equals(key) )
				return curr;
			else
				curr = curr.getNext();
		}
		while ( curr != head );
		return null;
	}
	
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String str = "";
		CDLL_Node<T> curr = head;
		do
		{
			str += curr.getData();
			if ( curr.getNext() != head )
				str += "<=>";
			curr = curr.getNext();
		}
		while ( curr != head );
		return str;
	}
	
} // END CDLL_LIST CLASS