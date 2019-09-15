package cs146F19.Sunilkumar.project1;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;

public class DataShuffling {
	
	//populates an ArrayList with the data from the input file
	public static ArrayList createArray(int rows) throws IOException {
		ArrayList<String> data = new ArrayList<String>();
		FileReader fr = new FileReader("/Users/preranasunilkumar/Desktop/cs146/Projects/src/cs146F19/Sunilkumar/project1/ErdosCA.txt");
		BufferedReader br = new BufferedReader(fr);
		br.readLine();
		for(int i=0; i<rows; i++) {
			data.add(br.readLine());
		}
		br.close();
		fr.close();
		return data;
	}
	
	//shuffles the data in the ArrayList
	public static ArrayList shuffle(ArrayList<String> data) {
		Random r = new Random();
		r.setSeed(20);
		
		for(int i=data.size()-1; i>0; i--) {

			String temp = data.get(i);
			int index = r.nextInt(i);

			data.add(i, data.get(index));
			data.remove(i+1);
			data.add(index, temp);
			data.remove(index+1);
		}
		return data;
	}
	
	//prints the shuffled data onto the specified output text file
	public static void printOutput(ArrayList<String> data) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("/Users/preranasunilkumar/Desktop/cs146/Projects/src/cs146F19/Sunilkumar/project1/SunilkumarPreranaShuffled.txt");
		for(String s: data) {
			out.println(s);
		}
		out.close();
	}
	
	public static void main(String[] args) throws IOException {
		
		//getting rid of the %, and the first two integers
		Scanner scan = new Scanner(Paths.get("/Users/preranasunilkumar/Desktop/cs146/Projects/src/cs146F19/Sunilkumar/project1/ErdosCA.txt"));
		scan.next();
		scan.nextInt();
		scan.nextInt();
		//saving the number of rows
		int rows = scan.nextInt();
		scan.close();

		//creating the ArrayList
		long l1 = System.currentTimeMillis();
		ArrayList<String> data = createArray(rows);
		long l2 = System.currentTimeMillis();
		System.out.println("Time taken to read and copy the input file into the array: "+(l2-l1));
		
		//Shuffling the data
		long l3 = System.currentTimeMillis();
		data = shuffle(data);
		long l4 = System.currentTimeMillis();
		System.out.println("time taken to shuffle the data: "+(l4-l3));

		//printing the output
		long l5 = System.currentTimeMillis();
		printOutput(data);
		long l6 = System.currentTimeMillis();
		System.out.println("time taken to write the shuffled data to an outpur file: "+(l6-l5));
		

	}
}
