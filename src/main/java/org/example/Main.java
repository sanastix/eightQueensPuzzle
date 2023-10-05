package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        StringBuilder result = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int[] positions = new int[8];
            for (int i = 0; i < 8; i++) {
                positions[i] = scanner.nextInt() - 1;
            }

            int moves = solveQueens(positions);
            result.append(moves);
        }

        System.out.println(result);
    }

    private static boolean isNotUnderAttack(int[] board, int row, int col) {
        //Перевіряємо, чи атакує ферзь інших ферзів
        for (int i = 0; i < row; i++) {
            if (board[i] == col || board[i] - i == col - row || board[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }

    private static int solveQueens(int[] positions) {
        int[] board = new int[8];
        System.arraycopy(positions, 0, board, 0, 8);
        return solveQueensRec(board, 0, 0);
    }

    private static int solveQueensRec(int[] board, int row, int moves) {
        if (row == 8) {
            return moves;
        }

        //Ферзь може бути розміщений на клітинці, якщо він не під атакою
        int minMoves = Integer.MAX_VALUE;
        for (int col = 0; col < 8; col++) {
            if (isNotUnderAttack(board, row, col)) {
                int[] newBoard = board.clone();
                newBoard[row] = col;
                minMoves = Math.min(minMoves, solveQueensRec(newBoard, row + 1, moves + (board[row] == col ? 0 : 1)));
            }
        }
        return minMoves;
    }
}
