package state;

public abstract class TimerState {
	
	protected abstract String getTime();
	public abstract int currentTime();
	protected abstract void resetTime();
	protected abstract TimerState decrement();
	protected abstract TimerState pomodoro();
	protected abstract TimerState shortBreak();
	protected abstract TimerState longBreak();
	
	public String print() {
		return getTime();
	}
	
}
