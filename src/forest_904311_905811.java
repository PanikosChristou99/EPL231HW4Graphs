

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
import java.io.IOException;

public class forest_904311_905811 {

	public static void main(String[] args) throws IOException {
		if (args.length !=2) {
			System.out.println("en evales 2 arguments bye");
			System.exit(0);

		}
		double d = Integer.valueOf(args[0]);
		Graph g = new Graph(d);
		try {
			File file = new File(args[1]);
			Scanner sc = new Scanner(file);
//			long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//			long startTime = System.currentTimeMillis();

			while (sc.hasNextLine()) {// insert the dictionary to the trie
				String str = sc.nextLine();
				int x = Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(",")));
				int y = Integer.parseInt(str.substring(str.indexOf(",") + 2, str.indexOf(")")));
				int id = Integer.parseInt(str.substring(0, str.indexOf("(")-1));
				int temp = Integer.parseInt(str.substring(str.indexOf(")") + 2, str.length()));
				System.out.println(x + y+  id +temp );
				g.addNodeAdvance(x, y, id, temp);
				
				
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
System.out.println(g.toString());
g.getMst().createMinimumSpanningTree(g);
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
					g.getMst().createMinimumSpanningTree(g);
					System.out.println();
					continue;
				case 2:
					System.out.println(g.getMst().toString());
					System.out.println();
					continue;

				case 3:
					System.out.println("dose m id :");
					int id = in.nextInt();
					System.out.println("dose m x :");
					int x = in.nextInt();
					System.out.println("dose m y :");
					int y = in.nextInt();
					System.out.println("dose m temp :");
					double t = in.nextDouble();
					g.addNodeAdvance(x, y, id, t);
					System.out.println(g.toString());
					System.out.println();
					continue;

				case 4:
					System.out.println("dose m id :");
					id = in.nextInt();
					g.removeNode(id);
					System.out.println(g.toString());
					System.out.println();
				case 5:
					System.out.println("dose m id :");
					id = in.nextInt();
					System.out.println("dose m temp :");
					double temp = in.nextDouble();
					g.search(id).setTemp(temp);
					System.out.println(g.toString());
					System.out.println();
					continue;
				case 6:
					System.out.println("dose m id1 :");
					int id1 = in.nextInt();
					System.out.println("dose m id2 :");
					int id2 = in.nextInt();
					removeEdgesAndPrintPath(transferFromAtoB(id1, id2, g),id1);
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

		File file = new File(args[1]);
		 BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < g.getHashtable().length; i++) {
			LinkedList<node> list = g.getHashtable(i);
			if (list.isEmpty() == true) {
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
				try {
					writer.write(list.get(j).toString() + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}
		}
	}
	public static void removeEdgesAndPrintPath(ArrayDeque<edge> arE,int idA) {
		
		ArrayDeque<Integer> ar = new  ArrayDeque<Integer>();
		edge e = arE.pop();
		node other = returnOtherNode(e,idA);
		node want = returnOtherNode(e,idA);
		double  temp = want.getTemp();
		ar.push(other.getId());
		while(!arE.isEmpty()) {
			 e = arE.pop();
			 other = returnOtherNode(e,idA);
			 ar.push(other.getId());
		}
		System.out.println("the path is : \n");
		while (!ar.isEmpty()) {
			System.out.println(ar.pop().toString()+"\n");
		}
		System.out.println("and the temp of what you wanted is : " + temp);
	}
	public static node returnOtherNode(edge e,int idA) {
	if(e.getN1().getId()==idA) return e.getN2();
	else return e.getN1();
	}
	public static ArrayDeque<edge> transferFromAtoB(int idA, int idB, Graph g) {
		if (idA == idB) {
			System.out.println("they are the same node");
			return null;
		}
		Queue<ArrayDeque<edge>> q = new LinkedList<ArrayDeque<edge>>();
		ArrayList<edge> e = searchThroughEdgesForMatch(idA, g);
		ArrayDeque<edge> temp = new ArrayDeque<edge>();
		for (int i = 0; i <e.size(); i++) {
			temp = new ArrayDeque<edge>();
			temp.push(e.get(i));
			q.add(temp);
		}
		while (!q.isEmpty()) {
			temp = q.remove();
			if (temp.peek().getN1().getId() == idB || temp.peek().getN2().getId() == idB) {
				
				for (int i = 0; i < g.getMst().getMedges().size();i++) {
					g.getMst().getTaken().set(i, false);
				}
				return temp;
				
			}
		e = searchThroughEdgesForMatch(temp.peek().getN1().getId(), g);
		
		ArrayList<edge>e2 = searchThroughEdgesForMatch(temp.peek().getN2().getId(), g);
			
		e.addAll(e2);
		
			for (int i = 0; i < e.size(); i++) {
				ArrayDeque<edge> temp2 = temp.clone();
				temp2.add(e.get(i));
				q.add(temp2);
			}
		}
		for (int i = 0; i < g.getMst().getMedges().size();i++) {
			g.getMst().getTaken().set(i, false);
		}
		return null;
	}

	public static boolean containsNode(int idA, edge e) {
		return e.getN1().getId() == idA || e.getN2().getId() == idA;
	}
	
	public static ArrayList<edge> searchThroughEdgesForMatch(int idA, Graph g) {
		ArrayList<edge> e = new ArrayList<edge>();
		for (int i = 0; i < g.getMst().getMedges().size();i++) {
			if (!g.getMst().getTaken().get(i)) {
				if (containsNode(idA, g.getMst().getMedges().get(i))) {
					e.add(g.getMst().getMedges().get(i));
					g.getMst().getTaken().set(i, true);
				}
			}
		}
		return e;
		
	}

}

