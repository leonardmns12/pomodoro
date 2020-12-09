package builder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class JButtonBuilder {
	private String text;
	private ImageIcon icon;
	private Color color;
	private Border border;
	private float x;
	private float y;
	private Font font;
	private boolean isFocus;
	private boolean isVisible;
	
	public JButtonBuilder setText(String text) {
		this.text = text;
		return this;
	}
	
	public JButtonBuilder setIcon(String image) {
		this.icon = new ImageIcon(getClass().getResource(image));
		return this;
	}
	
	public JButtonBuilder setBackground(Color color) {
		this.color = color;
		return this;
	}
	
	public JButtonBuilder setBorder(Border border) {
		this.border = border;
		return this;
	}
	
	public JButtonBuilder setX(float x) {
		this.x = x;
		return this;
	}
	
	public JButtonBuilder setY(float y) {
		this.y = y;
		return this;
	}
	
	public JButtonBuilder setFont(String name, int size) {
		this.font = new Font(name , Font.PLAIN , size);
		return this;
	}
	
	public JButtonBuilder setFocus(boolean isFocus) {
		this.isFocus = isFocus;
		return this;
	}
	
	public JButtonBuilder setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		return this;
	}
	
	public JButton build() {
		JButton jButton = new JButton();
		jButton.setText(text);
		jButton.setIcon(icon);
		jButton.setBackground(color);
		jButton.setBorder(border);
		jButton.setAlignmentX(x);
		jButton.setAlignmentY(y);
		jButton.setFont(font);
		jButton.setFocusPainted(isFocus);
		jButton.setVisible(isVisible);
		
		return jButton;
	}
}
