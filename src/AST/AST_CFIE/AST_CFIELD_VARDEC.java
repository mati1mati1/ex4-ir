package AST.AST_CFIE;
import AST.AST_DEC.AST_VARDEC;
import AST.AST_DEC.AST_VARDEC_SIMPLE;
import AST.AST_EXP.AST_EXP_NIL;
import AST.AST_EXP.AST_EXP_SIGNEDINT;
import AST.AST_EXP.AST_EXP_STRING;
import AST.SemanticErrorException;
import TYPES.*;
public class AST_CFIELD_VARDEC extends AST_CFIELD
{
    public AST_VARDEC v;

    public AST_CFIELD_VARDEC(AST_VARDEC v) {
        this.v = v;
    }

    @Override
    public TYPE_CLASS_VAR_DEC SemantMe() {
        if (!(v instanceof AST_VARDEC_SIMPLE)) {
            System.out.println("Class field must be primitive val.");
            throw new SemanticErrorException("" + lineNumber);
        }
        else if ((((AST_VARDEC_SIMPLE)v).o.exp != null) &&
            !(((AST_VARDEC_SIMPLE)v).o.exp instanceof AST_EXP_SIGNEDINT) && !(((AST_VARDEC_SIMPLE)v).o.exp instanceof AST_EXP_STRING)
                    && !(((AST_VARDEC_SIMPLE)v).o.exp instanceof AST_EXP_NIL)) {
                System.out.println("Class field must be primitive val.");
                throw new SemanticErrorException("" + lineNumber);
        }
        return new TYPE_CLASS_VAR_DEC(v.SemantMe(), ((AST_VARDEC_SIMPLE)v).id);
    }
}
