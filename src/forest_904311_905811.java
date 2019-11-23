import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileReader;
import cs.ac.ucy.cs.epl231.ID904311.homework2.trie_904311;

public class forest_904311_905811 {

	public static void main(String[] args) {
		File file = new File(args[0]);
		try {
			Scanner sc = new Scanner(file);
//			long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//			long startTime = System.currentTimeMillis();
			ArrayList<String> words = new ArrayList<>();
			String lineJustFetched = null;
			String[] wordsArray;
			int i = 0;
			ArrayList<Integer> idAr = new ArrayList<Integer>();
			ArrayList<Integer> xAr = new ArrayList<Integer>();
			ArrayList<Integer> yAr = new ArrayList<Integer>();
			ArrayList<Integer> tempAr = new ArrayList<Integer>();

			while (sc.hasNextLine()) {// insert the dictionary to the trie
				String str = sc.nextLine();
				int x = Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(",")));
				int y = Integer.parseInt(str.substring(str.indexOf(",") + 1, str.indexOf(")")));
				int id = Integer.parseInt(str.substring(0, str.indexOf("(")));
				int temp = Integer.parseInt(str.substring(str.indexOf(")") + 1, str.length() - 1));
				idAr.add(id);
				xAr.add(x);
				yAr.add(y);
				tempAr.add(temp);
			}
			for (int j = 0; j < idAr.size(); j++) {
				
			}
//			long endTime = System.currentTimeMillis();
//			long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//			long actualMemUsed = afterUsedMem - beforeUsedMem;
//			System.out.println("i used " + actualMemUsed + " bytes for the 904311_trie");
//			System.out.println("\nIt took me  " + (endTime - startTime) + " ms to build the 904311_trie");
		} catch (

		FileNotFoundException e) {
			System.out.println("i couldnt find the file");
			System.exit(0);
		}
		int option = 1;
		Scanner in = new Scanner(System.in);
		if (args.length > 1 || args.length == 0) {
			System.out.println("en evales i evales pano apo 2 arguments bye");
			System.exit(0);

		} else {
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
						// TODO calcSpanTree()
						System.out.println();
						continue;
					case 2:
						// TODO printSpanTree()
						System.out.println();
						continue;

					case 3:
						// TODO insertNode()
						System.out.println();
						continue;

					case 4:
						// TODO insertNode()
						System.out.println();
					case 5:
						// TODO removeNode()
						System.out.println();
						continue;
					case 6:
						// TODO changeTempOfNode()
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

		}

		// printToFile();

	}

}
