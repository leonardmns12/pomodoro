package state;

public abstract class TimerState {
	protected abstract String getTime();
	
	public abstract TimerState increment();
	public abstract TimerState shortBreak();
	public abstract TimerState longBreak();
	
	public String print() {
		return getTime();
	}
	
}
