package state;

public abstract class TimerState {
	protected abstract String getTime();
	
	public abstract TimerState increment();
	
	public String print() {
		return getTime();
	}
	
}
