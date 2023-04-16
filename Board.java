public class Board {
    private int[][] tiles;
    private int length;
    private int[][] goalTiles = {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}};

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.length = tiles.length;
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

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1) {
                if (tiles[i][j] != goalTiles[i][j] && tiles[i][j] != 0) {

                }
            }
        }

        return 0;
    }
/*
    // is this board the goal board?
    public boolean isGoal() {

    }

    // does this board equal y?
    public boolean equals(Object y) {

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

    } */

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] sample = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Board newB = new Board(sample);

        System.out.println(newB.toString());
    }

}