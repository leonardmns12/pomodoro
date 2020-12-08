package state;

public class Start extends TimerState {
	TimerState Source;
	int second;
	int minute;
	int elapsedTime;
	String status;
	
	public Start(TimerState Source, int second, int minute, int elapsedTime) {
		this.Source = Source;
		this.second = second;
		this.minute = minute;
		this.elapsedTime = elapsedTime;
	}

	@Override
	protected String getTime() {
		String scd = String.format("%02d", second);
		String mnt = String.format("%02d", minute);
		return mnt + ":" + scd;
	}
	
	@Override
	public int currentTime() {
		return elapsedTime;
	}
	
	public void resetTime() {
		second = 0;
		minute = 0;
		getTime();
	}
	
	@Override
	public TimerState increment() {
		elapsedTime = elapsedTime - 1000;
		return new Start(this, (elapsedTime / 1000) % 60, (elapsedTime / 60000) % 60, elapsedTime);
	}
	
	@Override
	public TimerState pomodoro() {
		elapsedTime = 1500000;
		elapsedTime = elapsedTime - 1000;
		return new Start(this, (elapsedTime / 1000) % 60, (elapsedTime / 60000) % 60, elapsedTime);
	} 
	
	@Override
	public TimerState shortBreak() {
		elapsedTime = 300000;
		elapsedTime = elapsedTime - 1000;
		return new Start(this, (elapsedTime / 1000) % 60, (elapsedTime / 60000) % 60, elapsedTime);
	}
}
