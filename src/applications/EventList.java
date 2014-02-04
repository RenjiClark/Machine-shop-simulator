package applications;

class EventList {
    // data members
    private int[] finishTime; // finish time array

    // constructor
    // finishTime becomes length theNumMachines with each element being theLargeTime
    EventList(int theNumMachines, int theLargeTime) {
        if (theNumMachines < 1)
            throw new IllegalArgumentException("number of machines must be >= 1");
        finishTime = new int[theNumMachines];

        // all machines are idle, initialize with
        // large finish time
        for (int i = 0; i < theNumMachines; i++)
            finishTime[i] = theLargeTime;
    }

    /** @return machine for next event */
    int nextEventMachine() {
        // find first machine to finish, this is the
        // machine with smallest finish time
        int p = 0;
        int t = finishTime[0];
        for (int i = 1; i < finishTime.length; i++)
            if (finishTime[i] < t) {// i finishes earlier
                p = i;
                t = finishTime[i];
            }
        return p;
    }

    int nextEventTime(int theMachine) {
        return finishTime[theMachine];
    }

    void setFinishTime(int theMachine, int theTime) {
        finishTime[theMachine] = theTime;
    }
}