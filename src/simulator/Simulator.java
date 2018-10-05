package simulator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Simulator extends JFrame{
	// Panels
	public ControlPanel cp	= new ControlPanel(this);
	public ProgramPanel pp  = new ProgramPanel(this);
	public CPUpanel		cpu = new CPUpanel(this);
	// extra classes
	public CodeReader cr;
	public Processes p;
	//public Display			D = new Display(this);
	private Timer		timer = new Timer(100, new TimerHandler());	// checks the expired time
	public String		stepFlag, runFlag, resetFlag, interruptFlag, continueFlag;
	// Code List
	public ArrayList<CodeLine> code = new ArrayList<CodeLine>();
	public int lineCount = 0;

	public Simulator(){
		super("Simulator");		// transfer all properties of the j frame
		setLayout(new BorderLayout());
		add(pp , BorderLayout.SOUTH);	// program ppanel
		add(cp , BorderLayout.WEST);	//control panel
		add(cpu, BorderLayout.CENTER);	// cpu panel
		setSize(700,450);
		setVisible(true);
		timer.start();
		stepFlag = "ON";
		runFlag = "OFF";
		resetFlag = "OFF";
		
		this.cr = new CodeReader(this);
		this.p = new Processes(this);
		
		String a = "7E";
		String b = "7F";
		int c = Integer.valueOf(a, 16);
		int d = Integer.valueOf(b, 16);
		System.out.println("c:"+c);
		System.out.println("d:"+d);
		System.out.println("AND:"+ (c&d));
		System.out.println("OR :"+ (c|d));
		System.out.println("XOR:"+ (c^d));
		
	}
	
	class TimerHandler implements ActionListener{
		
		public void actionPerformed (ActionEvent ae){
			repaint();	// if creates error comment out the code
		}
	}
	
	public static void main(String[] args) {
		Simulator app = new Simulator();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
