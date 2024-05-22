package AST.AST_TYPE;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;

public class AST_TYPE_ID extends AST_TYPE
{
    String id;

    public AST_TYPE_ID(String i) {
        this.id = i;
    }

    public TYPE SemantMe() {
        TYPE type = SYMBOL_TABLE.getInstance().find(id);
        if (type == null) // the type does not exist
        {
           System.out.println("The type does not exist.");
           throw new SemanticErrorException("" + lineNumber);
        }
        return type;
    }
}