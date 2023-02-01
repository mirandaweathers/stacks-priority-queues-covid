
/**
 * PriorityQ class implements a priority queue of State objects using
 * a sorted array. Priority is given to the state with the lowest
 * COVID death rate with the rest of the array being sorted in descending
 * order based on this statistic, calculated in the State class.
 * <p>
 * States being added to the priority queue are first compared to existing 
 * States, and are added such that removing a state will take constant time
 * because the last element in the array is the top priority State.
 * <p>
 * Before adding a State to the priority queue, the program ensures the
 * array is not already full; before removing, it's checked whether the
 * array is empty.
 * 
 * @author Miranda Weathers N01482572
 * @version 10/7/2021
 */

public class PriorityQ {
	
	private State[] statePQ;
	private int nItems;
	
	/**
	 * PriorityQ() constructs a new array of 50 State objects.
	 */
	public PriorityQ() {
		statePQ = new State[50];
		nItems = 0;
	}//PriorityQ() constructor
	
	/**
	 * insert() takes a State object and inserts it into the priority
	 * queue array, maintaining a sorted array based on each State's
	 * COVID death rate with lowest rated State being the last element
	 * and thus top priority.
	 * 
	 * @param State s
	 */
	public void insert(State s) { //O(N) method
		//lower death rate = higher priority
		int i;
		if(nItems==0)
			statePQ[nItems++] = s;
		else {
			for(i = nItems-1; i >= 0; i--) {
				if(s.getDeathRate() > statePQ[i].getDeathRate()) {
					statePQ[i+1] = statePQ[i];
				} else {
					break;
				}//if..else
			}//for
			statePQ[i+1] = s;
			nItems++;
		}//if..else
	}//insert()
	
	/**
	 * remove() removes the last State in the priority queue by 
	 * decrementing the index number and returns the State.
	 * 
	 * @return State at end of array
	 */
	public State remove() { //O(1) method
		return statePQ[--nItems];
	}//remove()
	
	/**
	 * printQueue() prints the priority queue array of States in
	 * a formatted list starting from top priority (lowest death 
	 * rate) to lowest priority (highest death rate).
	 */
	public void printQueue() {
		System.out.println("\nName           MHI           VCR           CFR           Case Rate     Death Rate");
		System.out.println("---------------------------------------------------------------------------------");
		for(int i = nItems-1; i > -1; i--) {
			//States Report data formatting
			System.out.printf("%-15s%-14d%-14.1f%-14.6f%-14.2f%-14.2f\n",
					statePQ[i].getName(),
					statePQ[i].getMhi(),
					statePQ[i].getVcr(),
					statePQ[i].getCfr(),
					statePQ[i].getCaseRate(),
					statePQ[i].getDeathRate()
					);
		}//for
		System.out.println("---------------------------------------------------------------------------------");
	}//printQueue()

	/**
	 * isEmpty() checks to see if the Priority Queue is empty.
	 * @return nItems==0
	 */
	public boolean isEmpty() {
		return nItems==0;
	}//isEmpty()

	/**
	 * isFull() checks to see if the Priority Queue is full.
	 * @return nItems==50
	 */
	public boolean isFull() {
		return nItems==50;
	}//isFull()
	
}//PriorityQ
