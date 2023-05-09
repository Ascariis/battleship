package campodibattaglia;

import java.util.Random;

public class Bot {
    private int[][] tabella = new int[10][10];
    private int[] lunghezzaBarche = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
    private int numBarche;
    private Random random = new Random();
    boolean alreadyPlaced = false;

    public Bot() {
        posizionaBarche();
    }

    private void posizionaBarche() {
        for (int lunghezza : lunghezzaBarche) {
            boolean posizionata = false;
            while (!posizionata) {
                int riga = random.nextInt(10);
                int colonna = random.nextInt(10);
                boolean verticale = random.nextBoolean();
                if (posizioneValida(riga, colonna, verticale, lunghezza)) {
                    for (int i = 0; i < lunghezza; i++) {
                        if (verticale) {
                            tabella[riga + i][colonna] = lunghezza;
                        } else {
                            tabella[riga][colonna + i] = lunghezza;
                        }
                    }
                    posizionata = true;
                }
            }
        }
        numBarche = lunghezzaBarche.length * 2;
        printMat();
    }

    public String play() {
        String position;
        int lastRiga;
        int lastColonna;

        int riga = random.nextInt(10);
        int colonna = random.nextInt(10);

        lastRiga = riga;
        lastColonna = colonna;
        position = riga + "," + colonna;
        
        if (alreadyPlaced) {
            boolean verticale = random.nextBoolean();
            if (verticale) {
                position = (lastColonna + 1) + "," + lastRiga;
                lastColonna += 1;
            } else {
                position = lastColonna + "," + (lastRiga + 1);
                lastRiga += 1;
            }

        } 
        alreadyPlaced = true;
        return position;
    }

    public int getNumBarche() {
        return numBarche;
    }

    // Subire colpi
    public boolean setShot(int x, int y) {
        if (tabella[x][y] != 0) {
            numBarche--;
            return true;
        } else
            return false;
    }

    private void printMat() {
        for (int[] x : tabella) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        // System.out.println(Arrays.deepToString(tabella));
    }

    private boolean posizioneValida(int riga, int colonna, boolean verticale, int lunghezza) {
        if (verticale) {
            if (riga + lunghezza > 10) {
                return false;
            }
            for (int i = 0; i < lunghezza; i++) {
                if (tabella[riga + i][colonna] != 0) {
                    return false;
                }
            }
        } else {
            if (colonna + lunghezza > 10) {
                return false;
            }
            for (int i = 0; i < lunghezza; i++) {
                if (tabella[riga][colonna + i] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
