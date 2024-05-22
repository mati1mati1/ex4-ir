package TYPES;

public class TYPE_NIL extends TYPE
{
    private static TYPE_NIL instance = null;

    protected TYPE_NIL() {}

    public static TYPE_NIL getInstance()
    {
        if (instance == null)
        {
            instance = new TYPE_NIL();
            instance.name = "nil";
        }
        return instance;
    }

    public static boolean nillable(TYPE type) {
        return type.isClass() || type.isArray() || type instanceof TYPE_NIL;
    }

    public static boolean nil_match(TYPE nillableType, TYPE matchedType) {
        return (TYPE_NIL.nillable(nillableType) && matchedType == TYPE_NIL.getInstance());
    }
}