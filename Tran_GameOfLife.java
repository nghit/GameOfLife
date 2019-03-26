/********************************
 * file: Tran_GameOfLife.java
 * author: N. Tran
 * class: CS 141 - Programing and Problem Solving
 *
 * assignment: program 6
 * date last modified: 3/16/2017
 *
 * purpose: implements Conway's Game of Life
 ********************************/
 
import java.io.*;
import java.util.Scanner;

public class Tran_GameOfLife
{
    private char [][] board;
    private int numOfRow;
    private int numOfCol;
    
    //Constructor
    public Tran_GameOfLife()
    {
        try
        {
            System.out.print("Enter file name: ");
            Scanner scan = new Scanner(new File("/Users/NghiTran/Documents/CS141/" + new Scanner(System.in).nextLine()));

            numOfCol = scan.nextInt();
            numOfRow = scan.nextInt();
            
            board = new char [numOfRow][numOfCol];
            
            for(int r = 0; r < numOfRow; r++)
            {
                for(int c = 0; c < numOfCol; c++)
                {
                    board[r][c] = scan.next().charAt(0);
                }
            }
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //getter, setter
    public int getColumns()
    {
        return numOfCol;
    }
    
    public int getRows()
    {
        return numOfRow;
    }
    
    public int getCell(int row, int col)
    {
        return board[row][col];
    }
    
    public void setCell(int column, int row, int value)
    {
        board[row][column] = (char) value;
    }
    
    public void print()
    {
        for(int r = 0; r < getRows(); r++)
        {
            for(int c = 0; c < getColumns(); c++)
            {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }
    
    public boolean isAlive(int cell)
    {
        return cell == 'X';
    }
    
    //determine whether or not cell is alive
    public int getNumAdjacentLivingCells(int row, int col) 
    {
        int numLiving = 0;

        if (row - 1 >= 0 && col - 1 >= 0 && isAlive(getCell(row - 1, col - 1))) 
        {
            numLiving++;
        }
        if (row - 1 >= 0 && isAlive(getCell(row - 1, col))) 
        {
            numLiving++;
        }
        if (row - 1 >= 0 && col + 1 < getColumns() && isAlive(getCell(row - 1, col + 1))) 
        {
            numLiving++;
        }
        if (col - 1 >= 0 && isAlive(getCell(row, col - 1))) 
        {
            numLiving++;
        }
        if (col + 1 < getColumns() && isAlive(getCell(row, col + 1))) 
        {
            numLiving++;
        }
        if (row + 1 < getRows() && col - 1 >= 0 && isAlive(getCell(row + 1, col - 1))) 
        {
            numLiving++;
        }
        if (row + 1 < getRows() && isAlive(getCell(row + 1, col)))
        {
            numLiving++;
        }
        if (row + 1 < getRows() && col + 1 < getColumns() && isAlive(getCell(row + 1, col + 1))) 
        {
            numLiving++;
        }
        return numLiving;
    }
    
    //Compute new generations
    public void computeNextGeneration(int generation) 
    {
        if (generation > 0) 
        {
            computeNextGeneration(generation - 1);
            System.out.println("\nGeneration " + generation + "\n");
            print();

            char[][] nextGen = new char[getRows()][getColumns()];
            for (int r = 0; r < getRows(); r++) 
            {
                for (int c = 0; c < getColumns(); c++) 
                {
                    if (!isAlive(board[r][c]) && getNumAdjacentLivingCells(r, c) == 3) 
                    {
                        nextGen[r][c] = 'X';
                    } else if (isAlive(board[r][c]) && getNumAdjacentLivingCells(r, c) < 2) 
                    {
                        nextGen[r][c] = '0';
                    } else if (isAlive(board[r][c]) && getNumAdjacentLivingCells(r, c) > 3) 
                    {
                        nextGen[r][c] = '0';
                    } else if (isAlive(board[r][c]) && getNumAdjacentLivingCells(r, c) <= 3 && getNumAdjacentLivingCells(r, c) >= 2) 
                    {
                        nextGen[r][c] = 'X';
                    } else 
                    {
                        nextGen[r][c] = board[r][c];
                    }
                }
            }
            board = nextGen;
        }
    }
    
    //main method
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        Tran_GameOfLife gridCell = new Tran_GameOfLife();
        
        System.out.print("Enter how many generations to compute: ");
        gridCell.computeNextGeneration(scan.nextInt());
    }
}
