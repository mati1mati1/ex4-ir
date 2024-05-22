package AST.AST_STMT;
import AST.AST_OPT.AST_OPTEXP;
import AST.SemanticErrorException;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.*;
public class AST_STMT_RETURN extends AST_STMT
{
    public AST_OPTEXP e;

    public AST_STMT_RETURN(AST_OPTEXP e) {
        this.e = e;
    }

    @Override
    public TYPE SemantMe() {
        TYPE returnValueType;
        TYPE currentFuncReturnType = SYMBOL_TABLE.getInstance().getCurrentFunction().returnType;
        if (e != null) {
            returnValueType = e.SemantMe();
            if (returnValueType == TYPE_VOID.getInstance()) {
                System.out.println("We are trying to return a value to a void function.");
                throw new SemanticErrorException("" + lineNumber);
            }
            else if (!TYPE.isMatching(returnValueType, currentFuncReturnType)){
                System.out.println("The return value does not match the required return type.");
                throw new SemanticErrorException("" + lineNumber);
            }
        } else {
            if (currentFuncReturnType != TYPE_VOID.getInstance()) {
                System.out.println("We are trying to return void to a non-void function.");
                throw new SemanticErrorException("" + lineNumber);
            }
        }
        return null;
    }
}