package campodibattaglia;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;

public class Pedina extends JButton {

    ImageIcon icon;
    private int taglia;
    private boolean isRotated = false;

    private int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private int mouseX, mouseY;
    private int FIELD_CLOSEST_X = screenWidth / 2 - (300);
    private int FIELD_CLOSEST_Y = screenHeight / 2 - (300);

    public Pedina(int taglia) {
        this.taglia = taglia;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        if (taglia == 1) {
            setBounds(100, taglia * 200, 60, 60);
            icon = new ImageIcon("image/barca1.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 2) {
            setBounds(100, taglia * 200, 120, 60);
            icon = new ImageIcon("image/barca2.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 3) {
            setBounds(100, taglia * 200, 180, 60);
            icon = new ImageIcon("image/barca3.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 4) {
            setBounds(100, taglia * 200, 240, 60);
            icon = new ImageIcon("image/barca4.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        this.setIcon(icon);
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) { // GODOOOOO
                    rotateButton();
                } else
                ;

                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getXOnScreen() - mouseX > FIELD_CLOSEST_X && e.getXOnScreen() - mouseX < FIELD_CLOSEST_X + 600
                        && e.getYOnScreen() - mouseY < FIELD_CLOSEST_Y + 600) {

                } else {
                    if (taglia == 1) {
                        setLocation(100, taglia * 200);
                    } else if (taglia == 2) {
                        setLocation(100, taglia * 200);
                    } else if (taglia == 3) {
                        setLocation(100, taglia * 200);
                    } else if (taglia == 4) {
                        setLocation(100, taglia * 200);
                    } else if (taglia == 5) {
                        setLocation(100, taglia * 200);
                    }
                }
                super.mouseReleased(e);
            }
        });


        
        int GRID_SIZE = 60;
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                int deltaX = e.getX() - mouseX;
                int deltaY = e.getY() - mouseY;


                int newX = getX() + deltaX;
                int newY = getY() + deltaY;

                int snappedX = (newX + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE;
                int snappedY = (newY + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE;

                setLocation(snappedX, snappedY);
            }
        });
/* 
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {

                // SNAP TO GRID BRUTTO
                if (e.getXOnScreen() - mouseX > FIELD_CLOSEST_X
                        && e.getYOnScreen() - mouseY > FIELD_CLOSEST_Y
                        && e.getXOnScreen() - mouseX < FIELD_CLOSEST_X + 600
                        && e.getYOnScreen() - mouseY < FIELD_CLOSEST_Y + 600) {

                    setLocation(((Math.round(e.getXOnScreen()) / 60) * 60), ((Math.round(e.getYOnScreen()) / 60) * 60));
                } else
                    setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
            }
        });
*/
    }

    private void setImageRotated() {
        if (taglia == 1) {
            setSize(60, 60);
            icon = new ImageIcon("image/barca1ruotata.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 2) {
            setSize(60, 120);
            icon = new ImageIcon("image/barca2ruotata.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 3) {
            setSize(60, 180);
            icon = new ImageIcon("image/barca3ruotata.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 4) {
            setSize(60, 240);
            icon = new ImageIcon("image/barca4ruotata.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        this.setIcon(icon);
    }
    
    private void setImage() {
        if (taglia == 1) {
            setSize(60, 60);
            icon = new ImageIcon("image/barca1.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 2) {
            setSize(120, 60);
            icon = new ImageIcon("image/barca2.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 3) {
            setSize(180, 60);
            icon = new ImageIcon("image/barca3.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 4) {
            setSize(240, 60);
            icon = new ImageIcon("image/barca4.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        this.setIcon(icon);
    }

    public int getTaglia() {
        return taglia;
    }

    public boolean getRotationState() {
        return isRotated;
    }

    private void rotateButton() {
        if (!isRotated) {
            setSize(getHeight(), getWidth());
            setImageRotated();
            isRotated = true;
        } else {
            setImage();
            isRotated = false;
        }

    }
}
