package template;

import main.Pomodoro;

public class PomodoroTemplate extends TimerTemplate {
	public PomodoroTemplate(Pomodoro pomodoro) {
		super(pomodoro);
	}
	
	@Override
	public void reset() {
		pomodoro.skip.setVisible(false);
	}
}
