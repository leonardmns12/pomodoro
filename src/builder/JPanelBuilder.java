package builder;

import java.awt.Color;

import javax.swing.JPanel;

public class JPanelBuilder {
	private JPanel jPanel;
	
	public JPanelBuilder() {
		this.jPanel = new JPanel();
	}
	
	public JPanelBuilder background(Color color) {
		jPanel.setBackground(color);
		return this;
	}
}
