package AST.AST_VAR;
import AST.AST_EXP.AST_EXP_SIGNEDINT;
import TYPES.*;
import AST.AST_EXP.AST_EXP;
import AST.SemanticErrorException;

public class AST_VAR_SUBSCRIPT extends AST_VAR
{
	public AST_VAR var;
	public AST_EXP subscript;

	public AST_VAR_SUBSCRIPT(AST_VAR var,AST_EXP subscript)
	{
		this.var = var;
		this.subscript = subscript;
	}

	@Override
	public TYPE SemantMe() {
		TYPE var_type = var.SemantMe();
		TYPE subscript_type = subscript.SemantMe();

		if (!var_type.isArray() || subscript_type != TYPE_INT.getInstance()) {
			throw new SemanticErrorException("" + lineNumber);
		}

		if (subscript instanceof AST_EXP_SIGNEDINT) {
			if (((AST_EXP_SIGNEDINT) subscript).s.sign == 1) { // sign = 1 is minus
				throw new SemanticErrorException("" + lineNumber);
			}
		}
		return ((TYPE_ARRAY)var_type).type;
	}
}
