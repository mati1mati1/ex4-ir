package AST;
import AST.AST_LIST.AST_DEC_LIST;
import SYMBOL_TABLE.SYMBOL_TABLE.ScopeType;
import SYMBOL_TABLE.*;
import AST.AST_DEC.AST_DEC;
import TYPES.*;
public class AST_PRGM extends AST_Node
{
    public AST_DEC_LIST dl;

    public AST_PRGM(AST_DEC_LIST dl) {
        this.dl = dl;
    }

    public TYPE SemantMe() {

        SYMBOL_TABLE.getInstance().beginScope("global", ScopeType.Global_Scope);

        if (dl != null) {
            dl.SemantMe();
        }
        SYMBOL_TABLE.getInstance().endScope();

        return null;
    }
}