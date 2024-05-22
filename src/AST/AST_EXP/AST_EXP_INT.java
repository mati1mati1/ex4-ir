package AST.AST_EXP;
import TYPES.*;
public class AST_EXP_INT extends AST_EXP {
	public int value;

	public AST_EXP_INT(int value) {
		this.value = value;
	}

	@Override
	public TYPE SemantMe() {
		return TYPE_INT.getInstance();
	}
}