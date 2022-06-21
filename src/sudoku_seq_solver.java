import java.util.ArrayList;

public class sudoku_seq_solver {
    public static boolean solver(int[][] currentPuzzle, int[][] answer){

        //Since solution and currentPuzzle are connected, can't have isolated enviornments for future use I.E. look at line 67
        int[][] solution = new int[9][9];

        for(int p = 0; p < 9; p++) {
            System.arraycopy(currentPuzzle[p], 0, solution[p], 0, 9);
        }

        ArrayList<Integer> counter = new ArrayList<Integer>();

        for(int z = 1; z < 10; z++) {
            counter.add(z);
        }

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(solution[i][j] == 0) {
                    //Column
                    for(int a = 0; a < 9; a++) {
                        if(a == i) {
                            
                        }
                        else {
                            if(counter.lastIndexOf(solution[a][j]) != -1) {
                                counter.remove(counter.lastIndexOf(solution[a][j]));
                            }
                        }
                    }
                    
                    //Row
                    for(int b = 0; b < 9; b++) {
                        if(b == j) {

                        }
                        else {
                            if(counter.lastIndexOf(solution[i][b]) != -1) {
                                counter.remove(counter.lastIndexOf(solution[i][b]));
                            }
                        }
                    }

                    //Within the square
                    for(int c = 0; c < 3; c++) {
                        for(int d = 0; d < 3; d++) {
                            if(((i/3)*3 + c) == i && ((j/3)*3 + d) == j) {

                            }
                        else {
                            if(counter.lastIndexOf(solution[(i/3)*3 + c][(j/3)*3 + d]) != -1) {
                                counter.remove(counter.lastIndexOf(solution[(i/3)*3 + c][(j/3)*3 + d]));
                            }
                        }
                    }
                }

            //Need to refigure this logic out, causing current bug
            if(counter.size() != 0) {
                for(int e = 0; e < counter.size(); e++) {
                    solution[i][j] = counter.get(e);
                    if(i == 8 && j == 8) {
                        for(int p = 0; p < 9; p++) {
                            System.arraycopy(solution[p], 0, answer[p], 0, 9);
                        }
                        return true;
                    }
                    else {
                        if(solver(solution, answer) == false) {
                            for(int p = 0; p < 9; p++) {
                                System.arraycopy(currentPuzzle[p], 0, solution[p], 0, 9);
                            }
                        }
                        else {
                            currentPuzzle = solution;
                            return true;
                        }
                    }
                }
            }
            else {
                return false;
            }
                
            }
        }
    }
    return true;
}

    public static void main(String[] args) throws Exception {
        int[][] puzzle = 
        {{0, 5, 0, 1, 0, 0, 0, 0, 0}, 
        {2, 0, 4, 0, 0, 0, 0, 9, 3}, 
        {0, 0, 0, 0, 0, 3, 4, 5, 0}, 
        {7, 2, 1, 0, 3, 8, 6, 4, 0}, 
        {4, 3, 0, 0, 5, 7, 9, 8, 1}, 
        {0, 0, 0, 0, 6, 1, 0, 0, 2}, 
        {0, 0, 0, 0, 0, 4, 0, 0, 9}, 
        {1, 0, 5, 3, 0, 0, 8, 0, 0}, 
        {6, 4, 0, 8, 0, 2, 0, 0, 0}};
        // int[][] puzzle = 
        // //Breaks at 2,2.  Starting throwing weird numbers.
        // {{3, 5, 8, 1, 4, 9, 2, 7, 6}, 
        // {2, 6, 4, 0, 0, 0, 0, 9, 3}, 
        // {0, 0, 0, 0, 0, 3, 4, 5, 0}, 
        // {7, 2, 1, 0, 3, 8, 6, 4, 0}, 
        // {4, 3, 0, 0, 5, 7, 9, 8, 1}, 
        // {0, 0, 0, 0, 6, 1, 0, 0, 2}, 
        // {0, 0, 0, 0, 0, 4, 0, 0, 9}, 
        // {1, 0, 5, 3, 0, 0, 8, 0, 0}, 
        // {6, 4, 0, 8, 0, 2, 0, 0, 0}};
        int[][] answer = new int[9][9]; 

        solver(puzzle, answer);

        for(int i=0;i<9;i++){  
            for(int j=0;j<9;j++){  
              System.out.print(answer[i][j]+" ");  
            }  
            System.out.println();  
        }
    }
}
