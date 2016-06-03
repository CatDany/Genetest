package catdany.genetest;

public class Evolution
{
	/**
	 * Evolve this generation into a new one<br>
	 * For each child in the new generation, parents will be chosen using {@link #tournamentSelection(Generation, Calculator, int) tournament selection}<br>
	 * After that, a mutation will occur with given mutation rate
	 * @param oldGen Old generation
	 * @param calc Fitness calculator
	 * @param tournamentSize Tournament size for tournament selection
	 * @param mutationRate Mutation rate (in range between <code>0</code> and <code>0.(9)</code>)
	 * @return
	 */
	public static Generation evolve(Generation oldGen, Calculator calc, int tournamentSize, double mutationRate)
	{
		int genSize = oldGen.getSize();
		Generation newGen = new Generation(genSize);
		newGen.setSingle(0, oldGen.getFittest(calc));
		for (int i = 1; i < genSize; i++)
		{
			Single s1 = tournamentSelection(oldGen, calc, tournamentSize);
			Single s2 = tournamentSelection(oldGen, calc, tournamentSize);
			Single child = breed(s1, s2);
			newGen.setSingle(i, child);
		}
		for (int i = 1; i < genSize; i++)
		{
			mutate(newGen.getSingle(i), mutationRate);
		}
		return newGen;
	}
	
	/**
	 * Breed two individuals using crossover algorithm<br>
	 * Both individuals must have the same gene size
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static Single breed(Single s1, Single s2)
	{
		Single child = new Single(s1.getSize());
		for (int i = 0; i < child.getSize(); i++)
		{
			if (Math.random() < 0.5)
				child.setGene(i, s1.getGene(i));
			else
				child.setGene(i, s2.getGene(i));
		}
		return child;
	}
	
	/**
	 * Perform tournament selection with specified tournament size
	 * @param gen
	 * @param calc
	 * @param tournamentSize
	 * @return
	 */
	public static Single tournamentSelection(Generation gen, Calculator calc, int tournamentSize)
	{
		Generation tournament = new Generation(tournamentSize);
		for (int i = 0; i < tournamentSize; i++)
		{
			int random = (int)(Math.random() * gen.getSize());
			tournament.setSingle(i, gen.getSingle(random));
		}
		return tournament.getFittest(calc);
	}
	
	/**
	 * Mutate this individual with specified rate
	 * @param single
	 * @param mutationRate
	 */
	public static void mutate(Single single, double mutationRate)
	{
		for (int i = 0; i < single.getSize(); i++)
		{
			if (Math.random() < mutationRate)
			{
				single.setGene(i, !single.getGene(i));
			}
		}
	}
}