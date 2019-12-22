package cs146F19.Sunilkumar.project4;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.junit.Test;


public class RBTTester {

	@Test
    //Test the Red Black Tree
	public void test() {
		RedBlackTree rbt = new RedBlackTree();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str=     "Color: 1, Key:D Parent: \n"+
                        "Color: 1, Key:B Parent: D\n"+
                        "Color: 1, Key:A Parent: B\n"+
                        "Color: 1, Key:C Parent: B\n"+
                        "Color: 1, Key:F Parent: D\n"+
                        "Color: 1, Key:E Parent: F\n"+
                        "Color: 0, Key:H Parent: F\n"+
                        "Color: 1, Key:G Parent: H\n"+
                        "Color: 1, Key:I Parent: H\n"+
                        "Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
            
    }
	
	@Test
    //Test the Red Black Tree (trivial case)
	public void test2() {
		RedBlackTree rbt = new RedBlackTree();
        rbt.insert("x");
        rbt.insert("d");
        rbt.insert("f");
        rbt.insert("a");
        assertEquals("fdax", makeString(rbt));
        String str=     "Color: 1, Key:f Parent: \n"+
                        "Color: 1, Key:d Parent: f\n"+
                        "Color: 0, Key:a Parent: d\n"+
                        "Color: 1, Key:x Parent: f\n";
		assertEquals(str, makeStringDetails(rbt));
            
    }
	
	@Test
    //Test the Red Black Tree (trivial case)
	public void test4() {
		RedBlackTree rbt = new RedBlackTree();
        rbt.insert("a");
        rbt.insert("b");
        rbt.insert("c");
        assertEquals("bac", makeString(rbt));
        String str=     "Color: 1, Key:b Parent: \n"+
                        "Color: 0, Key:a Parent: b\n"+
                        "Color: 0, Key:c Parent: b\n";
		assertEquals(str, makeStringDetails(rbt));
		
		//testing the lookup method
		assertEquals(0, rbt.lookup("a").color);
		assertEquals(null, rbt.lookup("f"));
            
    }
	
	@Test
    //Spell check using a dictionary implemented as a red black tree
	public void test5() throws IOException {
		URL url = new URL("http://www.math.sjsu.edu/~foster/dictionary.txt");
		Scanner scan = new Scanner(url.openStream());
		RedBlackTree rbt = new RedBlackTree();
		long start = System.currentTimeMillis();
		while(scan.hasNext()) {
			String s = scan.next();
	        rbt.insert(s);
		}
		long end = System.currentTimeMillis();
		scan.close();
		
		System.out.println("time taken to create the dictionary is "+(end-start));
		
		//spell check a poem using this dictionary
		FileReader fr = new FileReader("/Users/preranasunilkumar/Desktop/cs146/Projects/src/cs146F19/Sunilkumar/project4/poem.txt");
		BufferedReader br = new BufferedReader(fr);
		int wordsNotFound = 0;
		String line = br.readLine();
		long start2 = System.currentTimeMillis();
		while(line!=null) {
			String[] arr = line.split(" ");
			for(String s: arr) {
				s = s.toLowerCase();
				if(rbt.lookup(s)==null) {
					wordsNotFound = wordsNotFound + 1;
				}
			}
			line = br.readLine();
		}
		long end2 = System.currentTimeMillis();
		br.close();
		fr.close();
		System.out.println("time taken for the spell-checking is "+(end2-start2));
		System.out.println("number of words not found in the dictionary = "+wordsNotFound);
		
		
		//testing spell check for a trivial case
		int n = 0;
		String[] arr2 = {"data", "structures", "and", "algorithms"};
		for(String s: arr2) {
			if(rbt.lookup(s)==null)
				n = n+1;
		}
		//n should be 1 since the dictionary does not contain the word 'and'
		System.out.println("number of words not found (trivial case) is "+n);
    }
	
    
    public static String makeString(RedBlackTree t)
    {
       class MyVisitor implements RedBlackTree.Visitor {
          String result = "";
          public void visit(RedBlackTree.Node n)
          {
             result = result + n.key;
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    public static String makeStringDetails(RedBlackTree t) {
    	{
    	       class MyVisitor implements RedBlackTree.Visitor {
    	          String result = "";
    	          public void visit(RedBlackTree.Node n)
    	          {
    	        	  if(!(n.key).equals("")) {
    	        		  if(n.parent==null)
        	        		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+"\n";
    	        		  else
    	        			  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
    	        	  }
    	          }
    	       };
    	       MyVisitor v = new MyVisitor();
    	       t.preOrderVisit(v);
    	       return v.result;
    	 }
    }
 }
  
