package frontend;

import backend.Board;
import backend.Piece;
import backend.Solver;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private JTextArea outputArea;
    private JButton loadButton, solveButton;
    private BoardPanel boardPanel;
    private Board board;
    private List<Piece> pieces;

    public GUI() {
        setTitle("IQ Puzzler Pro Solver");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel untuk visualisasi papan
        boardPanel = new BoardPanel(null);
        add(boardPanel, BorderLayout.CENTER);

        // Panel untuk output teks dan tombol
        JPanel controlPanel = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        controlPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        loadButton = new JButton("Load Test Case");
        solveButton = new JButton("Solve");
        buttonPanel.add(loadButton);
        buttonPanel.add(solveButton);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(controlPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> loadTestCase());
        solveButton.addActionListener(e -> solvePuzzle());
    }

    private void loadTestCase() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                String[] dimensions = br.readLine().split(" ");
                int n = Integer.parseInt(dimensions[0]);
                int m = Integer.parseInt(dimensions[1]);
                int p = Integer.parseInt(dimensions[2]);
                String s = br.readLine();

                pieces = new ArrayList<>();
                for (int i = 0; i < p; i++) {
                    String shapeLine1 = br.readLine();
                    String shapeLine2 = br.readLine(); // Baca baris kedua jika ada
                    String[] shapeLines = shapeLine2 != null ? new String[]{shapeLine1, shapeLine2} : new String[]{shapeLine1};
                    pieces.add(new Piece((char) ('A' + i), shapeLines));
                }

                // Urutkan potongan berdasarkan ukuran (dari terbesar ke terkecil)
                pieces.sort((p1, p2) -> Integer.compare(p2.getShape().size(), p1.getShape().size()));

                board = new Board(n, m);
                boardPanel.setBoard(board);
                outputArea.setText("Test case loaded successfully!\n");
            } catch (IOException e) {
                outputArea.setText("Error loading test case: " + e.getMessage());
            }
        }
    }

    private void solvePuzzle() {
        if (board == null || pieces == null) {
            outputArea.setText("Please load a test case first!");
            return;
        }

        // Jalankan solver di thread terpisah agar GUI tidak freeze
        new Thread(() -> {
            Solver solver = new Solver(board, pieces, this);
            long startTime = System.currentTimeMillis();
            boolean solved = solver.solve(0);
            long endTime = System.currentTimeMillis();

            if (solved) {
                outputArea.setText("Solution found:\n" + board.toString());
            } else {
                outputArea.setText("No solution found.\n");
            }

            outputArea.append("Waktu pencarian: " + (endTime - startTime) + " ms\n");
            outputArea.append("Banyak kasus yang ditinjau: " + solver.getCasesExamined() + "\n");
        }).start();
    }

    public void updateBoard() {
        boardPanel.setBoard(board);
    }
}