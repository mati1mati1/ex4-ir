package AST.AST_DEC;
import TYPES.*;
public class AST_DEC_CLASSDEC extends AST_DEC {
    public AST_CLASSDEC classDec;

    public AST_DEC_CLASSDEC(AST_CLASSDEC classDec) {

        this.classDec = classDec;
    }

    public TYPE SemantMe() {
        if (classDec != null) {
            return classDec.SemantMe();
        }
        return null;
    }
}