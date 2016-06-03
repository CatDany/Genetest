package catdany.genetest.calc;

import catdany.genetest.Single;

public class MultiSolutionCalc implements Calculator
{
	public final boolean[][] solutions;
	private final int maxFitness;
	
	public MultiSolutionCalc(boolean[]... solutions)
	{
		this.solutions = solutions;
		this.maxFitness = solutions[0].length;
	}
	
	@Override
	public double getFitness(Single single)
	{
		int overallFitness = 0;
		for (int i = 0; i < solutions.length; i++)
		{
			int fitness = 0;
			for (int k = 0; k < single.getSize(); k++)
			{
				if (single.getGene(k) == solutions[i][k])
				{
					fitness++;
				}
			}
			if (fitness > overallFitness)
			{
				overallFitness = fitness;
			}
		}
		return (double)overallFitness/maxFitness;
	}
}
