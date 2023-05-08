package campodibattaglia;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

public class ButtonIcon extends JButton {
    int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    int x;
    int y;
    ImageIcon icon;
    Icon indirizzo;

    ButtonIcon(int nFinestra, double Width, double Height) {

        // x = (int) Width;
        // y = (int) Height;

        x = Integer.parseInt(String.valueOf((int) Width));
        y = Integer.parseInt(String.valueOf((int) Height));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        if (nFinestra == 1) {
            setBounds((int) (screenWidth / 2 - (0.1171875 * screenWidth)), screenHeight / 2, x, y);
            icon = new ImageIcon("image/singleplayer.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 2) {
            setBounds((int) (screenWidth / 2 - (0.1171875 * screenWidth)),
                    (int) (screenHeight / 2 + (0.092592592592593 * screenHeight)), x, y);
            icon = new ImageIcon("image/multiplayer.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 3) {
            setBounds((int) (screenWidth / 2 - (0.1171875 * screenWidth)),
                    (int) (screenHeight / 2 + (0.185185185185185 * screenHeight)), x, y);
            icon = new ImageIcon("image/impostazioni.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 4) {
            setBounds((int) (screenWidth / 2 - (0.208333333333333 * screenWidth)),
                    (int) (screenHeight / 2 - (0.092592592592593 * screenHeight)), x, y);
            icon = new ImageIcon("image/ospite.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 5) {
            setBounds((int) (screenWidth / 2 + (0.052083333333333 * screenWidth)),
                    (int) (screenHeight / 2 - (0.092592592592593 * screenHeight)), x, y);
            icon = new ImageIcon("image/accedi.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 6) {
            setBounds(70, 70, x, y);
            icon = new ImageIcon("image/indietro.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 7) {
            setBounds((int) (screenWidth / 2 - (0.1171875 * screenWidth)),
                    (int) (screenHeight / 2 - (0.092592592592593 * screenHeight)), x, y);
            icon = new ImageIcon("image/fullscreen.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 8) {
            setBounds(screenWidth - (140), 70, x, y);
            icon = new ImageIcon("image/impo.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 9) {
            setBounds((int) (screenWidth / 2 - (0.1171875 * screenWidth)), screenHeight / 2, x, y);
            icon = new ImageIcon("image/comandi.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (nFinestra == 10) {
            setBounds((int) (screenWidth / 2 - (0.1171875 * screenWidth)),
                    (int) (screenHeight / 2 + (0.092592592592593 * screenHeight)), x, y);
            icon = new ImageIcon("image/regole.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        this.setIcon(icon);
    }
}
