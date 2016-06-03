package catdany.genetest;

import java.util.Scanner;

import catdany.genetest.calc.Calculator;
import catdany.genetest.calc.MultiSolutionCalc;

public class Main
{
	/**
	 * Byte array size for genes of one {@link Single}
	 */
	public static final int SINGLE_GENE_LENGTH = 470;
	/**
	 * Tournament size for tournament selection
	 * @see Evolution#tournamentSelection(Generation, Calculator, int) Tournament selection algorithm
	 */
	public static final int TOURNAMENT_SIZE = 5;
	/**
	 * Initial generation size
	 */
	public static final int INITIAL_GENERATION_SIZE = 50;
	/**
	 * Mutation rate for evolution
	 */
	public static final double MUTATION_RATE = 0.015;
	
	public static void main(String[] args)
	{
		MultiSolutionCalc calc = new MultiSolutionCalc(randomBits(), randomBits(), randomBits());
		Generation init = Generation.random(INITIAL_GENERATION_SIZE, SINGLE_GENE_LENGTH);
		int genCount = 0;
		while (calc.getFitness(init.getFittest(calc)) < 1)
		{
			genCount++;
			init = Evolution.evolve(init, calc, TOURNAMENT_SIZE, MUTATION_RATE);
			System.out.println(genCount + " gen > Max fitness reached: " + calc.getFitness(init.getFittest(calc)));
		}
		System.out.println(String.format("Done after %s generations.", genCount));
	}
	
	static void printSolution(boolean[] solution)
	{
		String solutionStr = "Solution chosen at random:\n";
		for (int i = 0; i < solution.length; i++)
		{
			solutionStr += solution[i] ? "1" : "0";
		}
		System.out.println(solutionStr);
	}
	
	static String prompt()
	{
		Scanner scanner = new Scanner(System.in);
		String read = scanner.nextLine();
		scanner.close();
		return read;
	}
	
	static boolean[] randomBits()
	{
		boolean[] bits = new boolean[SINGLE_GENE_LENGTH];
		for (int i = 0; i < bits.length; i++)
		{
			bits[i] = Math.random() < 0.5;
		}
		return bits;
	}
	
	@Deprecated
	static byte bitsToByte(boolean[] bits)
	{
		return (byte)((bits[0]?1<<7:0) + (bits[1]?1<<6:0) + (bits[2]?1<<5:0) + 
                (bits[3]?1<<4:0) + (bits[4]?1<<3:0) + (bits[5]?1<<2:0) + 
                (bits[6]?1<<1:0) + (bits[7]?1:0));
	}
}