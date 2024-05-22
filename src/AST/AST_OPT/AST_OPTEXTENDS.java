package AST.AST_OPT;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;
public class AST_OPTEXTENDS extends AST_OPT {
    String id;

    public AST_OPTEXTENDS(String id) {
        this.id = id;
    }

    public TYPE SemantMe() {
        if (id == null) {
            return null;
        }
        TYPE id_type = SYMBOL_TABLE.getInstance().find(id);
        if (id_type == null || !id_type.isClass()) {
            throw new SemanticErrorException("" + lineNumber);
        }
        return id_type;
    }
}