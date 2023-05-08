package campodibattaglia;

import java.awt.*;
import java.io.*;
import java.util.prefs.PreferenceChangeEvent;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener {
	int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public final int FIELD_X = screenWidth / 2 - (300);
	public final int FIELD_Y = screenHeight / 2 - (300);
	private boolean isFullscreen = false;

	//dimensione bottoni
	private double homeWidth = ((0.234375) * screenWidth);
	private double homeHeight = ((0.074074074074074) * screenHeight);

	private double sceltaWidth = (0.15625) * screenWidth;
	private double sceltaHeight = (0.296296296296296) * screenHeight;


	private double backWidth = (0.036458333333333) * screenWidth;
	private double backHeight = (0.064814814814815) * screenHeight;

	private Container c = this.getContentPane();
	private SfondoPanel sfondo1Panel = new SfondoPanel(1);
	private ButtonIcon offlineButton = new ButtonIcon(1, homeWidth, homeHeight);
	private ButtonIcon onlineButton = new ButtonIcon(2, homeWidth, homeHeight);
	private ButtonIcon optionsButton = new ButtonIcon(3, homeWidth, homeHeight);

	private Boolean Online = false;
	private int finestra = 0;
	private Font font;
	private Font font1;

	private SfondoPanel sfondo2Panel = new SfondoPanel(1);
	private ButtonIcon ospiteButton = new ButtonIcon(4, sceltaWidth, sceltaHeight);
	private ButtonIcon accountButton = new ButtonIcon(5, sceltaWidth, sceltaHeight);
	private ButtonIcon backButton = new ButtonIcon(6, backWidth, backHeight);

	private SfondoPanel sfondo3Panel = new SfondoPanel(3);
	private ButtonIcon impo = new ButtonIcon(8, backWidth, backHeight);

	private SfondoPanel sfondo4Panel = new SfondoPanel(2);
	private Casella arButton[][] = new Casella[10][10];
	private int field[][] = new int[10][10];
	private Pedina pedina[] = new Pedina[10];
	JButton confirmPositions = new JButton("CONFERMA");

	private String nameString;
	private JLabel nameLabel = new JLabel(nameString);
	private String scoreSting = "Score 0";
	private JLabel scoreLabel = new JLabel(scoreSting);
	private ButtonIcon fullscreenToggle = new ButtonIcon(7, homeWidth, homeHeight);
	private ButtonIcon comandiButton = new ButtonIcon(9, homeWidth, homeHeight);
	private ButtonIcon regoleButton = new ButtonIcon(10, homeWidth, homeHeight);
	private SfondoPanel sfondo5Panel = new SfondoPanel(5);
	private SfondoPanel sfondo6Panel = new SfondoPanel(4);

	private SfondoPanel sfondo7Panel = new SfondoPanel(6);
	private JLabel nomeLabel = new JLabel("User:");
	private JTextField nomeText = new JTextField();
	private JLabel passwordLabel = new JLabel("Password:");
	private JPasswordField passwordField = new JPasswordField();
	private JButton loginButton = new JButton();
	private JButton registratiButton = new JButton();

	private SfondoPanel sfondo8Panel = new SfondoPanel(7);
	private JLabel passworConfLabel = new JLabel("Conferma password:");
	private JPasswordField passwordConfField = new JPasswordField();
	private JButton registerButton = new JButton();
	private JButton accediButton = new JButton();

	int acaso = 0;

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
		sfondo2Panel.add(ospiteButton);
		sfondo2Panel.add(accountButton);
		sfondo2Panel.add(backButton);
		c.add(sfondo2Panel);

		c.revalidate();
		c.repaint();
	}

	private void impostazioni() {
		c.removeAll();
		acaso = 0;
		c.setLayout(new GridLayout(1, 1));
		sfondo3Panel.setLayout(null);

		fullscreenToggle.addActionListener(this);
		fullscreenToggle.setActionCommand("Fullscreen");

		sfondo3Panel.add(comandiButton);

		sfondo3Panel.add(regoleButton);

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

		confirmPositions.addActionListener(this);
		confirmPositions.setActionCommand("CONFERMA");

		confirmPositions.setBounds(screenWidth - 100, 1000, 200, 80);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(100, 40, 200, 80);
		nameLabel.setFont(font);
		scoreLabel.setForeground(Color.WHITE);
		sfondo4Panel.add(nameLabel);
		scoreLabel.setFont(font);
		scoreLabel.setBounds(300, 40, 200, 80);
		sfondo4Panel.add(scoreLabel);

		final int dimensionePedinaTEMP[] = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
		for (int i = 0; i < pedina.length; i++) {
			pedina[i] = new Pedina(dimensionePedinaTEMP[i]);
		}

		for (int i = 0; i < pedina.length; i++) {
			sfondo4Panel.add(pedina[i]);
		}

		sfondo4Panel.add(impo);
		sfondo4Panel.add(confirmPositions);

		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				arButton[k][j] = new Casella();
			}
		}

		int r = 50;
		int t = 0;
		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				arButton[k][j].setBounds(FIELD_X + r, FIELD_Y + t, 50, 50);
				r += 50;
				sfondo4Panel.add(arButton[k][j]);
			}
			t += 50;
			r = 50;
		}

		System.out.println(arButton[0][0].getPosX() + " X PRIMO BUTTON");
		System.out.println(arButton[0][0].getPosY() + " X PRIMO BUTTON");

		c.add(sfondo4Panel);
		c.revalidate();
		c.repaint();
	}

	private void comandi() {
		c.removeAll();
		c.setLayout(new GridLayout(1, 1));
		sfondo5Panel.setLayout(null);
		acaso = 1;
		JLabel comando1 = new JLabel("Ruota barca:");
		JLabel comando2 = new JLabel("Vuoto:");
		JLabel comando3 = new JLabel("Vuoto:");
		JLabel comando4 = new JLabel("Vuoto:");
		JLabel comando5 = new JLabel("Vuoto:");
		JLabel comando6 = new JLabel("Vuoto:");

		// backButton.setBounds(70, 70, backWidth, backHeight);
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

	private void regole() {
		c.removeAll();
		acaso = 1;
		c.setLayout(new GridLayout(1, 1));
		sfondo6Panel.setLayout(null);
		// backButton.setBounds(70, 70, backWidth, backHeight);
		sfondo6Panel.add(backButton);

		JLabel testo = new JLabel("<html><body style='width: 400px;'>"
				+ "'Sa Battala' e' un gioco di strategia e abilita' ispirato a battaglia navale, "
				+ "in cui due giocatori cercano di distruggere le navi dell'avversario. "
				+ "Il gioco viene su una griglia quadrata, dove ciascun giocatore posiziona "
				+ "le proprie navi in segreto sulla propria griglia e cerca di individuare e "
				+ "distruggere le navi del proprio avversario.				"
				+ "Ecco le regole del gioco:			"
				+ "1. Ciascun giocatore posiziona le proprie navi sulla propria griglia in segreto."
				+ "   Le navi possono essere posizionate orizzontalmente o verticalmente, ma non diagonalmente.			"
				+ "2. Esistono diverse dimensioni di navi, a partire dalle piu' piccole di 1 caselle "
				+ "   fino alle piu' grandi di 4 caselle. Ciascun giocatore ha 10 navi a disposizione "
				+ "   (4 da 1 casella, 3 da 2 caselle, 2 da 3 caselle e 1 da 4 caselle).		"
				+ "3 .Per determinare chi inizia lo decidera' il programma che fara' tutto in automatico. "
				+ "   Il giocatore che inizia sceglie una casella sulla griglia dell'avversario e "
				+ "   dichiara se c'e' una nave o meno. Se la casella scelta contiene una nave, "
				+ "   il giocatore avversario la dichiara colpita, altrimenti la dichiara mancata. "
				+ "   In caso tutte le caselle che contengono la nave vengono dichiarate "
				+ "   la nave verra' dichiarata affondata.			"
				+ "4. A turno, i giocatori cercano di individuare e distruggere le navi dell'avversario. "
				+ "   Ogni volta che un giocatore colpisce una nave dell'avversario, ha diritto a un altro turno. "
				+ "   Se invece il giocatore manca il bersaglio, il turno passa all'avversario.			"
				+ "5. Il gioco prosegue fino a quando tutte e 10 le navi di uno dei giocatori vengono distrutte. "
				+ "   Il giocatore che distrugge tutte le navi dell'avversario vince il gioco.			"
				+ "6  E' importante notare che durante il gioco non e' consentito comunicare "
				+ "   informazioni sulla posizione delle navi al proprio avversario.					"
				+ "Buona fortuna!"
				+ " </body></html>");

		testo.setBounds(screenWidth / 2 - (150), screenHeight / 2 - (300), 300, 80);
		testo.setForeground(Color.BLACK);
		testo.setFont(font1);
		testo.setOpaque(false);
		testo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JScrollPane scrollPanel = new JScrollPane(testo);
		scrollPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		scrollPanel.setOpaque(false);
		scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setBounds(screenWidth / 2 - (300), screenHeight / 2 - (100), 600, 450);
		sfondo6Panel.add(scrollPanel);

		c.add(sfondo6Panel);
		c.revalidate();
		c.repaint();
	}

	private void accedi() {
		c.removeAll();
		c.setLayout(new GridLayout(1, 1));
		sfondo7Panel.setLayout(null);
		sfondo7Panel.add(backButton);

		nomeLabel.setBounds(screenWidth / 2 - (200), screenHeight / 2, 200, 50);
		nomeLabel.setFont(font);
		nomeText.setBounds(screenWidth / 2, screenHeight / 2, 200, 50);
		nomeText.setFont(font);
		passwordLabel.setBounds(screenWidth / 2 - (200), screenHeight / 2 + (50), 200, 50);
		passwordLabel.setFont(font);
		passwordField.setBounds(screenWidth / 2, screenHeight / 2 + (50), 200, 50);
		loginButton.setBounds(screenWidth / 2 - (100), screenHeight / 2 + (200), 200, 80);

		sfondo7Panel.add(nomeLabel);
		sfondo7Panel.add(nomeText);
		sfondo7Panel.add(passwordLabel);
		sfondo7Panel.add(passwordField);
		sfondo7Panel.add(loginButton);

		c.add(sfondo7Panel);
		c.revalidate();
		c.repaint();
	}

	private void finestra() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static boolean checkShipOverlap(JButton casella, JButton barca) {
		Rectangle bounds1 = casella.getBounds();
		Rectangle bounds2 = barca.getBounds();
		return bounds1.intersects(bounds2);
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
		if (e.getActionCommand().equals("CONFERMA")) {

			// GAME LOOP - RICERCA PUNTI OCCUPATI DA BARCA
			for (int pID = 0; pID < pedina.length; pID++) { // pid = idPedina

				for (int col = 0; col < arButton.length; col++) {
					for (int row = 0; row < arButton.length; row++) {
						if (checkShipOverlap(arButton[col][row], pedina[pID])) {
							System.out.println(checkShipOverlap(arButton[col][row], pedina[pID]));
							field[col][row] = 1;
						}
					}
				}
			}
		}

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
			else if (finestra == 2) {
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
			accedi();
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
