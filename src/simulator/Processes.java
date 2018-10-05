package simulator;

import java.awt.Color;
import javax.swing.*;

public class Processes {
	
	public Simulator s;
	private int result;
	private String nextAdres;
	
	public Processes (Simulator s){
		this.s = s;
	}
	
	public void stepOrRun(){
		
		s.pp.instructionLabel.setText(s.code.get(s.lineCount).asm);
		s.pp.instructionLabel.setBorder(BorderFactory.createTitledBorder("Instruction :   " + s.lineCount));
		// set PC
		s.cpu.pc.setText(s.code.get(s.lineCount).tok1);
		// set P1
		s.cpu.p1.setText(s.code.get(s.lineCount).tok2);
		// set P2
		s.cpu.p2.setText(s.code.get(s.lineCount).tok3);
		// set P3
		s.cpu.p3.setText(s.code.get(s.lineCount).tok4);
		calculations();
		s.lineCount++;
		if (s.lineCount % s.code.size() == 0)
			s.lineCount = 0;
	}
	
	private void calculations(){
		//-------------  ARTIHMETIC OP -----------------------------------
		if (s.cpu.p1.getText().equals("10")){	// ADD M   A = A + M(AR)
			result = ((int)Long.parseLong(ifNegative(s.cpu.a.getText()), 16) + (int) Long.parseLong(ifNegative(s.cpu.M[Integer.valueOf(s.cpu.ar.getText(), 16)].getText()), 16));	// ifNegative
			flags();
			s.cpu.a.setText(twoHex(Integer.toHexString(result)).toUpperCase());
		}
		else if (s.cpu.p1.getText().equals("12")){	// SUB M   A = A-M
			result = ((int)Long.parseLong(ifNegative(s.cpu.a.getText()), 16) - (int) Long.parseLong(ifNegative(s.cpu.M[Integer.valueOf(s.cpu.ar.getText(), 16)].getText()), 16));	// ifNegative
			flags();
			s.cpu.a.setText(twoHex(Integer.toHexString(result)).toUpperCase());
		}
		else if (s.cpu.p1.getText().equals("11")){	// ADI d or A = A+d
			result = (int) Long.parseLong(ifNegative(s.cpu.a.getText()), 16) + (int)Long.parseLong(ifNegative(s.cpu.p2.getText()), 16);		// ifNegative
			flags();
			s.cpu.a.setText(twoHex(Integer.toHexString(result)).toUpperCase());
		}
		else if (s.cpu.p1.getText().equals("13")){	// SUI d  A = A-d
			result = (int) Long.parseLong(ifNegative(s.cpu.a.getText()), 16) - (int)Long.parseLong(ifNegative(s.cpu.p2.getText()), 16);		// ifNegative
			flags();
			s.cpu.a.setText(twoHex(Integer.toHexString(result)).toUpperCase());
		}
		//------  LOGICAL OPER   ----------------------------------------------------
		else if (s.cpu.p1.getText().equals("20")){		// AND M
			result = ((int)Long.parseLong(ifNegative(s.cpu.a.getText()), 16)) & ((int)Long.parseLong(ifNegative(s.cpu.M[Integer.valueOf(s.cpu.ar.getText())].getText())));
			s.cpu.a.setText(twoHex(Integer.toHexString(result)));
		}
		else if (s.cpu.p1.getText().equals("21")){		// ANI d
			result = ((int)Long.parseLong(ifNegative(s.cpu.a.getText()), 16)) & ((int)Long.parseLong(ifNegative(s.cpu.p2.getText())));
			s.cpu.a.setText(twoHex(Integer.toHexString(result)));
		}
		else if (s.cpu.p1.getText().equals("22")){		// OR M
			result = ((int)Long.parseLong(ifNegative(s.cpu.a.getText()), 16)) | ((int)Long.parseLong(ifNegative(s.cpu.M[Integer.valueOf(s.cpu.ar.getText())].getText())));
			s.cpu.a.setText(twoHex(Integer.toHexString(result)));
		}
		else if (s.cpu.p1.getText().equals("23")){		// ORI d
			result = ((int)Long.parseLong(ifNegative(s.cpu.a.getText()), 16)) | ((int)Long.parseLong(ifNegative(s.cpu.p2.getText())));
			s.cpu.a.setText(twoHex(Integer.toHexString(result)));
		}
		else if (s.cpu.p1.getText().equals("24")){		// XOR M
			result = ((int)Long.parseLong(ifNegative(s.cpu.a.getText()), 16)) ^ ((int)Long.parseLong(ifNegative(s.cpu.M[Integer.valueOf(s.cpu.ar.getText())].getText())));
			s.cpu.a.setText(twoHex(Integer.toHexString(result)));
		}
		else if (s.cpu.p1.getText().equals("25")){		// XRI d
			result = ((int)Long.parseLong(ifNegative(s.cpu.a.getText()), 16)) ^ ((int)Long.parseLong(ifNegative(s.cpu.p2.getText())));
			s.cpu.a.setText(twoHex(Integer.toHexString(result)));
		}
		else if (s.cpu.p1.getText().equals("26")){		// CMA
			
		}
		//------  DATA TRANSFERS ----------------------------------------------------
		else if (s.cpu.p1.getText().equals("31")){	// MOV M, A
			s.cpu.M[Integer.valueOf(s.cpu.ar.getText(), 16)].setText(twoHex(s.cpu.a.getText()));		// twoHex
		}
		else if	(s.cpu.p1.getText().equals("30")){	// MOV A, M
			s.cpu.a.setText(s.cpu.M[Integer.valueOf(s.cpu.ar.getText())].getText());					// possible ifNegative
		}
		else if (s.cpu.p1.getText().equals("32")){	// MVI A, d
			s.cpu.a.setText(s.cpu.p2.getText());
		}
		else if (s.cpu.p1.getText().equals("33")){	// MVI M, d
			s.cpu.M[Integer.valueOf(s.cpu.ar.getText())].setText(twoHex(s.cpu.a.getText()));			// twoHex
		}
		else if (s.cpu.p1.getText().equals("34"))	// LXI AR = P2 + P3
			s.cpu.ar.setText(s.cpu.p2.getText() + s.cpu.p3.getText());
		//-----   BRANCHING INSTR  ----------------------------------------------------
		else if (s.cpu.p1.getText().equals("40")){	// JMP aa
			//findAdres(s.cpu.p2.getText()+s.cpu.p3.getText());
			System.out.println("JMP going to:"+nextAdres);
		}
		else if (s.cpu.p1.getText().equals("41") && s.cpu.f.getText().charAt(0) == '1'){		// JZ aa
			//findAdres(s.cpu.p2.getText()+s.cpu.p3.getText());
			System.out.println("JMP going to:"+nextAdres);
		}
		else if (s.cpu.p1.getText().equals("44") && s.cpu.f.getText().charAt(0) == '0'){		// JNZ aa
			//findAdres(s.cpu.p2.getText()+s.cpu.p3.getText());
			System.out.println("JMP going to:"+nextAdres);
		}
		else if (s.cpu.p1.getText().equals("42") && s.cpu.f.getText().charAt(4) == '1'){		// JGE aa
			//findAdres(s.cpu.p2.getText()+s.cpu.p3.getText());
			System.out.println("JMP going to:"+nextAdres);
		}
		else if (s.cpu.p1.getText().equals("43") && s.cpu.f.getText().charAt(8) == '1'){		// JL aa
			//findAdres(s.cpu.p2.getText()+s.cpu.p3.getText());
			System.out.println("JMP going to:"+nextAdres);
		}
		else if (s.cpu.p1.getText().equals("46")){		// CALL aa
			findAdres(s.cpu.p2.getText() + s.cpu.p3.getText());
			nextAdres = Integer.toHexString(Integer.valueOf(s.cpu.pc.getText(), 16) +2);		// == the offset in the type-2 assembler
			s.cpu.S[Integer.valueOf(s.cpu.sp.getText(), 16)].setText(nextAdres.substring(0, 2).toUpperCase());		// adres HIGH
			decrementSP();
			s.cpu.S[Integer.valueOf(s.cpu.sp.getText(), 16)].setText(nextAdres.substring(2).toUpperCase());			// adres LOW
			decrementSP();
		}
		else if (s.cpu.p1.getText().equals("47")){		//RET
			incrementSP();
			String adres = s.cpu.S[Integer.valueOf(s.cpu.sp.getText() ,16)].getText();
			incrementSP();
			adres = s.cpu.S[Integer.valueOf(s.cpu.sp.getText() ,16)].getText() + adres;
			findAdres(adres);
		}
		//----- MISC OPER -----------------------------------------------------------------
		else if (s.cpu.p1.getText().equals("50")){		// NOP
			System.out.println("no code yet");
		}
		else if (s.cpu.p1.getText().equals("51")){		// HLT
			s.lineCount = 0;
		}
		else if (s.cpu.p1.getText().equals("52")){		// IN n
			System.out.println("no code yet");
		}
		else if (s.cpu.p1.getText().equals("53")){		// OUT n
			System.out.println("no code yet");
		}
		else if (s.cpu.p1.getText().equals("54")){		// PUSH A
			int i = Integer.valueOf(s.cpu.sp.getText(), 16);
			s.cpu.S[i].setText(s.cpu.a.getText());
			decrementSP();
			i = Integer.valueOf(s.cpu.sp.getText(), 16);
			s.cpu.S[i].setText(s.cpu.f.getText());
			decrementSP();
		}
		else if (s.cpu.p1.getText().equals("55")){		// PUSH PC
			int i = Integer.valueOf(s.cpu.sp.getText(), 16);
			s.cpu.S[i].setText(s.cpu.pc.getText().substring(0,2));
			decrementSP();
			i = Integer.valueOf(s.cpu.sp.getText(), 16);
			s.cpu.S[i].setText(s.cpu.pc.getText().substring(2));
			decrementSP();
		}
		else if (s.cpu.p1.getText().equals("56")){		// POP A
			incrementSP();
			String m = s.cpu.S[Integer.valueOf(s.cpu.sp.getText(),16)].getText();
			s.cpu.f.setText(m);
			incrementSP();
			m = s.cpu.S[Integer.valueOf(s.cpu.sp.getText(),16)].getText();
			s.cpu.a.setText(m);
		}
		else if (s.cpu.p1.getText().equals("57")){		// POP PC
			incrementSP();
			String l = s.cpu.S[Integer.valueOf(s.cpu.sp.getText(), 16)].getText();
			incrementSP();
			String h = s.cpu.S[Integer.valueOf(s.cpu.sp.getText(), 16)].getText();
			s.cpu.ar.setText(h+l);
		}
	}
	
	private void findAdres(String adres){
		//System.out.println("adres:"+adres);
		for (int i=0; i < s.code.size(); i++){
			if(s.code.get(i).tok1.equals(adres)){
				System.out.println("i == "+i);
				s.lineCount = --i;		// -- is the offset. it increments to proper index after calculation() in the stepOrRun()
				break;
			}
		}
	}
	
	private void decrementSP(){
		int sp = Integer.valueOf(s.cpu.sp.getText(), 16);
		s.cpu.S[sp].setBackground(new Color(139, 156, 173));
		s.cpu.sp.setText(Integer.toHexString(sp-1).toUpperCase());
	}
	
	private void incrementSP(){
		int sp = Integer.valueOf(s.cpu.sp.getText(), 16)+1;
		s.cpu.S[sp].setBackground(Color.BLACK);
		s.cpu.sp.setText(Integer.toHexString(sp).toUpperCase());
	}
	
	private void flags(){
		if (result == 0)
			s.cpu.f.setText("1 , 1 , 0");
		else if (result > 127)
			s.cpu.f.setText("0 , 0 , 1");
		else
			s.cpu.f.setText("0 , 1 , 0");
	}
	
	private String ifNegative (String stringNum){
		
		if (stringNum.charAt(0) >= 56)
			stringNum = "FFFFFF"+stringNum;
		return stringNum;
	}
	
	private String twoHex (String hexString){
		if (hexString.length() ==8)
			hexString = hexString.substring(6);
		return hexString;
	}
	
	public void reset(){
		s.lineCount = 0;
		// resets all other labels
		s.pp.instructionLabel.setText(" ");
		s.pp.instructionLabel.setBorder(BorderFactory.createTitledBorder("Instruction :     "));

		s.cpu.pc.setText("");
		s.cpu.p1.setText("");
		s.cpu.p2.setText("");
		s.cpu.p3.setText("");
		
		s.cpu.a.setText("");
		s.cpu.f.setText("");
		s.cpu.ar.setText("");
		
		s.cpu.sp.setText("F");
		s.cpu.f.setText("0 , 0 , 0");
		
		for (int i=0; i < s.cpu.M.length; i++){
			s.cpu.M[i].setText("FF");
		}
		for (int i=0; i < s.cpu.S.length; i++){
			s.cpu.S[i].setText("FF");
		}
	}
}
