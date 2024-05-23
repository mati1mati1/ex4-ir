package AST.AST_DEC;
import TYPES.*;
import AST.AST_STMT.AST_STMT;
import IR.*;

public abstract class AST_VARDEC extends AST_DEC
{
    public abstract TYPE SemantMe();
}