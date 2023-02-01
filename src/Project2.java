import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP3530: Project 2 - Stacks and PriorityQ Queues
 * <p>
 * This project uses Stacks and Priority Queues to store, sort,
 * and display data from a user-input CSV file with 50 U.S. states'
 * statistics relating to population and COVID. Each row of the file
 * contains a state's name, capitol, region, number of U.S. House 
 * seats, population, COVID cases, COVID deaths, median household
 * income, and violent crime rate.
 * <p>
 * An empty stack and priority queue are each created, data is read 
 * in from the file to construct a new State object line-by-line, and 
 * each State is added to the stack and priority queue arrays in the
 * order in which they appear in the original file.
 * <p>
 * The Stack array maintains a last in, first out order.
 * <p>
 * The Priority Queue array is sorted in descending order based on
 * each State's COVID death rate, the state with lowest death rate
 * being at the end of the array with top priority. States are added
 * in sorted order with O(N) time complexity and removed with O(1) time
 * complexity due to top-priority element always being the last element 
 * of the array.
 * 
 * @author Miranda Weathers N01482572
 * @version 10/7/2021
 */

public class Project2 {

	/**
	 * main() scans in a file, creates empty stack and priority queue,
	 * constructs State objects and adds them to the stack and priority
	 * queue arrays, and prompts the user with a menu of 7 options to
	 * add, remove, and display the contents of the arrays, or exit.
	 * @param String[] args
	 */
	public static void main(String[] args) {
		
		//prompt user for CSV filename
		Scanner scan = new Scanner(System.in);
		System.out.print("Input file name: ");
		String fileName = scan.nextLine();
		
		//check if file exists
		Scanner readFile = null;
		try {
			readFile = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("\nFile not found.");
			System.exit(1);
		}//end try/catch
		
		Stack sStack = new Stack(); //create empty Stack
		PriorityQ sPQ = new PriorityQ(); //create empty Priority Queue
		
		readFile.nextLine(); //skip csv row 1 (labels)
		readFile.useDelimiter(",|\n");
		
		while(readFile.hasNext()) { //as long as file has more rows of data,
			State s = new State 	//create State object from file
					(
						readFile.next(), 		//name
						readFile.next(), 		//capitol
						readFile.next(), 		//region
						readFile.nextInt(), 	//house seats
						readFile.nextInt(), 	//population
						readFile.nextInt(), 	//COVID cases
						readFile.nextInt(), 	//COVID deaths
						readFile.nextInt(), 	//median household income
						readFile.nextDouble() 	//violent crime rate
					);
			sStack.push(s); //push new State to stack
			sPQ.insert(s); //insert new State to priority queue
		}//end while
		
		//variables for user-entered State constructor
		String n, c, r; 		//name, capitol, region
		int hs, p, cc, cd, mhi; //House seats, population, cases, deaths, income
		double vcr; 			//violent crime rate
		
		//display interactive menu to user
		boolean flag = true; //repeat menu (true) or quit (false)
		int choice = 0;
		Scanner menuIn = new Scanner(System.in);
		
		while(flag == true) {
			
			System.out.println("\n1. Print stack");
			System.out.println("2. Pop a state object from stack");
			System.out.println("3. Push a state object onto stack");
			System.out.println("4. Print priority queue");
			System.out.println("5. Remove a state object from priority queue");
			System.out.println("6. Insert a state object into priority queue");
			System.out.println("7. Exit\n");
			System.out.print("Enter the number of your choice: ");
			
			//check if input matches type int
			if(menuIn.hasNextInt()) {
				choice = menuIn.nextInt();
			} else {
				System.out.println("\nPlease enter an integer next time!");
				menuIn.next();
				continue;
			}//end if else
			
			switch(choice) {
			
			case 1: //Print stack
				sStack.printStack();
				break;
			
			case 2: //pop off Stack
				if(!sStack.isEmpty()) { //if Stack still has elements
					sStack.pop();
					System.out.println("\nState popped from stack.");
				} else {
					System.out.println("\nStack empty; can't pop.");
				}
				break;
			
			case 3: //push to Stack
				//first check if Stack has room
				if(!sStack.isFull()) { //as long as Stack isn't full,
					//prompt for state input: name, capitol, region, seats, pop, cases, deaths, mhi, vcr
					System.out.print("State Name: ");
					n = scan.nextLine();
					
					System.out.print("Capitol: ");
					c = scan.nextLine();
					
					System.out.print("Region: ");
					r = scan.nextLine();
					
					System.out.print("US House Seats: ");
					if(scan.hasNextInt()) { 	//check if input matches type
						hs = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("Population: ");
					if(scan.hasNextInt()) { 	//check if input matches type
						p = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("COVID-19 Cases: ");
					if(scan.hasNextInt()) { 	//check if input matches type
						cc = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("COVID-19 Deaths: ");
					if(scan.hasNextInt()) { 	//check if input matches type
						cd = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("Median Household Income: ");
					if(scan.hasNextInt()) { 	//check if input matches type
						mhi = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("Violent Crime Rate: ");
					if(scan.hasNextDouble()) { 	//check if input matches type
						vcr = scan.nextDouble();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					//State constructor
					State uState = new State(n,c,r,hs,p,cc,cd,mhi,vcr);
					sStack.push(uState); //push State to stack
					System.out.println("\nState pushed to stack.");
				} else {
					System.out.println("\nStack full; pop before adding a new state.");
				}//if else
				
				break;
			
			case 4: //print Priority Queue
				sPQ.printQueue();
				break;
			
			case 5: //remove from Priority Queue
				if(!sPQ.isEmpty()) { //if Priority Queue still has elements
					sPQ.remove();
					System.out.println("\nState removed from priority queue.");
				} else {
					System.out.println("\nPriority Queue empty; can't remove.");
				}
				break;
			
			case 6: //insert to Priority Queue
				if(!sPQ.isFull()) { //as long as Priority Queue isn't full,
					//prompt for state input: name, capitol, region, seats, pop, cases, deaths, mhi, vcr
					System.out.print("State Name: ");
					n = scan.nextLine();
					
					System.out.print("Capitol: ");
					c = scan.nextLine();
					
					System.out.print("Region: ");
					r = scan.nextLine();
					
					System.out.print("US House Seats: ");
					if(scan.hasNextInt()) { //check if input matches type
						hs = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("Population: ");
					if(scan.hasNextInt()) { //check if input matches type
						p = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("COVID-19 Cases: ");
					if(scan.hasNextInt()) { //check if input matches type
						cc = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("COVID-19 Deaths: ");
					if(scan.hasNextInt()) { //check if input matches type
						cd = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("Median Household Income: ");
					if(scan.hasNextInt()) { //check if input matches type
						mhi = scan.nextInt();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a whole number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					System.out.print("Violent Crime Rate: ");
					if(scan.hasNextDouble()) { //check if input matches type
						vcr = scan.nextDouble();
						scan.nextLine();
					} else {
						System.out.println("\nPlease enter a number; try again.");
						scan.nextLine();
						continue;
					}//end if else
					
					//constructor for new State object
					State uState = new State(n,c,r,hs,p,cc,cd,mhi,vcr);
					sPQ.insert(uState); //insert new State to Priority Queue
					System.out.println("\nState added to priority queue.");
				} else {
					System.out.println("\nPriority queue full; remove before adding a new state.");
				}
				break;
			case 7: //exit
				flag = false;
				break;
			default:
				System.out.println("\nPlease enter an integer between 1 - 7.");
				break;
			}//end switch
			
		}//end while (menu)
		
		System.out.println("\nGoodbye!");
		
		//close scanners
		scan.close();
		readFile.close();
		menuIn.close();
		
	}//main method

}//Project2 Class
