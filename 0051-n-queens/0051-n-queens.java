import java.util.*;

class Solution {

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];

        for(int i=0;i<n;i++)
            Arrays.fill(board[i],'.');

        solve(0,board,ans);
        return ans;
    }

    void solve(int r,char[][] board,List<List<String>> ans){

        if(r==board.length){
            List<String> temp = new ArrayList<>();
            for(char[] row:board)
                temp.add(new String(row));
            ans.add(temp);
            return;
        }

        for(int c=0;c<board.length;c++){

            if(valid(board,r,c)){

                board[r][c]='Q';
                solve(r+1,board,ans);
                board[r][c]='.';
            }
        }
    }

    boolean valid(char[][] b,int r,int c){

        for(int i=0;i<r;i++)
            if(b[i][c]=='Q') return false;

        for(int i=r-1,j=c-1;i>=0&&j>=0;i--,j--)
            if(b[i][j]=='Q') return false;

        for(int i=r-1,j=c+1;i>=0&&j<b.length;i--,j++)
            if(b[i][j]=='Q') return false;

        return true;
    }
}