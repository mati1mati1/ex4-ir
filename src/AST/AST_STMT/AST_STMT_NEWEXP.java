package AST.AST_STMT;
import AST.AST_NEWEXP.AST_NEWEXP;
import AST.AST_VAR.AST_VAR;
import TYPES.*;
import AST.SemanticErrorException;

public class AST_STMT_NEWEXP extends AST_STMT {

    public AST_VAR v;
    public AST_NEWEXP n;

    public AST_STMT_NEWEXP(AST_VAR v, AST_NEWEXP n) {
        this.v = v;
        this.n = n;
    }

    @Override
    public TYPE SemantMe() {
        TYPE varType = null;
        TYPE newExpType = null;

        if (v != null) {
            varType = v.SemantMe();
        }
        if (n != null) {
            newExpType = n.SemantMe();
        }

        if (newExpType == null){
            System.out.println("Invalid assignment (tried to assign \'VAR = NEW null\')");
            throw new SemanticErrorException("" + lineNumber);
        }
        if (varType.isClass()) {
            if (!newExpType.isClass() || (newExpType.isClass() && !TYPE.isMatching(newExpType, varType))) {
                System.out.println("The variable is a class but the new exp is not a class, or there is a mismatch in the class types");
                throw new SemanticErrorException("" + lineNumber);
            }
        }
        else if (varType.isArray()) {
            if (!newExpType.isArray() || (newExpType.isArray() && !TYPE.isMatching(((TYPE_ARRAY)newExpType).type, ((TYPE_ARRAY) varType).type))) {
                System.out.println("The variable is an array but the new exp is not an array, or there is a mismatch in the array types");
                throw new SemanticErrorException("" + lineNumber);
            }
        }
        else {
            System.out.println("tried assigning a non array or class type with the NEW keyword");
            throw new SemanticErrorException("" + lineNumber);
        }

        return null;
    }
}