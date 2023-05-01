package campodibattaglia;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Pedina extends JButton {

    Icon indirizzo;
    private int mouseX, mouseY;
        
    public Pedina(int taglia) {
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        if (taglia == 1) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 65, 65);
        }
        else if (taglia == 2) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 130, 65);
        }
        else if (taglia == 3) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 195, 65);
        }
        else if (taglia == 4) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 260, 65);
        }
        else if (taglia == 5) {
            indirizzo = new ImageIcon("image/singleplayer.png");
            setBounds(100, taglia * 150, 325, 65);
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
                setLocation((e.getXOnScreen() - mouseX), (e.getYOnScreen() - mouseY));
            }
        });
    }

    public void rotate(double angle) {
        setSize(getHeight(), getWidth());
        // Get the current Icon of the button
        ImageIcon icon = (ImageIcon)getIcon();
        angle = 90;
        // Create a new rotated version of the Icon
        BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.rotate(Math.toRadians(angle), icon.getIconWidth()/2, icon.getIconHeight()/2);
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        setIcon(new ImageIcon(img));
        
    }
}
