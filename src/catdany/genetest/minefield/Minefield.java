package catdany.genetest.minefield;

import java.util.Arrays;

import catdany.genetest.Single;
import catdany.genetest.calc.Calculator;

public class Minefield implements Calculator
{
	/**
	 * [row][column] 2D matrix for this minefield
	 */
	public final boolean[][] matrix;
	
	/**
	 * Create an empty minefield with specified size
	 * @param rows
	 * @param columns
	 */
	public Minefield(int rows, int columns)
	{
		this.matrix = new boolean[rows][columns];
	}
	
	/**
	 * Create a matrix with specified matrix
	 * @param matrix
	 */
	public Minefield(boolean[][] matrix)
	{
		this.matrix = matrix;
	}
	
	/**
	 * Create a matrix with a matrix specified by an array of binary strings
	 * @param binaryStrings
	 */
	public Minefield(String[] binaryStrings)
	{
		this(binaryStrings.length, binaryStrings[0].length());
		for (int i = 0; i < rows(); i++)
		{
			for (int k = 0; k < columns(); k++)
			{
				this.matrix[i][k] = binaryStrings[i].charAt(k) == '1' ? true : false;
			}
		}
	}
	
	public int rows()
	{
		return matrix.length;
	}
	
	public int columns()
	{
		return matrix[0].length;
	}
	
	/**
	 * <code>true</code> means move to the next row
	 * <code>false</code> means move to the next column
	 */
	@Override
	public double getFitness(Single single)
	{
		int x = 0;
		int y = 0;
		for (int i = 0; i < single.getSize(); i++)
		{
			if (single.getGene(i))
			{
				if (matrix[y][x])
				{
					break;
				}
				else
				{
					y++;
					if (y == rows())
					{
						break;
					}
				}
			}
			else
			{
				x++;
				if (x == columns())
					x = 0;
			}
		}
		return (double)y/rows();
	}
	
	public void simulate(Single single)
	{
		int x = 0;
		int y = 0;
		int count = 0;
		for (int i = 0; i < single.getSize(); i++)
		{
			count++;
			if (single.getGene(i))
			{
				if (matrix[y][x])
				{
					break;
				}
				else
				{
					y++;
					if (y == rows())
					{
						break;
					}
				}
			}
			else
			{
				x++;
				if (x == columns())
					x = 0;
			}
		}
		MineTest.sim.actions = Arrays.copyOfRange(single.getGenes(), 0, count);
		MineTest.sim.refreshRobot();
		MineTest.startRobot();
		try
		{
			synchronized (MineTest.sim.robot)
			{
				MineTest.sim.robot.wait();
			}
		}
		catch (InterruptedException t)
		{
			t.printStackTrace();
		}
	}
	
	/**
	 * Cut gene array of specified individual, so only relevant actions are left and return them as bit array
	 * @param single
	 * @return
	 * @see #getFitness(Single)
	 */
	@Deprecated
	public boolean[] getRelevantActions(Single single)
	{
		int x = 0;
		int y = 0;
		int count = 0;
		for (int i = 0; i < single.getSize(); i++)
		{
			count++;
			if (single.getGene(i))
			{
				if (matrix[y][x])
				{
					break;
				}
				else
				{
					y++;
					if (y == rows())
					{
						break;
					}
				}
			}
			else
			{
				x++;
				if (x == columns())
					x = 0;
			}
		}
		boolean[] relevant = new boolean[count];
		for (int i = 0; i < relevant.length; i++)
		{
			relevant[i] = single.getGene(i);
		}
		return relevant;
	}
	
	@Override
	public String toString()
	{
		String s = "Minefield\n";
		for (int i = 0; i < rows(); i++)
		{
			for (int k = 0; k < columns(); k++)
			{
				s += matrix[i][k] ? "1" : "0";
			}
			s += "\n";
		}
		return s;
	}
}