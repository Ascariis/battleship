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

    ButtonIcon(int nFinestra, float Width, float Height) {
        x = Integer.parseInt(String.valueOf((int) Width));
        y = Integer.parseInt(String.valueOf((int) Height));
        System.out.println("x"+x);
        System.out.println("y"+y);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        if (nFinestra == 1){
            setBounds(screenWidth / 2 - ((225/1920)*screenWidth), screenHeight / 2, x,y);
            icon = new ImageIcon("image/singleplayer.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 2){
            setBounds(screenWidth / 2 - ((225/1920)*screenWidth), screenHeight / 2 + ((100/1080)*screenHeight), x,y);
            icon = new ImageIcon("image/multiplayer.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 3){
            setBounds(screenWidth / 2 - ((225/1920)*screenWidth), screenHeight / 2 + ((200/1080)*screenHeight), x,y);
            icon = new ImageIcon("image/impostazioni.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 4){
            setBounds(screenWidth / 2 - ((400/1920)*screenWidth), screenHeight / 2 - ((100/1080)*screenHeight), x, y);
            icon = new ImageIcon("image/ospite.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 5){
            setBounds(screenWidth / 2 + ((100/1920)*screenWidth), screenHeight / 2 - ((100/1080)*screenHeight), x, y);
            icon = new ImageIcon("image/accedi.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 6){
            setBounds(70, 70, x, y);
            icon = new ImageIcon("image/indietro.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 7){
        setBounds(screenWidth / 2 - ((225/1920)*screenWidth), screenHeight / 2 - ((100/1080)*screenHeight),  x,y);
        icon = new ImageIcon("image/fullscreen.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 8){
            setBounds(screenWidth - (140), 70, x, y);
            icon = new ImageIcon("image/impo.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 9){
            setBounds(screenWidth / 2 - ((225/1920)*screenWidth), screenHeight / 2,  x,y);
            icon = new ImageIcon("image/comandi.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else if (nFinestra == 10){
            setBounds(screenWidth / 2 - ((225/1920)*screenWidth), screenHeight / 2 + ((100/1080)*screenHeight),  x,y);
            icon = new ImageIcon("image/regole.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        this.setIcon(icon);
    }
}
