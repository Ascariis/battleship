package campodibattaglia;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener, MouseListener,MouseMotionListener {

	Bot bot;
	boolean canContinue = true;
	int pedinePosizionate = 0;

	int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public final int FIELD_X = 760 - (300);
	public final int FIELD_Y = 540 - (300);
	private boolean isFullscreen = false;
	boolean isPlaced = false;

	// dimensione bottoni
	private double homeWidth = ((0.234375) * screenWidth);
	private double homeHeight = ((0.074074074074074) * screenHeight);

	private double sceltaWidth = (0.15625) * screenWidth;
	private double sceltaHeight = (0.296296296296296) * screenHeight;

	private double backWidth = (0.036458333333333) * screenWidth;
	private double backHeight = (0.064814814814815) * screenHeight;

	private double confWidth = (0.104166666666667) * screenWidth;
	private double confHeight = (0.074074074074074) * screenHeight;

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
	private Casella playerField[][] = new Casella[10][10];
	private Casella attackField[][] = new Casella[10][10];
	int field[][] = new int[10][10];
	private Pedina pedina[] = new Pedina[10];
	private ButtonIcon confirmPositions = new ButtonIcon(11, confWidth, confHeight);
	private ButtonIcon start = new ButtonIcon(12, confWidth, confHeight);

	private String nameString;
	private JLabel nameLabel = new JLabel(nameString);
	private String scoreSting = "Score 0";
	private JLabel scoreLabel = new JLabel(scoreSting);
	private JLabel field1Label = new JLabel("Il tuo campo");
	private JLabel field2Label = new JLabel("Il campo avversario");
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
	private JButton loginButton = new JButton("Login");
	private JButton registratiButton = new JButton("Registrati");

	private SfondoPanel sfondo8Panel = new SfondoPanel(7);
	private JLabel passwordConfLabel = new JLabel("Conferma password:");
	private JPasswordField passwordConfField = new JPasswordField();
	private JButton registerButton = new JButton("Register");
	private JButton accediButton = new JButton("Accedi");
	private JLabel nota1 = new JLabel("Non hai un'account? Registrane uno ora");
	private JLabel nota2 = new JLabel("Hai un'account? Accedi ora");
	private int tornaprima = 0;
	private int acaso = 0;
	boolean poba = true;
	boolean botTurn;

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

		/* 
			for (int pID = 0; pID < pedina.length; pID++) { // pid = idPedina
			for (int col = 0; col < 10; col++) {
				for (int row = 0; row < playerField.length; row++) {
					if (checkOverlap(playerField[col][row], pedina[pID])) {
						ceckPedina[col][row] = 1;
						}
					}
				}
			for (int col = 0; col < 10; col++) {
				for (int row = 0; row < 10; row++) {
					if (ceckPedina[col][row]==1) { //al posto di uno mettere le x e le y della pedina appena posizionata
						//riportare la pedina all posizione originale
						}
					}
				}
			}
		*/
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

		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(100, 40, 200, 80);
		nameLabel.setFont(font);
		scoreLabel.setForeground(Color.WHITE);
		field1Label.setFont(font);
		field1Label.setForeground(Color.WHITE);
		field2Label.setFont(font);
		field2Label.setForeground(Color.WHITE);
		scoreLabel.setFont(font);
		scoreLabel.setBounds(300, 40, 200, 80);
		field1Label.setBounds(510, FIELD_Y - 65, 200, 80);
		field2Label.setBounds(1110, FIELD_Y - 65, 300, 80);
		sfondo4Panel.add(field1Label);
		sfondo4Panel.add(field2Label);
		sfondo4Panel.add(nameLabel);
		sfondo4Panel.add(scoreLabel);

		if (!isPlaced) {
			final int dimensionePedinaTEMP[] = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
			for (int i = 0; i < pedina.length; i++) {
				pedina[i] = new Pedina(dimensionePedinaTEMP[i]);
			}

			for (int i = 0; i < pedina.length; i++) {
				sfondo4Panel.add(pedina[i]);
			}

			isPlaced = true;
		}

		sfondo4Panel.add(impo);
		sfondo4Panel.add(confirmPositions);
		sfondo4Panel.add(start);

		// Player Field
		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				playerField[k][j] = new Casella();
			}
		}

		int r = 50;
		int t = 0;
		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				playerField[k][j].setBounds(FIELD_X + r, FIELD_Y + t, 50, 50);
				r += 50;
				sfondo4Panel.add(playerField[k][j]);
			}
			t += 50;
			r = 50;
		}

		// Enemy Field
		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				attackField[k][j] = new Casella(this);
			}
		}

		int rt = 50;
		int tr = 0;
		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				attackField[k][j].setBounds((FIELD_X + rt) + 600, FIELD_Y + tr, 50, 50);
				attackField[k][j].setPosX(j);
				attackField[k][j].setPosY(k);
				attackField[k][j].addActionListener(attackField[k][j]);
				attackField[k][j].setActionCommand(attackField[k][j].getText());
				rt += 50;
				sfondo4Panel.add(attackField[k][j]);
			}
			tr += 50;
			rt = 50;
		}

		System.out.println(playerField[0][0].getPosX() + " X PRIMO BUTTON");
		System.out.println(playerField[0][0].getPosY() + " Y PRIMO BUTTON");

		c.add(sfondo4Panel);
		c.revalidate();
		c.repaint();
	}

	private void comandi() {
		c.removeAll();
		c.setLayout(new GridLayout(1, 1));
		sfondo5Panel.setLayout(null);
		acaso = 1;
		JLabel comando1 = new JLabel("Ruota barca: Tasto Destro");
		JLabel comando2 = new JLabel("Vuoto:");
		JLabel comando3 = new JLabel("Vuoto:");
		JLabel comando4 = new JLabel("Vuoto:");
		JLabel comando5 = new JLabel("Vuoto:");
		JLabel comando6 = new JLabel("Vuoto:");

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
		sfondo6Panel.add(backButton);

		JLabel testo = new JLabel("<html><body style='width: 400px;'>"
				+ "Sa Battala e' un gioco di strategia e abilita' ispirato a battaglia navale,"
				+ "in cui due giocatori cercano di distruggere le navi dell'avversario.<br> "
				+ "Il gioco viene su una griglia quadrata, dove ciascun giocatore posiziona "
				+ "le proprie navi in segreto sulla propria griglia e cerca di individuare e  "
				+ "distruggere le navi del proprio avversario.<br><br>"
				+ "Ecco le regole del gioco:<br>"
				+ "<ol><li> Ciascun giocatore posiziona le proprie navi sulla propria griglia in segreto."
				+ "    Le navi possono essere posizionate orizzontalmente o verticalmente, ma non diagonalmente.<br></li>"
				+ "<li> Esistono diverse dimensioni di navi, a partire dalle piu' piccole di 1 caselle"
				+ "    fino alle piu' grandi di 4 caselle. Ciascun giocatore ha 10 navi a disposizione"
				+ "    (4 da 1 casella, 3 da 2 caselle, 2 da 3 caselle e 1 da 4 caselle).</li>"
				+ "<li> Per determinare chi inizia lo decidera' il programma che fara' tutto in automatico."
				+ "    Il giocatore che inizia sceglie una casella sulla griglia dell'avversario e"
				+ "    dichiara se c'e' una nave o meno. Se la casella scelta contiene una nave,"
				+ "    il giocatore avversario la dichiara colpita, altrimenti la dichiara mancata.<br>"
				+ "    In caso tutte le caselle che contengono la nave vengono dichiarate"
				+ "    la nave verra' dichiarata affondata.</li>"
				+ "<li> A turno, i giocatori cercano di individuare e distruggere le navi dell'avversario."
				+ "    Ogni volta che un giocatore colpisce una nave dell'avversario, ha diritto a un altro turno. "
				+ "    Se invece il giocatore manca il bersaglio, il turno passa all'avversario.</li>"
				+ "<li> Il gioco prosegue fino a quando tutte e 10 le navi di uno dei giocatori vengono distrutte. "
				+ "    Il giocatore che distrugge tutte le navi dell'avversario vince il gioco.</li>"
				+ "<li> E' importante notare che durante il gioco non e' consentito comunicare "
				+ "    informazioni sulla posizione delle navi al proprio avversario.</li></ol>"
				+ "Buona fortuna!<br>"
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

		tornaprima = 1;

		nomeLabel.setBounds(screenWidth / 2 - (200), screenHeight / 2, 200, 50);
		nomeLabel.setFont(font);
		nomeText.setBounds(screenWidth / 2, screenHeight / 2, 200, 50);
		nomeText.setFont(font);
		passwordLabel.setBounds(screenWidth / 2 - (200), screenHeight / 2 + (50), 200, 50);
		passwordLabel.setFont(font);
		passwordField.setBounds(screenWidth / 2, screenHeight / 2 + (50), 200, 50);
		loginButton.setBounds(screenWidth / 2 - (100), screenHeight / 2 + (120), 200, 50);
		nota1.setHorizontalAlignment(JLabel.CENTER);
		nota1.setBounds(screenWidth / 2 - (150), screenHeight / 2 + (230), 300, 15);
		registratiButton.setBounds(screenWidth / 2 - (60), screenHeight / 2 + (250), 120, 30);
		nomeLabel.setForeground(Color.WHITE);
		passwordLabel.setForeground(Color.WHITE);
		passwordConfLabel.setForeground(Color.WHITE);
		nomeText.setText(null);
		passwordField.setText(null);

		sfondo7Panel.add(nomeLabel);
		sfondo7Panel.add(nomeText);
		sfondo7Panel.add(passwordLabel);
		sfondo7Panel.add(passwordField);
		sfondo7Panel.add(loginButton);
		sfondo7Panel.add(registratiButton);
		sfondo7Panel.add(nota1);

		c.add(sfondo7Panel);
		c.revalidate();
		c.repaint();
	}

	private void registrati() {
		c.removeAll();
		c.setLayout(new GridLayout(1, 1));
		sfondo8Panel.setLayout(null);
		sfondo8Panel.add(backButton);

		tornaprima = 1;

		nomeLabel.setBounds(screenWidth / 2 - (200), screenHeight / 2 - (50), 200, 50);
		nomeLabel.setFont(font);
		nomeText.setBounds(screenWidth / 2, screenHeight / 2 - (50), 200, 50);
		nomeText.setFont(font);
		passwordLabel.setBounds(screenWidth / 2 - (200), screenHeight / 2, 200, 50);
		passwordLabel.setFont(font);
		passwordField.setBounds(screenWidth / 2, screenHeight / 2, 200, 50);
		passwordConfLabel.setBounds(screenWidth / 2 - (200), screenHeight / 2 + (50), 200, 50);
		passwordConfLabel.setFont(font);
		passwordConfField.setBounds(screenWidth / 2, screenHeight / 2 + (50), 200, 50);
		registerButton.setBounds(screenWidth / 2 - (100), screenHeight / 2 + (120), 200, 50);
		nota2.setHorizontalAlignment(JLabel.CENTER);
		nota2.setBounds(screenWidth / 2 - (150), screenHeight / 2 + (230), 300, 15);
		accediButton.setBounds(screenWidth / 2 - (60), screenHeight / 2 + (250), 120, 30);

		nomeText.setText(null);
		passwordField.setText(null);
		passwordConfField.setText(null);

		sfondo8Panel.add(nomeLabel);
		sfondo8Panel.add(nomeText);
		sfondo8Panel.add(passwordLabel);
		sfondo8Panel.add(passwordField);
		sfondo8Panel.add(passwordConfLabel);
		sfondo8Panel.add(passwordConfField);
		sfondo8Panel.add(registerButton);
		sfondo8Panel.add(accediButton);
		sfondo8Panel.add(nota2);

		c.add(sfondo8Panel);
		c.revalidate();
		c.repaint();
	}

	private void finestra() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public static boolean checkOverlap(JButton casella, JButton barca) {
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

		loginButton.addActionListener(this);
		loginButton.setActionCommand("Login");

		registerButton.addActionListener(this);
		registerButton.setActionCommand("Register");

		accediButton.addActionListener(this);
		accediButton.setActionCommand("Accedi");

		registratiButton.addActionListener(this);
		registratiButton.setActionCommand("Registrati");

		confirmPositions.addActionListener(this);
		confirmPositions.setActionCommand("CONFERMA");

		start.addActionListener(this);
		start.setActionCommand("START");
		start.setVisible(false);
	}

	public int getPedinePosizionate() {
		int pedinePosizionate = 0;
		for (int i = 0; i < pedina.length; i++) {
			if (pedina[i].getSnapState()) {
				pedinePosizionate++;
			}
		}
		return pedinePosizionate;
	}

	public boolean setPlayerShot(int x, int y) {
		if (field[x][y] != 0) { // se non manca
			field[x][y] = 5;
			playerField[x][y].setBackground(Color.RED);
			pedinePosizionate--;
			return true;
		} else {
			playerField[x][y].setBackground(Color.GRAY);
			return false;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("CONFERMA")) {
	
			if (getPedinePosizionate() == 10) {
				for (Pedina pedina : pedina) {
					pedina.setLockedState();
				}
				confirmPositions.setVisible(false);
				// GAME LOOP - RICERCA PUNTI OCCUPATI DA BARCA
				for (int pID = 0; pID < pedina.length; pID++) { // pid = idPedina

					for (int col = 0; col < playerField.length; col++) {
						for (int row = 0; row < playerField.length; row++) {
							if (checkOverlap(playerField[col][row], pedina[pID])) {
								System.out.println(checkOverlap(playerField[col][row], pedina[pID]));
								field[col][row] = pedina[pID].getTaglia();
							}
						}
					}
				}
				start.setVisible(true);
			}
			pedinePosizionate *= 2; //parità con bot

		}

		if (e.getActionCommand().equals("START")) {
			bot = new Bot();
			start.setVisible(false);

			for (int[] x : field) {
				for (int y : x) {
					System.out.print(y + " ");
				}
				System.out.println();
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
			if (finestra == 1 && tornaprima == 0)
				home();
			else if (finestra == 2) {
				for (int k = 0; k < 10; k++) {
					for (int j = 0; j < 10; j++) {
						sfondo4Panel.remove(playerField[k][j]);
					}
				}
				campoDaGioco();
			} else if (finestra == 1 && tornaprima == 1)
				scelta();
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
		} else if (e.getActionCommand().equals("Login")) {
		} else if (e.getActionCommand().equals("Register")) {
		} else if (e.getActionCommand().equals("Accedi")) {
			accedi();
		} else if (e.getActionCommand().equals("Registrati")) {
			registrati();
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");

	}
				
}
