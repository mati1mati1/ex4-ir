package AST.AST_EXP;
import TYPES.*;
public class AST_EXP_STRING extends AST_EXP {

    String s;

    public AST_EXP_STRING(String s) {
        this.s = s;
    }
    public TYPE SemantMe() {
        return TYPE_STRING.getInstance();
    }
}
