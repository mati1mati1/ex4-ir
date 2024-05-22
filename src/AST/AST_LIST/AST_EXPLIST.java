package AST.AST_LIST;
import TYPES.*;
import AST.AST_EXP.AST_EXP;

public class AST_EXPLIST extends AST_LIST {
    public AST_EXP e;
    public AST_EXPLIST el;

    public AST_EXPLIST(AST_EXP e, AST_EXPLIST el) {
        this.e = e;
        this.el = el;
    }

    public TYPE_LIST SemantMe() {
        if (e == null && el == null) {
            return null;
        }
        else if (el == null && e != null) {
            return new TYPE_LIST(e.SemantMe(), null);
        }
        else if (e != null && el != null) {
            return new TYPE_LIST(e.SemantMe(), el.SemantMe());
        }
        return null;
    }
}