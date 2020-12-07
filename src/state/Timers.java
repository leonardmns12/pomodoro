package state;

public class Timers {
	private TimerState currentState;
	
	public Timers(int elapsedTime) {
		currentState = new Start(currentState, (elapsedTime / 1000) % 60 , (elapsedTime / 60000) % 60, elapsedTime);
	}
	
	public void Start() {
		currentState = currentState.increment();
	}
	
	public String printTime() {
		return currentState.print();
	}
}
