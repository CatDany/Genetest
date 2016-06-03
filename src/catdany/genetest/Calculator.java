package catdany.genetest;

public interface Calculator
{
	/**
	 * Get fitness for one individual
	 * @param single
	 * @return
	 */
	public int getFitness(Single single);

	/**
	 * Get maximal possible fitness for this calculator
	 * @return
	 */
	public int getMaxFitness();
}