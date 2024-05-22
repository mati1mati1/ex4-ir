package AST.AST_DEC;
import TYPES.*;
public class AST_DEC_FUNCDEC extends AST_DEC {
    public AST_FUNCDEC funcdec;

    public AST_DEC_FUNCDEC(AST_FUNCDEC funcdec) {

        this.funcdec = funcdec;
    }

    public TYPE SemantMe() {
        if (funcdec != null) {
            return funcdec.SemantMe();
        }
        return null;
    }
}