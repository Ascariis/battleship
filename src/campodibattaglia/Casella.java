package campodibattaglia;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Casella extends JButton implements MouseListener{
    int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    Icon indirizzo;

    Casella() {
        //this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
       this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));;
        
    }
    /*@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255, 100)); // Imposta il colore di sfondo del pulsante con una trasparenza del 50%
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Disegna un rettangolo arrotondato come sfondo del pulsante
        super.paintComponent(g);
    }*/
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        float opacity = (float)0.4;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        super.paintComponent(g2d);
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
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
        
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
