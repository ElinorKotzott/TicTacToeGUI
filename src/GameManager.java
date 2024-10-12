public class GameManager {
    private Runnable myRepaintReference;
    private int[][] board;
    private int width;
    private int height;
    private boolean isPlayerTwoTurn = false;

    public GameManager(Runnable r, int[][] board, int x, int y) {
        this.myRepaintReference = r;
        this.board = board;
        this.width = x;
        this.height = y;
    }

    public void handleMouseClick(int x, int y) {
        if (isOccupiedElseSetMarker(checkQuadrantClicked(x, y))) {
            System.out.println("You cannot set a marker here. Please choose a different quadrant.");
        } else {
            if (isPlayerWin()) {
                if (!isPlayerTwoTurn) {
                    System.out.println("Congrats, player one, you won!");
                } else {
                    System.out.println("Congrats, player two, you won!");
                }
                myRepaintReference.run();
                System.exit(0);
            }
            if (isNoWin()) {
                emptyBoard();
            }
            myRepaintReference.run();
        }


    }

    // TODO: If we should repaint, we call
//        myRepaintReference.run();

    // 1. Quadrant: x 0-200, y 0-200
    // 2. Quadrant: x 201-400, y 0-200
    // 3. Quadrant: x 401 - 600, y 0-200
    // 4. Quadrant: x 0-200, y 201 - 400
    // 5. Quadrant: x 201 - 400, y 201 - 400
    // 6. Quadrant: x 401 - 600, y 201 - 400
    // 7. Quadrant: x 0 - 200, y 401 - 600
    // 8. Quadrant: x 201 - 400, y 401 - 600
    // 9. Quadrant: x 401 - 600, y 401 - 600


    public int checkQuadrantClicked(int x, int y) {
        if (x >= 0 && x <= width / 3 && y >= 0 && y <= height / 3) {
            return 1;
        } else if ((x > width / 3 && x <= width * 2 / 3 && y >= 0 && y <= height / 3)) {
            return 2;
        } else if ((x > width * 2 / 3 && x <= width && y >= 0 && y <= height / 3)) {
            return 3;
        } else if ((x >= 0 && x <= width / 3 && y > height / 3 && y <= height * 2 / 3)) {
            return 4;
        } else if ((x > width / 3 && x <= width * 2 / 3 && y > height / 3 && y <= height * 2 / 3)) {
            return 5;
        } else if ((x > width * 2 / 3 && x <= width && y > height / 3 && y <= height * 2 / 3)) {
            return 6;
        } else if ((x >= 0 && x <= width / 3 && y > height * 2 / 3 && y <= height)) {
            return 7;
        } else if ((x > width / 3 && x <= width * 2 / 3 && y > height * 2 / 3 && y <= height)) {
            return 8;
        } else if ((x > width * 2 / 3 && x <= width && y > height * 2 / 3 && y <= height)) {
            return 9;
        } else {
            return -1;
        }
    }

    public boolean isOccupiedElseSetMarker(int quadrantClicked) {
        int q = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                q++;
                if (quadrantClicked == q) {
                    if (board[i][j] == 1 || board[i][j] == 2) {
                        return true;
                    } else {
                        if (!isPlayerTwoTurn) {
                            board[i][j] = 1;
                        } else {
                            board[i][j] = 2;
                        }
                        isPlayerTwoTurn = !isPlayerTwoTurn;
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean isPlayerWin() {
        return (isHorizontalWin() || isVerticalWin() || isDiagonalWin());
    }

    public boolean isNoWin() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isHorizontalWin() {
        for (int i = 0; i < board.length; i++) {
            int counterFor1 = 0;
            int counterFor2 = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    counterFor1++;
                    if (counterFor1 == 3) {
                        return true;
                    }
                } else if (board[i][j] == 2) {
                    counterFor2++;
                    if (counterFor2 == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isVerticalWin() {
        for (int i = 0; i < board.length; i++) {
            int counterFor1 = 0;
            int counterFor2 = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == 1) {
                    counterFor1++;
                    if (counterFor1 == 3) {
                        return true;
                    }
                } else if (board[j][i] == 2) {
                    counterFor2++;
                    if (counterFor2 == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isDiagonalWin() {
        int counterFor1 = 0;
        int counterFor2 = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == 1) {
                counterFor1++;
                if (counterFor1 == 3) {
                    return true;
                }
            } else if (board[i][i] == 2) {
                counterFor2++;
                if (counterFor2 == 3) {
                    return true;
                }
            }
        }
        counterFor1 = 0;
        counterFor2 = 0;
        for (int j = 0; j < board.length; j++) {
            if (board[j][board.length - j - 1] == 1) {
                counterFor1++;
                if (counterFor1 == 3) {
                    return true;
                }
            } else if (board[j][board.length - j - 1] == 2) {
                counterFor2++;
                if (counterFor2 == 3) {
                    return true;
                }
            }
        }
        return false;
    }


    public void emptyBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = 0;
            }
        }
    }
}



    


