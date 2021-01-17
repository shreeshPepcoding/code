public class floodfill {

    public static int[] xdir = { -1, 0, 1, 0 };
    public static int[] ydir = { 0, -1, 0, 1 };
    public static char[] dname = { 't', 'l', 'd', 'r' };

    // print single path
    public static void floodfill1(int[][] maze, int row, int col, String psf, boolean[][] visited) {
        // marking
        visited[row][col] = true;

        // stupid base case
        if (row == maze.length - 1 && col == maze[0].length - 1) {
            // this is destination
            System.out.println(psf);
            return;
        }

        // make smart calls
        for (int d = 0; d < 4; d++) {
            int x = row + xdir[d];
            int y = col + ydir[d];

            if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 1
                    && visited[x][y] == false) {
                floodfill1(maze, x, y, psf + dname[d], visited);
            }
        }
    }

    // print all path
    public static void flood_fill(int[][] maze, int row, int col, String psf, boolean[][] visited) {
        // stupid base case
        if (row == maze.length - 1 && col == maze[0].length - 1) {
            // this is destination
            System.out.println(psf);
            return;
        }
        // marking
        visited[row][col] = true;

        // make smart calls
        for (int d = 0; d < 4; d++) {
            int x = row + xdir[d];
            int y = col + ydir[d];

            if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 1
                    && visited[x][y] == false) {
                flood_fill(maze, x, y, psf + dname[d], visited);
            }
        }
        // unmark
        visited[row][col] = false;
    }

    public static void main(String[] args) {
        int[][] maze = { { 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 1, 1, 0 }, { 0, 1, 0, 1, 0, 0, 0, 0 },
                { 0, 1, 0, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 1, 1, 0 },
                { 0, 1, 0, 1, 1, 1, 1, 0 }, { 0, 1, 0, 0, 0, 0, 0, 0 } };

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        floodfill1(maze, 0, 0, "", visited);
        System.out.println("=====================");
        visited = new boolean[maze.length][maze[0].length];
        flood_fill(maze, 0, 0, "", visited);
    }
}