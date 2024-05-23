package AST.AST_EXP;
import AST.AST_BINOP;
import TYPES.*;
import AST.SemanticErrorException;
import IR.*;
import TEMP.*;
import AST.AST_STMT.AST_STMT;
import IR.*;

public class AST_EXP_BINOP extends AST_EXP {
    public AST_BINOP op;
    public AST_EXP left;
    public AST_EXP right;

    public AST_EXP_BINOP(AST_EXP left, AST_EXP right, AST_BINOP op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public TYPE SemantMe() {
        TYPE t_right = null;
        TYPE t_left = null;

        if (left != null) t_left = left.SemantMe();
        if (left != null) t_right = right.SemantMe();

        if (t_left == null) {
            throw new SemanticErrorException("" + lineNumber);
        }
        if (t_right == null) {
            throw new SemanticErrorException("" + lineNumber);
        }

        switch (op.op) {
            case 6: // equal
                if (!TYPE.isMatching(t_right, t_left) && !TYPE.isMatching(t_left, t_right)) {
                    throw new SemanticErrorException("" + lineNumber);
                }
                break;
            case 0: // plus
                if ((t_left == TYPE_STRING.getInstance()) && (t_right == TYPE_STRING.getInstance())) {
                    return TYPE_STRING.getInstance();
                }
                if (!(t_left == TYPE_INT.getInstance()) || !(t_right == TYPE_INT.getInstance())) {
                    throw new SemanticErrorException("" + lineNumber);
                }
                break;
            case 3: // divide
                if (!(t_left == TYPE_INT.getInstance()) || !(t_right == TYPE_INT.getInstance())) {
                    throw new SemanticErrorException("" + lineNumber);
                }
                if (right instanceof AST_EXP_SIGNEDINT && ((AST_EXP_SIGNEDINT) right).s.i == 0) {
                    throw new SemanticErrorException("" + lineNumber);
                }
            default:
                if (!(t_left == TYPE_INT.getInstance()) || !(t_right == TYPE_INT.getInstance())) {
                    System.out.println("One of the variables is not of type int");
                    throw new SemanticErrorException("" + lineNumber);
                }
        }
        return TYPE_INT.getInstance();
    }
    public TEMP IRme() {
        TEMP t1 = left.IRme();
        TEMP t2 = right.IRme();
        return op.IRme(t1, t2);
    }
}
