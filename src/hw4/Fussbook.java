package hw4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class Fussbook {
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("/Users/annie/eclipse-workspace/cpre310 hw4/bin/hw4/network_instaface.txt");
		// scanner that scans through file line by line
		Scanner scan = new Scanner(file);
		// Stores all the nodes in type integer to ensure there's no duplicates, and as
		// reference of indexes
		ArrayList<Integer> total = new ArrayList<Integer>();
		// Store nodes as type person in order to
		ArrayList<person<Integer>> nodes = new ArrayList<person<Integer>>(100);
		// Checks whether there are more lines in file
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			// Scanner that checks through each line
			Scanner lineScan = new Scanner(line);
			// Make sure that the next element is an int
			if (lineScan.hasNextInt()) {
				Integer a = lineScan.nextInt();
				// Skip through strings until reaching second int
				while (!lineScan.hasNextInt()) {
					lineScan.next();
				}
				Integer b = lineScan.nextInt();
				// Store person to arraylists if the person have not appear in previous lines
				if (!total.contains(a)) {
					nodes.add(new person<Integer>(a));
					total.add(a);
				}
				if (!total.contains(b)) {
					nodes.add(new person<Integer>(b));
					total.add(b);
				}
				// record the edge
				nodes.get(total.indexOf(a)).connect(nodes.get(total.indexOf(b)));
				nodes.get(total.indexOf(b)).connect(nodes.get(total.indexOf(a)));
			}
			lineScan.close();
		}
		scan.close();
		// Count total degree and find the most well-connected person
		int edges = 0;
		int mostDegree = 0;
		person<Integer> popular = null;
		for (int i = 0; i < nodes.size(); i++) {
			int degree = nodes.get(i).getDegree();
			if (degree > mostDegree) {
				mostDegree = degree;
				popular = nodes.get(i);
			}
			edges += degree;
		}
		// Number of edges = half of total degrees
		System.out.print("Nodes:" + nodes.size() + " Edges:" + edges / 2 + " Most well-connected person:" + popular);
	}

	// created type person to keep track of edges
	public static class person<E> {
		ArrayList<E> edges = new ArrayList<E>();
		int degree;
		E value;

		public person(E value) {
			this.value = value;
			edges.clear();
			degree = 0;
		}

		public boolean connect(person<E> friend) {
			// Checks for duplicate connections
			if (!edges.contains(friend.value)) {
				edges.add(friend.value);
				degree++;
				return true;
			}
			return false;
		}

		public int getDegree() {
			return degree;
		}

		public String toString() {
			String s = "";

			return s + value;
		}
	}
}
