package state;

public class Start extends TimerState{
	TimerState Source;
	int second;
	int minute;
	int elapsedTime;
	public Start(TimerState Source , int second , int minute, int elapsedTime) {
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
	public TimerState increment() {
		if(second >= 60) {
			elapsedTime = elapsedTime+1000;
			return new Start(this, (elapsedTime/1000) % 60  , (elapsedTime/60000) % 60 , elapsedTime);
		} else {
			elapsedTime = elapsedTime+1000;
			return new Start(this, (elapsedTime/1000) % 60  , (elapsedTime/60000) % 60 , elapsedTime);
		}
	}

}
