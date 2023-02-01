/**
 * Stack class implements a stack of State objects using an unsorted
 * array. States may be added or removed after first checking that the
 * Stack is not full or empty, respectively. The Stack of States may
 * be printed in a formatted list.
 * 
 * @author Miranda Weathers N01482572
 * @version 10/7/2021
 */

public class Stack {
	
	private State[] stateStack;
	private int top;
	
	/**
	 * Stack() constructs a new Stack array of 50 State objects.
	 * Top counter is initialized to -1, indicating an empty array.
	 */
	public Stack() {
		stateStack = new State[50];
		top = -1;
	}//Stack()
	
	/**
	 * push() adds a State to the top of the Stack array, first
	 * incrementing the top element counter.
	 * @param State s
	 */
	public void push(State s) {
		//check if full done in menu
		stateStack[++top] = s;
	}//push()
	
	/**
	 * pop() removes the topmost Stack array element and 
	 * decrements top count.
	 * @return top element of Stack
	 */
	public State pop() {
		//check if empty done in menu
		return stateStack[top--];
	}//pop()
	
	/**
	 * printStack() prints the stack array of States in
	 * a formatted list starting from top (end) to bottom.
	 */
	public void printStack() {
		//print stack of States starting from top to 0
		System.out.println("\nName           MHI           VCR           CFR           Case Rate     Death Rate");
		System.out.println("---------------------------------------------------------------------------------");
		for(int i = top; i > -1; i--) {
			//States Report data formatting
			System.out.printf("%-15s%-14d%-14.1f%-14.6f%-14.2f%-14.2f\n",
					stateStack[i].getName(),
					stateStack[i].getMhi(),
					stateStack[i].getVcr(),
					stateStack[i].getCfr(),
					stateStack[i].getCaseRate(),
					stateStack[i].getDeathRate()
					);
		}//for
		System.out.println("---------------------------------------------------------------------------------");
	}//printStack()
	
	/**
	 * isEmpty() checks to see if the Stack is empty.
	 * @return whether top element is -1 (uninitialized Stack)
	 */
	public boolean isEmpty() {
		return (top == -1);
	}//isEmpty()
	
	/**
	 * isFull() checks to see if the Stack is full.
	 * @return whether top element is 49 (50 States in array)
	 */
	public boolean isFull() {
		return (top == 49);
	}//isFull()
	
}//end Stack class
