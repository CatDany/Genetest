package catdany.genetest;

import catdany.genetest.calc.Calculator;

public class Generation
{
	private final Single[] singles;
	
	/**
	 * Create an empty generation for given size
	 * @param size
	 */
	public Generation(int size)
	{
		this.singles = new Single[size];
	}
	
	/**
	 * Create a generation for given size and fill it with random individuals
	 * @param genSize Size of this generation
	 * @param singleSize Gene size 
	 * @return
	 */
	public static Generation random(int genSize, int singleSize)
	{
		Generation gen = new Generation(genSize);
		for (int i = 0; i < gen.getSize(); i++)
		{
			gen.setSingle(i, Single.random(singleSize));
		}
		return gen;
	}
	
	/**
	 * Get individual for specified index
	 * @param index
	 * @return
	 */
	public Single getSingle(int index)
	{
		return singles[index];
	}
	
	/**
	 * Set individual for specified index
	 * @param index
	 * @param single
	 */
	public void setSingle(int index, Single single)
	{
		this.singles[index] = single;
	}
	
	/**
	 * Get size of this generation
	 * @return
	 */
	public int getSize()
	{
		return singles.length;
	}
	
	/**
	 * Get the fittest individual of this generation
	 * @param calc
	 * @return
	 */
	public Single getFittest(Calculator calc)
	{
		Single fittest = singles[0];
		for (int i = 0; i < getSize(); i++)
		{
			Single iterated = getSingle(i);
			if (calc.getFitness(iterated) > calc.getFitness(fittest))
			{
				fittest = iterated;
			}
		}
		return fittest;
	}
}