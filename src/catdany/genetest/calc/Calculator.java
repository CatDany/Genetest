package catdany.genetest.calc;

import catdany.genetest.Single;

public interface Calculator
{
	/**
	 * Get fitness for one individual
	 * @param single
	 * @return
	 */
	public double getFitness(Single single);
}