import java.util.*;
import java.io.*;

public class Potus 
{
    public static void main( String[] args ) throws Exception
    {
        TreeMap<String, TreeSet<String>> state2Presidents = new TreeMap<String, TreeSet<String>>();
        // step 1
        BufferedReader s2p = new BufferedReader( new FileReader( "state2Presidents.txt" ) );
        while ( s2p.ready() )
        {
            String[] line = s2p.readLine().split("\\s+");
            String[] newArr = Arrays.copyOfRange(line,1,line.length);
            TreeSet<String> newSet = new TreeSet<String>();
            for ( String word : newArr )
            {
                newSet.add(word);
            }
            state2Presidents.put(line[0], newSet);
        }
        s2p.close();
        for ( String element : state2Presidents.keySet() )
        {
            System.out.print(element + " ");
            for ( String value : state2Presidents.get(element) )
                System.out.print(value + " ");
            System.out.println();
        }
        System.out.println();
        // step 2
        BufferedReader allP = new BufferedReader( new FileReader( "allPresidents.txt" ) );
        ArrayList<String> pList = new ArrayList<String>();
        while ( allP.ready() )
        {
            String line = allP.readLine();
            pList.add(line);
        }
        allP.close();
        Collections.sort(pList);
        TreeSet<String> lonelyPresidents = new TreeSet<String>();
        for ( String name : pList )
        {
            boolean lonely = false;
            for ( String state : state2Presidents.keySet() )
            {
                if ( state2Presidents.get(state).contains(name) )
                {
                    System.out.println(name + " " + state);
                    lonely = true;
                }
            }
            if ( !lonely )
                lonelyPresidents.add(name);
        }
        System.out.println();
        // step 3
        for ( String pres : lonelyPresidents )
        {
            System.out.println(pres);
        }
        System.out.println();
        // step 4
        BufferedReader allStates = new BufferedReader( new FileReader( "allStates.txt" ) );
        TreeSet<String> states = new TreeSet<String>();
        while ( allStates.ready() )
        {
            String state = allStates.readLine();
            if ( state2Presidents.get(state) == null )
                states.add(state);
        }
        allStates.close();
        for ( String word : states )
        {
            System.out.println(word);
        }
    }
}
