package template;

import java.awt.Color;

import main.Pomodoro;

public class PomodoroTemplate extends TimerTemplate{

	private Pomodoro pomodoro;
	
	
	public PomodoroTemplate(Pomodoro pomodoro) {
		this.pomodoro = pomodoro;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPanelBackground(Color color) {
		pomodoro.topPanel.setBackground(color);
		pomodoro.midPanel.setBackground(color);
		pomodoro.botPanel.setBackground(color);
		pomodoro.statsPanel.setBackground(color);
		
	}

	@Override
	public void setButtonBackground(Color color) {
		// TODO Auto-generated method stub
		pomodoro.start.setBackground(color);
		pomodoro.skip.setBackground(color);
	}



}
