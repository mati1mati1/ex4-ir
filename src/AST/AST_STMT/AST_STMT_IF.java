package AST.AST_STMT;
import AST.AST_EXP.AST_EXP;
import AST.AST_LIST.AST_STMT_LIST;
import AST.SemanticErrorException;
import TYPES.*;
import SYMBOL_TABLE.SYMBOL_TABLE.ScopeType;
import SYMBOL_TABLE.*;

public class AST_STMT_IF extends AST_STMT
{
	public AST_EXP cond;
	public AST_STMT_LIST body;

	public AST_STMT_IF(AST_EXP cond, AST_STMT_LIST body)
	{
		this.cond = cond;
		this.body = body;
	}

	@Override
	public TYPE SemantMe() {

		TYPE condition_type = cond.SemantMe();

		if (condition_type != TYPE_INT.getInstance()) {
			System.out.print("The condition of the IF statement is not an integer");
			throw new SemanticErrorException("" + lineNumber);
		}

		SYMBOL_TABLE.getInstance().beginScope("if", ScopeType.If_While_Block);

		body.SemantMe();

		SYMBOL_TABLE.getInstance().endScope();
		return null;
	}
	public void IRme() {
		TEMP condTemp = cond.IRme();
		String labelEnd = IRcommand.getFreshLabel("end_if");
		IR.getInstance().Add_IRcommand(new IRcommand_Jump_If_Eq_To_Zero(condTemp, labelEnd));
		body.IRme();
		IR.getInstance().Add_IRcommand(new IRcommand_Label(labelEnd));
	}
}