package TYPES;

public class TYPE_CLASS extends TYPE
{
	/*********************************************************************/
	/* If this class does not extend a father class this should be null  */
	/*********************************************************************/
	public TYPE_CLASS father;

	/**************************************************/
	/* Gather up all data members in one place        */
	/* Note that data members coming from the AST are */
	/* packed together with the class methods         */
	/**************************************************/
	public TYPE_LIST data_members;
	
	/****************/
	/* CTROR(S) ... */
	/****************/
	public TYPE_CLASS(TYPE_CLASS father,String name,TYPE_LIST data_members)
	{
		this.name = name;
		this.father = father;
		this.data_members = data_members;
	}

	public boolean isClass(){ return true;}

	//TODO: go over this, maybe change
	public boolean isSuperclass(TYPE_CLASS other) {
		for (; other.father != null; other = other.father) {
			if (this.equals(other.father)) {
				return true;
			}
		}
		return false;
	}

	// For a specific member we would like to check if this member was declared
	// in the current class or in one of the class ancestors
	public TYPE checkIfClassMemberExistsInAncestors(String member) {
		TYPE_LIST members = data_members;
		while (members != null && members.head != null) {
			if (members.head instanceof TYPE_CLASS_VAR_DEC) {
				if (((TYPE_CLASS_VAR_DEC)members.head).name.equals(member)) {
					return members.head;
				}
			} else {
				if (((TYPE_FUNCTION)members.head).name.equals(member)) {
					return members.head;
				}
			}
			members = members.tail;
		}
		if (father != null) {
			return father.checkIfClassMemberExistsInAncestors(member);
		}
		return null;
	}

	// Check if a function name exists in the class or it's ancestors
	public TYPE_FUNCTION getFunctionDeclaredInAncestors(String funcName) {
		TYPE classMember = this.checkIfClassMemberExistsInAncestors(funcName);
		if (classMember != null && (classMember instanceof TYPE_FUNCTION)) {
			return (TYPE_FUNCTION) classMember;
		}
		return null;
	}

}
