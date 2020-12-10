package template;

import java.awt.Color;

abstract class TimerTemplate {

	public abstract void setPanelBackground(Color color);
	public abstract void setButtonBackground(Color color);
	public void setView(Color color) {
		setPanelBackground(color);
		setButtonBackground(color);
	};
}
