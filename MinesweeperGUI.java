
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesweeperGUI extends JFrame {
    private static final int SIZE = 10;
    private static final int MINES = 15;
    private JButton[][] buttons;
    private char[][] mineField;
    private boolean[][] revealed;
    private boolean gameOver;

    public MinesweeperGUI() {
        setTitle("Minesweeper");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[SIZE][SIZE];
        mineField = new char[SIZE][SIZE];
        revealed = new boolean[SIZE][SIZE];
        gameOver = false;

        initializeGame();
        initializeUI();
    }

    private void initializeGame() {
        // Code for initializing the game remains the same
        // ...

        // Place mines randomly
        // ...

        // Calculate numbers around mines
        // ...
    }

    private void initializeUI() {
        setLayout(new GridLayout(SIZE, SIZE));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(40, 40));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));

                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gameOver && !revealed[row][col]) {
                revealed[row][col] = true;

                if (mineField[row][col] == 'X') {
                    gameOver = true;
                    revealAllMines();
                    showGameOverDialog();
                } else if (mineField[row][col] == '0') {
                    revealEmptyCells(row, col);
                } else {
                    buttons[row][col].setText(String.valueOf(mineField[row][col]));
                }

                if (checkWin()) {
                    showWinDialog();
                }
            }
        }
    }

    private void revealEmptyCells(int row, int col) {
        // Code for revealing empty cells remains the same
        // ...

        // Update the button text
        buttons[row][col].setText(String.valueOf(mineField[row][col]));
    }

    private void revealAllMines() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mineField[i][j] == 'X') {
                    buttons[i][j].setText("X");
                }
            }
        }
    }

    private void showGameOverDialog() {
        JOptionPane.showMessageDialog(this, "Game over. You hit a mine!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showWinDialog() {
        JOptionPane.showMessageDialog(this, "Congratulations! You won!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!revealed[i][j] && mineField[i][j] != 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MinesweeperGUI minesweeperGUI = new MinesweeperGUI();
            minesweeperGUI.setVisible(true);
        });
    }
}
