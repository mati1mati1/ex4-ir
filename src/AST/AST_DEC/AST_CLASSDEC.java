package AST.AST_DEC;

import AST.AST_LIST.AST_CFIELDLIST;
import AST.AST_OPT.AST_OPTEXTENDS;
import TYPES.*;
import SYMBOL_TABLE.SYMBOL_TABLE.ScopeType;
import SYMBOL_TABLE.*;
import AST.SemanticErrorException;
import AST.AST_STMT.AST_STMT;
import IR.*;

public class AST_CLASSDEC extends AST_DEC
{
    String id;
    public AST_OPTEXTENDS o;
    public AST_CFIELDLIST cl;

    public AST_CLASSDEC(String id, AST_OPTEXTENDS o, AST_CFIELDLIST cl) {
        this.id = id;
        this.o = o;
        this.cl = cl;
    }

    @Override
    public TYPE SemantMe() {

        // check the ID is not with an illegal name
        if(!SYMBOL_TABLE.getInstance().isValidName(id)){
            System.out.println("The id name is equal to string/int/void");
            throw new SemanticErrorException("" + lineNumber);
        }
        TYPE_CLASS this_clazz = new TYPE_CLASS(null, id, null);

        SYMBOL_TABLE.getInstance().enter(id, this_clazz);

        SYMBOL_TABLE.getInstance().beginScope(id, ScopeType.Class);
        TYPE_CLASS father_type = (TYPE_CLASS)o.SemantMe();
        TYPE_LIST father_cl = null;
        if (father_type != null) { //add father data_members
            System.out.println(father_type.name);
            father_cl = father_type.data_members;
            this_clazz.data_members = father_cl;
            this_clazz.father = father_type;
        }
        TYPE_LIST cl_type = cl.SemantMe();
        if (father_type != null) {
            TYPE_LIST current = cl_type;
            while (current.tail != null) {
                current = current.tail;
            }
            current.tail = father_cl;
        }
        this_clazz.data_members = cl_type;
        TYPE_LIST members = this_clazz.data_members;
        TYPE_CLASS class_type = new TYPE_CLASS(father_type, id, this_clazz.data_members);
        SYMBOL_TABLE.getInstance().enter(id, class_type);

        SYMBOL_TABLE.getInstance().endScope();

        TYPE clazz = SYMBOL_TABLE.getInstance().find(id);
        ((TYPE_CLASS)clazz).data_members = members; // add data members

        return null;
    }
}