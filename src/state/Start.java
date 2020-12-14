package state;

public class Start extends TimerState {
	TimerState Source;
	int second;
	int minute;
	int elapsedTime;
	int state = 0;
	String status;
	
	public Start(TimerState Source, int second, int minute, int elapsedTime , int state) {
		this.Source = Source;
		this.second = second;
		this.minute = minute;
		this.state = state;
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
		state = 1;
		getTime();
	}
	
	@Override
	public TimerState increment() {
		elapsedTime = elapsedTime - 1000;
		if(elapsedTime < 0) {
			changeState();
		}
		return new Start(this, (elapsedTime / 1000) % 60, (elapsedTime / 60000) % 60, elapsedTime , state);
	}
	
	@Override
	public TimerState pomodoro() {
		return new Start(this, (elapsedTime / 1000) % 60, (elapsedTime / 60000) % 60, elapsedTime , state);
	} 
	
	@Override
	public TimerState shortBreak() {
		return new Start(this, (elapsedTime / 1000) % 60, (elapsedTime / 60000) % 60, elapsedTime , state);
	}

	@Override
	protected TimerState longBreak() {
		return new Start(this, (elapsedTime / 1000) % 60, (elapsedTime / 60000) % 60, elapsedTime , 1);
	}
	
	public void changeState() {
		this.state++;
		if(state % 2 == 0 && state < 8) {
			elapsedTime = 300000;
			second = (elapsedTime / 1000) % 60;
			minute = (elapsedTime / 60000) % 60;
			System.out.println("starting shortBreak");
			shortBreak();
		} else if(state % 2 != 0 && state < 8) {
			elapsedTime = 1500000;
			second = (elapsedTime / 1000) % 60;
			minute = (elapsedTime / 60000) % 60;
			System.out.println("starting work");
			pomodoro();
		} else if(state == 8) {
			elapsedTime = 900000;
			second = (elapsedTime / 1000) % 60;
			minute = (elapsedTime / 60000) % 60;
			System.out.println("starting longBreak");
			longBreak();
		} else {
			elapsedTime = 1500000;
			pomodoro();
		}
	}

	@Override
	protected int getState() {
		return this.state;
	}

	@Override
	protected void skipState() {
		changeState();
	}
}
