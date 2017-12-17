
/**
 *
 * @author Martin
 */
public class TTTAI 
{
    public boolean check_win(String[][] board, String xo)
    {
        for(int i = 0; i < board.length; i++)
        {
            if (board[i][0].equals(xo) && 
                    board[i][1].equals(xo) && board[i][2].equals(xo))
            {
                return true;
            }
            else if (board[0][i].equals(xo) && 
                    board[1][i].equals(xo) && board[2][i].equals(xo))
            {
                return true;
            }
        }
        if (board[0][0].equals(xo) && board[1][1].equals(xo)
                && board[2][2].equals(xo))
        {
            return true;
        }
        else if (board[2][0].equals(xo) && 
                board[1][1].equals(xo) && board[0][2].equals(xo))
        {
            return true;
        }
        return false;
                
    }
    
    
    public String[][] copy_board(String[][] board)
    {
        String[][] new_board = new String[3][3];
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                new_board[i][j] = board[i][j];
            }
        }
        return new_board;
    }
    
    public void generate_move_priorities(String[][] board, int difficulty, 
            int[][] priority_list, int moves, int row, int column)
    {
        if (difficulty != 0)
        {
            String[][] board2 = copy_board(board);
            for (int i = 0; i < board2.length; i++)
            {
                for (int j = 0; j < board2[i].length; j++)
                {
                    if (board2[i][j].equals("-"))
                    {
                        if (moves%2 == 0)
                        {
                            board2[i][j] = "O";
                        }
                        else
                        {
                            board2[i][j] = "X";
                        }
                        if (moves == 0)
                        {
                            row = i;
                            column = j;
                        }
                        if (check_win(board2, "O"))
                        {
                            priority_list[row][column] = 10 - moves;
                            return;
                        }
                        else if (check_win(board2, "X"))
                        {
                            priority_list[row][column] = -10 + moves;
                            return;
                        }
                        else
                        {
                            generate_move_priorities(board2, difficulty - 1, 
                                    priority_list, moves + 1, row, column);
                        }
                    }
                    else
                    {
                        if (moves == 0)
                        {
                            priority_list[i][j] = -100;
                        }                        
                    }
                    if (board[i][j].equals("-"))
                    {
                        board2[i][j] = "-";
                    }
                        
                    
                }
            }
        }
    }
    
    public void move(String[][] board, int difficulty)
    {
        int[][] priority_list = new int[][] 
        {
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}
        };
        int moves = 0;
        int row = 0;
        int column = 0;
        generate_move_priorities(board, difficulty, priority_list, 
                moves, row, column);
        int max_value = -2000;
        for (int i = 0; i < priority_list.length; i++)
        {
            for (int j = 0; j < priority_list[i].length; j++)
            {
                if (max_value < priority_list[i][j])
                {
                    max_value = priority_list[i][j];
                }
                        
            }
        }
        for (int i = 0; i < priority_list.length; i++)
        {
            for (int j = 0; j < priority_list[i].length; j++)
            {
                if (max_value == priority_list[i][j])
                {
                    board[i][j] = "O";
                    return;
                }
                        
            }
        }
    }
    
}
