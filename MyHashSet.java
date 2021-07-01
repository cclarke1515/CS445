import java.util.*;
import java.io.*;

public class MyHashSet implements HS_Interface
{
    private int numBuckets;
    private Node[] bucketArray;
    private int size; // total # keys stored in set right now

    private final int MAX_BUCKET = 20;
    
    public MyHashSet( int numBuckets )
    {
        size = 0;
        this.numBuckets = numBuckets;
        bucketArray = new Node[numBuckets]; // array of linked lists
    }

    private int hash( String key )
    {
        char[] arr = key.toCharArray();
        int hash = 0;
        for ( int i = 0; i < arr.length; i++ )
            hash = arr[i] + ((hash << 5) - hash);
        return Math.abs(hash) % numBuckets;
    }

    public boolean add( String key )
    {
        if ( !contains(key) )
        {
            int hash = hash(key);
            Node curr = bucketArray[hash];
            Node prev = null;
            Node newNode = new Node(key, null);
            if ( curr == null )
                bucketArray[hash] = newNode;
            else
            {
                while ( curr != null )
                {
                    prev = curr;
                    curr = curr.next;
                }
                prev.next = newNode;
            }
            size++;
            if ( size > MAX_BUCKET * this.numBuckets )
                upSize_ReHash();
            return true;
        }
        return false;
    }

    private void upSize_ReHash()
    {
        Node[] old = bucketArray;
        bucketArray = new Node[ old.length * 2 ];
        this.numBuckets = bucketArray.length;
        for ( int i = 0; i < old.length; i++ )
        {
            Node curr = old[i];
            while ( curr != null )
            {
                add(curr.data);
                curr = curr.next;
            }
        }
    }

    public int size()
	{	
		return size;
    }
    
    public boolean contains( String key )
    {
        int hash = hash(key);
        Node curr = bucketArray[hash];
        while ( curr != null )
        {
            if ( curr.data.equals(key) )
            {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean remove( String key )
    {
        int hash = hash(key);
        Node curr = bucketArray[hash];
        Node prev = null;
        while ( curr != null )
        {
            if ( curr.data.equals(key) )
            {
                if ( prev == null )
                    bucketArray[hash] = curr.next;
                else
                    prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public void clear()
    {
        for ( int i = 0; i < bucketArray.length; i++ )
            bucketArray[i] = null;
        size = 0;
    }
} // End of Class

class Node
{
    String data;
    Node next;
    public Node ( String data, Node next )
    {
        this.data = data;
        this.next = next;
    }
}