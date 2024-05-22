package AST.AST_DEC;
import TYPES.*;
public  class AST_DEC_VARDEC extends AST_DEC
{
    public AST_VARDEC vardec;

    public AST_DEC_VARDEC(AST_VARDEC vardec) {
        this.vardec = vardec;
    }

    public TYPE SemantMe() {
        if (vardec != null) {
            return vardec.SemantMe();
        }
        return null;
    }
}