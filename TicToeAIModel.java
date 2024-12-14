package TicToeTerminalVersion;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;



public class TicToeAIModel extends TicToeManualVersion{
    private boolean AIasMaximizer;
    public int playerChoice;
    private Map<String, Integer> scores = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    
    public TicToeAIModel(){
        System.out.println();
        System.out.println("===============================");        
        System.out.println("Who is the first player? \n 1. Human \n 2. AI");
        System.out.println("===============================");
        System.out.println();
        System.out.print("Enter the Player's Choice: ");
        this.playerChoice = sc.nextInt();
        player1 = (playerChoice == 1) ? "X" : "O";
        player2 = (playerChoice != 1) ? "X" : "O";
        this.AIasMaximizer = (playerChoice != 1);
        /*
         * The change made, leads to the AI as a better thinker
         * 
         * 1. updates on scores as "X" & "O" rather player1 and player2 (which unambiguously make the AI as a weak one in its Maximizer chance)
         * 
         * 2. the pruning of minimax 
         * -- with bestScore consider in alpha-beta than score
         * -- to exit from the both loops - refactored to return statement with bestScore
         * 
         */
        
        this.scores = new HashMap<>();
        // if error comes then check this first.
        scores.put("X", 1);
        scores.put("O", -1);
        scores.put("tie", 0);
    }

    public  boolean findBestMove(){
        int bestScore = (AIasMaximizer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = new int[] {-1, -1};
        int alpha = -1000;
        int beta = 1000;



        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (!board[i][j].equals("X") && !board[i][j].equals("O")) { // the values range from [1-9]
                    String temp = board[i][j];
                    board[i][j] = player2;
                    int score = minimax(0, false, alpha, beta);
                    board[i][j] = temp;


                    if ((AIasMaximizer && score > bestScore) || (!AIasMaximizer && score < bestScore)){
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestScore = score;
                    }
                }
            }
        }

        return setPosition(bestMove[0], bestMove[1], player2);
    }





    public  int minimax(int depth, boolean isMaximizing, int alpha, int beta){
        String result = checkWinner();
        if (result != null){
            return scores.get(result);
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                        String temp = board[i][j];
                        board[i][j] = player2;
                        int score = minimax(depth + 1, false, alpha, beta);
                        board[i][j] = temp;
                        bestScore = Math.max(bestScore, score);
                        alpha = Math.max(alpha, score);
                    }
                }
                if (beta <= alpha){
                    return bestScore;
                }
            }

            return bestScore;

        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                        String temp = board[i][j];
                        board[i][j] = player1;
                        int score = minimax(depth + 1, true, alpha, beta);
                        board[i][j] = temp;
                        bestScore = Math.min(bestScore, score);
                        beta = Math.min(beta, score);
                    }
                }
                if (beta <= alpha){
                    return bestScore;
                }
            }
            return bestScore;
        }

    }


    @Override
    public void playing(){
        
            
        String currentPlayer = (playerChoice == 1) ? player1 : player2;
        boolean gameOver = false; 
        int position = 0;
        boolean invalid = true;

        while (!gameOver){
            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("\t   " + currentPlayer + "'s turn.");
            System.out.println("--------------------------------");
            invalid = true;

            if (currentPlayer.equals(player1)){

                while (invalid){
                    System.out.println();
                    
                    displaying();
                    System.out.println();
                    System.out.println();

                    
                    
                    System.out.print("Enter the position: ");

                    position = sc.nextInt() - 1;// Try reading the position
                    if (position >= 0 && position < 9){
                        int row = position / 3;
                        int col = position % 3;

                        invalid = setPosition(row, col, currentPlayer);
                    } else {
                        System.out.println("Invalid input! Please enter a number between 1 to 9.");
                    }
                
                }
            } else {
                invalid = findBestMove();
            }
            displaying();

            if (checkWinner() != null) {
                gameOver = setVictory(checkWinner());
            } else {
                currentPlayer = ((currentPlayer.equals(player1)) ? player2 : player1);
            }

            System.out.println();
            
        }
    }

}