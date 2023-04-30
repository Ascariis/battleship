package campodibattaglia;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

import org.w3c.dom.Text;

public class Home extends JFrame implements ActionListener {

	int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	private GraphicsDevice device;

	private Container c = this.getContentPane();
	private SfondoPanel sfondo1Panel = new SfondoPanel(1);
	private ButtonIcon offlineButton = new ButtonIcon(1);
	private ButtonIcon onlineButton = new ButtonIcon(2);
	private ButtonIcon optionsButton = new ButtonIcon(3);

	private Boolean Online = false;
	private int finestra = 0;
	private Font font;

	private SfondoPanel sfondo2Panel = new SfondoPanel(1);
	private ButtonIcon ospiteButton = new ButtonIcon(4);
	private ButtonIcon accountButton = new ButtonIcon(5);
	private ButtonIcon backButton = new ButtonIcon(6);

	private SfondoPanel sfondo3Panel = new SfondoPanel(2);

	private SfondoPanel sfondo4Panel = new SfondoPanel(2);
	private JButton arButton[][] = new JButton[10][10];

	Home() {
		super("Sa Battalla");
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		home();
		ascoltatori();
		finestra();

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("font/SinkiS93.ttf")).deriveFont(40f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	private void home() {
		c.removeAll();

		c.setLayout(new GridLayout(1, 1));
		sfondo1Panel.setLayout(null);
		offlineButton.setBounds(x / 2 - (225), y / 2, 450, 80);
		onlineButton.setBounds(x / 2 - (225), y / 2 + (100), 450, 80);
		optionsButton.setBounds(x / 2 - (225), y / 2 + (200), 450, 80);
		sfondo1Panel.add(offlineButton);
		sfondo1Panel.add(onlineButton);
		sfondo1Panel.add(optionsButton);
		c.add(sfondo1Panel);

		c.revalidate();
		c.repaint();
	}

	private void scelta() {
		c.removeAll();

		finestra = 1;

		c.setLayout(new GridLayout(1, 1));
		sfondo2Panel.setLayout(null);
		ospiteButton.setBounds(x / 2 - (400), y / 2 - (150), 300, 400);
		accountButton.setBounds(x / 2 + (100), y / 2 - (150), 300, 400);
		backButton.setBounds(20, y - 400, 370, 500);

		sfondo2Panel.add(ospiteButton);
		sfondo2Panel.add(accountButton);
		sfondo2Panel.add(backButton);
		c.add(sfondo2Panel);
		ascoltatori();
		finestra();

		c.revalidate();
		c.repaint();
	}

	private void impostazioni() {
		c.removeAll();
		finestra = 1;
		c.setLayout(new GridLayout(1, 1));
		sfondo3Panel.setLayout(null);
		backButton.setBounds(20, y - 400, 370, 500);

		JButton fullscreenToggle = new JButton("Fullscreen ");

		JLabel prova = new JLabel("ciao");
		prova.setForeground(Color.WHITE);
		fullscreenToggle.setBounds(x / 2, y / 2, 200, 50);
		sfondo3Panel.add(fullscreenToggle);
		sfondo3Panel.add(backButton);
		c.add(sfondo3Panel);

		ascoltatori();
		finestra();

		c.revalidate();
		c.repaint();
	}

	private void campoDaGioco() {
		c.removeAll();
		c.setLayout(new GridLayout(1, 1));
		sfondo4Panel.setLayout(null);

		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				arButton[k][j] = new JButton("+");
			}
		}
		
		int r = 60;
		int t = 60;
		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				arButton[k][j].setBounds(x / 2 - (360) + r, y / 2 - (360) + t, 50, 50);
				r += 60;
				sfondo4Panel.add(arButton[k][j]);
			}
			t += 60;
			r = 60;
		}
		c.add(sfondo4Panel);
		ascoltatori();
		finestra();
		c.revalidate();
		c.repaint();
	}

	private void finestra() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void ascoltatori() {
		offlineButton.addActionListener(this);
		offlineButton.setActionCommand("Offline");

		onlineButton.addActionListener(this);
		onlineButton.setActionCommand("Online");

		optionsButton.addActionListener(this);
		optionsButton.setActionCommand("Impostazioni");

		backButton.addActionListener(this);
		backButton.setActionCommand("Indietro");

		ospiteButton.addActionListener(this);
		ospiteButton.setActionCommand("Ospite");

		accountButton.addActionListener(this);
		accountButton.setActionCommand("Account");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Offline")) {
			scelta();
		} else if (e.getActionCommand().equals("Online")) {
			scelta();
			Online = true;
		}

		else if (e.getActionCommand().equals("Impostazioni")) {
			impostazioni();
		} else if (e.getActionCommand().equals("Indietro")) {
			if (finestra == 1)
				home();
		} else if (e.getActionCommand().equals("Ospite")) {
			campoDaGioco();
		} else if (e.getActionCommand().equals("Account")) {
			impostazioni();
		}
	}

}
