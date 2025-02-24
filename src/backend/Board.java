package backend;

import java.util.Arrays;

public class Board {
    private int rows, cols;
    private char[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        for (char[] row : grid) {
            Arrays.fill(row, ' ');
        }
    }

    public boolean canPlace(Piece piece, int x, int y) {
        for (int[] cell : piece.getShape()) {
            int newX = x + cell[0];
            int newY = y + cell[1];
            if (newX < 0 || newX >= rows || newY < 0 || newY >= cols || grid[newX][newY] != ' ') {
                return false;
            }
        }
        return true;
    }

    public void place(Piece piece, int x, int y) {
        for (int[] cell : piece.getShape()) {
            int newX = x + cell[0];
            int newY = y + cell[1];
            grid[newX][newY] = piece.getSymbol();
        }
    }

    public void remove(Piece piece, int x, int y) {
        for (int[] cell : piece.getShape()) {
            int newX = x + cell[0];
            int newY = y + cell[1];
            grid[newX][newY] = ' ';
        }
    }

    public boolean isFull() {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            for (char cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}