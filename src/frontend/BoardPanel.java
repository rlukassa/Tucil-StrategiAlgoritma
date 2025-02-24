package frontend;

import backend.Board;
import javax.swing.*;
import java.awt.*;

class BoardPanel extends JPanel {
    private Board board;

    public BoardPanel(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (board == null) return;

        char[][] grid = board.getGrid();
        int cellSize = 50; // Ukuran setiap sel di papan
        int startX = (getWidth() - grid[0].length * cellSize) / 2; // Posisi X tengah
        int startY = (getHeight() - grid.length * cellSize) / 2; // Posisi Y tengah

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Gambar grid
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(startX + j * cellSize, startY + i * cellSize, cellSize, cellSize);

                // Gambar potongan puzzle
                if (grid[i][j] != ' ') {
                    g.setColor(getColorForSymbol(grid[i][j]));
                    g.fillRect(startX + j * cellSize, startY + i * cellSize, cellSize, cellSize);

                    // Gambar huruf di tengah kotak
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(grid[i][j]), startX + j * cellSize + cellSize / 2 - 5, startY + i * cellSize + cellSize / 2 + 5);
                }
            }
        }
    }

    private Color getColorForSymbol(char symbol) {
        // Beri warna unik untuk setiap simbol potongan
        switch (symbol) {
            case 'A': return Color.RED;
            case 'B': return Color.BLUE;
            case 'C': return Color.GREEN;
            case 'D': return Color.YELLOW;
            case 'E': return Color.ORANGE;
            case 'F': return Color.CYAN;
            case 'G': return Color.MAGENTA;
            default: return Color.BLACK;
        }
    }

    public void setBoard(Board board) {
        this.board = board;
        repaint(); // Perbarui tampilan
    }
}