package AST.AST_OPT;
import AST.AST_EXP.AST_EXP;
import TYPES.*;
public class AST_OPTEXP extends AST_OPT
{
    public AST_EXP e;

    public AST_OPTEXP(AST_EXP e) {
        this.e = e;
    }

    public TYPE SemantMe() {
        return e.SemantMe();
    }
}