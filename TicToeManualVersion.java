package TicToeTerminalVersion;
import java.util.Objects;
import java.util.Scanner;


public class TicToeManualVersion{
    public String[][] board = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
    public String player1;
    public String player2;
    public int turns;

    public TicToeManualVersion(){
        this.player1 = "X";
        this.player2 = "O";
    }
    public String checkWinner(){
        String winner = null;

        for (int row = 0; row < 3; row++){
            if (!board[row][0].equals(player1) && !board[row][0].equals(player2)) continue;
            else if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])){
                winner = board[row][0];
                break;
            }
        }
        //vertical
        for (int col = 0; col < 3; col++){
            if (!board[0][col].equals(player1) && !board[0][col].equals(player2)) continue;
            else if (Objects.equals(board[0][col], board[1][col]) && Objects.equals(board[1][col], board[2][col])){
                winner = board[0][col];
                break;
            }
        }
        //diagonal
        if ((board[0][0].equals(player1) || board[0][0].equals(player2)) && Objects.equals(board[0][0], board[1][1]) && Objects.equals(board[1][1], board[2][2])){
            winner = board[0][0];
        }
        //anti-diagonal
        if ((board[0][2].equals(player1) || board[0][2].equals(player2)) && Objects.equals(board[0][2], board[1][1]) && Objects.equals(board[1][1], board[2][0])){
            winner = board[0][2];
        }

        if ((winner == null) && (turns == 9)){
            return "tie";
        } else {
            return winner;
        }
    }
    
    public void displaying(){
        System.out.println();
        System.out.println("The Current State of the Board");
        System.out.println();

        System.out.println("\t-------------");
        for (int i = 0; i < 3; i++){
            System.out.print("\t|");
            for (int j = 0; j < 3; j++){
                System.out.printf(" %s ", board[i][j]);
                if (j == 2){
                    System.out.print("|");
                    continue;
                } else {
                    System.out.print("|");
                }
            }
            if (i == 2){
                continue;
            } else {
                System.out.println();
                System.out.print("\t----+---+----");
                System.out.println();
            }

        }
        System.out.println("\n\t-------------");
        System.out.println();
    }

    public boolean setPosition(int i, int j, String currentPlayer){

        if (board[i][j].equals(player1) || board[i][j].equals(player2)){
            System.out.println();
            System.out.println("--> INVALID POSITION! ENTER THE RIGHT ONE <--");
            return true;
        } else {
            board[i][j] = currentPlayer;
            turns++;
            return false;
        }
    }
    public boolean setVictory(String currentPlayer){
        displaying();
        System.out.println();
        if (currentPlayer.equals("tie")){
            System.out.println("      --------------------");
            System.out.println("      |    It's a " + currentPlayer +  "    |");
            System.out.println("      --------------------");
            return true;
        }

        System.out.println("      --------------------");
        System.out.println("      | " + currentPlayer + " is the winner!" + " |");
        System.out.println("      --------------------");


        return true;
    }
    public void playing(){
        try (Scanner scan = new Scanner(System.in)) {
            String currentPlayer = player1;
            

            boolean gameOver = false; 
            int position;
            boolean invalid;
            while (!gameOver){
                System.out.println("--------------------------------");
                System.out.println("\t" + currentPlayer + "'s turn.");
                System.out.println("--------------------------------");
                invalid = true;


                while (invalid){
                    System.out.println();
                    
                    displaying();
                    System.out.print("Enter the position: ");
                    position = scan.nextInt() - 1;
                    if (position >= 0 && position < 9){
                        int row = position / 3;
                        int col = position % 3;

                        
                        invalid = setPosition(row, col, currentPlayer);
                    } else {
                        System.out.println("Invalid input! Please enter a number between 1 to 9.");
                    }
                }

                displaying();


                if (checkWinner()!= null) {
                    gameOver = setVictory(checkWinner());
                } else {
                    currentPlayer = ((currentPlayer.equals(player1)) ? player2 : player1);

                }
                System.out.println();
            }
        }
    }
}
    