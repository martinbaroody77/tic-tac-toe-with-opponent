import java.util.*;
/**
 *
 * @author Martin
 */
public class TicTacToe 
{

   
    
   public boolean fullBoard(String[][] board){
       for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
               if(board[i][j].equals("-")) {
                   return false;
               }
            }
            
        }
       return true;
   }
    
    public void player_turn(String[][] board, Scanner s)
    {
        boolean input_loop = true;
        while(input_loop)
        {
            System.out.print("Enter where you would like to place your X: ");
            String location = s.nextLine();
            int row = Integer.parseInt(location.substring(0, 1));
            int column = Integer.parseInt(location.substring(1));
            if (board[row][column].equals("-"))
            {
                board[row][column] = "X";
                input_loop = false;
            }
            else
            {
                System.out.println("Stop that right now!");
            }
        }
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                System.out.print(board[i][j]);
                System.out.print("   ");
            }
            System.out.println("\n");
        }
    }
    
    public void ai_turn(String[][] board, TTTAI ttt, int difficulty, 
            int moves, int row2, int column2)
    {
        int[][] priority_list = new int[][] 
        {
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}
        };
        ttt.generate_move_priorities(board, difficulty, priority_list, 
                moves, row2, column2);
        
        ttt.move(board, difficulty);
        System.out.println();
        System.out.println();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                System.out.print(board[i][j]);
                System.out.print("   ");
            }
            System.out.println("\n");
        }
    }
    
    public static void main(String[] args) 
    {
        TicTacToe t = new TicTacToe();
        TTTAI ttt = new TTTAI();
        String board[][] = new String[3][3];
        int difficulty = 9;
        int moves = 0;
        int row2 = 0;
        int column2 = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
               board[i][j] = "-";
            }
            
        }
        
        Scanner s = new Scanner(System.in);
        System.out.print("You first or AI first?");
        String ans = s.nextLine();
        if (ans.equalsIgnoreCase("me"))
        {
            boolean looper = true;
            while(looper)
            {
                t.player_turn(board, s);
                System.out.println();
                boolean winx = ttt.check_win(board, "X");
                if (winx)
                {
                    System.out.println("X won!");
                    looper = false;
                    break;
                }
                else if (t.fullBoard(board)) {
                    System.out.println("It's a draw!");
                    break;
                }
                t.ai_turn(board, ttt, difficulty, moves, row2, column2);
                boolean wino = ttt.check_win(board, "O");
                if (wino)
                {
                    System.out.println("O won!");
                    looper = false;
                    break;
                }
                else if (t.fullBoard(board)) {
                    System.out.println("It's a draw!");
                    break;
                }

                System.out.print("Would you like to enter again? ");
                String yesno = s.nextLine();
                if (yesno.equalsIgnoreCase("N"))
                {
                    looper = false; 
                }
            }
        }
        else if(ans.equalsIgnoreCase("ai"))
        {
            boolean looper = true;
            while(looper)
            {
                
                t.ai_turn(board, ttt, difficulty, moves, row2, column2);
                boolean wino = ttt.check_win(board, "O");
                if (wino)
                {
                    System.out.println("O won!");
                    looper = false;
                    break;
                }
                else if (t.fullBoard(board)) {
                    System.out.println("It's a draw!");
                    break;
                }
                System.out.println();
                t.player_turn(board, s);
                boolean winx = ttt.check_win(board, "X");
                if (winx)
                {
                    System.out.println("X won!");
                    looper = false;
                    break;
                }
                else if (t.fullBoard(board)) {
                    System.out.println("It's a draw!");
                    break;
                }
                System.out.print("Would you like to enter again? ");
                String yesno = s.nextLine();
                if (yesno.equalsIgnoreCase("N"))
                {
                    looper = false; 
                }
            }
        }   
        
    }
    
}
