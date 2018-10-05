package simulator;

import java.awt.*;
import javax.swing.*;

public class CPUpanel extends JPanel{

	//public Display A, F, AR, PC, nextPC, P1, P2, P3, SP;
	//private JLabel L;
	public JLabel a, f, pc, p1, p2, p3, ar, sp;
	public JLabel M[];
	public JLabel S[];
	Simulator s;

	public CPUpanel(Simulator s){
		super();
		this.s = s;
		setLayout(new GridLayout(8,8));
		setBackground(new Color(100, 128, 134));
		
		M = new JLabel[16];
		for (int i=0; i < 16; i++){
			JLabel l = new JLabel();
			M[i] = createLabel(l, ("M"+i+" : "), "FF");
			M[i].setBackground(new Color(119, 136, 153));
		}
		
		S = new JLabel[16];
		for (int i=0; i < 16; i++){
			JLabel l = new JLabel();
			if ((i%2) == 0)
				S[i] = createLabel(l, ("S"+Integer.toHexString(i).toUpperCase()+" Low : "), "FF");
			else
				S[i] = createLabel(l, ("S"+Integer.toHexString(i).toUpperCase()+" High : "), "FF");
			S[i].setBackground(Color.BLACK);//new Color(139, 156, 173)
		}
		//==================   ROW 0  =======================================================
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(M[7]);
		add(M[8]);
		add(S[1]);
		add(S[0]);
		//==================   ROW 1  =======================================================
		this.a = new JLabel();
	    add(createLabel(a, "A :", ""));
		add(new JLabel());
		this.f = new JLabel();
		add(createLabel(f, "  Z , GE , L ", "0 , 0 , 0"));
		add(new JLabel());
		add(M[6]);
		add(M[9]);
		add(S[3]);
		add(S[2]);
		//==================   ROW 2  =======================================================
	    add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(M[5]);
		add(M[10]);
		add(S[5]);
		add(S[4]);
		//==================   ROW 3  =======================================================
		this.pc = new JLabel();
		add(createLabel(pc, "PC : ", ""));
	    this.p1 = new JLabel("");
	    add(createLabel(p1, "P1 :", ""));
	    this.p2 = new JLabel();
	    add(createLabel(p2, "P2 :", ""));
	    this.p3 = new JLabel();
	    add(createLabel(p3, "P3 :", ""));
		add(M[4]);
		add(M[11]);
		add(S[7]);
		add(S[6]);
		//==================   ROW 4  =======================================================
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(M[3]);
		add(M[12]);
		add(S[9]);
		add(S[8]);
		//==================   ROW 5  =======================================================
		add(new JLabel());
		this.ar = new JLabel();
		add(createLabel(ar, "AR :", ""));
		add(new JLabel());
		add(new JLabel());
		add(M[2]);
		add(M[13]);
		add(S[11]);
		add(S[10]);
		//==================   ROW 6  =======================================================
		add(new JLabel());
		add(new JLabel());
		this.sp = new JLabel();
		add(createLabel(sp, "SP :", "F"));
		add(new JLabel());
		add(M[1]);
		add(M[14]);
		add(S[13]);
		add(S[12]);
		//==================   ROW 7  =======================================================
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(M[0]);
		add(M[15]);
		add(S[15]);
		add(S[14]);
		//==================  END OF ROWS  ==================================================
	}
	
	public JLabel createLabel(JLabel L, String title, String text){
		L.setText(text);
		L.setHorizontalAlignment(JLabel.CENTER);
		L.setOpaque(true);
	    //L.setBackground(Color.WHITE);
		L.setBackground(new Color(100, 128, 134));
	    L.setForeground(Color.BLACK);
	    L.setBorder(BorderFactory.createTitledBorder(title));
		return L;
	}
}
