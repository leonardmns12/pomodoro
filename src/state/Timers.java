package state;

public class Timers {
	private TimerState currentState;
	
	public Timers(int elapsedTime) {
		currentState = new Start(currentState , (elapsedTime/1000) % 60 , (elapsedTime/60000) % 60, elapsedTime);
	}
	
	public void Start() {
		currentState = currentState.increment();
	}
	
	public String printTime() {
		return currentState.print();
	}
	
	public void resetTime() {
		currentState.resetTime();
	}
	
	public int currentTime()
	{
		return currentState.currentTime();
	}
	
	public void pomodoro() {
		currentState = currentState.pomodoro();
	}
	
	public void shortBreak() {
		currentState = currentState.shortBreak();
	}
}
