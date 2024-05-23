package AST;
import TYPES.*;
import IR.*;
import TEMP.*;
public class AST_BINOP extends AST_Node {
    public int op;

    public AST_BINOP(int op) {
        this.op = op;
    }

    public TYPE SemantMe() {
        return null;
    }

    public TEMP IRme(TEMP t1, TEMP t2) {
        TEMP dst = TEMP_FACTORY.getInstance().getFreshTEMP();
        IRcommand cmd;
        switch (op) {
            case 0: // PLUS
                cmd = new IRcommand_Binop_Add_Integers(dst, t1, t2);
                break;
            case 1: // MINUS
                cmd = new IRcommand_Binop_Sub_Integers(dst, t1, t2);
                break;
            case 2: // MUL
                cmd = new IRcommand_Binop_Mul_Integers(dst, t1, t2);
                break;
            case 3: // DIV
                cmd = new IRcommand_Binop_Div_Integers(dst, t1, t2);
                break;
            case 6: // EQUAL
                cmd = new IRcommand_Binop_EQ_Integers(dst, t1, t2);
                break;
            default:
                throw new UnsupportedOperationException("Operation not supported");
        }
        IR.getInstance().Add_IRcommand(cmd);
        return dst;
    }
}
