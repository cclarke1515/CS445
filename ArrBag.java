// this file formatted by JFormat - jlbalder@netscape.net
//  STARTER FILE FOR ARRBAG PROJECT #2

import java.io.*;
import java.util.*;

public class ArrBag<T>
{
    final int NOT_FOUND = -1;
    final int INITIAL_CAPACITY = 1;
    private int count; // LOGICAL SIZE
    
    // DEFINE & INITIALIZE REF TO OUR ARRAY OF T OBJECTS
    @SuppressWarnings("unchecked") // SUPRESSION APPLIES TO THE NEXT LINE OF CODE
    T[] theArray = (T[]) new Object[INITIAL_CAPACITY]; // CASTING TO T[] (creates warning we have to suppress)
    
    // DEFAULT C'TOR
    public ArrBag()
    {
        count = 0; // i.e. logical size, actual number of elems in the array
    }
    
    // SPECIFIC INITIAL LENGTH VERSION OF CONSTRUCTOR
    // only the union,intersect,diff call this version of constructor
    @SuppressWarnings("unchecked")
    public ArrBag( int optionalCapacity )
    {
        theArray = (T[]) new Object[optionalCapacity ];
        count = 0; // i.e. logical size, actual number of elems in the array
    }
    
    // C'TOR LOADS FROM FILE Of STRINGS
    @SuppressWarnings("unchecked")
    public ArrBag( String infileName ) throws Exception
    {
        count = 0; // i.e. logical size, actual number of elems in the array
        BufferedReader infile = new BufferedReader( new FileReader(  infileName ) );
        while ( infile.ready() )
			this.add( (T) infile.readLine() );
        infile.close();
    }
    
    // APPENDS NEW ELEM AT END OF LOGICAL ARRAY. UPSIZES FIRST IF NEEDED
    public boolean add( T element )
    {
        // THIS IS AN APPEND TO THE LOGICAL END OF THE ARRAY AT INDEX count
        if (size() == theArray.length) upSize(); // DOUBLES PHYSICAL CAPACITY
			theArray[ count++] = element; // ADD IS THE "setter"
        return true; // success. it was added
    }
    
    // INCR EVERY SUCESSFULL ADD, DECR EVERY SUCESSFUL REMOVE
    public int size()
    {
        return count;
    }
    
    // RETURNS TRUE IF THERE ARE NO ELEMENTS IN THE ARRAY, OTHERWISE FALSE
    public boolean isEmpty()
    {
        return count==0;
    }
    
    public T get( int index )
    {
        if ( index < 0 || index >=size() )
			die("FATAL ERROR IN .get() method. Index to uninitialized element or out of array bounds. Bogus index was: " + 
				 index + "\n" );
        return theArray[index];
    }
    
    // SEARCHES FOR THE KEY. TRUE IF FOUND, OTHERWISE FALSE
    public boolean contains( T key )
    {
        if (key == null) return false;
        for ( int i=0 ; i < size() ; ++i )
			if ( get(i).equals( key ) ) 
				return true;
			
        return false;
    }
    
    void die( String errMsg )
    {
        System.out.println( errMsg );
        System.exit(0);
    }
    
    // --------------------------------------------------------------------------------------------
    // # # # # # # # # # # #   Y O U   W R I T E   T H E S E   M E T H O D S  # # # # # # # # # # #
    // --------------------------------------------------------------------------------------------
    public void clear()
    {
        count=0;
    }
    @SuppressWarnings("unchecked")
    private void upSize()
    {
        T[] upArr = (T[]) new Object[count*2];
        for ( int i = 0; i < size(); i++)
            upArr[i] = theArray[i];
        theArray = upArr;
    }
    public String toString()
    {
        String toString  = ""; 
        for ( int i=0 ; i < size() ; ++i  )
        {
            toString += theArray[i];
            if ( i < size()-1 )
                toString += " ";
        }
        return toString;
    }
    public ArrBag<T> union( ArrBag<T> other )
    {
        ArrBag<T> unionResult = new ArrBag<T>( this.size() + other.size() );
        for ( int i = 0; i < this.size(); i++ )
                unionResult.add( get(i) );
        for ( int i = 0; i < other.size(); i++ )
            if ( !unionResult.contains( other.get(i) ) )
                unionResult.add( other.get(i) );
        return unionResult;
    } 
    public ArrBag<T> intersection( ArrBag<T> other )
    {
        ArrBag<T> intersectResult = new ArrBag<T>( Math.min( this.size(), other.size() ) ); 
        for ( int i = 0; i < this.size(); i++ )
            if ( other.contains( this.get(i) ) )
                intersectResult.add( get(i) );
        return intersectResult;
    }
    public ArrBag<T> difference( ArrBag<T> other )
    {
        ArrBag<T> diffResult = new ArrBag<T>( this.size() );
        for ( int i = 0; i < this.size(); i++ )
            if ( !other.contains( this.get(i) ) )
                diffResult.add( get(i) );
		return diffResult;
    }
    public ArrBag<T> xor( ArrBag<T> other )
    {
        return union(other).difference(intersection(other)); 
    }
}

