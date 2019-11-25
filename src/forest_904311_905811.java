import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileReader;
import java.io.FileWriter;

public class forest_904311_905811 {

	public static void main(String[] args) {
		if (args.length > 1 || args.length == 0) {
			System.out.println("en evales i evales pano apo 2 arguments bye");
			System.exit(0);

		} 
		double d = 10;
		Graph g = new Graph(d);
		try {
			File file = new File(args[0]);
			Scanner sc = new Scanner(file);
//			long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//			long startTime = System.currentTimeMillis();
		
			while (sc.hasNextLine()) {// insert the dictionary to the trie
				String str = sc.nextLine();
				int x = Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(",")));
				int y = Integer.parseInt(str.substring(str.indexOf(",") + 1, str.indexOf(")")));
				int id = Integer.parseInt(str.substring(0, str.indexOf("(")));
				int temp = Integer.parseInt(str.substring(str.indexOf(")") + 1, str.length() - 1));
				node n = new node(x, y, id, temp);
				g.addNode(n);
			}
			
			
//			long endTime = System.currentTimeMillis();
//			long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//			long actualMemUsed = afterUsedMem - beforeUsedMem;
//			System.out.println("i used " + actualMemUsed + " bytes for the 904311_trie");
//			System.out.println("\nIt took me  " + (endTime - startTime) + " ms to build the 904311_trie");
			sc.close();
		} catch (

		FileNotFoundException e) {
			System.out.println("i couldnt find the file");
			System.exit(0);
		}
		
		int option = 1;
		Scanner in = new Scanner(System.in);
	
			
			try {
				while (option != 7) {
					System.out.println("please choose your option :");
					System.out.println("1 : Calculate  minnimun spanninng tree");
					System.out.println("2 : print minimun spanning tree  ");
					System.out.println("3 : insert new node");
					System.out.println("4 : remove node ");
					System.out.println("5 : chenge temp of node");
					System.out.println("6 : get tempreture starting from A  to B.");
					System.out.println("7 : Exit");
					System.out.print("your choice is : ");
					option = in.nextInt();
					switch (option) {
					case 1:
						g.calcSpanTree();
						System.out.println();
						continue;
					case 2:
						System.out.println(g.getMst().toString());
						System.out.println();
						continue;

					case 3:
						System.out.println("dose m id :");
						int id= in.nextInt();
						System.out.println("dose m x :");
						int x= in.nextInt();
						System.out.println("dose m y :");
						int y= in.nextInt();
						System.out.println("dose m temp :");
						double t= in.nextDouble();
						g.addNode(x, y, id, t);
						System.out.println();
						continue;

					case 4:
						System.out.println("dose m id :");
						id= in.nextInt();
						g.removeNode(id);
						System.out.println();
					case 5:
						System.out.println("dose m id :");
						id= in.nextInt();
						System.out.println("dose m temp :");
						double temp = in.nextDouble();
						g.search(id).setTemp(temp);
						System.out.println();
						continue;
					case 6:
						transferFromAtoB()
						System.out.println();
						continue;
					case 7:
						System.out.println("bye bye!");
						continue;
					default:
						System.out.println("this is not a correct answer");
						continue;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("this is not an int");
				option = 1;
			} catch (NoSuchElementException e2) {
				System.out.println("what is happening?");
				System.exit(0);
			} catch (IllegalStateException e3) {
				System.out.println("what is happening?");
				System.exit(0);

			}
	
			File file = new File(args[0]);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < g.getHashtable().length; i++) {
			LinkedList<node> list = g.getHashtable(i);
			if (list.isEmpty() == true) {
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
			writer.write(list.get(j).toString()+'\n');
			}
		}
			
		
	}

	ArrayDeque<edge> transferFromAtoB(int idA, int idB, Graph g) {
		if (idA == idB) {
			System.out.println("they are the same node");
			return null;
		}
		Queue<ArrayDeque<edge>> q = new LinkedList<ArrayDeque<edge>>();
		ArrayList<edge> e = searchThroughEdgesForMatch(idA, g);
		ArrayDeque<edge> temp = 
		while (!q.isEmpty()) {
			temp = q.remove();
			if (temp.getLast().getN1().getId() == idB || temp.getLast().getN2().getId() == idB) {
				return temp;
			}
			ArrayDeque<edge> copiedArrayDeque = (ArrayDeque<edge>) temp.clone();
		e = searchThroughEdgesForMatch(temp.getLast().getN1().getId(), g);
		ArrayList<edge>e2 = searchThroughEdgesForMatch(temp.getLast().getN2().getId(), g);
			e.addAll(e2);
			for (int i = 0; i < e.size(); i++) {
				ArrayDeque<edge> temp2 = temp.clone();
				temp2.add(e.get(i));
				q.add(temp2);
			}
		}
		return null;
	}

	boolean containsNode(int idA, edge e) {
		return e.getN1().getId() == idA || e.getN2().getId() == idA;
	}

	ArrayList<edge> searchThroughEdgesForMatch(int idA, Graph g) {
		ArrayList<edge> e = new ArrayList<edge>();
		for (int i = 0; i < g.getMst().getMedges.length; i++) {
			if (!g.getMst().getTaken[i]) {
				if (containsNode(idA, g.getMst().getMedges[i])) {
					e.add(g.getMst().getMedges[i]);
					g.getMst().getTaken[i] = true;
				}
			}
		}
		return e;
	}

}
