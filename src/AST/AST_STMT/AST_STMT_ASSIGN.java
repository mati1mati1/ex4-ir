package AST.AST_STMT;
import AST.AST_EXP.AST_EXP;
import AST.AST_VAR.AST_VAR;
import AST.SemanticErrorException;
import TYPES.*;
import IR.*;
import TEMP.*;
public class AST_STMT_ASSIGN extends AST_STMT
{
	public AST_VAR var;
	public AST_EXP exp;

	public AST_STMT_ASSIGN(AST_VAR var,AST_EXP exp)
	{
		this.var = var;
		this.exp = exp;
	}

	@Override
	public TYPE SemantMe() {
		TYPE varType = null;
		TYPE expType = null;

		if (var != null) {
			varType = var.SemantMe();
		}
		if (exp != null) {
			expType = exp.SemantMe();
		}

		if(!TYPE.isMatching(expType, varType)) {
			System.out.println("The expression is not a matching subtype of the variable we are trying to store it in.");
			throw new SemanticErrorException("" + lineNumber);
		}
		return null;
	}
	public void IRme() {
		TEMP varTemp = var.IRme();
		TEMP expTemp = exp.IRme();
		IR.getInstance().Add_IRcommand(new IRcommand_Store(varTemp, expTemp));
	}
}
