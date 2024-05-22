package AST.AST_LIST;

import AST.AST_TYPE.AST_TYPE;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;

public class AST_PARAMS_LIST extends AST_LIST
{
   public AST_TYPE t;
   String id;
   public AST_PARAMS_LIST p;

    public AST_PARAMS_LIST(AST_TYPE t, String id, AST_PARAMS_LIST p) {
        this.t = t;
        this.id = id;
        this.p = p;
    }
    public TYPE_LIST SemantMe() {
        if (t == null){
            return null;
        }

        if (SYMBOL_TABLE.getInstance().isInScope(this.id)){
            throw new SemanticErrorException(""+ lineNumber);
        }
        // check the ID is not with an illegal name
        if(!SYMBOL_TABLE.getInstance().isValidName(id)){
            System.out.println("The id name is equal to string/int/void");
            throw new SemanticErrorException("" + lineNumber);
        }
        TYPE t_type = t.SemantMe();
        SYMBOL_TABLE.getInstance().enter(this.id,t_type);
        return new TYPE_LIST(t_type, p.SemantMe());
    }
}
