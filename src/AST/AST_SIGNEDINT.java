package AST;
import TYPES.*;
public class AST_SIGNEDINT extends AST_Node
{
    public int i;
    public int sign;

    public AST_SIGNEDINT(int i, int sign) {
        this.i = i;
        this.sign = sign;
    }

    public TYPE SemantMe() {
        return TYPE_INT.getInstance();
    }
}