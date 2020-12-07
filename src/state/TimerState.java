package state;

public abstract class TimerState {
	
	protected abstract String getTime();
	public abstract int currentTime();
	protected abstract void resetTime();
	protected abstract TimerState increment();
	protected abstract TimerState pomodoro();
	protected abstract TimerState shortBreak();
	
	public String print() {
		return getTime();
	}
	
}
