package AST.AST_LIST;
import TYPES.*;
import AST.AST_CFIE.AST_CFIELD;

public class AST_CFIELDLIST extends AST_LIST
{
    public AST_CFIELDLIST cl;
    public AST_CFIELD c;

    public AST_CFIELDLIST(AST_CFIELD c, AST_CFIELDLIST cl) {
        this.c = c;
        this.cl = cl;
    }

    @Override
    public TYPE_LIST SemantMe() {
        if (cl == null) {
            return new TYPE_LIST(c.SemantMe(), null);
        } else {
            return new TYPE_LIST(c.SemantMe(), cl.SemantMe());
        }
    }
}