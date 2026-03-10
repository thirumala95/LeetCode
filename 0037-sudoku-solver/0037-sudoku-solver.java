class Solution {

    public void solveSudoku(char[][] board) {
        solve(board);
    }

    boolean solve(char[][] board){

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){

                if(board[i][j]=='.'){

                    for(char c='1'; c<='9'; c++){

                        if(isValid(board,i,j,c)){
                            board[i][j]=c;

                            if(solve(board))
                                return true;

                            board[i][j]='.'; // backtrack
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    boolean isValid(char[][] board,int r,int c,char num){

        for(int i=0;i<9;i++){

            if(board[r][i]==num) return false;
            if(board[i][c]==num) return false;

            if(board[3*(r/3)+i/3][3*(c/3)+i%3]==num)
                return false;
        }

        return true;
    }
}