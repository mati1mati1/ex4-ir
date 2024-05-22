package AST.AST_DEC;
import AST.AST_LIST.AST_OPTPARAMS_LIST;
import AST.AST_LIST.AST_PARAMS_LIST;
import AST.AST_LIST.AST_STMT_LIST;
import AST.AST_TYPE.AST_TYPE;
import TYPES.*;
import SYMBOL_TABLE.SYMBOL_TABLE.ScopeType;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;

public class AST_FUNCDEC extends AST_DEC
{
    public AST_TYPE t;
    String id;
    public AST_OPTPARAMS_LIST o;
    public AST_STMT_LIST sl;

    public AST_FUNCDEC(AST_TYPE t, String id, AST_OPTPARAMS_LIST o, AST_STMT_LIST sl) {
        this.t = t;
        this.id = id;
        this.o = o;
        this.sl = sl;
    }
    @Override
    public TYPE SemantMe() {
        // check if already someone declared a func with this name in the current scope
        if (SYMBOL_TABLE.getInstance().isInScope(this.id)){
            throw new SemanticErrorException(""+ lineNumber);
        }
        // check the ID is not with an illegal name
        if((!SYMBOL_TABLE.getInstance().isValidName(id)) || id.equals("PrintInt") || id.equals("PrintString")){
            System.out.println("The id name is equal to string/int/void/PrintInt/PrintString");
            throw new SemanticErrorException("" + lineNumber);
        }
        //check we are not in another function or in an if/while block
        ScopeType scope_t = SYMBOL_TABLE.getInstance().getTypeOfScope();
        if((scope_t.equals(ScopeType.If_While_Block)) || (scope_t.equals(ScopeType.Function))){
            throw new SemanticErrorException(""+ lineNumber);
        }
        TYPE return_type = t.SemantMe();
        TYPE father_class = SYMBOL_TABLE.getInstance().GetCurrentClassParent();
        if(father_class!= null){
            checkOverloadingAndShadowing((TYPE_CLASS) father_class);
        }
        SYMBOL_TABLE.getInstance().beginScope(this.id,ScopeType.Function);

        TYPE_LIST paramsTypesList = o.SemantMe();
        TYPE_FUNCTION this_func = new TYPE_FUNCTION(return_type,id,paramsTypesList);

        SYMBOL_TABLE.getInstance().enter(this.id,this_func);

        sl.SemantMe();

        SYMBOL_TABLE.getInstance().endScope();
        SYMBOL_TABLE.getInstance().enter(this.id,this_func);

        return this_func;
    }

    public void checkOverloadingAndShadowing(TYPE_CLASS father) {
        TYPE father_func_t = father.checkIfClassMemberExistsInAncestors(id);
        if (father_func_t != null) { // the function name ID exists somewhere in the ancestors
            if (!(father_func_t instanceof TYPE_FUNCTION)) { // the function name ID exists in ancestors as a class field
                System.out.println("Illegal to give a function the same name as a previously defined class field.");
                throw new SemanticErrorException("" + lineNumber);
            }
            else {
                if (((TYPE_FUNCTION) father_func_t).returnType == TYPE_VOID.getInstance() && (t.SemantMe() != TYPE_VOID.getInstance())) {
                    System.out.println("Illegal to overload methods");
                    throw new SemanticErrorException("" + lineNumber);
                }
                else if (!((TYPE_FUNCTION) father_func_t).returnType.name.equals(this.t.SemantMe().name)) {
                    System.out.println("Illegal to overload methods");
                    throw new SemanticErrorException("" + lineNumber);
                }
                TYPE_LIST func_params = ((TYPE_FUNCTION) father_func_t).params;
                if(func_params != null && func_params.head != null && o != null && o.t != null) {
                    TYPE first_param = o.t.SemantMe();
                    if(!TYPE.isMatching(first_param,((TYPE_FUNCTION) father_func_t).params.head)) {
                        System.out.println("Illegal to overload methods");
                        throw new SemanticErrorException("" + lineNumber);
                    }
                    func_params = func_params.tail;

                }
                AST_PARAMS_LIST params = o.p;
                while (func_params != null && func_params.head != null && params != null) {
                    if(!TYPE.isMatching(params.t.SemantMe(),((TYPE_FUNCTION) father_func_t).params.head)) {
                        System.out.println("Illegal to overload methods");
                        throw new SemanticErrorException("" + lineNumber);
                    }
                    func_params = func_params.tail;
                    params = params.p;
                }
                if(func_params != null && func_params.head != null || params != null) {
                    System.out.println("Illegal to overload methods");
                    throw new SemanticErrorException("" + lineNumber);
                }

            }
        }
    }
}


