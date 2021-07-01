/* Lab0.java

	compilation:         C:\> javac Lab0.java
	sample execution:    C:\> java Lab0 Tim Hoffman hoffmant@pitt Computer Science SCI
*/

public class Lab0
{
	public static void main( String[] args )
	{
		for (int i=0 ; i<args.length ; ++i)
			System.out.format( "args[%d] %s\n", i, args[i] );
	}
	/* USE THIS PROGRAM AS A TESTER TO VERIFY YOUR JAVA INSTALLATION.
	   HAND IN THIS PROGRAM AS YOUR VERIFICATION THAT YOUR ID IS IN THE HANDIN SYSTEM_EXCEPTION
	*/
}