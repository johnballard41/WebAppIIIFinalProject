
package edu.slcc.normalization;



import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASDV2
 */
public class RegEx
{

    
    public static void regEx( String... args)
    {
        Pattern p = Pattern.compile( args[0] );
        Matcher m = p.matcher( args[1]);
        
        System.out.println( "\nsource: " + args[1] );
        System.out.println( "expression :" + m.pattern() );
        System.out.print( "match positions :" );
        
        while ( m.find() )
             System.out.print( m.start() + " " );
        
         System.out.println("");       
    }
    
        public static void regEx2( String... args)
    {
        Pattern p = Pattern.compile( args[0] );
        Matcher m = p.matcher( args[1]);
        
        System.out.println( "\nsource: " + args[1] );
        System.out.println("pattern: " + m.pattern());
        while ( m.find() )
             System.out.println( m.start() + " "  +  m.group());
        
         System.out.println("");       
    }
        
     public static void scannerToTokenize( String ... args)
     {
         System.out.print("input: ");
         System.out.flush();
      
         Scanner s = new Scanner( System.in);
         String token;
         do
            {
            
            token = s.findInLine( args[0]);
            System.out.println("found " + token );

            }
         while ( token != null );
         
         
     }
     
     
     public static void scannerSplit( String ... args)
     {
         String[] tokens = args[0].split( args[1]);
         System.out.println("count " + tokens.length);
         
         for ( String s : tokens )
             System.out.println( ">" + s + "<");
             
     }
     
     
     public static void scannerTokens( String ... args)
     {
         boolean b2, b;
         int i;
         String s, hits = " ";
         Scanner s1 = new Scanner( args[0] );
         Scanner s2 = new Scanner( args[0] );
         
         while ( b = s1.hasNext()  == true)
            {
            s = s1.next();
            hits += "s";
            }
         
        while ( b = s2.hasNext() )
        {
            if ( s2.hasNextInt())
                {
                i = s2.nextInt();
                hits += "i";                
                }
            else if ( s2.hasNextBoolean())
                {
                b2 = s2.nextBoolean();
                hits += "b";                
                }       
            else 
                {
                s2.next();
                hits += "s2";                
                }
        }
        System.out.println("hits " + hits);
     }
     
     
     public static void stringTokenizerClass(String... args)
     {
         StringTokenizer st = new StringTokenizer("a bc d e ");
         System.out.println( "\n" + st.countTokens() );
         
     
         while ( st.hasMoreTokens() )
              System.out.print(">" + st.nextToken()  + "< ");         
          System.out.println( "\n" + st.countTokens() );
         
        
        StringTokenizer st2 = new StringTokenizer("a b cab a ba d", "a");
         System.out.println( "\n" + st2.countTokens() );
         
         
         while ( st2.hasMoreTokens() )
              System.out.print(">" + st2.nextToken()  + "< ");
                  
          System.out.println( "\n" + st2.countTokens() );}
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String regX ="[A-Z]+->[A-Z]{1}";
        System.out.println("A->A".matches(regX));
    //stringTokenizerClass();
    //scannerTokens( "1 true 34 hi");
        
     //regEx( "ab", "abaaaba");
     /*
     regEx( "aba", "abababa");
     regEx("\\d","a12c3e456f" );
     regEx("\\w","a 1 568_Za" );
     regEx("\\w","_a" );
     regEx("\\w","_ab" );
     regEx("\\w","_abC" );
     regEx("\\d", "123");
     regEx("\\D", "123a");
     regEx("\\S", "w1w w$ @#w1");
     regEx("\\s", "w1w w$ @#w1");
         
     regEx("\\b", "w2w w$ @#w2");
*/
     ///////////////////////////////
     //regEx2("\\d+", "1 a12 234b");
     //regEx2("proj1[^,]*", "proj3.txt,proj1sched.pdf,proj1,proj2,proj1.java");
        
     //regEx2("a.c", "ac abc a c");
        
        //regEx2(".*xx", "yyxxxyxx");
        //regEx2(".*?xx", "yyxxxyxx");
        
        //scannerToTokenize( "\\d\\d");
        //scannerSplit( "ab5 ccc 45 @", "\\d");
     
     
    }
    
}
