package campodibattaglia;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

import org.w3c.dom.Text;

public class Home extends JFrame implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

	JButton testBarca = new JButton("barchettta", null);
	Point puntoBarca = new Point();

	private boolean isFullscreen = false;
	int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

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
	private ImageIcon iconaCursore = new ImageIcon("image/pedina1.png");
	Home() {
		super("Sa Battalla");

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
		backButton.setBounds(70, 70, 70, 70);

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
		backButton.setBounds(70, 70, 70, 70);

		JButton fullscreenToggle = new JButton("Fullscreen ");
		fullscreenToggle.addActionListener(this);
		fullscreenToggle.setActionCommand("Fullscreen");

		JLabel prova = new JLabel("ciao");
		prova.setForeground(Color.WHITE);
		fullscreenToggle.setBounds(screenWidth / 2, screenHeight / 2, 200, 50);
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

		int r = 65;
		int t = 0;
		for (int k = 0; k < 10; k++) {
			for (int j = 0; j < 10; j++) {
				arButton[k][j].setBounds(screenWidth / 2 - (325) + r, screenHeight / 2 - (325) + t, 65, 65);
				r += 65;
				sfondo4Panel.add(arButton[k][j]);
			}
			t += 65;
			r = 65;
		}

		testBarca.setBounds(200, 190, 50, 35);
		backButton.setBounds(70, 70, 70, 70);
		sfondo4Panel.add(testBarca);
		sfondo4Panel.add(backButton);
		ButtonIcon pedina = new ButtonIcon(7);
		pedina.setBounds(70, 200, 130, 65);
		sfondo4Panel.add(pedina);
		

		pedina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iconaCursore = (ImageIcon)pedina.getIcon();
                pedina.setIcon(iconaCursore);
            }
        });
		sfondo4Panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDrugt(MouseEvent e) {
                if (iconaCursore != null) {
                    int x = e.getX();
                    int y = e.getY();
                    pedina.setBounds(x - 50, y - 87/2, 371, 87);
                }
            }
        });

		c.add(sfondo4Panel);
		ascoltatori();
		finestra();
		c.revalidate();
		c.repaint();
	}

<<<<<<< HEAD
=======
	/*
	 * ++ private void controllo(){
	 * ++
	 * ++ }
	 */
	
>>>>>>> ea4261fd681b5f880c766dfe1f9c03b64ae0f14d
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

		testBarca.addMouseListener(this);
		addKeyListener(this);
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
		} else if (e.getActionCommand().equals("Ospite")) {
			campoDaGioco();
		} else if (e.getActionCommand().equals("Account")) {
			impostazioni();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		puntoBarca = (Point) testBarca.getLocation();
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

	@Override
	public void mouseDragged(MouseEvent e) {
		
		throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F11) {
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
		throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
	}

}
