package AST.AST_STMT;
import AST.AST_DEC.AST_VARDEC;
import TYPES.*;
public class AST_STMT_VARDEC extends AST_STMT {

    public AST_VARDEC v;

    public AST_STMT_VARDEC(AST_VARDEC v) {
        this.v = v;
    }

    @Override
    public TYPE SemantMe() {
        return v.SemantMe();
    }
}