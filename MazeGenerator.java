package Hackathon;
/**
 * Uses the Aldous-Broder 
 * Select a random cell and add it to the list visited cells. This is the current cell
 * Randomly choose an adjacent cell, this cell is now the current cell
 * If the cell is not in the list of visited cells
 * Add it to the list of visited cells
 * Remove the edge between the current cell and the previous cell
 * Else
 * Do nothing
 * Repeat step 2 until all cells have been visited
*/
import java.util.*;

public class MazeGenerator
{

    private static final int DEFAULT_VAL = 10;
    private boolean[][] maze; //marking visited cells in grid

    public MazeGenerator()
    {
        maze = createMaze(DEFAULT_VAL);
    }

    public MazeGenerator(int length)
    {
        maze = createMaze(length);
    }

    public boolean[][] createMaze(int length)
    {
        boolean[][] visited = new boolean[length * 2 + 1][length * 2 + 1];
        int numVisited = 0;
        int currX = 1; 
        int currY = 1;
        Random rand = new Random();
        while(numVisited < length * length)
        {
          int randInt = rand.nextInt(4);
          if(randInt == 0) //North
          {
            if(currX == 1)
              continue;
            
            currX -= 2;
            if(visited[currX][currY])
              continue;
            else if(!visited[currX][currY])
            {
              visited[currX][currY] = true;
              visited[currX + 1][currY] = true;
            }
          }
          else if(randInt == 1) //South
          {
            if(currX == visited.length - 2)
              continue;
            
            currX += 2;
            if(visited[currX][currY])
              continue;
            else if(!visited[currX][currY])
            {
              visited[currX][currY] = true;
              visited[currX - 1][currY] = true;
            }
          }
          else if(randInt == 2) //West
          {
            if(currY == 1)
              continue;
            
            currY -= 2;
            if(visited[currX][currY])
              continue;
            else if(!visited[currX][currY])
            {
              visited[currX][currY] = true;
              visited[currX][currY + 1] = true;
            }
          }
          else if(randInt == 3) //East
          {
            if(currY == visited[0].length - 2)
              continue;
            
            currY += 2;
            if(visited[currX][currY])
              continue;
            else if(!visited[currX][currY])
            {
              visited[currX][currY] = true;
              visited[currX][currY - 1] = true;
            }
          }
          numVisited++;
        }
        return visited;
    }

    @Override
    public String toString()
    {
        String printedMaze = "";
        for(int row = 0; row < maze.length; row++)
        {
            for(int col = 0; col < maze[0].length; col++)
            {
                if((col == 0 && row == 1) || (col == maze[0].length - 1 && row == maze.length - 2))
                {
                    printedMaze += " ";
                }
                else if(row == 0)
                {
                  if(col == 0)
                    printedMaze += "|";
                  else if(col == maze[0].length - 1)
                    printedMaze += "|";
                  else
                    printedMaze += "+---";
                }
                else if(col == 0)
                {
                    printedMaze += "|";
                }
                else if(col == maze[0].length - 1)
                {
                    printedMaze += "|";
                }
                else if(row % 2 == 0)
                {
                    if(maze[row][col])
                    {
                        if(col == 1)
                          printedMaze += "    ";
                        else
                          printedMaze += "|   ";
                    }
                    else
                    {
                      printedMaze += "+---";
                    }
                }
                else if(col % 2 == 0)
                {
                    if(maze[row][col])
                    {
                        printedMaze += "    ";
                    }
                    else
                    {
                      printedMaze += "+   ";
                    }
                }
                else
                {
                    printedMaze += "    ";
                }
                
     
            }
            printedMaze += "\n";
        }
        return printedMaze;
    }
  public static void main(String[] args)
  {
    MazeGenerator mazeTest = new MazeGenerator(20); 
    System.out.println(mazeTest.toString());
    
  }
}