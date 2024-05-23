package AST.AST_EXP;
import AST.AST_LIST.AST_OPTEXPS_LIST;
import AST.AST_VAR.AST_VAR;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;
import IR.*;
import TEMP.*;
import AST.AST_STMT.AST_STMT;
import IR.*;
public class AST_EXP_FUNC extends AST_EXP {
    public AST_VAR v;
    String id;
    public AST_OPTEXPS_LIST oe;

    public AST_EXP_FUNC(AST_VAR v, String id, AST_OPTEXPS_LIST oe) {
        this.v = v;
        this.id = id;
        this.oe = oe;
    }
    public AST_EXP_FUNC( String id, AST_OPTEXPS_LIST oe) {
        this.v = null;
        this.id = id;
        this.oe = oe;
    }

    public TYPE SemantMe() {
        TYPE type;
        if (v != null) { //  v.id(oe)
            TYPE varType = v.SemantMe();
            if (!(varType instanceof TYPE_CLASS)) {
                System.out.println("var is not a class type");
                throw new SemanticErrorException("" + lineNumber);
            }
            type = ((TYPE_CLASS) varType).getFunctionDeclaredInAncestors(id);
        }
        else { // id(oe)
            type = SYMBOL_TABLE.getInstance().find(id);
        }
        if (type == null || !(type instanceof TYPE_FUNCTION)) {
            System.out.println("The function name we are trying to call doesn't exist or is not of type function");
            throw new SemanticErrorException("" + lineNumber);
        }
        TYPE_FUNCTION func_type = (TYPE_FUNCTION) type;
        TYPE_LIST params = func_type.params;
        TYPE_LIST thisParams = oe.SemantMe();
        while(params != null && params.head != null) {
            if (thisParams.head == null) {
                System.out.println("We did not pass enough arguments to the function!");
                throw new SemanticErrorException("" + lineNumber);
            }
            if(!TYPE.isMatching(thisParams.head, params.head)){
                System.out.println("This param type doesn't match the given function's param type.");
                throw new SemanticErrorException("" + lineNumber);
            }
            params = params.tail;
            thisParams = thisParams.tail;
        }
        if (thisParams != null){
            System.out.println("We passed more arguments than the function requires!");
            throw new SemanticErrorException("" + lineNumber);
        }
        return func_type.returnType;
    }
    public TEMP IRme() {
        TEMP_LIST args = oe != null ? oe.IRme() : null;
        TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
        if (v != null) {
            TEMP obj = v.IRme();
            IR.getInstance().Add_IRcommand(new IRcommand_CallMethod(t, obj, id, args));
        } else {
            IR.getInstance().Add_IRcommand(new IRcommand_CallFunction(t, id, args));
        }
        return t;
    }

}