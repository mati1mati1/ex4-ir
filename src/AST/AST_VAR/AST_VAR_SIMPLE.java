package AST.AST_VAR;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;

public class AST_VAR_SIMPLE extends AST_VAR
{
	public String name;

	public AST_VAR_SIMPLE(String name)
	{
		this.name = name;
	}
	public TYPE SemantMe() {
		TYPE t = SYMBOL_TABLE.getInstance().extendedFind(name);
		if(t == null){
			throw new SemanticErrorException("" + lineNumber);
		}
		return t;
	}
}
