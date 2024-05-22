package TYPES;
import SYMBOL_TABLE.SYMBOL_TABLE.ScopeType;
public class TYPE_FOR_SCOPE_BOUNDARIES extends TYPE
{
	/****************/
	/* CTROR(S) ... */
	/****************/
	public String name;
	public ScopeType type = null;
	public TYPE_FOR_SCOPE_BOUNDARIES(String name, ScopeType type)
	{
		this.name = name;
		this.type = type;
	}
	public TYPE_FOR_SCOPE_BOUNDARIES(String name)
	{
		this.name = name;
	}
}
