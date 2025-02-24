package backend;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    private char symbol;
    private List<int[]> shape;

    public Piece(char symbol, String[] shapeLines) {
        this.symbol = symbol;
        this.shape = new ArrayList<>();

        for (int i = 0; i < shapeLines.length; i++) {
            String line = shapeLines[i];
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) != ' ') {
                    shape.add(new int[]{i, j}); // Simpan koordinat sel yang terisi
                }
            }
        }
    }

    public Piece(char symbol, List<int[]> shape) {
        this.symbol = symbol;
        this.shape = shape;
    }

    public char getSymbol() {
        return symbol;
    }

    public List<int[]> getShape() {
        return shape;
    }

    public List<Piece> generateRotations() {
        List<Piece> rotations = new ArrayList<>();
        rotations.add(this); // Bentuk asli

        // Rotasi 90 derajat
        List<int[]> rotatedShape = new ArrayList<>();
        for (int[] cell : shape) {
            rotatedShape.add(new int[]{cell[1], -cell[0]});
        }
        rotations.add(new Piece(symbol, rotatedShape));

        return rotations;
    }
}