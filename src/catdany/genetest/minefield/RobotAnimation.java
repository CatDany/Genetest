package catdany.genetest.minefield;

public class RobotAnimation implements Runnable
{
	public int startX;
	public int startY;
	public int moveX;
	public int moveY;
	public long startTime;
	public long duration;
	public double action;
	
	private SimPanel sim;
	
	/**
	 * Initialize robot with all values set to <code>0</code>
	 * @param sim
	 */
	public RobotAnimation(SimPanel sim)
	{
		this.sim = sim;
		this.startX = 0;
		this.startY = 0;
		this.startTime = 0;
		this.duration = 0;
		this.moveX = 0;
		this.moveY = 0;
		this.action = 0;
	}
	
	private void updateRobot()
	{
		this.startX = getX();
		this.startY = getY();
		if (sim.actions[(int)action])
		{
			this.moveX = 0;
			this.moveY = sim.size;
			this.duration = 200;
			if (sim.actions.length - 1 == action) // if action is the last one and it's moving down then it's going to step onto a mine
				action += 1;
			else
				action += 0.5;
		}
		else
		{
			this.moveX = sim.size;
			this.moveY = 0;
			this.duration = 200;
			action += 1;
		}
		this.startTime = System.currentTimeMillis();
	}
	
	@Override
	public void run()
	{
		while (action < sim.actions.length)
		{
			updateRobot();
			try
			{
				Thread.sleep(duration);
			}
			catch (InterruptedException t)
			{
				t.printStackTrace();
			}
		}
		synchronized (this)
		{
			notify();
		}
	}
	
	/**
	 * Get X position for the robot at specified moment in time
	 * @return
	 * @see #getX()
	 */
	public int getX(long time)
	{
		return Math.round((int)(startX+Math.min((double)(time - startTime)/duration, 1)*moveX)) % (sim.field.columns()*sim.size);
	}
	
	/**
	 * Get Y position for the robot at specified moment in time
	 * @return
	 * @see #getY()
	 */
	public int getY(long time)
	{
		return Math.round((int)(startY+Math.min((double)(time - startTime)/duration, 1)*moveY));
	}
	
	/**
	 * Get X position for the robot at this moment
	 * @return
	 * @see #getX(long)
	 */
	public int getX()
	{
		return getX(System.currentTimeMillis());
	}
	/**
	 * Get Y position for the robot at this moment
	 * @return
	 * @see #getY(long)
	 */
	public int getY()
	{
		return getY(System.currentTimeMillis());
	}
}