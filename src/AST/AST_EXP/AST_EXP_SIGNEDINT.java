package AST.AST_EXP;
import AST.AST_SIGNEDINT;
import TYPES.*;
public class AST_EXP_SIGNEDINT extends AST_EXP
{
    public AST_SIGNEDINT s;

    public AST_EXP_SIGNEDINT(AST_SIGNEDINT s) {
        this.s = s;
    }

    public TYPE SemantMe() {
        return s.SemantMe();
    }
}