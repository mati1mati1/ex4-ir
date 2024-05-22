package AST.AST_LIST;

import AST.AST_DEC.AST_DEC;
import TYPES.TYPE_LIST;

public class AST_DEC_LIST extends AST_LIST {
    public AST_DEC d;
    public AST_DEC_LIST dl;

    public AST_DEC_LIST(AST_DEC d, AST_DEC_LIST dl) {
        this.d = d;
        this.dl = dl;
    }

    public TYPE_LIST SemantMe() {
        if (d != null) {
            d.SemantMe();
        }
        if (dl != null) {
            dl.SemantMe();
        }
        return null;
    }
}