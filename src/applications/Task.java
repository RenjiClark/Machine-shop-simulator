package applications;

// top-level nested classes
class Task {
	private int machine;
	private int time;

	Task(int theMachine, int theTime) {
		machine = theMachine;
		time = theTime;
	}

	int getMachine() {
		return machine;
	}

	int getTime() {
		return time;
	}
}