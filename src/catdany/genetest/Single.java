package catdany.genetest;

import java.util.Arrays;

public class Single implements Cloneable
{
	private boolean[] genes;
	
	/**
	 * Create a single with specified gene size
	 * @param geneSize
	 */
	public Single(int geneSize)
	{
		this.genes = new boolean[geneSize];
	}
	
	private Single(boolean[] genes)
	{
		this.genes = genes;
	}
	
	@Override
	protected Single clone() throws CloneNotSupportedException
	{
		return new Single(Arrays.copyOf(genes, genes.length));
	}
	
	/**
	 * Generate a single with random genes
	 * @param geneSize
	 * @return
	 */
	public static Single random(int geneSize)
	{
		Single single = new Single(geneSize);
		for (int i = 0; i < single.getSize(); i++)
		{
			single.setGene(i, Math.random() < 0.5 ? false : true);
		}
		return single;
	}
	
	/**
	 * Get size of gene array
	 * @return
	 */
	public int getSize()
	{
		return genes.length;
	}
	
	/**
	 * Get gene for specified index
	 * @param index
	 * @return
	 * @see #getSize()
	 */
	public boolean getGene(int index)
	{
		return genes[index];
	}
	
	/**
	 * Set gene for specified index
	 * @param index
	 * @param gene
	 */
	public void setGene(int index, boolean gene)
	{
		this.genes[index] = gene;
	}
	
	@Override
	public String toString()
	{
		String s = "Single:";
		for (int i = 0; i < getSize(); i++)
		{
			s += getGene(i) ? "1" : "0";
		}
		return s;
	}
}