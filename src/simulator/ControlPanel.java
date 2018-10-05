package simulator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ControlPanel extends JPanel {

	private JLabel				L;
	private JButton				step, run, reset, interrupt, quit;
	private ButtonHandler		bh;
	private Simulator			s;
	private Timer time = new Timer(500, null);
	
	public ControlPanel(Simulator s){
		super();
		this.s = s;
		setLayout (new GridLayout(8, 1, 5, 10));
		setBackground(new Color(80, 108, 114)); 	// new Color(50, 100, 150)
		
		add(new JLabel());
		add(new JLabel());
		// step button
		step = new JButton("Step");
		step.setFont(new Font("Serif", Font.PLAIN, 20));
		step.setForeground(Color.RED);
		add(step);
		// run button
		run = new JButton("Run");
		run.setFont(new Font("Serif", Font.PLAIN, 20));
		run.setForeground(Color.RED);
		add(run);
		// reset button
		reset = new JButton("Reset");
		reset.setFont(new Font("Serif", Font.PLAIN, 20));
		reset.setForeground(Color.RED);
		add(reset);
		// interrupt button
		//interrupt = new JButton("Interrupt");
		//interrupt.setFont(new Font("Serif", Font.PLAIN, 20));
		//interrupt.setForeground(Color.RED);
		//add(interrupt);
		// quit button
		quit = new JButton("Quit");
		quit.setFont(new Font("Serif", Font.PLAIN, 20));
		quit.setForeground(Color.RED);
		add(quit);
		
		add(new JLabel());
		
		add(new JLabel());
		
		bh = new ButtonHandler();
		step.addActionListener(bh);
		run.addActionListener(bh);
		reset.addActionListener(bh);
		//interrupt.addActionListener(bh);
		quit.addActionListener(bh);
	}
	
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ae){
			if (ae.getSource() == quit)
				System.exit(0);
			if (ae.getSource() == step){		// STEP button
				s.stepFlag = "ON";
				s.runFlag = "OFF";
				s.p.stepOrRun();
			}
			if (ae.getSource() == run){		// RUN button
				s.runFlag = "ON";
				s.stepFlag = "OFF";
				
				ActionListener listener = new ActionListener() {	// creates new listener for a timer that calculates every .5 seconds
				    @Override 
				    public void actionPerformed(ActionEvent e) {
				        if (s.runFlag == "ON") {
				        	s.p.stepOrRun();
				        }
				        else if (s.stepFlag == "ON"){
				        	s.runFlag = "OFF";
				            time.stop();
				        }
				    }
				};
				time.addActionListener(listener);
				time.start();
			}
			if (ae.getSource() == reset){		// RESET button
				s.resetFlag = "ON";
				time.stop();
				s.p.reset();
			}
			/*if (ae.getSource() == interrupt){
				S.interruptFlag = "ON";
			}*/
		}
	}
}
