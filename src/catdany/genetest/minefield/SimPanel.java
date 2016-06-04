package catdany.genetest.minefield;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SimPanel extends JPanel implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7520130717524154460L;
	
	public final Minefield field;
	public final int size;
	
	public final RobotAnimation robot;
	public boolean[] actions;
	public int number = 0;
	
	public SimPanel(Minefield field, boolean[] actions, int size)
	{
		this.field = field;
		this.actions = actions;
		this.size = size;
		this.robot = new RobotAnimation(this);
	}
	
	@Override
	public void run()
	{
		repaint();
	}
	
	public void refreshRobot()
	{
		robot.startX = size/2;
		robot.startY = size/2;
		robot.moveX = 0;
		robot.moveY = 0;
		robot.duration = 1;
		robot.action = 0;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		if (field != null)
		{
			g.setColor(Color.RED);
			// Draw mines
			for (int i = 0; i < field.rows(); i++)
			{
				for (int k = 0; k < field.columns(); k++)
				{
					if (field.matrix[i][k])
					{
						g.fillRect(size*k, size*(i*2+1), size, size);
					}
				}
			}
			g.setColor(Color.BLACK);
			// Draw vertical grid
			for (int i = 0; i <= field.columns(); i++)
			{
				g.fillRect(i*size, 0, 3, field.rows()*size*2);
			}
			// Draw horizontal grid
			for (int i = 0; i <= field.rows()*2; i++)
			{
				g.fillRect(0, i*size, field.columns()*size, 3);
			}
			// Draw robot
			g.setColor(Color.MAGENTA);
			g.fillRect(robot.getX() - 10, robot.getY() - 10, 20, 20);
			g.setFont(new Font("Arial", Font.PLAIN, 14));
			g.drawString("" + number, 5, 15);
		}
	}
}
