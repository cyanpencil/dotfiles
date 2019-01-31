// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Desc.java

package javassist.runtime;


public class Desc
{

            public Desc()
            {
            }

            private static Class getClassObject(String s)
                throws ClassNotFoundException
            {
/*  40*/        if(useContextClassLoader)
/*  41*/            return Class.forName(s, true, Thread.currentThread().getContextClassLoader());
/*  43*/        else
/*  43*/            return Class.forName(s);
            }

            public static Class getClazz(String s)
            {
/*  52*/        return getClassObject(s);
                ClassNotFoundException classnotfoundexception;
/*  54*/        classnotfoundexception;
/*  55*/        throw new RuntimeException((new StringBuilder("$class: internal error, could not find class '")).append(s).append("' (Desc.useContextClassLoader: ").append(Boolean.toString(useContextClassLoader)).append(")").toString(), classnotfoundexception);
            }

            public static Class[] getParams(String s)
            {
/*  67*/        if(s.charAt(0) != '(')
/*  68*/            throw new RuntimeException("$sig: internal error");
/*  70*/        else
/*  70*/            return getType(s, s.length(), 1, 0);
            }

            public static Class getType(String s)
            {
/*  78*/        if((s = getType(s, s.length(), 0, 0)) == null || s.length != 1)
/*  80*/            throw new RuntimeException("$type: internal error");
/*  82*/        else
/*  82*/            return s[0];
            }

            private static Class[] getType(String s, int i, int j, int k)
            {
/*  88*/        if(j >= i)
/*  89*/            return new Class[k];
                char c;
                Class class1;
/*  91*/        switch(c = s.charAt(j))
                {
/*  94*/        case 90: // 'Z'
/*  94*/            class1 = Boolean.TYPE;
                    break;

/*  97*/        case 67: // 'C'
/*  97*/            class1 = Character.TYPE;
                    break;

/* 100*/        case 66: // 'B'
/* 100*/            class1 = Byte.TYPE;
                    break;

/* 103*/        case 83: // 'S'
/* 103*/            class1 = Short.TYPE;
                    break;

/* 106*/        case 73: // 'I'
/* 106*/            class1 = Integer.TYPE;
                    break;

/* 109*/        case 74: // 'J'
/* 109*/            class1 = Long.TYPE;
                    break;

/* 112*/        case 70: // 'F'
/* 112*/            class1 = Float.TYPE;
                    break;

/* 115*/        case 68: // 'D'
/* 115*/            class1 = Double.TYPE;
                    break;

/* 118*/        case 86: // 'V'
/* 118*/            class1 = Void.TYPE;
                    break;

/* 122*/        case 76: // 'L'
/* 122*/        case 91: // '['
/* 122*/            return getClassType(s, i, j, k);

/* 124*/        case 69: // 'E'
/* 124*/        case 71: // 'G'
/* 124*/        case 72: // 'H'
/* 124*/        case 75: // 'K'
/* 124*/        case 77: // 'M'
/* 124*/        case 78: // 'N'
/* 124*/        case 79: // 'O'
/* 124*/        case 80: // 'P'
/* 124*/        case 81: // 'Q'
/* 124*/        case 82: // 'R'
/* 124*/        case 84: // 'T'
/* 124*/        case 85: // 'U'
/* 124*/        case 87: // 'W'
/* 124*/        case 88: // 'X'
/* 124*/        case 89: // 'Y'
/* 124*/        default:
/* 124*/            return new Class[k];
                }
/* 127*/        (s = getType(s, i, j + 1, k + 1))[k] = class1;
/* 129*/        return s;
            }

            private static Class[] getClassType(String s, int i, int j, int k)
            {
                int l;
/* 134*/        for(l = j; s.charAt(l) == '['; l++);
/* 138*/        if(s.charAt(l) == 'L' && (l = s.indexOf(';', l)) < 0)
/* 141*/            throw new IndexOutOfBoundsException("bad descriptor");
/* 145*/        if(s.charAt(j) == 'L')
/* 146*/            j = s.substring(j + 1, l);
/* 148*/        else
/* 148*/            j = s.substring(j, l + 1);
/* 150*/        s = getType(s, i, l + 1, k + 1);
/* 152*/        try
                {
/* 152*/            s[k] = getClassObject(j.replace('/', '.'));
                }
                // Misplaced declaration of an exception variable
/* 154*/        catch(String s)
                {
/* 156*/            throw new RuntimeException(s.getMessage());
                }
/* 159*/        return s;
            }

            public static boolean useContextClassLoader = false;

}
