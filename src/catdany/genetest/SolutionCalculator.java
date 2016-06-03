package catdany.genetest;

public class SolutionCalculator implements Calculator
{
	private final boolean[] solution;
	
	/**
	 * Create a calculator for given solution
	 * @param solution
	 */
	public SolutionCalculator(boolean[] solution)
	{
		this.solution = solution;
	}
	
	/**
	 * Get fitness for one individual
	 * @param single
	 * @return
	 */
	@Override
	public int getFitness(Single single)
	{
		int fitness = 0;
		for (int i = 0; i < single.getSize(); i++)
		{
			if (single.getGene(i) == solution[i])
			{
				fitness++;
			}
		}
		return fitness;
	}
	
	/**
	 * Get maximal possible fitness.<br>
	 * Equal to size of the solution byte array.
	 * @return
	 */
	@Override
	public int getMaxFitness()
	{
		return solution.length;
	}
}
