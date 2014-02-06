package applications;

public class MachineList {
	private Machine[] machine; // array of machines
	private int currentIndex;	
	
	public MachineList(int numMachines){
		machine = new Machine[numMachines];
		currentIndex = 0;
	}
	
	public Machine[] getMachine() {
		return machine;
	}

	public void setMachine(Machine[] machine) {
		this.machine = machine;
	}
	
	public Machine get(int index){
		return machine[index];
	}
	
	public void add(Machine input){
		machine[currentIndex] = input;
		currentIndex++;
	}
}
