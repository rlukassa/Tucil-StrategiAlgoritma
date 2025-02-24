package backend;

import frontend.GUI;

import java.util.List;

public class Solver {
    private Board board;
    private List<Piece> pieces;
    private int casesExamined;
    private GUI gui;

    public Solver(Board board, List<Piece> pieces, GUI gui) {
        this.board = board;
        this.pieces = pieces;
        this.casesExamined = 0;
        this.gui = gui;
    }

    public boolean solve(int index) {
        if (index == pieces.size()) {
            return board.isFull(); // Cek apakah papan terisi penuh
        }

        Piece currentPiece = pieces.get(index);
        System.out.println("Mencoba menempatkan potongan " + currentPiece.getSymbol());

        for (Piece rotatedPiece : currentPiece.generateRotations()) {
            for (int x = 0; x < board.getGrid().length; x++) {
                for (int y = 0; y < board.getGrid()[0].length; y++) {
                    if (board.canPlace(rotatedPiece, x, y)) {
                        System.out.println("Menempatkan " + rotatedPiece.getSymbol() + " di (" + x + ", " + y + ")");
                        board.place(rotatedPiece, x, y);
                        casesExamined++;

                        if (!hasUnfillableArea(index)) {
                            if (solve(index + 1)) {
                                return true; // Solusi ditemukan
                            }
                        }

                        System.out.println("Backtrack: Menghapus " + rotatedPiece.getSymbol() + " dari (" + x + ", " + y + ")");
                        board.remove(rotatedPiece, x, y);
                        gui.updateBoard();
                    }
                }
            }
        }

        System.out.println("Tidak ada solusi untuk potongan " + currentPiece.getSymbol());
        return false; // Tidak ada solusi untuk konfigurasi ini
    }

    private boolean hasUnfillableArea(int index) {
        // Hitung jumlah sel kosong di papan
        int emptyCells = 0;
        for (char[] row : board.getGrid()) {
            for (char cell : row) {
                if (cell == ' ') {
                    emptyCells++;
                }
            }
        }

        // Hitung total ukuran potongan yang tersisa
        int remainingPiecesSize = 0;
        for (int i = index; i < pieces.size(); i++) {
            remainingPiecesSize += pieces.get(i).getShape().size();
        }

        // Jika jumlah sel kosong lebih kecil dari ukuran potongan yang tersisa, ada area yang tidak bisa diisi
        return emptyCells < remainingPiecesSize;
    }

    public int getCasesExamined() {
        return casesExamined;
    }
}