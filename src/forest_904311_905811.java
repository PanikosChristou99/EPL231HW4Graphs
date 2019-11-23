import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class forest_904311_905811 {

	public static void main(String[] args) {
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
		
		//printToFile();

	}

	
}
