package template;

import java.awt.Color;

import main.Pomodoro;

public class FinishTemplate extends TimerTemplate {

	Pomodoro pomodoro;
	
	
	public FinishTemplate(Pomodoro pomodoro) {
		this.pomodoro = pomodoro;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPanelBackground(Color color) {
		// TODO Auto-generated method stub
		pomodoro.topPanel.setBackground(Color.green);
		pomodoro.midPanel.setBackground(Color.green);
		pomodoro.botPanel.setBackground(Color.green);
		pomodoro.statsPanel.setBackground(Color.green);
	}

	@Override
	public void setButtonBackground(Color color) {
		// TODO Auto-generated method stub
		pomodoro.start.setBackground(Color.green);
		pomodoro.skip.setBackground(Color.green);
	}

}
