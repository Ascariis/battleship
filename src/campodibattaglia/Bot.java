package campodibattaglia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {
    private int[][] tabella = new int[10][10];
    private int[] lunghezzaBarche = { 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
    private int numBarche;
    private Random random = new Random();
    boolean alreadyPlaced = false;

    private boolean destroyMode = false;
    private int lastHitRow = -1;
    private int lastHitCol = -1;
    private List<String> destroyVicini = new ArrayList<>();

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

    // SEEK & DESTROY
    public String play() {
        String position;
        int row, col;

        if (destroyMode) {

            position = destroyVicini.remove(0);
            String[] parts = position.split(",");
            row = Integer.parseInt(parts[0]);
            col = Integer.parseInt(parts[1]);

        } else {
            do {
                row = random.nextInt(10);
                col = random.nextInt(10);
            } while ((row + col) % 2 == 1); // Spara a celle alterne
            position = row + "," + col;
        }

        System.out.println(position);
        return position;
    }

    public void reportHitResult(boolean hit, int row, int col) {
        if (hit) {
            destroyMode = true;
            lastHitRow = row;
            lastHitCol = col;
            destroyVicini.clear();

            // Le celle vicini alla lista destroy Vicini
            if (row > 0)
                destroyVicini.add((row - 1) + "," + col);
            if (row < 9)
                destroyVicini.add((row + 1) + "," + col);
            if (col > 0)
                destroyVicini.add(row + "," + (col - 1));
            if (col < 9)
                destroyVicini.add(row + "," + (col + 1));
        } else if (destroyMode && destroyVicini.isEmpty()) {
            destroyMode = false; // Torna alla modalità casuale
        }
    }

    /*
     * public String play() {
     * String position;
     * 
     * int riga = random.nextInt(10);
     * int colonna = random.nextInt(10);
     * 
     * position = riga + "," + colonna;
     * System.out.println(position);
     * 
     * alreadyPlaced = true;
     * return position;
     * }
     * 
     * public int getNumBarche() {
     * return numBarche;
     * }
     */

    // Subire colpi
    public boolean getHitFromPlayer(int x, int y) {
        if (numBarche > 0) {
            if (tabella[x][y] != 0) {
                numBarche--;
                tabella[x][y] = 5;
                return true;
            } else
                return false;
        } else {
            return false;
        }

    }

    public boolean hasNoShips() {
        if (numBarche > 0) {
            return false;
        } else
            return true;
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
