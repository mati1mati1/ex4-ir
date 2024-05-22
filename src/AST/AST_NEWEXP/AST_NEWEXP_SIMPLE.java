package AST.AST_NEWEXP;
import AST.AST_TYPE.AST_TYPE;
import TYPES.*;
import AST.SemanticErrorException;

public class AST_NEWEXP_SIMPLE extends AST_NEWEXP
{
    public AST_TYPE t;

    public AST_NEWEXP_SIMPLE(AST_TYPE t) {
        this.t = t;
    }

    public TYPE SemantMe() {
        TYPE new_type = t.SemantMe();
        if (new_type == null || new_type == TYPE_VOID.getInstance()) {
            throw new SemanticErrorException("" + lineNumber);
        }
        return new_type;
    }
}