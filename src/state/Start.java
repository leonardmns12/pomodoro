package state;

public class Start extends TimerState {
	TimerState Source;
	int second;
	int minute;
	int elapsedTime;
	String mode;
	
	public Start(TimerState Source, int second, int minute, int elapsedTime, String mode) {
		this.Source = Source;
		this.second = second;
		this.minute = minute;
		this.elapsedTime = elapsedTime;
		this.mode = mode;
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
	
	@Override
	public TimerState resetTime() {
		if(mode.equals("shortbreak")) return shortBreak();
		else if(mode.equals("longbreak")) return longBreak();
		return pomodoro();
	}
	
	@Override
	public TimerState decrement() {
		elapsedTime = elapsedTime - 1000;
		return newTime();
	}
	
	@Override
	public TimerState pomodoro() {
		mode = "pomodoro";
		elapsedTime = 1500000;
		return newTime();
	} 
	
	@Override
	public TimerState shortBreak() {
		mode = "shortbreak";
		elapsedTime = 300000;
		return newTime();
	}
	
	@Override
	public TimerState longBreak() {
		mode = "longbreak";
		elapsedTime = 900000;
		return newTime();
	}
	
	public TimerState newTime() {
		return new Start(this, (elapsedTime / 1000) % 60, (elapsedTime / 60000) % 60, elapsedTime, mode);
	}
}
