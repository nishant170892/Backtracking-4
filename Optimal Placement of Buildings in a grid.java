public class Main {

    public static class BuildingPlacement
    {
        int H, W, n, minDistance;
        int[][] grid;

        BuildingPlacement(int H, int W, int n)
        {
            this.H = H;
            this.W = W;
            this.n = n;
            minDistance = Integer.MAX_VALUE;

            grid = new int[H][W];

            // All the slots in grid as of now are parking lots represented by -1
            for(int i = 0; i < H; i++)
            {
                for(int j = 0; j < W; j++)
                {
                    grid[i][j] = -1;
                }
            }
        }

        public int findMinDistance()
        {
            // This will give us the minimum distance by applying BFS technique
            backtrack(0, 0, n);
            return minDistance;
        }

        public void findDistance()
        {
            boolean[][] visited = new boolean[H][W];

            Queue<int[]> queue = new LinkedList();

            for(int i = 0; i < H; i++)
            {
                for(int j = 0; j < W; j++)
                {
                    // This has been marked as a building
                    if(grid[i][j] == 0)
                    {
                        queue.add(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }

            int distance = 0;
            int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

            while(!queue.isEmpty())
            {
                // We need a size variable bcoz we are making a decision of finding minimum at a level
                int size = queue.size();

                for(int i = 0; i < size; i++)
                {
                    int[] pair = queue.poll();

                    for(int[] dir : dirs)
                    {
                        int row = dir[0] + pair[0];
                        int col = dir[1] + pair[1];

                        if(row >= 0 && row < H && col >= 0 && col < W && visited[row][col] == false)
                        {
                            queue.add(new int[]{row, col});
                            visited[row][col] = true;
                        }
                    }
                }

                ++distance;
            }

            if(minDistance > distance - 1)
            {
                System.out.println("Printing Grid : ");
                System.out.println();
                for(int i = 0; i < H; i++)
                {
                    for(int j = 0; j < W; j++)
                    {
                        System.out.print(grid[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }

            // This is bcoz distance will also include the current position. Just eliminate that
            minDistance = Math.min(minDistance, distance - 1);
        }

        public void backtrack(int row, int col, int n)
        {
            // base case
            // All the buildings have been placed
            if(n == 0)
            {
                // As all the buildings are placed, now it's time to call BFS
                findDistance();
                return;
            }

            // If column crossed its bound
            if(col == W)
            {
                // Go to next row
                row++;
                // Make column as 0
                col = 0;
            }

            for(int i = row; i < H; i++)
            {
                for(int j = col; j < W; j++)
                {
                    // action
                    // By marking 0 it means we placed a building
                    grid[i][j] = 0;

                    // recurse
                    // Once we placed one building at 0, 0 at beginning the next building we could place is at 0, 1
                    backtrack(i, j + 1, n - 1);

                    // backtrack
                    grid[i][j] = -1;
                }
            }
        }
    }
    public static void main(String[] args) {
        // Here H = 4, W = 4 and n(number of buildings) = 2
        BuildingPlacement bp = new BuildingPlacement(4, 4, 2);

        int minDistance = bp.findMinDistance();
        System.out.println("Minimum distance is : " + minDistance);
    }
}
