package campodibattaglia;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

public class ButtonIcon extends JButton{
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        Icon indirizzo;
        
        ButtonIcon (int nFinestra){
            this.setBorderPainted(false);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);

            if (nFinestra == 1)
                indirizzo = new ImageIcon("CampoDiBattaglia/image/singleplayer.png");

            else if (nFinestra == 2)
                indirizzo = new ImageIcon("CampoDiBattaglia/image/multiplayer.png");

            else if (nFinestra == 3)
                indirizzo = new ImageIcon("CampoDiBattaglia/image/impostazioni.png");

            else if (nFinestra == 4)
                indirizzo = new ImageIcon("CampoDiBattaglia/image/ospite.png");

            else if (nFinestra == 5)
                indirizzo = new ImageIcon("CampoDiBattaglia/image/accedi.png");

            else if (nFinestra == 6)
                indirizzo = new ImageIcon("CampoDiBattaglia/image/indietro.png");

            this.setIcon(indirizzo);
        }	
    }

