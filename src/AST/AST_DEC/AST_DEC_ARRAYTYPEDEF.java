package AST.AST_DEC;
import TYPES.*;
public class AST_DEC_ARRAYTYPEDEF extends AST_DEC {
    public AST_ARRAY_TYPEDEF arrayTypeDef;

    public AST_DEC_ARRAYTYPEDEF(AST_ARRAY_TYPEDEF arrayTypeDef) {
        this.arrayTypeDef = arrayTypeDef;
    }

    public TYPE SemantMe() {
        if (arrayTypeDef != null) {
            return arrayTypeDef.SemantMe();
        }
        return null;
    }
}