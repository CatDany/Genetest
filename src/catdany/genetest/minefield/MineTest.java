package catdany.genetest.minefield;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import catdany.genetest.Evolution;
import catdany.genetest.Generation;
import catdany.genetest.Single;

public class MineTest
{
	public static SimPanel sim;
	public static ScheduledExecutorService execRender = Executors.newSingleThreadScheduledExecutor();
	public static ScheduledFuture<?> futureRender;
	public static ExecutorService execRobot = Executors.newSingleThreadExecutor();
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Generation size:");
		int genSize = Integer.parseInt(scan.nextLine());
		System.out.println("Tournament size:");
		int tourSize = Integer.parseInt(scan.nextLine());
		System.out.println("Mutation rate:");
		double mutationRate = Double.parseDouble(scan.nextLine());
		System.out.println("Minefield:");
		ArrayList<String> minefield = new ArrayList<String>();
		while (true)
		{
			String read = scan.nextLine();
			if (!read.isEmpty())
			{
				minefield.add(read);
			}
			else
			{
				break;
			}
		}
		scan.close();
		
		Minefield calc = new Minefield(minefield.toArray(new String[0]));
		
		JFrame frame = new JFrame();
		sim = new SimPanel(calc, null, 40);
		frame.setTitle("Simulator");
		frame.setJMenuBar(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setSize(40*calc.columns()+19, 40*calc.rows()*2+41);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(sim);
		frame.setVisible(true);
		
		synchronized (sim)
		{
			try
			{
				sim.wait();
			}
			catch (InterruptedException t)
			{
				t.printStackTrace();
			}
		}
		
		futureRender = execRender.scheduleAtFixedRate(sim, 1000L/30, 1000L/30, TimeUnit.MILLISECONDS);
		
		Generation gen = Generation.random(genSize, calc.rows()*calc.columns());
		int genCount = 0;
		double ff = 0;
		while (ff < 1)
		{
			ff = calc.getFitness(gen.getFittest(calc));
			for (int i = 0; i < gen.getSize(); i++)
			{
				sim.number = genCount + 1;
				System.out.println("Simulating " + sim.number + ":" + gen.getSingle(i));
				calc.simulate(gen.getSingle(i));
			}
			genCount++;
			gen = Evolution.evolve(gen, calc, tourSize, mutationRate);
			System.out.println("Evolving generation " + genCount + " > Fitness: " + ff);
		}
		Single winner = gen.getFittest(calc);
		System.out.println("Winner:" + winner);
		JOptionPane.showMessageDialog(frame, "We've got a winner!", "Winner!", JOptionPane.INFORMATION_MESSAGE, null);
		calc.simulate(winner);
	}
	
	public static void startRobot()
	{
		execRobot.execute(sim.robot);
	}
}