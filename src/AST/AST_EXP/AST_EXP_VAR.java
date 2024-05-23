package AST.AST_EXP;
import AST.AST_VAR.AST_VAR;
import TYPES.*;
import IR.*;
import TEMP.*;

public class AST_EXP_VAR extends AST_EXP
{
	public AST_VAR var;

	public AST_EXP_VAR(AST_VAR var)
	{
		this.var = var;
	}
	public TYPE SemantMe() {
		return var.SemantMe();
	}
	public TEMP IRme() {
		return var.IRme();
	}
}
