package AST.AST_DEC;
import AST.AST_EXP.AST_EXP_NIL;
import AST.AST_OPT.AST_OPTASSIGN;
import AST.AST_SIGNEDINT;
import AST.AST_TYPE.AST_TYPE;
import AST.AST_EXP.*;
import AST.AST_TYPE.AST_TYPE_ID;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;
public class AST_VARDEC_SIMPLE extends AST_VARDEC
{
    public AST_TYPE t;
    public String id;
    public AST_OPTASSIGN o;

    public AST_VARDEC_SIMPLE(AST_TYPE t, String id, AST_OPTASSIGN o) {
        this.t = t;
        this.id = id;
        this.o = o;
    }
    public TYPE SemantMe() {
        // Check the Type:
        TYPE type = t.SemantMe(); // if the type does not exist, an error will be thrown here (See AST_TYPE_ID)
        if(type == TYPE_VOID.getInstance()){
            System.out.print("The type can't be void");
            throw new SemanticErrorException("" + lineNumber);
        }

        // Check the id name type:
        if(SYMBOL_TABLE.getInstance().isInScope(id)){
            System.out.print("this var already declared in Scope");
            throw new SemanticErrorException("" + lineNumber);
        }
        if (SYMBOL_TABLE.getInstance().getTypeOfScope().equals(SYMBOL_TABLE.ScopeType.Class)) {
            TYPE_CLASS father_class = (TYPE_CLASS) SYMBOL_TABLE.getInstance().GetCurrentClassParent();
            TYPE t = null;
            if(father_class!= null){
                t = father_class.checkIfClassMemberExistsInAncestors(id);
            }
            if (t == null) {
                if(SYMBOL_TABLE.getInstance().isInScope(id)) {
                    System.out.println("Illegal variable shadowing in a class.");
                    throw new SemanticErrorException("" + lineNumber);
                }
            }
            else {
                // if t == null (the id doesn't exist in ancestors) it is OK
                // else we must throw an error
                System.out.println("Illegal variable shadowing in a class.");
                throw new SemanticErrorException("" + lineNumber);
            }
        }

        // check the ID is not with an illegal name
        if(!SYMBOL_TABLE.getInstance().isValidName(id)){
            System.out.println("The id name is equal to string/int/void");
            throw new SemanticErrorException("" + lineNumber);
        }

        // Check the expression type:
        if (SYMBOL_TABLE.getInstance().getTypeOfScope().equals(SYMBOL_TABLE.ScopeType.Class)) {
            if(o.exp != null){
                if(!(o.exp instanceof AST_EXP_SIGNEDINT || o.exp instanceof AST_EXP_STRING || o.exp instanceof AST_EXP_NIL)){
                    System.out.println("We are in a class scope but the assignment is not primitive.");
                    throw new SemanticErrorException("" + lineNumber);
                }
            }
        }
        // check the assignment is not NIL into a primitive type
        if(!(t instanceof AST_TYPE_ID) && o.exp instanceof AST_EXP_NIL){
            System.out.println("We tried to assign NIL to a primitive type.");
            throw new SemanticErrorException("" + lineNumber);
        }
        SYMBOL_TABLE.getInstance().enter(id,type);
        return type;
    }


}