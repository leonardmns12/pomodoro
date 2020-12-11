package template;

import java.awt.Color;

import main.Pomodoro;

abstract class TimerTemplate {
	
	Pomodoro pomodoro;
	
	public TimerTemplate(Pomodoro pomodoro) {
		this.pomodoro = pomodoro;
	}
	
	public void setView(Color color) {
		setPanelBackground(color);
		setButtonBackground(color);
		reset();
	}
	
	public void setPanelBackground(Color color) {
		pomodoro.topPanel.setBackground(color);
		pomodoro.midPanel.setBackground(color);
		pomodoro.botPanel.setBackground(color);
		pomodoro.statsPanel.setBackground(color);
	}
	
	public void setButtonBackground(Color color) {
		pomodoro.start.setBackground(color);
		pomodoro.skip.setBackground(color);
	}
	
	public abstract void reset();
}
