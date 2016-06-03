package catdany.genetest;

import java.util.Scanner;

public class Main
{
	/**
	 * Byte array size for genes of one {@link Single}
	 */
	public static final int SINGLE_GENE_LENGTH = 1024;
	/**
	 * Tournament size for tournament selection
	 * @see Evolution#tournamentSelection(Generation, Calculator, int) Tournament selection algorithm
	 */
	public static final int TOURNAMENT_SIZE = 5;
	/**
	 * Initial generation size
	 */
	public static final int INITIAL_GENERATION_SIZE = 100;
	/**
	 * Mutation rate for evolution
	 */
	public static final double MUTATION_RATE = 0.015;
	
	public static void main(String[] args)
	{
		boolean[] solution = new boolean[SINGLE_GENE_LENGTH];
		for (int i = 0; i < solution.length; i++)
		{
			solution[i] = Math.random() < 0.5;
		}
		SolutionCalculator calc = new SolutionCalculator(solution);
		Generation init = Generation.random(INITIAL_GENERATION_SIZE, SINGLE_GENE_LENGTH);
		int genCount = 0;
		while (calc.getFitness(init.getFittest(calc)) < calc.getMaxFitness())
		{
			genCount++;
			init = Evolution.evolve(init, calc, TOURNAMENT_SIZE, MUTATION_RATE);
			System.out.println("Max fitness reached: " + calc.getFitness(init.getFittest(calc)));
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
}