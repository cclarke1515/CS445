import java.util.*;
import java.io.*;

public class Jumbles 
{
    public static void main( String[] args ) throws Exception
    {
        if ( args.length < 1 )
        {
            System.out.println("No input file(s) detected");
            System.exit(0);
        }
        HashMap<String, String> map = new HashMap<String, String>();
        BufferedReader dictionary = new BufferedReader( new FileReader( args[0] ) );
        while ( dictionary.ready() )
        {
            String dWord = dictionary.readLine();
            String dCanon = toCanonical(dWord);
            if ( !map.containsKey(dCanon) )
                map.put(dCanon, dWord);
            else
                map.put(dCanon, map.get(dCanon) + " " + dWord);
        }
        dictionary.close();
        ArrayList<String> jList = new ArrayList<String>();
        BufferedReader jumbles = new BufferedReader( new FileReader( args[1] ) );
        while ( jumbles.ready() )
        {
            String line = jumbles.readLine();
            jList.add(line);
        }
        jumbles.close();
        Collections.sort(jList);
        for ( String jWord : jList )
        {
            String jCanon = toCanonical(jWord);
            if ( map.get(jCanon) == null )
                System.out.println(jWord);
            else if ( map.get(jCanon).contains(" ") )
            {
                String[] jSplit = map.get(jCanon).split("\\s+");
                Arrays.sort(jSplit);
                System.out.println(jWord + " " + Arrays.toString(jSplit).replace("[", "").replace("]", "").replace(",", ""));
            }
            else
                System.out.println(jWord + " " + map.get(jCanon));
        }
    }// End of Main
    static String toCanonical( String word )
    {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }
}// End of Class Jumbles