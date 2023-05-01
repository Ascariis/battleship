package campodibattaglia;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

public class Pedina extends JButton {

    Icon indirizzo;
    private int mouseX, mouseY;
        
    public Pedina(int taglia) {
        
        
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        if (taglia == 1)
            indirizzo = new ImageIcon("image/singleplayer.png");

        else if (taglia == 2)
            indirizzo = new ImageIcon("image/multiplayer.png");

        else if (taglia == 3)
            indirizzo = new ImageIcon("image/multiplayer.png");

        else if (taglia == 4)
            indirizzo = new ImageIcon("image/multiplayer.png");

        else if (taglia == 5)
            indirizzo = new ImageIcon("image/multiplayer.png");
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
}
