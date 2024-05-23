package AST.AST_EXP;
import TYPES.*;
import AST.AST_STMT.AST_STMT;
import IR.*;
public class AST_EXP_NIL extends AST_EXP {

    public AST_EXP_NIL() {
    }
    public TYPE SemantMe() {
        return TYPE_NIL.getInstance();
    }
}