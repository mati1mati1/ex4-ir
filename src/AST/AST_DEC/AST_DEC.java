package AST.AST_DEC;

import AST.AST_Node;
import TYPES.*;
import AST.AST_STMT.AST_STMT;
import IR.*;
public abstract class AST_DEC extends AST_Node
{
    public abstract TYPE SemantMe();
}