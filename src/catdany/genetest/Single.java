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
		return new Single(getGenes());
	}
	
	/**
	 * Generate a single with random genes
	 * @param geneSize
	 * @param geneValues Possible values per one gene element. <code>2</code> would make it binary.
	 * @return
	 */
	public static Single random(int geneSize)
	{
		Single single = new Single(geneSize);
		for (int i = 0; i < single.getSize(); i++)
		{
			single.setGene(i, Math.random() < 0.5);
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
	
	/**
	 * Get genes as a bit array
	 * @return
	 */
	public boolean[] getGenes()
	{
		return Arrays.copyOf(genes, genes.length);
	}
}