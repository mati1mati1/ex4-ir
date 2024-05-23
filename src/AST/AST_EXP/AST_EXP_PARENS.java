package AST.AST_EXP;
import TYPES.*;
import AST.AST_STMT.AST_STMT;
import IR.*;
public class AST_EXP_PARENS extends AST_EXP {

    public AST_EXP e;

    public AST_EXP_PARENS(AST_EXP e) {
        this.e = e;
    }
    public TYPE SemantMe() {
        return e.SemantMe();
    }
}