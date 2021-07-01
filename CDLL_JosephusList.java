import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method 
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );	
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked") 
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data ); 
		}
		infile.close();
	}
	

	
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################
	
	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
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
		last.next = newNode;
		newNode.prev = last;
		newNode.next = head;
		head.prev = newNode;
	}

	
	public int size()
	{	
		if ( head == null )
			return 0;
		CDLL_Node<T> curr = head;
		count = 0;
		do
		{
			++count;
			curr = curr.next;
		} while ( curr != head );
		return count;
	}
	
	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
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
		} while ( curr != head );
		return null;
	}
	
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String toString = "";
		if ( head == null )
			return toString;
		CDLL_Node<T> curr = head;
		do
		{
			toString += curr.data;
			if ( curr.next != head )
				toString += "<=>";
			curr = curr.next;
		} while ( curr != head );
		return toString;
	}
	
	void removeNode( CDLL_Node<T> deadNode )
	{
		CDLL_Node<T> previous = deadNode.prev;
		CDLL_Node<T> after = deadNode.next;
		previous.next = after;
		after.prev = previous;
	}
	
	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() < 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;
		do
		{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.data;
			System.out.println( "stopping on " + curr.data + " to delete " + curr.data );
			if ( skipCount > 0 )
				curr = curr.next;
			else 
				curr = curr.prev;
			if ( head == deadNode )
				head = curr;
			removeNode(deadNode);
			System.out.println( "deleted. list now: " + toString() );
			if ( size() == 1 )
				return;
			int newSkip = Math.abs(skipCount) - 1;
			if ( skipCount > 0 )
				System.out.println( "resuming at " + curr.data + ", skipping " + curr.data + " + " + newSkip + " nodes CLOCKWISE after" );
			else
				System.out.println( "resuming at " + curr.data + ", skipping " + curr.data + " + " + newSkip + " nodes COUNTER_CLOCKWISE after" );
			int newCount = skipCount;
			if ( skipCount > 0 )
			{
				do
				{
					curr = curr.next;
					--newCount;
				} while ( newCount > 0 );
			}
			else
			{
				do
				{
					curr = curr.prev;
					++newCount;
				} while ( newCount < 0 );
			}
		}
		while (size() > 1 );
	}
} // END CDLL_LIST CLASS
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
	  return ""+this.data;
  } 	 
} 