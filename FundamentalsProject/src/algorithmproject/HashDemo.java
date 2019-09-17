package algorithmproject;


import java.util.*;

public class HashDemo{
   public static void main(String[] args) {
	   int counter = 0;
      String[] data = new String("nothing is as easy as it looks").split(" ");

      HashSet<String> myHashSet = new HashSet<String>();
      //LinkedHashSet<String> lhs = new LinkedHashSet<String>();
      //TreeSet<String> ts = new TreeSet<String>();

      HashMap<Integer,String> myHashMap = new HashMap<Integer,String>();
      //TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
      //LinkedHashMap<String, Integer> lhm = new LinkedHashMap<String, Integer>();

      for (String x : data) {
         counter++;
         myHashMap.put(counter, x);
         //tm.put(x, freq == null ? 1 : freq + 1);
         //lhm.put(x, freq == null ? 1 : freq + 1);

         myHashSet.add(x);
         //ts.add(x);
         //lhs.add(x);
      }

      /* this prints the HashSet */
      System.out.println("Hash map: " + myHashMap);
      System.out.println("Hash map size: " + myHashMap.size());
      System.out.println("Hash map value with key 1: " + myHashMap.get(1));
      System.out.println();
      
      System.out.println("Hash  set: " + myHashSet);
      System.out.println("Hash  set: " + myHashSet.size());
      System.out.println();
      
      System.out.println(myHashMap.size() + " distinct words detected:");
      System.out.println();

      /* this prints the hashtable in sorted order*/
      //System.out.println("Tree set: " + ts);
      //System.out.println();

      /* this prints the hashtable in the order items were inserted */
      //System.out.println("Linked set: " + lhs);
      //System.out.println();    
      

      //System.out.println("Tree map: " + tm);
      //System.out.println();

      //System.out.println("Linked map: " + lhm);
      //System.out.println();
   }
}

