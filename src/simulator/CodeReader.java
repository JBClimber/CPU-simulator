package simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CodeReader {

	public Simulator s;
	private FileReader fr;
	private BufferedReader br;
	private StringTokenizer strtok;
	
	public CodeReader(Simulator s){
		this.s = s;
		ReadAndFill();
	}

	private void ReadAndFill (){
		String buffer = "";
		String asm = "";
		try{
			fr = new FileReader("Program.hex");
			br = new BufferedReader(fr);			// reads program file
			String a=" ", b=" ", c=" ", d=" ";
			
			while ( (buffer = br.readLine()) != null ){
				if(!buffer.isEmpty() && buffer.contains("; ")){		// prevents empty strings to enter and code without comments/asm code
					strtok = new StringTokenizer( buffer, ";");
					while (strtok.hasMoreTokens()){
						buffer = strtok.nextToken();
						asm = strtok.nextToken();
						//System.out.println("buffer:"+buffer+"         asm:"+asm); /////////////// errr
						strtok = new StringTokenizer(buffer, ": ");
						a=" "; b=" "; c=" "; d=" ";			// sets all toks to " "
						if(strtok.hasMoreTokens())
							a = strtok.nextToken();
						if(strtok.hasMoreTokens())
							b = strtok.nextToken();
						if(strtok.hasMoreTokens())
							c = strtok.nextToken();
						if(strtok.hasMoreTokens())
							d = strtok.nextToken();
						s.code.add(new CodeLine(a, b, c, d, asm));
						System.out.println("a:"+a+"       b:"+b+"       c:"+c+"      d:"+d+"        asm:"+asm);
					}
				}
			}
		}
		catch (FileNotFoundException e){
			System.out.println("File NOT found "+e);
		}
		catch (IOException e) {
			System.out.println("IO problems "+e);
		}
	}
}