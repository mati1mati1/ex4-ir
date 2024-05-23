package AST.AST_EXP;
import TYPES.*;
import IR.*;
import TEMP.*;
public class AST_EXP_INT extends AST_EXP {
	public int value;

	public AST_EXP_INT(int value) {
		this.value = value;
	}

	@Override
	public TYPE SemantMe() {
		return TYPE_INT.getInstance();
	}
	public TEMP IRme() {
		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
		IR.getInstance().Add_IRcommand(new IRcommandConstInt(t, value));
		return t;
	}
}