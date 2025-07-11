// Tic Tac Toe is fun!

import java.awt.*;
import java.awt.event.*;

public class TicTacToe_Realisan extends Frame implements ActionListener {
    Button[] buttons = new Button[9];
    Button newGameButton;
    Label statusLabel;
    boolean xTurn = true;
    
    public TicTacToe_Realisan() {
        setTitle("AWT Tic Tac Toe");
        setSize(400, 450);
        setLayout(new BorderLayout());

        statusLabel = new Label("Player X's Turn");
        add(statusLabel, BorderLayout.NORTH);

        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 80));
            buttons[i].addActionListener(this);
            gridPanel.add(buttons[i]);
        }
        add(gridPanel, BorderLayout.CENTER);

        newGameButton = new Button("New Game");
        newGameButton.addActionListener(e -> resetGame());
        add(newGameButton, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        if (!clicked.getLabel().equals("")) return;

        clicked.setLabel(xTurn ? "X" : "O");
        if (checkWinner()) {
            statusLabel.setText("Player " + (xTurn ? "X" : "O") + " wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a Draw!");
        } else {
            xTurn = !xTurn;
            statusLabel.setText("Player " + (xTurn ? "X" : "O") + "'s Turn");
        }
    }

    private boolean checkWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getLabel();
        }

        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) return true;
            if (!board[0][i].equals("") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) return true;
        }

        if (!board[0][0].equals("") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) return true;
        if (!board[0][2].equals("") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) return true;

        return false;
    }

    private boolean isDraw() {
        for (Button b : buttons) {
            if (b.getLabel().equals("")) return false;
        }
        return true;
    }

    private void disableButtons() {
        for (Button b : buttons) {
            b.setEnabled(false);
        }
    }

    private void resetGame() {
        for (Button b : buttons) {
            b.setLabel("");
            b.setEnabled(true);
        }
        xTurn = true;
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        new TicTacToe_Realisan();
    }
}