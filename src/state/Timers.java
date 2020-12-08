package state;

public class Timers {
	private TimerState currentState;
	
	public Timers(int elapsedTime) {
		currentState = new Start(currentState, (elapsedTime / 1000) % 60 , (elapsedTime / 60000) % 60, elapsedTime, "pomodoro");
	}
	
	public void Start() {
		currentState = currentState.decrement();
	}
	
	public String printTime() {
		return currentState.print();
	}
	
	public int currentTime()
	{
		return currentState.currentTime();
	}
	
	public void resetTime() {
		currentState = currentState.resetTime();
	}
	
	public void pomodoro() {
		currentState = currentState.pomodoro();
	}
	
	public void shortBreak() {
		currentState = currentState.shortBreak();
	}
	
	public void longBreak() {
		currentState = currentState.longBreak();
	}
}
