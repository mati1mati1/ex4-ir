package AST.AST_DEC;
import AST.SemanticErrorException;
import SYMBOL_TABLE.SYMBOL_TABLE.ScopeType;
import SYMBOL_TABLE.*;
import AST.AST_TYPE.AST_TYPE;
import TYPES.*;
import AST.AST_STMT.AST_STMT;
import IR.*;
public class AST_ARRAY_TYPEDEF extends AST_DEC
{
    String id;
    public AST_TYPE t;

    public AST_ARRAY_TYPEDEF(String id, AST_TYPE t) {
        this.id = id;
        this.t = t;
    }

    @Override
    public TYPE SemantMe() {
        // check we are in global scope.
        ScopeType scopeType = SYMBOL_TABLE.getInstance().getTypeOfScope();
        if (!scopeType.equals(ScopeType.Global_Scope)) {
            System.out.println("Arrays can only be defined in the global scope.");
            throw new SemanticErrorException("" + lineNumber);
        }
        // check t != void
        TYPE type = t.SemantMe(); // if the type does not exist, an error will be thrown here (See AST_TYPE_ID)
        if(type == TYPE_VOID.getInstance()) {
            System.out.println("Array type cannot be void.");
            throw new SemanticErrorException("" + lineNumber);
        }
        // check the ID is not previously defined in this scope
        if(SYMBOL_TABLE.getInstance().isInScope(id)){
            System.out.print("this var already declared in Scope");
            throw new SemanticErrorException("" + lineNumber);
        }
        // check the ID is not with an illegal name
        if(!SYMBOL_TABLE.getInstance().isValidName(id)){
            System.out.println("The id name is equal to string/int/void");
            throw new SemanticErrorException("" + lineNumber);
        }
        SYMBOL_TABLE.getInstance().enter(id, new TYPE_ARRAY(type, id));

        return null;
    }
}