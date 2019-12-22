package cs146F19.Sunilkumar.project2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

class MaxSubarrayTest extends TestCase{
	
	BufferedReader In;
	
	@Test
	public void test() throws IOException {
		
		
		//testing for array with both positive and negative integers
		MaxSubarray m = new MaxSubarray(17, 1, 4);
		MaxSubarray n = new MaxSubarray(17, 1, 4);
		int[] a = new int[8];
		a[0] = -9;
		a[1] = 10;
		a[2] = -8;
		a[3] = 10;
		a[4] = 5;
		a[5] = -4;
		a[6] = -2;
		a[7] = 5;
		assertEquals(m, m.algorithmA(a));
		assertEquals(m, m.algorithmB(a, 0, 7));
		assertEquals(m, m.algorithmC(a));
		assertEquals(true, m.equals(n)); //testing equals method
		
		//testing array with all positive integers
		MaxSubarray p = new MaxSubarray(8, 0, 7);
		MaxSubarray q = new MaxSubarray(7, 0, 7);
		int[] b = new int[8];
		b[0] = 1;
		b[1] = 1;
		b[2] = 1;
		b[3] = 1;
		b[4] = 1;
		b[5] = 1;
		b[6] = 1;
		b[7] = 1;
		assertEquals(p, p.algorithmA(b));
		assertEquals(p, p.algorithmB(b, 0, 7));
		assertEquals(p, p.algorithmC(b));
		assertEquals(false, p.equals(q));
		
		//testing array with all negative integers
		MaxSubarray x = new MaxSubarray(0, -1, -1);
		int[] c = new int[3];
		c[0] = -1;
		c[1] = -1;
		c[2] = -1;
		assertEquals(x, x.algorithmA(c));
		assertEquals(x, x.algorithmB(c, 0, 2));
		assertEquals(x, x.algorithmC(c));
		
		//testing array with only one element (negative)
		MaxSubarray y = new MaxSubarray(0, -1, -1);
		int[] d = new int[1];
		d[0] = -1;
		assertEquals(y, y.algorithmA(d));
		assertEquals(y, y.algorithmB(d, 0, 0));
		assertEquals(y, y.algorithmC(d));
		
		//testing array with only one element (positive)
		MaxSubarray z = new MaxSubarray(5, 0, 0);
		int[] e = new int[1];
		e[0] = 5;
		assertEquals(z, z.algorithmA(e));
		assertEquals(z, z.algorithmB(e, 0, 0));
		assertEquals(z, z.algorithmC(e));
		
		//testing array with only zeroes
		MaxSubarray t = new MaxSubarray(0, -1, -1);
		int[] f = new int[4];
		f[0] = 0;
		f[1] = 0;
		f[2] = 0;
		f[3] = 0;
		assertEquals(t, t.algorithmA(f));
		assertEquals(t, t.algorithmB(f, 0, 3));
		assertEquals(t, t.algorithmC(f));
		
		//testing all the cases in the input text file
		In=new BufferedReader (new FileReader ("/Users/preranasunilkumar/Desktop/cs146/Projects/src/cs146F19/Sunilkumar/project2/maxSumtest.txt")); 
		String line = In.readLine();
		
		while(line!=null) {
			line = line.trim(); //getting rid of spaces in the beginning
			String[] split = line.split("  ");
	
			//filling array with integers
			int[] testArray = new int[100];
			for(int i=0; i<100; i++) {
				testArray[i] = Integer.parseInt(split[i]);
			}
			
			//getting rid of extra space before the max sum 
			split[100] = split[100].trim();

			int sum = Integer.parseInt(split[100]);
			int arrival = Integer.parseInt(split[101]);
			int departure = Integer.parseInt(split[102]);
			
			MaxSubarray expected = new MaxSubarray(sum, arrival, departure);

			assertEquals(expected, expected.algorithmA(testArray));
			assertEquals(expected, expected.algorithmB(testArray, 0, 99));
			assertEquals(expected, expected.algorithmC(testArray));
			
			In.readLine();
			line = In.readLine();
		}
		
		//running tests on different array sizes to analyse their run times
		Random r = new Random();
		
		System.out.println("Brute force run times");
		int[] sizes1 = {100, 200, 500, 1000};
		for(Integer size: sizes1) {
			int sum = 0;
			for(int trials=0; trials<10; trials++) {
				int[] arr = new int[size];
				for(int i=0; i<size; i++) {
					arr[i] = r.nextInt(10);
				}
				long start = System.nanoTime();
				m.algorithmA(arr);
				long end = System.nanoTime();
				sum = sum + (int)(end-start);
			}
			int avg = sum/10;
			System.out.println("time for array of size "+size+" is "+avg);
		}
		
		System.out.println();
		System.out.println("Divide and Conquer run times");
		int[] sizes2 = {100, 200, 500, 1000, 2000, 5000, 10000};
		for(Integer size: sizes2) {
			int sum = 0;
			for(int trials=0; trials<10; trials++) {
				int[] arr = new int[size];
				for(int i=0; i<size; i++) {
					arr[i] = r.nextInt(10);
				}
				long start = System.nanoTime();
				m.algorithmB(arr, 0, size-1);
				long end = System.nanoTime();
				sum = sum + (int)(end-start);
			}
			int avg = sum/10;
			System.out.println("time for array of size "+size+" is "+avg);
		}
		
		System.out.println();
		System.out.println("Kadane's algorithm run times");
		int[] sizes3 = {100, 200, 500, 1000, 2000, 5000, 10000};
		for(Integer size: sizes3) {
			int sum = 0;
			for(int trials=0; trials<10; trials++) {
				int[] arr = new int[size];
				for(int i=0; i<size; i++) {
					arr[i] = r.nextInt(10);
				}
				long start = System.nanoTime();
				m.algorithmC(arr);
				long end = System.nanoTime();
				sum = sum + (int)(end-start);
			}
			int avg = sum/10;
			System.out.println("time for array of size "+size+" is "+avg);
		}
		

	}

}
