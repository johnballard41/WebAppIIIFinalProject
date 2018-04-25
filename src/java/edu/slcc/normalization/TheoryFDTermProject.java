package edu.slcc.normalization;

import java.util.ArrayList;
import java.util.Arrays;

public class TheoryFDTermProject
{

    /**
     * Eliminates redundant attributes from set of FDs
     *
     * @param fds set of FDs where the RHS is singleton
     * @param token what separates the LHS form the RHS of the FD: AB->C
     * @return An array list where the FDs of the RHS have no redundant
     * attributes or null of the parameter array fds is empty.
     */
    public static ArrayList<String> eliminateRedundantAttibutes(ArrayList<String> fds, String token)
    {
        //> if no FDs reurn null
        if (fds.isEmpty())
          {
            return null;
          }
        for (int index = 0; index < fds.size(); ++index)
          {
            String fd = fds.get(index); 
            //> get x of X -> Y
            String x = TheoryFDTermProject.LHSofFD(fd, token);

            //> get y of X -> Y
            String y = TheoryFDTermProject.RHSofFD(fd, token);

            //>traverse the X
            for (int i = 0; i < x.length(); ++i)
              {
                //>> create an array of characters in X leaving the character at i out
                String[] xWithoutA = x.split(Character.toString(x.charAt(i)));

                //>>traverse xWithoutA
                for (int j = 0; j < xWithoutA.length; ++j)
                  {
                    //>>> clone the parameter FDs, and remove the currect FD at index
                    ArrayList<String> fdsWithoutCurrentFD = (ArrayList<String>) fds.clone();
                    fdsWithoutCurrentFD.remove(index);

                    //>>> Compute the closure of X using all FDs except currect FD
                    String closureOfX = TheoryFDTermProject.closure(fdsWithoutCurrentFD, xWithoutA[j], token);

                    //>> If fthe closure of X contains ALL characters of LHS of FD
                    //  replace( set)  the  FD at index 
                    if (closureOfX.contains(Character.toString(x.charAt(i))))
                      {
                        String xWithoutRedundacy = "";
                        for (int k = 0; k < xWithoutA.length; ++k)
                          {
                            if ("".equals(xWithoutA[k].toString()) == false)
                              {
                                xWithoutRedundacy += xWithoutA[k].toString();
                              }
                          }
                        fds.set(index, xWithoutRedundacy + token + y);
                      }
                  }

              }
          }
        //>returrn the FDs without redundant attributes
        return fds;
    }



    /**
     * The Left Hand Side X, of fd X->Y
     *
     * @param fd X->Y
     * @param token any separator (-> i sour case)
     * @return the LHS of fd
     */
    private static String LHSofFD(String fd, String token)
    {
        return fd.substring(0, fd.indexOf(token));
    }

    private static String RHSofFD(String fd, String token)
    {
        return null;
    }

    /**
     * Calculates the closure a parameter a
     *
     * @param fds set of FDs
     * @param a set of attributes to compute its closure
     * @param token token that separates LHS from RHS of an FD
     * @return returns the closure of parm a or null if fds is empty.
     */
    public static String closure(ArrayList<String> fds, String a, String token)
    {
        //> return null if no FDs
        if (fds.isEmpty())
          {
            return null;
          }
        String originalClosure = a;
        String closure = a;
        boolean done = false;

        int i = 0;
        //> do forever, treverse all FDs startingg at i =0
        while (true)
          {
            //>>  if i reaches size of FDs you are done
            if (i >= fds.size())
              {
                break;
              }
            //>> get X and Y of X->Y
            String x = TheoryFDTermProject.LHSofFD(fds.get(i), token);
            String y = TheoryFDTermProject.RHSofFD(fds.get(i), token);

            //>>if the clousre so far, conatnt the Y of the cirrect FD, then go to next FD
            if (closure.contains(y) && i < fds.size())
              {
                ++i;
                continue;
              }

            boolean charInClousre = true;
            //>>if the closure so far, does not contain all of X of X->Y
            // set boolean charInClousre to false
            for (int j = 0; j < x.length(); ++j)
              {
                if (closure.contains(Character.toString(x.charAt(j))) == false)
                  {
                    charInClousre = false;
                    break;
                  }
              }

            //>> if we are here, then the  closure so far, contains all of X, of fd  X->Y
            // so add to closure so far, the Y of fd X->Y
            if (charInClousre == true)
              {
                for (int j = 0; j < y.length(); ++j)
                  {
                    if (closure.contains(Character.toString(y.charAt(j))) == false)
                      {
                        closure += y.charAt(j);
                      }
                  }
                //>>> sort the closure
                closure = sortString(closure);

                //>>> start the  first FD
                i = 0;
              }
            //>get next FD
            i++;
          }

        //> retun closure of parm a
        return closure;
    }


    /**
     * Determines if A is inside B
     *
     * @param A set of attributes
     * @param B set of attributes
     * @return true of B contains A, false otherwise
     */
    private static boolean isAllOfAInsideB(String A, String B)
    {
        for (int i = 0; i < A.length(); ++i)
          {
            if (B.contains(Character.toString(A.charAt(i))) == false)
              {
                return false;
              }
          }
        return true;

    }

    /** Sorts the parameter in ascending order
     *
     * @param s to be sorted
     * @return sorted s
     */
    private static String sortString(String s)
    {

        Character[] charObjectArray
                = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

        Arrays.sort(charObjectArray);
        String ret = "";
        for (int i = 0; i < charObjectArray.length; ++i)
          {
            ret += charObjectArray[i].toString();
          }

        return ret;

    }
    /**Calculates the minimal cover given a set of FDs 
     * 
     * @param fds FDS
     * @param token  "->"
     * @return minimal cover as array list
     */
    public static ArrayList<String> minimalCover(ArrayList<String> fds, String token)
    {
             //> if no FDs reurn null
        if (fds.isEmpty())
          {
            return null;
          }
        ArrayList<String> ar =  ( ArrayList<String>) fds.clone();
        ar = TheoryFD.eliminateRedundantAttibutes(ar, token);
        ar = TheoryFD.eliminateRedundantFDs(ar, token);
        return ar;
    }
      /**
     * Makes all the Right hand sides of fds singletons
     *
     * @param fds the set of FDs to make their RHS singletons
     * @param token what separates LHS from RHS of FD
     * @return an array list where all RHS of FDs are singletons
     */
    private static ArrayList<String> makeRHSsinglenton(ArrayList<String> fds, String token)
    {
        return null;
    }
      /**
     * Eliminates redundant FDs from set of FDs
     *
     * @param fds set of FDs where the RHS is singleton
     * @param token what separates the LHS form the RHS of the FD: AB->C
     * @return An array list where the FDs of the RHS have no redundant FDs or
     * null of the parameter array fds is empty.
     */
    public static ArrayList<String> eliminateRedundantFDs(ArrayList<String> fds, String token)
    {
        
        return null;
    }
    public static void main(String[] args)
    {
        System.out.println( TheoryFDTermProject.LHSofFD(("ABC->D"), "->") );
        /*
        ArrayList<String> fds = new ArrayList();

        fds.add("A->Bcdf");
        fds.add("C->D");
        fds.add("BA->adE");
        fds.add("BD->F");
        fds.add("EF->Ba");
        fds.add("BE->ABC");
        /*
        fds.add("AB->C");
        fds.add("C->A");
        fds.add("BC->D"); 
        fds.add("ACD->B"); 
        fds.add("D->E");
        fds.add("D->G");
        fds.add("BE->C");
        fds.add("CG->B");
        fds.add("CG->D");
        fds.add("CE->A");
        fds.add("CE->G");
         */
/*
        fds = TheoryFDTermProject.makeRHSsinglenton(fds, "->");
        //fds = eliminateRedundantAttibutes(fds, "->");
        //fds = eliminateRedundantFDs(fds, "->");
        for (String s : fds)
          {
            System.out.println(s);
          }
*/
    }
}
