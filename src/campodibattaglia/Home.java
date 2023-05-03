package campodibattaglia;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener {
	int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	
	public final int FIELD_X = screenWidth / 2 - (300);
	public final int FIELD_Y = screenHeight / 2 - (300);
	private boolean isFullscreen = false;
	

	private Container c = this.getContentPane();
	private SfondoPanel sfondo1Panel = new SfondoPanel(1);
	private ButtonIcon offlineButton = new ButtonIcon(1);
	private ButtonIcon onlineButton = new ButtonIcon(2);
	private ButtonIcon optionsButton = new ButtonIcon(3);

	private Boolean Online = false;
	private int finestra = 0;
	private Font font;
	private Font font1;

	private SfondoPanel sfondo2Panel = new SfondoPanel(1);
	private ButtonIcon ospiteButton = new ButtonIcon(4);
	private ButtonIcon accountButton = new ButtonIcon(5);
	private ButtonIcon backButton = new ButtonIcon(6);

	private SfondoPanel sfondo3Panel = new SfondoPanel(2);
	private ButtonIcon impo = new ButtonIcon(8);
	private Pedina pedina1 = new Pedina(1);
	private Pedina pedina2 = new Pedina(1);
	private Pedina pedina3 = new Pedina(1);
	private Pedina pedina4 = new Pedina(1);
	private Pedina pedina5 = new Pedina(2);
	private Pedina pedina6 = new Pedina(2);
	private Pedina pedina7 = new Pedina(2);
	private Pedina pedina8 = new Pedina(3);
	private Pedina pedina9 = new Pedina(3);
	private Pedina pedina0 = new Pedina(4);

	private SfondoPanel sfondo4Panel = new SfondoPanel(2);
	private Casella arButton[][] = new Casella[10][10];
	private String nameString;
	private JLabel nameLabel = new JLabel(nameString);
	private String scoreSting = "Score 0";
	private JLabel scoreLabel = new JLabel(scoreSting);
	private ButtonIcon comandiButton = new ButtonIcon(9);
	private ButtonIcon regoleButton = new ButtonIcon(10);
	private SfondoPanel sfondo5Panel = new SfondoPanel(2);
	private SfondoPanel sfondo6Panel = new SfondoPanel(2);
	int acaso=0;

	Home() {
		super("Sa Battalla");

		home();
		ascoltatori();
		finestra();

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("font/game_over.ttf")).deriveFont(70f);
			font1 = Font.createFont(Font.TRUETYPE_FONT, new File("font/game_over.ttf")).deriveFont(100f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	private void home() {
		c.removeAll();
		finestra = 1;
		
		c.setLayout(new GridLayout(1, 1));
		sfondo1Panel.setLayout(null);
		offlineButton.setBounds(screenWidth / 2 - (225), screenHeight / 2, 450, 80);
		onlineButton.setBounds(screenWidth / 2 - (225), screenHeight / 2 + (100), 450, 80);
		optionsButton.setBounds(screenWidth / 2 - (225), screenHeight / 2 + (200), 450, 80);
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
		ospiteButton.setBounds(screenWidth / 2 - (400), screenHeight / 2 - (100), 300, 320);
		accountButton.setBounds(screenWidth / 2 + (100), screenHeight / 2 - (100), 300, 320);
		backButton.setBounds(100, 100, 70, 70);

		sfondo2Panel.add(ospiteButton);
		sfondo2Panel.add(accountButton);
		sfondo2Panel.add(backButton);
		c.add(sfondo2Panel);

		c.revalidate();
		c.repaint();
	}

	private void impostazioni() {
		c.removeAll();
		acaso=0;
		c.setLayout(new GridLayout(1, 1));
		sfondo3Panel.setLayout(null);
		backButton.setBounds(70, 70, 70, 70);

		ButtonIcon fullscreenToggle = new ButtonIcon(7);
		fullscreenToggle.addActionListener(this);
		fullscreenToggle.setActionCommand("Fullscreen");

		comandiButton.setBounds(screenWidth / 2 - (225), screenHeight / 2, 450, 80);
		sfondo3Panel.add(comandiButton);
		regoleButton.setBounds(screenWidth / 2 - (225), screenHeight / 2 + (100), 450, 80);
		sfondo3Panel.add(regoleButton);

		fullscreenToggle.setBounds(screenWidth / 2 - (225), screenHeight / 2 - (100), 450, 80);
		sfondo3Panel.add(fullscreenToggle);
		sfondo3Panel.add(backButton);
		c.add(sfondo3Panel);

		c.revalidate();
		c.repaint();
	}

	private void campoDaGioco() {
		c.removeAll();
		c.setLayout(new GridLayout(1, 1));
		finestra = 2;
		sfondo4Panel.setLayout(null);

		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(100, 40, 200, 80);
		nameLabel.setFont(font);
		scoreLabel.setForeground(Color.WHITE);
		sfondo4Panel.add(nameLabel);
		scoreLabel.setFont(font);
		scoreLabel.setBounds(300, 40, 200, 80);
		sfondo4Panel.add(scoreLabel);
		impo.setBounds(screenWidth - (140), 70, 70, 70);

		sfondo4Panel.add(pedina1);
		sfondo4Panel.add(pedina2);
		sfondo4Panel.add(pedina3);
		sfondo4Panel.add(pedina4);
		sfondo4Panel.add(pedina5);
		sfondo4Panel.add(pedina6);
		sfondo4Panel.add(pedina7);
		sfondo4Panel.add(pedina8);
		sfondo4Panel.add(pedina9);
		sfondo4Panel.add(pedina0);
		sfondo4Panel.add(impo);
		
			for (int k = 0; k < 10; k++) {
				for (int j = 0; j < 10; j++) {
					arButton[k][j] = new Casella();
				}
			}

			int r = 60;
			int t = 0;
			for (int k = 0; k < 10; k++) {
				for (int j = 0; j < 10; j++) {
					arButton[k][j].setBounds(FIELD_X + r, FIELD_Y + t, 60, 60);
					r += 60;
					sfondo4Panel.add(arButton[k][j]);
				}
				t += 60;
				r = 60;
			}
		
		System.out.println(arButton[0][0].getPosX() + " X PRIMO BUTTON");
        System.out.println(arButton[0][0].getPosY() + " X PRIMO BUTTON");

		c.add(sfondo4Panel);
		c.revalidate();
		c.repaint();
	}

	private void comandi(){
		c.removeAll();
		c.setLayout(new GridLayout(1, 1));
		sfondo5Panel.setLayout(null);
		acaso=1;
		JLabel comando1 = new JLabel("Ruota barca:");
		JLabel comando2 = new JLabel("Vuoto:");
		JLabel comando3 = new JLabel("Vuoto:");
		JLabel comando4 = new JLabel("Vuoto:");
		JLabel comando5 = new JLabel("Vuoto:");
		JLabel comando6 = new JLabel("Vuoto:");

		backButton.setBounds(70, 70, 70, 70);
		sfondo5Panel.add(backButton);

		comando1.setForeground(Color.WHITE);
		comando2.setForeground(Color.WHITE);
		comando3.setForeground(Color.WHITE);
		comando4.setForeground(Color.WHITE);
		comando5.setForeground(Color.WHITE);
		comando6.setForeground(Color.WHITE);

		comando1.setFont(font1);
		comando2.setFont(font1);
		comando3.setFont(font1);
		comando4.setFont(font1);
		comando5.setFont(font1);
		comando6.setFont(font1);

		comando1.setBounds(screenWidth / 2 - (300), screenHeight / 2 + (100), 300, 80);
		comando2.setBounds(screenWidth / 2 - (300), screenHeight / 2, 300, 80);
		comando3.setBounds(screenWidth / 2 - (300), screenHeight / 2 - (100), 300, 80);
		comando4.setBounds(screenWidth / 2 - (300), screenHeight / 2 - (200), 300, 80);
		comando5.setBounds(screenWidth / 2 - (300), screenHeight / 2 - (300), 300, 80);
		comando6.setBounds(screenWidth / 2 - (300), screenHeight / 2 - (400), 300, 80);

		sfondo5Panel.add(comando1);
		sfondo5Panel.add(comando2);
		sfondo5Panel.add(comando3);
		sfondo5Panel.add(comando4);
		sfondo5Panel.add(comando5);
		sfondo5Panel.add(comando6);

		c.add(sfondo5Panel);
		c.revalidate();
		c.repaint();
	}
	private void regole(){
		c.removeAll();
		acaso=1;
		c.setLayout(new GridLayout(1, 1));
		sfondo6Panel.setLayout(null);
		backButton.setBounds(70, 70, 70, 70);
		sfondo6Panel.add(backButton);

		JLabel testo = new JLabel("<html><body style='width: 400px;'>"
				+ "A Battaglia navale ogni giocatore ha in dotazione una tavola, "
				+ "cartacea o elettronica, dove ci sono due griglie uguali con 100 caselline. "
				+ "Su una delle due griglie dovra' posizionare le proprie navi, sull’altra invece "
				+ "dovrà sparare dei colpi alla cieca nel tentativo di indovinare la posizione delle "
				+ "navi avversarie. Insieme al tabellone a doppia griglia, ciascun giocatore riceve "
				+ "una flotta di cinque navi e un pacchetto di pedine, che serviranno come marcatori "
				+ "per individuare i colpi andati a segno e quelli falliti, sulla griglia avversaria. "
				+ "Insieme alle pedine ci sono anche i segnalini rossi che servono a marcare i colpi "
				+ "ricevuti. Ovviamente l’obiettivo di ciascun giocatore è quello di affondare tutte "
				+ "le navi del nemico, prima che questi abbia il sopravvento e affondi le proprie. "
				+ "Fortuna, intuito e strategia in questo gioco sono determinanti per vincere. "
				+ " Ma vediamo le regole."
				+ "</body></html>");

		testo.setBounds(screenWidth / 2 - (150), screenHeight / 2 - (300), 300, 80);
		testo.setForeground(Color.BLACK);
		testo.setFont(font1);
		testo.setOpaque(false);
		testo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JScrollPane scrollPanel = new JScrollPane(testo);
		scrollPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		scrollPanel.setOpaque(false);
       	scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setBounds(screenWidth/2-(300), screenHeight/2-(250), 600, 500);
		sfondo6Panel.add(scrollPanel);

		c.add(sfondo6Panel);
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
		impo.addActionListener(this);
		impo.setActionCommand("Impostazioni");

		backButton.addActionListener(this);
		backButton.setActionCommand("Indietro");

		ospiteButton.addActionListener(this);
		ospiteButton.setActionCommand("Ospite");

		accountButton.addActionListener(this);
		accountButton.setActionCommand("Account");

		comandiButton.addActionListener(this);
		comandiButton.setActionCommand("Comandi");

		regoleButton.addActionListener(this);
		regoleButton.setActionCommand("Regole");
	}

	@Override
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
			else if (finestra == 2){
				for (int k = 0; k < 10; k++) {
					for (int j = 0; j < 10; j++) {
						sfondo4Panel.remove(arButton[k][j]);
					}
				}
				campoDaGioco();
			}
				
			if (acaso == 1)
				impostazioni();
		} else if (e.getActionCommand().equals("Ospite")) {
			nameString = "Ospite";
			nameLabel.setText(nameString);
			campoDaGioco();
		} else if (e.getActionCommand().equals("Account")) {
			impostazioni();
		} else if (e.getActionCommand().equals("Comandi")) {
			comandi();
		} else if (e.getActionCommand().equals("Regole")) {
			regole();
		} else if (e.getActionCommand().equals("Fullscreen")) {
			if (!isFullscreen) {
				dispose();
				setUndecorated(true);
				isFullscreen = true;
				setBounds(0, 0, screenWidth, screenHeight);
				this.setVisible(true);
			} else {
				dispose();
				setUndecorated(false);
				isFullscreen = false;
				setBounds(0, 0, screenWidth, screenHeight);
				this.setVisible(true);
			}
		}
	}

}
