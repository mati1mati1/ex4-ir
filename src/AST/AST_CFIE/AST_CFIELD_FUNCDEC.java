package AST.AST_CFIE;
import AST.AST_DEC.AST_FUNCDEC;
import TYPES.*;
public class AST_CFIELD_FUNCDEC extends AST_CFIELD
{
    public AST_FUNCDEC v;

    public AST_CFIELD_FUNCDEC(AST_FUNCDEC v) {
        this.v = v;
    }

    @Override
    public TYPE SemantMe() {
        return v.SemantMe();
    }
}
