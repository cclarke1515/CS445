import java.io.*;
import java.util.*;

public class Knapsack
{
    public static void main( String[] args ) throws Exception
    {
        if ( args.length < 1 )
        {
            System.out.println("No input file detected");
            System.exit(0);
        }
        int[] arr = new int[16];
        int target;
        int j = 0;
        BufferedReader infile = new BufferedReader( new FileReader( args[0] ) );
        String line = infile.readLine();
        for ( String element : line.split(" ") )
        {
            arr[j] = Integer.parseInt(element);
            j++;
        }
        target = Integer.parseInt( infile.readLine() );
        infile.close();
        System.out.println(line);
        System.out.println(target);
        for ( int bitMap = 0; bitMap <= 0xFFFF; bitMap++ )
        {
            int sum = 0;
            String str = "";
            for ( int i = 0; i < arr.length; i++ )
            {
                if ( ( bitMap >>> i ) % 2 == 1 )
                {
                    sum += arr[i];
                    str += (arr[i] + " ");
                }
            }
            if ( sum == target )
                System.out.println(str);
        }
    } // EOF Main
} // EOF Class