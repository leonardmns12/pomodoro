package state;

public abstract class TimerState {
	
	protected abstract String getTime();
	protected abstract int getState();
	public abstract int currentTime();
	protected abstract void resetTime();
	protected abstract void skipState();
	protected abstract TimerState increment();
	protected abstract TimerState pomodoro();
	protected abstract TimerState shortBreak();
	protected abstract TimerState longBreak();
	
	public String print() {
		return getTime();
	}
	
	public int state() {
		return getState();
	}
	
}
