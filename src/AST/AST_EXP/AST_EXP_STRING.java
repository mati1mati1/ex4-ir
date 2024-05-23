package AST.AST_EXP;
import TYPES.*;
import AST.AST_STMT.AST_STMT;
import IR.*;
public class AST_EXP_STRING extends AST_EXP {

    String s;

    public AST_EXP_STRING(String s) {
        this.s = s;
    }
    public TYPE SemantMe() {
        return TYPE_STRING.getInstance();
    }
}
