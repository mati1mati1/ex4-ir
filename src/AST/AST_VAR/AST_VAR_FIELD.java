package AST.AST_VAR;
import TYPES.*;
import AST.SemanticErrorException;

public class AST_VAR_FIELD extends AST_VAR
{
	public AST_VAR var;
	public String fieldName;

	public AST_VAR_FIELD(AST_VAR var,String fieldName)
	{
		this.var = var;
		this.fieldName = fieldName;
	}
	public TYPE SemantMe() {
		TYPE var_type = null;
		if (var != null) {
			var_type = var.SemantMe();
		}
		if (!(var_type instanceof TYPE_CLASS)) {
			System.out.println("Trying to access the field of a var that is not a class.");
			throw new SemanticErrorException("" + lineNumber);
		}
		TYPE t = ((TYPE_CLASS)var_type).checkIfClassMemberExistsInAncestors(fieldName);
		if (t == null || t instanceof TYPE_FUNCTION) {
			System.out.println("The class field does not exist.");
			throw new SemanticErrorException("" + lineNumber);
		}
		return ((TYPE_CLASS_VAR_DEC)t).t;
	}
	public TEMP IRme() {
		TEMP obj = var.IRme();
		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
		IR.getInstance().Add_IRcommand(new IRcommand_LoadField(t, obj, fieldName));
		return t;
	}
}
