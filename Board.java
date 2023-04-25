import edu.princeton.cs.algs4.Queue;

public class Board {
    private int[][] tiles;
    private int length;
    private int zeroRow, zeroCol;
    private int[][] goalTiles = {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}};

    private class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.length = tiles.length;
        for (int i = 0; i < length; i += 1)
            for (int j = 0; j < length; j += 1)
                if (tiles[i][j] == 0) {
                    this.zeroRow = i;
                    this.zeroCol = j;

                }
    }

    // string representation of this board
    public String toString() {
        String strResult = "";
        strResult += tiles.length + "\n";
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1) {
                strResult += " " + tiles[i][j];
            }
            strResult += "\n";
        }
        return strResult;
    }

    // board dimension n
    public int dimension() {
        return length;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1) {
                if (tiles[i][j] != 0 && tiles[i][j] != goalTiles[i][j])
                    count += 1;
            }
        }

        return count;
    }

    private int stepsDistanceTwoPoint(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    // sum of Manhattan distances between tiles and goalasd
    public int manhattan() {
        // init the correct position for titles by x,y
        Position[] correctPosition = new Position[10];
        for (int i = 0; i < length; i += 1)
            for (int j = 0; j < length; j += 1) {
                if (goalTiles[i][j] != 0) {
                    correctPosition[goalTiles[i][j]] = new Position(i, j);
                }
            }

        int sum = 0;
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1) {
                if (tiles[i][j] != 0) {
                    int thisX = correctPosition[tiles[i][j]].x;
                    int thisY = correctPosition[tiles[i][j]].y;
                    sum += stepsDistanceTwoPoint(i, j, thisX, thisY);
                }
            }
        }

        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return y.equals(tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> q = new Queue<Board>();
        Board copy;

        if (zeroRow > 0) {
            copy = new Board(this.tiles);
            copy.switchZero(zeroRow, zeroCol, zeroRow - 1, zeroCol);
            q.enqueue(copy);
        }

        if (zeroRow < length - 1) {
            copy = new Board(this.tiles);
            copy.switchZero(zeroRow, zeroCol, zeroRow + 1, zeroCol);
            q.enqueue(copy);
        }

        if (zeroCol > 0) {
            copy = new Board(this.tiles);
            copy.switchZero(zeroRow, zeroCol, zeroRow, zeroCol - 1);
            q.enqueue(copy);
        }

        if (zeroCol < length - 1) {
            copy = new Board(this.tiles);
            copy.switchZero(zeroRow, zeroCol, zeroRow, zeroCol + 1);
            q.enqueue(copy);
        }

        return q;
    }

    private void switchZero(int x1, int y1, int x2, int y2) {
        int temp = tiles[x1][y1];
        tiles[x1][y1] = tiles[x2][y2];
        tiles[x2][y2] = temp;

        if (tiles[x1][y1] == 0) {
            zeroRow = x1;
            zeroCol = y1;
        }

        if (tiles[x2][y2] == 0) {
            zeroRow = x2;
            zeroCol = y2;
        }
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        int[][] copy = this.tiles;
        int temp = copy[0][0];
        copy[0][0] = copy[0][1];
        copy[0][1] = temp;
        Board copyB = new Board(copy);
        
        return copyB;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] sample = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };

        Board newB = new Board(sample);

        System.out.println(newB.toString());
        System.out.println(newB.manhattan());
    }

}