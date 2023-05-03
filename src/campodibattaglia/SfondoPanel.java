package campodibattaglia;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class SfondoPanel extends JPanel {
	int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	String indirizzo;

	SfondoPanel(int nFinestra) {
		switch (nFinestra) {
			case 1:
				indirizzo = "image/Sfondo.gif";
				break;
			case 2:
				indirizzo = "image/Sfondo1.gif";
				break;
			case 3:
				indirizzo = "image/settingsSfondo.gif";
				break;
			case 4:
				indirizzo = "image/rulesSfondo.gif";
				break;
			case 5:
				indirizzo = "image/comandsSfondo.gif";
				break;
		}
	}

	protected void paintComponent(Graphics g) {
		ImageIcon backgroundImage = new ImageIcon(indirizzo);
		Image img = backgroundImage.getImage();
		g.drawImage(img, 0, 0, x, y, this);
	}
}
