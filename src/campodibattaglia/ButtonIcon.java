package campodibattaglia;

import java.awt.Toolkit;
import javax.swing.*;

public class ButtonIcon extends JButton {
    int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    Icon indirizzo;

    ButtonIcon(int nFinestra) {
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        if (nFinestra == 1)
            indirizzo = new ImageIcon("image/singleplayer.png");

        else if (nFinestra == 2)
            indirizzo = new ImageIcon("image/multiplayer.png");

        else if (nFinestra == 3)
            indirizzo = new ImageIcon("image/impostazioni.png");

        else if (nFinestra == 4)
            indirizzo = new ImageIcon("image/ospite.png");

        else if (nFinestra == 5)
            indirizzo = new ImageIcon("image/accedi.png");

        else if (nFinestra == 6)
            indirizzo = new ImageIcon("image/indietro.png");

        else if (nFinestra == 7)
<<<<<<< HEAD
            indirizzo = new ImageIcon("image/pedina1.png");

=======
            indirizzo = new ImageIcon("image/fullscreen.png");
            
        else if (nFinestra == 8)
            indirizzo = new ImageIcon("image/impo.png");
          
>>>>>>> 7ff65b6bb39061dfd693e0f3cce8433f3e284db6
        this.setIcon(indirizzo);
    }
}
