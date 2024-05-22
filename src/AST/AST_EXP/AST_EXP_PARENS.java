package AST.AST_EXP;
import TYPES.*;
public class AST_EXP_PARENS extends AST_EXP {

    public AST_EXP e;

    public AST_EXP_PARENS(AST_EXP e) {
        this.e = e;
    }
    public TYPE SemantMe() {
        return e.SemantMe();
    }
}