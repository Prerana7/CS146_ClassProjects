package cs146F19.Sunilkumar.project1;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class DataShufflingTesting extends TestCase{
	
	BufferedReader Out;
	BufferedReader In;
	String expectedLine;
	
	@Test
	public void test() throws IOException {
		Out=new BufferedReader (new FileReader ("/Users/preranasunilkumar/Desktop/cs146/Projects/src/cs146F19/Sunilkumar/project1/SunilkumarPreranaShuffled.txt")); 
		In=new BufferedReader (new FileReader ("/Users/preranasunilkumar/Desktop/cs146/Projects/src/cs146F19/Sunilkumar/project1/Target2.txt")); 
		while ((expectedLine = In.readLine ()) != null) {
			String actualLine = Out.readLine ();
			assertEquals(expectedLine, actualLine);
		}
	}
	

}
