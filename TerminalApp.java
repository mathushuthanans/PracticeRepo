package TicToeTerminalVersion;

import java.util.Scanner;


public class TerminalApp {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=====================================");
            System.out.println("         Welcome to Tic-Tac-Toe!   ");
            System.out.println("=====================================");


            boolean exit = false;

            while (!exit){
                int choice = 0;
                System.out.println();
                System.out.println("===================================");
                System.out.println("Please choose an option below:");
                System.out.println("1. Play against Computer");
                System.out.println("2. Play Manually");
                System.out.println("3. Exit");
                System.out.println("===================================");
                System.out.println();
                System.out.print("Enter the Choice: ");

                choice = sc.nextInt();
    
    
                if (choice == 3) {
                    System.out.println("Exiting...");
                    exit = true;
                }
                
                else if (choice == 1){
                    TicToeAIModel newGame1 = new TicToeAIModel();
                    newGame1.playing();
                } else {
                    TicToeManualVersion newGame2 = new TicToeManualVersion();
                    newGame2.playing();
                }
                System.out.println();
            }

            

        }
    }
}
