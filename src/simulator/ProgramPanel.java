package simulator;

import java.awt.*;
import javax.swing.*;

public class ProgramPanel extends JPanel{

	private Simulator s;
	public JLabel instructionLabel;
	
	public ProgramPanel(Simulator s){
		super();
		this.s = s;
		setLayout(new GridLayout(1,3));
		setBackground(new Color(80, 108, 114)); //new Color(50, 100, 150)

		add(new JLabel());
		instructionLabel = new JLabel(" ");
		instructionLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionLabel.setOpaque(true);
		instructionLabel.setBackground(Color.WHITE);
		instructionLabel.setForeground(Color.BLACK);
		instructionLabel.setBorder(BorderFactory.createTitledBorder("Instruction :"));
		instructionLabel.setSize(200, 10);
		add(instructionLabel);
		add(new JLabel());

	}
}
