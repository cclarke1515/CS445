import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner6;

public class CDLL_List<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	
	public CDLL_List()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public CDLL_List( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
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



	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		CDLL_Node<T> newNode = new CDLL_Node<T>(data, null, null);
		if ( head == null )
		{
			newNode.next = newNode;
			newNode.prev = newNode;
			head = newNode;
			return;
		}
		CDLL_Node<T> last = head.prev;
		newNode.next = head;
		head.prev = newNode;
		newNode.prev = last;
		last.next = newNode;
		head = newNode;
	}
	public void insertAtTail(T data)
	{
		insertAtFront(data);
		head = head.next;
	}
	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if ( head == null )
			return false; 
		else if ( head.next == head )
		{
			head = null;
			return true;
		}
		CDLL_Node<T> tmp = head.prev;
		tmp.prev.next = head;
		head.prev = tmp.prev;
		return true;
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		head = head.next;
		return removeAtTail(); // YOUR CODE HERE.
	}

	public String toString()
	{
		String toString = "";  // NO <=> DELIMITERS REQUIRED ANYWHERE IN OUTPUT
		if ( head == null )
			return toString;
		CDLL_Node<T> curr = head;
		do
		{
			toString += curr.data;
			if ( curr.next != head )
				toString += " ";
			curr = curr.next;
		}
		while ( curr != head );
		return toString;  // JUST A SPACE BETEEN ELEMENTS LIKE IN LAB3
	}

	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		if ( head == null )
			return 0;
		CDLL_Node<T> curr = head;
		do
		{
			++count;
			curr = curr.next;
		} 
		while ( curr != head );
		return count;
	}

	public boolean empty()
	{
		return ( size() != 0 ) ? false:true;  
	}

	public boolean contains( T key )
	{
		return ( search(key) != null ) ? true:false;  
	}

	public CDLL_Node<T> search( T key )
	{
		if ( head == null )
			return null;
		CDLL_Node<T> curr = head;
		do
		{
			if ( curr.data.equals(key) )
				return curr;
			else
				curr = curr.next;
		} 
		while ( curr != head );
		return null; 
	}

	@SuppressWarnings("unchecked")  //CAST TO COMPARABLE THROWS WARNING
	public void insertInOrder(T  data)
	{
		Comparable<T> comp = ( Comparable<T> ) data;
		if ( head == null || comp.compareTo( head.data ) < 0 )
		{
			insertAtFront(data);
			return;
		}
		else if ( comp.compareTo( head.prev.data ) > 0 )
		{
			insertAtTail(data);
			return;
		}
		CDLL_Node<T> curr = head;
		do
		{
			if ( comp.compareTo( curr.next.data ) < 0 )
				break;
			curr = curr.next;
		} 
		while ( curr != head && comp.compareTo( curr.next.data ) > 0 );
		CDLL_Node<T> tmp = new CDLL_Node<T>( data, curr, curr.next );
		curr.next = tmp;
		tmp.next.prev = tmp;
	}

	public boolean remove(T key)
	{
		if ( head == null )
			return false; 
		if ( head.data.equals(key) )
		{
			removeAtFront();
			return true;
		}
		CDLL_Node<T> curr = head, prev = null;
		while ( !curr.data.equals(key) )
		{
			if ( curr.next == head )
				return false;
			prev = curr;
			curr = curr.next;
		}
		prev.next = curr.next;
		prev.next.prev = prev;
		return true;
	}


	public CDLL_List<T> union( CDLL_List<T> other )
	{
		CDLL_List<T> union = new CDLL_List<T>();
		CDLL_Node<T> curr = head;
		CDLL_Node<T> newNode = other.head;
		do
		{
			union.insertInOrder( (T) curr.data );
			curr = curr.next;
		} while ( curr != head );
		do
		{
			if ( !union.contains( (T) newNode.data ) )
				union.insertInOrder( (T) newNode.data );
			newNode = newNode.next;
		} while ( newNode != other.head );
		return union;
	}
	public CDLL_List<T> inter( CDLL_List<T> other )
	{
		CDLL_List<T> inter = new CDLL_List<T>();
		CDLL_Node<T> curr = head;
		do
		{
			if ( other.contains( (T) curr.data ) && !inter.contains( (T) curr.data ) )
				inter.insertInOrder( (T) curr.data );
			curr = curr.next;
		}
		while ( curr != head );
		return inter;
	}
	public CDLL_List<T> diff( CDLL_List<T> other )
	{
		CDLL_List<T> diff = new CDLL_List<T>();
		CDLL_Node<T> curr = head;
		do
		{
			if ( !other.contains( (T) curr.data ) && !diff.contains( (T) curr.data ) )
				diff.insertInOrder( (T) curr.data );
			curr = curr.next;
		}
		while ( curr != head );
		return diff;
	}
	public CDLL_List<T> xor( CDLL_List<T> other )
	{
		return  union(other).diff(inter(other));
	}

} //END LINKEDLIST CLASS

// A D D   C D L L  N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C
class CDLL_Node<T>
{
  T data;
  CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

  CDLL_Node()
  {
    this( null, null, null );  // 3 FIELDS TO INIT
  }

  CDLL_Node(T data)
  {
    this( data, null, null);
  }

  CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {
    this.data = data;
	this.prev = prev;
    this.next = next;
  } 
  public String toString()
  {
	  return ""+ this.data;
  } 
	 
}