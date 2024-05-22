   
import java.io.*;
import java.io.PrintWriter;
import java_cup.runtime.Symbol;
import AST.*;

public class Main
{
	static public void main(String argv[])
	{
		Lexer l;
		Parser p;
		Symbol s;
		AST_PRGM AST;
		FileReader file_reader;
		PrintWriter file_writer;
		String inputFilename = argv[0];
		String outputFilename = argv[1];
		
		try
		{

			file_reader = new FileReader(inputFilename);
			file_writer = new PrintWriter(outputFilename);

			l = new Lexer(file_reader);
			p = new Parser(l, file_writer);

			try {
				AST = (AST_PRGM) p.parse().value;
				AST.SemantMe();
			} catch (SemanticErrorException e) {
				file_writer.println("ERROR("+e.getMessage()+")");
				file_writer.flush();
				file_writer.close();
			}
			if(p.failed) {
				file_writer.write("ERROR("+p.NumOfLine+")\n");
				file_writer.flush();
				file_writer.close();
			}

			file_writer.println("OK");
			file_writer.flush();
			file_writer.close();
    	}
			     
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


