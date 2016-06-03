package catdany.genetest.calc;

import catdany.genetest.Single;

public class OneSolutionCalc implements Calculator
{
	private final boolean[] solution;
	
	/**
	 * Create a calculator for given solution
	 * @param solution
	 */
	public OneSolutionCalc(boolean[] solution)
	{
		this.solution = solution;
	}
	
	/**
	 * Get fitness for one individual
	 * @param single
	 * @return
	 */
	@Override
	public double getFitness(Single single)
	{
		int fitness = 0;
		for (int i = 0; i < single.getSize(); i++)
		{
			if (single.getGene(i) == solution[i])
			{
				fitness++;
			}
		}
		return fitness/solution.length;
	}
}
