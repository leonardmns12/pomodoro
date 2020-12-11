package builder;

import java.awt.Color;
import java.awt.Font;

import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import facade.FontFacade;

public class JButtonBuilder {
	private JButton jButton;
	
	public JButtonBuilder() {
		this.jButton = new JButton();
	}
	
	public JButtonBuilder text(String text) {
		jButton.setText(text);
		return this;
	}
	
	public JButtonBuilder icon(String image) {
		jButton.setIcon(new ImageIcon(getClass().getResource(image)));
		return this;
	}
	
	public JButtonBuilder background(Color color) {
		jButton.setBackground(color);
		return this;
	}
	
	public JButtonBuilder border(Border border) {
		jButton.setBorder(border);
		return this;
	}
	
	public JButtonBuilder font(String name, int size, String style) {
		jButton.setFont(FontFacade.create(name, size, style));
		return this;
	}
	
	public JButtonBuilder focus(boolean isFocus) {
		jButton.setFocusPainted(isFocus);
		return this;
	}
	
	public JButtonBuilder visible(boolean isVisible) {
		jButton.setVisible(isVisible);
		return this;
	}
	
	public JButton build() {
		return jButton;
	}
}
