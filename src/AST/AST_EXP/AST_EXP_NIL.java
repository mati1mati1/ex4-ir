package AST.AST_EXP;
import TYPES.*;
public class AST_EXP_NIL extends AST_EXP {

    public AST_EXP_NIL() {
    }
    public TYPE SemantMe() {
        return TYPE_NIL.getInstance();
    }
}