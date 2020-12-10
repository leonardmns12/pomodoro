package builder;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class JLabelBuilder {
	private JLabel jLabel;
	
	public JLabelBuilder() {
		jLabel = new JLabel();
	}
	
	public JLabelBuilder text(String text) {
		this.jLabel.setText(text);
		return this;
	}
	
	public JLabelBuilder icon(String image) {
		jLabel.setIcon(new ImageIcon(getClass().getResource(image)));
		return this;
	}
	
	public JLabelBuilder border(Border border) {
		jLabel.setBorder(border);
		return this;
	}
	
	public JLabelBuilder font(String name, int size) {
		jLabel.setFont(new Font(name , Font.PLAIN , size));
		return this;
	}
	
	public JLabel build() {
		return jLabel;
	}
}
