package campodibattaglia;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Pedina extends JButton {

    // Define the grid size and origin
    private static final int GRID_SIZE = 65;
    private static final int GRID_ORIGIN_X = 100;
    private static final int GRID_ORIGIN_Y = 150;
    Icon indirizzo;
    private int mouseX, mouseY;

    public Pedina(int taglia) {
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        if (taglia == 1) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 50, 50);
        } else if (taglia == 2) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 120, 50);
        } else if (taglia == 3) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 180, 50);
        } else if (taglia == 4) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 240, 50);
        } else if (taglia == 5) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 300, 50);
        }
        this.setIcon(indirizzo);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                boolean isOnSafezone = false;
                if (e.getXOnScreen() - mouseX >= screenWidth / 2 - (300) && e.getYOnScreen() - mouseY >= screenHeight / 2 - (300)) {
                    setLocation( (int) ((e.getXOnScreen() / 50 ) * 50), (int) ((e.getYOnScreen() / 50 ) * 50));
                    
                } else 
                    setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
            }
        });
    }
    
    /* Se dio vuole
    public void rotate(double angle, taglia) {
        setSize(getHeight(), getWidth());
        ImageIcon icon = (ImageIcon) getIcon();
        angle = 90;
        BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.rotate(Math.toRadians(angle), icon.getIconWidth() / taglia, icon.getIconHeight() / 2);
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        setIcon(new ImageIcon(img));

    }*/
}
