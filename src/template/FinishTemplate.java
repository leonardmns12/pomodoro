package template;

import javax.swing.ImageIcon;

import builder.JButtonBuilder;
import main.Pomodoro;

public class FinishTemplate extends TimerTemplate {
	Pomodoro pomodoro;
	
	public FinishTemplate(Pomodoro pomodoro) {
		super(pomodoro);
		this.pomodoro = pomodoro;
	}
	
	@Override
	public void reset() {
		pomodoro.start.setIcon(new ImageIcon(getClass().getResource("/res/play.png")));
		pomodoro.skip.setVisible(false);
		pomodoro.isStarted = false;
		emptyDot();
		writeRecord();
	}
	
	public void emptyDot() {
		pomodoro.phase1.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		pomodoro.phase2.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		pomodoro.phase3.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		pomodoro.phase4.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
	}
	
	public void writeRecord() {
		System.out.println("pomodoro finish!");
		pomodoro.records.write("finish");
	}
}
