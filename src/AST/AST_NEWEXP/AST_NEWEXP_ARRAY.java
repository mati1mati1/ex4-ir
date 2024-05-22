package AST.AST_NEWEXP;
import AST.AST_EXP.AST_EXP;
import AST.AST_EXP.AST_EXP_SIGNEDINT;
import AST.AST_TYPE.AST_TYPE;
import TYPES.*;
import AST.SemanticErrorException;
public class AST_NEWEXP_ARRAY extends AST_NEWEXP
{
    public AST_TYPE t;
    public AST_EXP e;

    public AST_NEWEXP_ARRAY(AST_TYPE t, AST_EXP e) {
        this.t = t;
        this.e = e;
    }
    public TYPE SemantMe() {

        TYPE new_type = t.SemantMe(); // get the AST_TYPE of the array

        // Check type exists
        if (new_type == null || new_type == TYPE_VOID.getInstance()) {
            System.out.println("The array type is null or void.");
            throw new SemanticErrorException("" + lineNumber);
        }

        // Validate subscript for arrays
        if (e != null) {
            TYPE exp_type = e.SemantMe();
            if (exp_type != TYPE_INT.getInstance()) { // the subscript must be an integer
                System.out.println("The subscript type of the array is not an integer.");
                throw new SemanticErrorException("" + lineNumber);
            }

            if (e instanceof AST_EXP_SIGNEDINT) {
                if((((AST_EXP_SIGNEDINT) e).s.sign == 1)) { // the number is < 0 (sign=1 is minus)
                    System.out.println("The subscript type of the array is a negative integer.");
                    throw new SemanticErrorException("" + lineNumber);
                }
            }
        }

        return new TYPE_ARRAY(new_type, null);
    }
}
