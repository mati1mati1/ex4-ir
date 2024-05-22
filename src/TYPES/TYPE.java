package TYPES;

public abstract class TYPE
{
	/******************************/
	/*  Every type has a name ... */
	/******************************/
	public String name;

	/*************/
	/* isClass() */
	/*************/
	public boolean isClass(){ return false;}

	/*************/
	/* isArray() */
	/*************/
	public boolean isArray(){ return false;}

	public static boolean isMatching(TYPE thisType, TYPE otherType) {
		if (thisType == null || otherType == null) {
			return false;
		}
		return thisType.equals(otherType) || TYPE_NIL.nil_match(otherType, thisType) ||
				(thisType instanceof TYPE_CLASS && otherType instanceof TYPE_CLASS &&
						((TYPE_CLASS) otherType).isSuperclass((TYPE_CLASS) thisType));
	}
}
