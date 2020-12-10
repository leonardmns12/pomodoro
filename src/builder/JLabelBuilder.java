package builder;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import facade.FontFacade;

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
	
	public JLabelBuilder font(String name, int size, String style) {
		FontFacade fontFacade = new FontFacade();
		jLabel.setFont(fontFacade.create(name, size, style));
		return this;
	}
	
	public JLabel build() {
		return jLabel;
	}
}
