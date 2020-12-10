package facade;

import java.awt.Font;

public class FontFacade {
	public Font create(String name, int size, String style) {
		if(style.equals("BOLD")) return new Font(name, Font.BOLD, size);
		else if(style.equals("ITALIC")) return new Font(name, Font.ITALIC, size);
		return new Font(name, Font.PLAIN, size);
	}
}
