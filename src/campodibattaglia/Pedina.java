package campodibattaglia;

//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
//import java.awt.geom.AffineTransform;
//import java.awt.image.BufferedImage;

import javax.swing.*;

public class Pedina extends JButton {

    ImageIcon icon;
    private boolean rotateIcon = false;

    private int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private int mouseX, mouseY;
    private int FIELD_CLOSEST_X = screenWidth / 2 - (300);
    private int FIELD_CLOSEST_Y = screenHeight / 2 - (300);

    public Pedina(int taglia) {

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
                    System.out.print("POBAAAA");
                }else
                
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
                        setBounds(100, taglia * 200, 120, 60);
                    } else if (taglia == 3) {
                        setBounds(100, taglia * 200, 180, 60);
                    } else if (taglia == 4) {
                        setBounds(100, taglia * 200, 240, 60);
                    } else if (taglia == 5) {
                        setBounds(100, taglia * 200, 300, 60);
                    }
                }
                super.mouseReleased(e);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {

                // SNAP TO GRID BRUTTO
                if (e.getXOnScreen() - mouseX > FIELD_CLOSEST_X
                        && e.getYOnScreen() - mouseY > FIELD_CLOSEST_Y
                        && e.getXOnScreen() - mouseX < FIELD_CLOSEST_X + 600
                        && e.getYOnScreen() - mouseY < FIELD_CLOSEST_Y + 600) {

                    setLocation((Math.round(e.getXOnScreen()) / 60) * 60, (Math.round(e.getYOnScreen()) / 60) * 60);
                } else
                    setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
                System.out.println(e.getXOnScreen() + " X BARCA");
                System.out.println(e.getYOnScreen() + " Y BARCA");
            }
        });

    }

    
/*  QUesto codice è talmente togo che non puoi capire
    boolean isRotated = false;

    public void rotateButton() {

        if (isRotated == false) {
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(90), getWidth() / 2, getHeight() / 2);
            setTransform(transform);
            isRotated = true;
        } else {
            isRotated = true;
        }

    }

    private void setTransform(AffineTransform transform) {
        if (isRotated == false) {
            setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.clearRect(0, 0, c.getWidth(), c.getHeight());
                    g2d.setTransform(transform);
                    super.paint(g2d, c);
                    g2d.dispose();
                    setSize(getHeight(), getWidth());
                }
            });
            isRotated = true;
        } else
            isRotated = false;
        
    }

    /*
     * Se dio vuole
     * public void rotate(double angle, taglia) {
     * setSize(getHeight(), getWidth());
     * ImageIcon icon = (ImageIcon) getIcon();
     * angle = 90;
     * BufferedImage img = new BufferedImage(icon.getIconWidth(),
     * icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
     * Graphics2D g2d = img.createGraphics();
     * g2d.rotate(Math.toRadians(angle), icon.getIconWidth() / taglia,
     * icon.getIconHeight() / 2);
     * icon.paintIcon(null, g2d, 0, 0);
     * g2d.dispose();
     * setIcon(new ImageIcon(img));
     * 
     * }
     */
}
