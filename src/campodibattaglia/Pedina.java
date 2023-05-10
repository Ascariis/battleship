package campodibattaglia;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Pedina extends JButton {

    int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    ImageIcon icon;
    private int taglia;
    private boolean isRotated = false;
    private boolean LOCK = false;

    private boolean isSnapped = false;
    private int mouseX, mouseY;
    public final int FIELD_CLOSEST_X = (int)(0.395833333333333*screenWidth) - (int)(0.15625*screenWidth);
	public final int FIELD_CLOSEST_Y = (int)(0.5*screenHeight) - (int)(0.277777777777778*screenHeight);
    

    public Pedina(int taglia) {
        this.taglia = taglia;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

        if (taglia == 1) {
            setBounds(100, 150 + taglia * 100, (int)(0.026041666666667*screenWidth), (int)(0.046296296296296*screenHeight));
            icon = new ImageIcon("image/barca1.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 2) {
            setBounds(100, 150 + taglia * 100, (int)(0.052083333333333*screenWidth), (int)(0.046296296296296*screenHeight));
            icon = new ImageIcon("image/barca2.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 3) {
            setBounds(100, 150 + taglia * 100, (int)(0.078125*screenWidth), (int)(0.046296296296296*screenHeight));
            icon = new ImageIcon("image/barca3.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 4) {
            setBounds(100, 150 + taglia * 100, (int)(0.104166666666667*screenWidth), (int)(0.046296296296296*screenHeight));
            icon = new ImageIcon("image/barca4.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        this.setIcon(icon);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!LOCK) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        rotateButton();
                    } else
                        ; // Molto bellino questo

                    mouseX = e.getX();
                    mouseY = e.getY();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!LOCK) {
                    if (getX() + getWidth() > (FIELD_CLOSEST_X + (int)(0.286458333333333*screenWidth))
                            || getY() + getHeight() > (FIELD_CLOSEST_Y + (int)(0.462962962962963*screenHeight))
                            || getY() + getHeight() < (FIELD_CLOSEST_Y)
                            || ((e.getXOnScreen() < FIELD_CLOSEST_X || e.getYOnScreen() < FIELD_CLOSEST_Y))) {
                        setToDefLocation();
                    }

                }
                super.mouseReleased(e);
            }
        });

        int GRID_SIZE = 50;
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!LOCK) {
                    if ((getX() > FIELD_CLOSEST_X && getX() <= FIELD_CLOSEST_X + (int)(0.260416666666667*screenWidth))
                            && (getY() >= FIELD_CLOSEST_Y && getY() <= FIELD_CLOSEST_Y + (int)(0.462962962962963*screenHeight))) {
                        int deltaX = e.getX() - mouseX;
                        int deltaY = e.getY() - mouseY;

                        int newX = getX() + deltaX;
                        int newY = getY() + deltaY;

                        System.out.println(getWidth());
                        System.out.println(" ");
                        System.out.println(getHeight());

                        int snappedX = ((newX + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE) + (int)(0.005208333333333*screenWidth); // 10 il valore mistico
                        int snappedY = ((newY + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE) - (int)(0.009259259259259*screenHeight);

                        setLocation(snappedX, snappedY);
                        System.out.println(snappedX + " " + snappedY);
                        isSnapped = true;
                    } else
                        setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
                }

            }

        });

    }

    private void setToDefLocation() {
        if (taglia == 1) {
            setLocation(100, 150 + taglia * 100);
        } else if (taglia == 2) {
            setLocation(100, 150 + taglia * 100);
        } else if (taglia == 3) {
            setLocation(100, 150 + taglia * 100);
        } else if (taglia == 4) {
            setLocation(100, 150 + taglia * 100);
        } else if (taglia == 5) {
            setLocation(100, 150 + taglia * 100);
        }
    }

    private void setImageRotated() {
        if (taglia == 1) {
            setSize((int)(0.026041666666667*screenWidth), (int)(0.046296296296296*screenHeight));
            icon = new ImageIcon("image/barca1ruotata.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 2) {
            setSize((int)(0.026041666666667*screenWidth), (int)(0.092592592592593*screenHeight));
            icon = new ImageIcon("image/barca2ruotata.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 3) {
            setSize((int)(0.026041666666667*screenWidth), (int)(0.138888888888889*screenHeight));
            icon = new ImageIcon("image/barca3ruotata.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 4) {
            setSize((int)(0.026041666666667*screenWidth), (int)(0.185185185185185*screenHeight));
            icon = new ImageIcon("image/barca4ruotata.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        this.setIcon(icon);
    }

    private void setImage() {
        if (taglia == 1) {
            setSize((int)(0.026041666666667*screenWidth), (int)(0.046296296296296*screenHeight));
            icon = new ImageIcon("image/barca1.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 2) {
            setSize((int)(0.052083333333333*screenWidth), (int)(0.046296296296296*screenHeight));
            icon = new ImageIcon("image/barca2.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 3) {
            setSize((int)(0.078125*screenWidth), (int)(0.046296296296296*screenHeight));
            icon = new ImageIcon("image/barca3.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        } else if (taglia == 4) {
            setSize((int)(0.104166666666667*screenWidth), (int)(0.046296296296296*screenHeight));
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

    public boolean getSnapState() {
        return isSnapped;
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

    public void setLockedState() {
        LOCK = true;
    }
}
