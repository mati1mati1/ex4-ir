package AST.AST_OPT;
import AST.AST_EXP.AST_EXP;
import TYPES.*;
public class AST_OPTASSIGN extends AST_OPT
{
    public AST_EXP exp;

    public AST_OPTASSIGN(AST_EXP exp) {
        this.exp = exp;
    }

    public TYPE SemantMe() {
        if (exp == null) {
            return null;
        }
        return exp.SemantMe();
    }
}