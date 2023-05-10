package campodibattaglia;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Casella extends JButton implements MouseListener, ActionListener {

    Icon indirizzo;
    private int posInArrayX;
    private int posInArrayY;
    private Home homeInstance;

    Casella() {
        // this.setBorderPainted(false);

        this.setContentAreaFilled(false);
        // this.setFocusPainted(false);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

    }

    Casella(Home homeInstance) {
        // this.setBorderPainted(false);
        this.homeInstance = homeInstance;
        this.setContentAreaFilled(false);
        // this.setFocusPainted(false);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

    }

    public int getPosX() {
        return getX();
    }

    public int getPosY() {
        return getY();
    }

    public void setPosX(int x) {
        posInArrayX = x;
    }

    public void setPosY(int y) {
        posInArrayY = y;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        float opacity = (float) 0.4;
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

    private void botTurn() {
        if (homeInstance.bot.hasNoShips()) {
            // homeInstance.fine();
            System.out.println("EOIA");
        } else {
            boolean botHasHit = false;
            do {
                homeInstance.bot.play();
                String botResponseString = homeInstance.bot.play();
                System.out.println(botResponseString);
                String[] splitStr = botResponseString.split(",");
                int X = Integer.parseInt(splitStr[0]);
                int Y = Integer.parseInt(splitStr[1]);
                botHasHit = homeInstance.setPlayerShot(X, Y);

                if (botHasHit) {
                    homeInstance.bot.reportHitResult(botHasHit, X, Y);
                } else {
                    botHasHit = false;
                    //break;
                }
            } while (botHasHit);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean isHit = homeInstance.bot.getHitFromPlayer(posInArrayY, posInArrayX);
        // Check if hit is registered from player to Bot
        if (isHit) {
            setBackground(Color.GREEN);
        } else { // if Hits water turn goes to Bot
            System.out.println("AGUA");
            setBackground(Color.GRAY);

            botTurn();
        }

    }

}
