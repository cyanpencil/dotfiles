// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Type
{

    private Type(int i, char ac[], int j, int k)
    {
        a = i;
        b = ac;
        c = j;
        d = k;
    }

    public static Type getType(String s)
    {
        return a(s.toCharArray(), 0);
    }

    public static Type getObjectType(String s)
    {
        s = s.toCharArray();
        return new Type(s[0] != '[' ? 10 : 9, s, 0, s.length);
    }

    public static Type getMethodType(String s)
    {
        return a(s.toCharArray(), 0);
    }

    public static transient Type getMethodType(Type type, Type atype[])
    {
        return getType(getMethodDescriptor(type, atype));
    }

    public static Type getType(Class class1)
    {
        if(class1.isPrimitive())
        {
            if(class1 == Integer.TYPE)
                return INT_TYPE;
            if(class1 == Void.TYPE)
                return VOID_TYPE;
            if(class1 == Boolean.TYPE)
                return BOOLEAN_TYPE;
            if(class1 == Byte.TYPE)
                return BYTE_TYPE;
            if(class1 == Character.TYPE)
                return CHAR_TYPE;
            if(class1 == Short.TYPE)
                return SHORT_TYPE;
            if(class1 == Double.TYPE)
                return DOUBLE_TYPE;
            if(class1 == Float.TYPE)
                return FLOAT_TYPE;
            else
                return LONG_TYPE;
        } else
        {
            return getType(getDescriptor(class1));
        }
    }

    public static Type getType(Constructor constructor)
    {
        return getType(getConstructorDescriptor(constructor));
    }

    public static Type getType(Method method)
    {
        return getType(getMethodDescriptor(method));
    }

    public static Type[] getArgumentTypes(String s)
    {
        s = s.toCharArray();
        int i = 1;
        int j = 0;
        do
        {
            char c1;
            if((c1 = s[i++]) == ')')
                break;
            if(c1 == 'L')
            {
                while(s[i++] != ';') ;
                j++;
            } else
            if(c1 != '[')
                j++;
        } while(true);
        Type atype[] = new Type[j];
        i = 1;
        for(int k = 0; s[i] != ')'; k++)
        {
            atype[k] = a(s, i);
            i += atype[k].d + (atype[k].a != 10 ? 0 : 2);
        }

        return atype;
    }

    public static Type[] getArgumentTypes(Method method)
    {
        Type atype[] = new Type[(method = method.getParameterTypes()).length];
        for(int i = method.length - 1; i >= 0; i--)
            atype[i] = getType(method[i]);

        return atype;
    }

    public static Type getReturnType(String s)
    {
        char ac[];
        return a(ac = s.toCharArray(), s.indexOf(')') + 1);
    }

    public static Type getReturnType(Method method)
    {
        return getType(method.getReturnType());
    }

    public static int getArgumentsAndReturnSizes(String s)
    {
        int i = 1;
        int j = 1;
        do
        {
            char c1;
            if((c1 = s.charAt(j++)) == ')')
            {
                c1 = s.charAt(j);
                return i << 2 | (c1 != 'V' ? c1 != 'D' && c1 != 'J' ? 1 : 2 : 0);
            }
            if(c1 == 'L')
            {
                while(s.charAt(j++) != ';') ;
                i++;
            } else
            if(c1 == '[')
            {
                while((c1 = s.charAt(j)) == '[') 
                    j++;
                if(c1 == 'D' || c1 == 'J')
                    i--;
            } else
            if(c1 == 'D' || c1 == 'J')
                i += 2;
            else
                i++;
        } while(true);
    }

    private static Type a(char ac[], int i)
    {
        switch(ac[i])
        {
        case 86: // 'V'
            return VOID_TYPE;

        case 90: // 'Z'
            return BOOLEAN_TYPE;

        case 67: // 'C'
            return CHAR_TYPE;

        case 66: // 'B'
            return BYTE_TYPE;

        case 83: // 'S'
            return SHORT_TYPE;

        case 73: // 'I'
            return INT_TYPE;

        case 70: // 'F'
            return FLOAT_TYPE;

        case 74: // 'J'
            return LONG_TYPE;

        case 68: // 'D'
            return DOUBLE_TYPE;

        case 91: // '['
            int j;
            for(j = 1; ac[i + j] == '['; j++);
            if(ac[i + j] == 'L')
                for(j++; ac[i + j] != ';'; j++);
            return new Type(9, ac, i, j + 1);

        case 76: // 'L'
            int k;
            for(k = 1; ac[i + k] != ';'; k++);
            return new Type(10, ac, i + 1, k - 1);

        case 69: // 'E'
        case 71: // 'G'
        case 72: // 'H'
        case 75: // 'K'
        case 77: // 'M'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 82: // 'R'
        case 84: // 'T'
        case 85: // 'U'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        default:
            return new Type(11, ac, i, ac.length - i);
        }
    }

    public int getSort()
    {
        return a;
    }

    public int getDimensions()
    {
        int i;
        for(i = 1; b[c + i] == '['; i++);
        return i;
    }

    public Type getElementType()
    {
        return a(b, c + getDimensions());
    }

    public String getClassName()
    {
        switch(a)
        {
        case 0: // '\0'
            return "void";

        case 1: // '\001'
            return "boolean";

        case 2: // '\002'
            return "char";

        case 3: // '\003'
            return "byte";

        case 4: // '\004'
            return "short";

        case 5: // '\005'
            return "int";

        case 6: // '\006'
            return "float";

        case 7: // '\007'
            return "long";

        case 8: // '\b'
            return "double";

        case 9: // '\t'
            StringBuffer stringbuffer = new StringBuffer(getElementType().getClassName());
            for(int i = getDimensions(); i > 0; i--)
                stringbuffer.append("[]");

            return stringbuffer.toString();

        case 10: // '\n'
            return (new String(b, c, d)).replace('/', '.');
        }
        return null;
    }

    public String getInternalName()
    {
        return new String(b, c, d);
    }

    public Type[] getArgumentTypes()
    {
        return getArgumentTypes(getDescriptor());
    }

    public Type getReturnType()
    {
        return getReturnType(getDescriptor());
    }

    public int getArgumentsAndReturnSizes()
    {
        return getArgumentsAndReturnSizes(getDescriptor());
    }

    public String getDescriptor()
    {
        StringBuffer stringbuffer = new StringBuffer();
        a(stringbuffer);
        return stringbuffer.toString();
    }

    public static transient String getMethodDescriptor(Type type, Type atype[])
    {
        StringBuffer stringbuffer;
        (stringbuffer = new StringBuffer()).append('(');
        for(int i = 0; i < atype.length; i++)
            atype[i].a(stringbuffer);

        stringbuffer.append(')');
        type.a(stringbuffer);
        return stringbuffer.toString();
    }

    private void a(StringBuffer stringbuffer)
    {
        if(b == null)
        {
            stringbuffer.append((char)((c & 0xff000000) >>> 24));
            return;
        }
        if(a == 10)
        {
            stringbuffer.append('L');
            stringbuffer.append(b, c, d);
            stringbuffer.append(';');
            return;
        } else
        {
            stringbuffer.append(b, c, d);
            return;
        }
    }

    public static String getInternalName(Class class1)
    {
        return class1.getName().replace('.', '/');
    }

    public static String getDescriptor(Class class1)
    {
        StringBuffer stringbuffer;
        a(stringbuffer = new StringBuffer(), class1);
        return stringbuffer.toString();
    }

    public static String getConstructorDescriptor(Constructor constructor)
    {
        constructor = constructor.getParameterTypes();
        StringBuffer stringbuffer;
        (stringbuffer = new StringBuffer()).append('(');
        for(int i = 0; i < constructor.length; i++)
            a(stringbuffer, constructor[i]);

        return stringbuffer.append(")V").toString();
    }

    public static String getMethodDescriptor(Method method)
    {
        Class aclass[] = method.getParameterTypes();
        StringBuffer stringbuffer;
        (stringbuffer = new StringBuffer()).append('(');
        for(int i = 0; i < aclass.length; i++)
            a(stringbuffer, aclass[i]);

        stringbuffer.append(')');
        a(stringbuffer, method.getReturnType());
        return stringbuffer.toString();
    }

    private static void a(StringBuffer stringbuffer, Class class1)
    {
        class1 = class1;
        do
        {
            if(class1.isPrimitive())
            {
                if(class1 == Integer.TYPE)
                    class1 = 73;
                else
                if(class1 == Void.TYPE)
                    class1 = 86;
                else
                if(class1 == Boolean.TYPE)
                    class1 = 90;
                else
                if(class1 == Byte.TYPE)
                    class1 = 66;
                else
                if(class1 == Character.TYPE)
                    class1 = 67;
                else
                if(class1 == Short.TYPE)
                    class1 = 83;
                else
                if(class1 == Double.TYPE)
                    class1 = 68;
                else
                if(class1 == Float.TYPE)
                    class1 = 70;
                else
                    class1 = 74;
                stringbuffer.append(class1);
                return;
            }
            if(!class1.isArray())
                break;
            stringbuffer.append('[');
            class1 = class1.getComponentType();
        } while(true);
        stringbuffer.append('L');
        int i = (class1 = class1.getName()).length();
        for(int j = 0; j < i; j++)
        {
            char c1 = class1.charAt(j);
            stringbuffer.append(c1 != '.' ? c1 : '/');
        }

        stringbuffer.append(';');
    }

    public int getSize()
    {
        if(b == null)
            return c & 0xff;
        else
            return 1;
    }

    public int getOpcode(int i)
    {
        if(i == 46 || i == 79)
            return i + (b != null ? 4 : c >> 8 & 0xff);
        else
            return i + (b != null ? 4 : c >> 16 & 0xff);
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof Type))
            return false;
        obj = (Type)obj;
        if(a != ((Type) (obj)).a)
            return false;
        if(a >= 9)
        {
            if(d != ((Type) (obj)).d)
                return false;
            int i = c;
            int j = ((Type) (obj)).c;
            for(int k = i + d; i < k;)
            {
                if(b[i] != ((Type) (obj)).b[j])
                    return false;
                i++;
                j++;
            }

        }
        return true;
    }

    public int hashCode()
    {
        int i = 13 * a;
        if(a >= 9)
        {
            int j;
            for(int k = (j = c) + d; j < k; j++)
                i = 17 * (i + b[j]);

        }
        return i;
    }

    public String toString()
    {
        return getDescriptor();
    }

    static void _clinit_()
    {
    }

    public static final int VOID = 0;
    public static final int BOOLEAN = 1;
    public static final int CHAR = 2;
    public static final int BYTE = 3;
    public static final int SHORT = 4;
    public static final int INT = 5;
    public static final int FLOAT = 6;
    public static final int LONG = 7;
    public static final int DOUBLE = 8;
    public static final int ARRAY = 9;
    public static final int OBJECT = 10;
    public static final int METHOD = 11;
    public static final Type VOID_TYPE = new Type(0, null, 0x56050000, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, null, 0x5a000501, 1);
    public static final Type CHAR_TYPE = new Type(2, null, 0x43000601, 1);
    public static final Type BYTE_TYPE = new Type(3, null, 0x42000501, 1);
    public static final Type SHORT_TYPE = new Type(4, null, 0x53000701, 1);
    public static final Type INT_TYPE = new Type(5, null, 0x49000001, 1);
    public static final Type FLOAT_TYPE = new Type(6, null, 0x46020201, 1);
    public static final Type LONG_TYPE = new Type(7, null, 0x4a010102, 1);
    public static final Type DOUBLE_TYPE = new Type(8, null, 0x44030302, 1);
    private final int a;
    private final char b[];
    private final int c;
    private final int d;

    static 
    {
        _clinit_();
    }
}
