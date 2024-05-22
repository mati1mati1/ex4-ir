package AST.AST_DEC;
import AST.AST_NEWEXP.AST_NEWEXP;
import AST.AST_TYPE.AST_TYPE;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;
public class AST_VARDEC_NEW extends AST_VARDEC
{
    public AST_TYPE t;
    String id;
    public AST_NEWEXP o;

    public AST_VARDEC_NEW(AST_TYPE t, String id, AST_NEWEXP o) {
        this.t = t;
        this.id = id;
        this.o = o;
    }
    public TYPE SemantMe() {
        TYPE type = t.SemantMe();
        if(!(type instanceof TYPE_CLASS || type instanceof  TYPE_ARRAY)){
            System.out.println("We are trying to put a new value into a primitive type.");
            throw new SemanticErrorException("" + lineNumber);
        }
        if(SYMBOL_TABLE.getInstance().getTypeOfScope().equals(SYMBOL_TABLE.ScopeType.Class)){
            if(SYMBOL_TABLE.getInstance().getCurrentClass().getFunctionDeclaredInAncestors(id) != null){
                System.out.println("we are in class scope and this id already defined ");
                throw new SemanticErrorException("" + lineNumber);
            }
        }
        else if (SYMBOL_TABLE.getInstance().isInScope(id)){
            System.out.println("we are not class scope and this id already defined in the scope ");
            throw new SemanticErrorException("" + lineNumber);
        }
        // check the ID is not with an illegal name
        if(!SYMBOL_TABLE.getInstance().isValidName(id)){
            System.out.println("The id name is equal to string/int/void");
            throw new SemanticErrorException("" + lineNumber);
        }
        TYPE newExp = o.SemantMe();
        if(type instanceof TYPE_CLASS){
            if (!(newExp instanceof TYPE_CLASS)) {
                System.out.println("Trying to assign a non-class to a class.");
                throw new SemanticErrorException("" + lineNumber);
            }
            if(!TYPE.isMatching(newExp, type)){
                System.out.println("This is type class but the assigned is not subclass  ");
                throw new SemanticErrorException("" + lineNumber);
            }
        }
        if (type instanceof TYPE_ARRAY) {
            if (!(newExp instanceof TYPE_ARRAY)) {
                System.out.println("Trying to assign a non-array to an array.");
                throw new SemanticErrorException("" + lineNumber);
            }
            TYPE newExpression_arr_type = ((TYPE_ARRAY)newExp).type;
            TYPE defined_arr_type = ((TYPE_ARRAY) type).type;
            if (!TYPE.isMatching(newExpression_arr_type, defined_arr_type)) {
                System.out.println("Type of newExp array does not match the type of the defined array.");
                throw new SemanticErrorException("" + lineNumber);
            }
        }

        SYMBOL_TABLE.getInstance().enter(id,type);

        return type;
    }
}