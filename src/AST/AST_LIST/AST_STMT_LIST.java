package AST.AST_LIST;

import AST.AST_STMT.AST_STMT;
import TYPES.*;

public class AST_STMT_LIST extends AST_LIST
{
	public AST_STMT head;
	public AST_STMT_LIST tail;

	public AST_STMT_LIST(AST_STMT head,AST_STMT_LIST tail)
	{
		this.head = head;
		this.tail = tail;
	}

	@Override
	public TYPE_LIST SemantMe() {
		if (tail == null) {
			return new TYPE_LIST(head.SemantMe(), null);
		} else {
			return new TYPE_LIST(head.SemantMe(), tail.SemantMe());
		}
	}
}
