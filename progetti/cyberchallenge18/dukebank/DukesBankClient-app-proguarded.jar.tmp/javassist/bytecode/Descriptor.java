// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Descriptor.java

package javassist.bytecode;

import java.util.Map;
import javassist.*;

public class Descriptor
{
    public static class Iterator
    {

                public boolean hasNext()
                {
/* 816*/            return index < desc.length();
                }

                public boolean isParameter()
                {
/* 822*/            return param;
                }

                public char currentChar()
                {
/* 827*/            return desc.charAt(curPos);
                }

                public boolean is2byte()
                {
                    char c;
/* 833*/            return (c = currentChar()) == 'D' || c == 'J';
                }

                public int next()
                {
/* 842*/            int i = index;
                    char c;
/* 843*/            if((c = desc.charAt(i)) == '(')
                    {
/* 845*/                index++;
/* 846*/                c = desc.charAt(++i);
/* 847*/                param = true;
                    }
/* 850*/            if(c == ')')
                    {
/* 851*/                index++;
/* 852*/                c = desc.charAt(++i);
/* 853*/                param = false;
                    }
/* 856*/            for(; c == '['; c = desc.charAt(++i));
/* 859*/            if(c == 'L')
                    {
/* 860*/                if((i = desc.indexOf(';', i) + 1) <= 0)
/* 862*/                    throw new IndexOutOfBoundsException("bad descriptor");
                    } else
                    {
/* 865*/                i++;
                    }
/* 867*/            curPos = index;
/* 868*/            index = i;
/* 869*/            return curPos;
                }

                private String desc;
                private int index;
                private int curPos;
                private boolean param;

                public Iterator(String s)
                {
/* 807*/            desc = s;
/* 808*/            index = curPos = 0;
/* 809*/            param = false;
                }
    }

    static class PrettyPrinter
    {

                static String toString(String s)
                {
/* 743*/            StringBuffer stringbuffer = new StringBuffer();
/* 744*/            if(s.charAt(0) == '(')
                    {
/* 745*/                int i = 1;
/* 746*/                stringbuffer.append('(');
/* 747*/                for(; s.charAt(i) != ')'; i = readType(stringbuffer, i, s))
/* 748*/                    if(i > 1)
/* 749*/                        stringbuffer.append(',');

/* 754*/                stringbuffer.append(')');
                    } else
                    {
/* 757*/                readType(stringbuffer, 0, s);
                    }
/* 759*/            return stringbuffer.toString();
                }

                static int readType(StringBuffer stringbuffer, int i, String s)
                {
/* 763*/            char c = s.charAt(i);
/* 764*/            int j = 0;
/* 765*/            for(; c == '['; c = s.charAt(++i))
/* 766*/                j++;

/* 770*/            if(c == 'L')
                    {
/* 772*/                while((c = s.charAt(++i)) != ';') 
                        {
/* 776*/                    if(c == '/')
/* 777*/                        c = '.';
/* 779*/                    stringbuffer.append(c);
                        }
                    } else
                    {
/* 782*/                s = Descriptor.toPrimitiveClass(c);
/* 783*/                stringbuffer.append(s.getName());
                    }
/* 786*/            while(j-- > 0) 
/* 787*/                stringbuffer.append("[]");
/* 789*/            return i + 1;
                }

                PrettyPrinter()
                {
                }
    }


            public Descriptor()
            {
            }

            public static String toJvmName(String s)
            {
/*  39*/        return s.replace('.', '/');
            }

            public static String toJavaName(String s)
            {
/*  52*/        return s.replace('/', '.');
            }

            public static String toJvmName(CtClass ctclass)
            {
/*  60*/        if(ctclass.isArray())
/*  61*/            return of(ctclass);
/*  63*/        else
/*  63*/            return toJvmName(ctclass.getName());
            }

            public static String toClassName(String s)
            {
/*  72*/        int i = 0;
/*  73*/        int j = 0;
                int k;
/*  74*/        for(k = s.charAt(0); k == 91; k = s.charAt(++j))
/*  76*/            i++;

/*  81*/        if(k == 76)
                {
/*  82*/            int l = s.indexOf(';', j++);
/*  83*/            k = s.substring(j, l).replace('/', '.');
/*  84*/            j = l;
                } else
/*  86*/        if(k == 86)
/*  87*/            k = "void";
/*  88*/        else
/*  88*/        if(k == 73)
/*  89*/            k = "int";
/*  90*/        else
/*  90*/        if(k == 66)
/*  91*/            k = "byte";
/*  92*/        else
/*  92*/        if(k == 74)
/*  93*/            k = "long";
/*  94*/        else
/*  94*/        if(k == 68)
/*  95*/            k = "double";
/*  96*/        else
/*  96*/        if(k == 70)
/*  97*/            k = "float";
/*  98*/        else
/*  98*/        if(k == 67)
/*  99*/            k = "char";
/* 100*/        else
/* 100*/        if(k == 83)
/* 101*/            k = "short";
/* 102*/        else
/* 102*/        if(k == 90)
/* 103*/            k = "boolean";
/* 105*/        else
/* 105*/            throw new RuntimeException((new StringBuilder("bad descriptor: ")).append(s).toString());
/* 107*/        if(j + 1 != s.length())
/* 108*/            throw new RuntimeException((new StringBuilder("multiple descriptors?: ")).append(s).toString());
/* 110*/        if(i == 0)
/* 111*/            return k;
/* 113*/        StringBuffer stringbuffer = new StringBuffer(k);
/* 115*/        do
/* 115*/            stringbuffer.append("[]");
/* 116*/        while(--i > 0);
/* 118*/        return stringbuffer.toString();
            }

            public static String of(String s)
            {
/* 126*/        if(s.equals("void"))
/* 127*/            return "V";
/* 128*/        if(s.equals("int"))
/* 129*/            return "I";
/* 130*/        if(s.equals("byte"))
/* 131*/            return "B";
/* 132*/        if(s.equals("long"))
/* 133*/            return "J";
/* 134*/        if(s.equals("double"))
/* 135*/            return "D";
/* 136*/        if(s.equals("float"))
/* 137*/            return "F";
/* 138*/        if(s.equals("char"))
/* 139*/            return "C";
/* 140*/        if(s.equals("short"))
/* 141*/            return "S";
/* 142*/        if(s.equals("boolean"))
/* 143*/            return "Z";
/* 145*/        else
/* 145*/            return (new StringBuilder("L")).append(toJvmName(s)).append(";").toString();
            }

            public static String rename(String s, String s1, String s2)
            {
/* 159*/        if(s.indexOf(s1) < 0)
/* 160*/            return s;
/* 162*/        StringBuffer stringbuffer = new StringBuffer();
/* 163*/        int i = 0;
/* 164*/        int j = 0;
/* 166*/label0:
/* 166*/        do
/* 166*/            do
                    {
/* 166*/                if((j = s.indexOf('L', j)) < 0)
/* 169*/                    break label0;
/* 169*/                if(!s.startsWith(s1, j + 1) || s.charAt(j + s1.length() + 1) != ';')
/* 171*/                    continue label0;
/* 171*/                stringbuffer.append(s.substring(i, j));
/* 172*/                stringbuffer.append('L');
/* 173*/                stringbuffer.append(s2);
/* 174*/                stringbuffer.append(';');
/* 175*/                i = j = j + s1.length() + 2;
                    } while(true);
/* 179*/        while((j = s.indexOf(';', j) + 1) > 0);
/* 184*/        if(i == 0)
/* 185*/            return s;
/* 187*/        j = s.length();
/* 188*/        if(i < j)
/* 189*/            stringbuffer.append(s.substring(i, j));
/* 191*/        return stringbuffer.toString();
            }

            public static String rename(String s, Map map)
            {
/* 204*/        if(map == null)
/* 205*/            return s;
/* 207*/        StringBuffer stringbuffer = new StringBuffer();
/* 208*/        int i = 0;
/* 209*/        int j = 0;
/* 211*/        do
                {
                    int k;
                    int i1;
/* 211*/            if((k = s.indexOf('L', j)) < 0 || (i1 = s.indexOf(';', k)) < 0)
/* 219*/                break;
/* 219*/            j = i1 + 1;
/* 220*/            String s1 = s.substring(k + 1, i1);
/* 221*/            if((s1 = (String)map.get(s1)) != null)
                    {
/* 223*/                stringbuffer.append(s.substring(i, k));
/* 224*/                stringbuffer.append('L');
/* 225*/                stringbuffer.append(s1);
/* 226*/                stringbuffer.append(';');
/* 227*/                i = j;
                    }
                } while(true);
/* 231*/        if(i == 0)
/* 232*/            return s;
/* 234*/        int l = s.length();
/* 235*/        if(i < l)
/* 236*/            stringbuffer.append(s.substring(i, l));
/* 238*/        return stringbuffer.toString();
            }

            public static String of(CtClass ctclass)
            {
                StringBuffer stringbuffer;
/* 246*/        toDescriptor(stringbuffer = new StringBuffer(), ctclass);
/* 248*/        return stringbuffer.toString();
            }

            private static void toDescriptor(StringBuffer stringbuffer, CtClass ctclass)
            {
/* 252*/        if(ctclass.isArray())
                {
/* 253*/            stringbuffer.append('[');
/* 255*/            try
                    {
/* 255*/                toDescriptor(stringbuffer, ctclass.getComponentType());
/* 262*/                return;
                    }
/* 257*/            catch(NotFoundException _ex)
                    {
/* 258*/                stringbuffer.append('L');
                    }
/* 259*/            ctclass = ctclass.getName();
/* 260*/            stringbuffer.append(toJvmName(ctclass.substring(0, ctclass.length() - 2)));
/* 261*/            stringbuffer.append(';');
/* 262*/            return;
                }
/* 264*/        if(ctclass.isPrimitive())
                {
/* 265*/            ctclass = (CtPrimitiveType)ctclass;
/* 266*/            stringbuffer.append(ctclass.getDescriptor());
/* 267*/            return;
                } else
                {
/* 269*/            stringbuffer.append('L');
/* 270*/            stringbuffer.append(ctclass.getName().replace('.', '/'));
/* 271*/            stringbuffer.append(';');
/* 273*/            return;
                }
            }

            public static String ofConstructor(CtClass actclass[])
            {
/* 282*/        return ofMethod(CtClass.voidType, actclass);
            }

            public static String ofMethod(CtClass ctclass, CtClass actclass[])
            {
                StringBuffer stringbuffer;
/* 293*/        (stringbuffer = new StringBuffer()).append('(');
/* 295*/        if(actclass != null)
                {
/* 296*/            int i = actclass.length;
/* 297*/            for(int j = 0; j < i; j++)
/* 298*/                toDescriptor(stringbuffer, actclass[j]);

                }
/* 301*/        stringbuffer.append(')');
/* 302*/        if(ctclass != null)
/* 303*/            toDescriptor(stringbuffer, ctclass);
/* 305*/        return stringbuffer.toString();
            }

            public static String ofParameters(CtClass actclass[])
            {
/* 316*/        return ofMethod(null, actclass);
            }

            public static String appendParameter(String s, String s1)
            {
                int i;
/* 329*/        if((i = s1.indexOf(')')) < 0)
                {
/* 331*/            return s1;
                } else
                {
                    StringBuffer stringbuffer;
/* 333*/            (stringbuffer = new StringBuffer()).append(s1.substring(0, i));
/* 335*/            stringbuffer.append('L');
/* 336*/            stringbuffer.append(s.replace('.', '/'));
/* 337*/            stringbuffer.append(';');
/* 338*/            stringbuffer.append(s1.substring(i));
/* 339*/            return stringbuffer.toString();
                }
            }

            public static String insertParameter(String s, String s1)
            {
/* 354*/        if(s1.charAt(0) != '(')
/* 355*/            return s1;
/* 357*/        else
/* 357*/            return (new StringBuilder("(L")).append(s.replace('.', '/')).append(';').append(s1.substring(1)).toString();
            }

            public static String appendParameter(CtClass ctclass, String s)
            {
                int i;
/* 370*/        if((i = s.indexOf(')')) < 0)
                {
/* 372*/            return s;
                } else
                {
                    StringBuffer stringbuffer;
/* 374*/            (stringbuffer = new StringBuffer()).append(s.substring(0, i));
/* 376*/            toDescriptor(stringbuffer, ctclass);
/* 377*/            stringbuffer.append(s.substring(i));
/* 378*/            return stringbuffer.toString();
                }
            }

            public static String insertParameter(CtClass ctclass, String s)
            {
/* 392*/        if(s.charAt(0) != '(')
/* 393*/            return s;
/* 395*/        else
/* 395*/            return (new StringBuilder("(")).append(of(ctclass)).append(s.substring(1)).toString();
            }

            public static String changeReturnType(String s, String s1)
            {
                int i;
/* 407*/        if((i = s1.indexOf(')')) < 0)
                {
/* 409*/            return s1;
                } else
                {
                    StringBuffer stringbuffer;
/* 411*/            (stringbuffer = new StringBuffer()).append(s1.substring(0, i + 1));
/* 413*/            stringbuffer.append('L');
/* 414*/            stringbuffer.append(s.replace('.', '/'));
/* 415*/            stringbuffer.append(';');
/* 416*/            return stringbuffer.toString();
                }
            }

            public static CtClass[] getParameterTypes(String s, ClassPool classpool)
                throws NotFoundException
            {
/* 431*/        if(s.charAt(0) != '(')
/* 432*/            return null;
                int i;
/* 434*/        CtClass actclass[] = new CtClass[i = numOfParameters(s)];
/* 436*/        int j = 0;
/* 437*/        for(int k = 1; (k = toCtClass(classpool, s, k, actclass, j++)) > 0;);
/* 441*/        return actclass;
            }

            public static boolean eqParamTypes(String s, String s1)
            {
/* 451*/        if(s.charAt(0) != '(')
/* 452*/            return false;
/* 454*/        int i = 0;
/* 455*/        do
                {
                    char c;
/* 455*/            if((c = s.charAt(i)) != s1.charAt(i))
/* 457*/                return false;
/* 459*/            if(c == ')')
/* 460*/                return true;
/* 454*/            i++;
                } while(true);
            }

            public static String getParamDescriptor(String s)
            {
/* 470*/        return s.substring(0, s.indexOf(')') + 1);
            }

            public static CtClass getReturnType(String s, ClassPool classpool)
                throws NotFoundException
            {
                int i;
/* 484*/        if((i = s.indexOf(')')) < 0)
                {
/* 486*/            return null;
                } else
                {
/* 488*/            CtClass actclass[] = new CtClass[1];
/* 489*/            toCtClass(classpool, s, i + 1, actclass, 0);
/* 490*/            return actclass[0];
                }
            }

            public static int numOfParameters(String s)
            {
/* 501*/        int i = 0;
/* 502*/        int j = 1;
                char c;
/* 504*/        while((c = s.charAt(j)) != ')') 
                {
/* 508*/            for(; c == '['; c = s.charAt(++j));
/* 511*/            if(c == 'L')
                    {
/* 512*/                if((j = s.indexOf(';', j) + 1) <= 0)
/* 514*/                    throw new IndexOutOfBoundsException("bad descriptor");
                    } else
                    {
/* 517*/                j++;
                    }
/* 519*/            i++;
                }
/* 522*/        return i;
            }

            public static CtClass toCtClass(String s, ClassPool classpool)
                throws NotFoundException
            {
/* 541*/        CtClass actclass[] = new CtClass[1];
                int i;
/* 542*/        if((i = toCtClass(classpool, s, 0, actclass, 0)) >= 0)
/* 544*/            return actclass[0];
/* 548*/        else
/* 548*/            return classpool.get(s.replace('/', '.'));
            }

            private static int toCtClass(ClassPool classpool, String s, int i, CtClass actclass[], int j)
                throws NotFoundException
            {
/* 559*/        int l = 0;
                int k;
/* 560*/        for(k = s.charAt(i); k == 91; k = s.charAt(++i))
/* 562*/            l++;

/* 566*/        if(k == 76)
                {
/* 567*/            k = s.indexOf(';', ++i);
/* 568*/            s = s.substring(i, k++).replace('/', '.');
                } else
                {
/* 571*/            if((s = toPrimitiveClass(k)) == null)
/* 573*/                return -1;
/* 575*/            k = i + 1;
/* 576*/            if(l == 0)
                    {
/* 577*/                actclass[j] = s;
/* 578*/                return k;
                    }
/* 581*/            s = s.getName();
                }
/* 584*/        if(l > 0)
                {
/* 585*/            s = new StringBuffer(s);
/* 586*/            while(l-- > 0) 
/* 587*/                s.append("[]");
/* 589*/            s = s.toString();
                }
/* 592*/        actclass[j] = classpool.get(s);
/* 593*/        return k;
            }

            static CtClass toPrimitiveClass(char c)
            {
/* 597*/        CtClass ctclass = null;
/* 598*/        switch(c)
                {
/* 600*/        case 90: // 'Z'
/* 600*/            ctclass = CtClass.booleanType;
                    break;

/* 603*/        case 67: // 'C'
/* 603*/            ctclass = CtClass.charType;
                    break;

/* 606*/        case 66: // 'B'
/* 606*/            ctclass = CtClass.byteType;
                    break;

/* 609*/        case 83: // 'S'
/* 609*/            ctclass = CtClass.shortType;
                    break;

/* 612*/        case 73: // 'I'
/* 612*/            ctclass = CtClass.intType;
                    break;

/* 615*/        case 74: // 'J'
/* 615*/            ctclass = CtClass.longType;
                    break;

/* 618*/        case 70: // 'F'
/* 618*/            ctclass = CtClass.floatType;
                    break;

/* 621*/        case 68: // 'D'
/* 621*/            ctclass = CtClass.doubleType;
                    break;

/* 624*/        case 86: // 'V'
/* 624*/            ctclass = CtClass.voidType;
                    break;
                }
/* 628*/        return ctclass;
            }

            public static int arrayDimension(String s)
            {
                int i;
/* 640*/        for(i = 0; s.charAt(i) == '['; i++);
/* 644*/        return i;
            }

            public static String toArrayComponent(String s, int i)
            {
/* 657*/        return s.substring(i);
            }

            public static int dataSize(String s)
            {
/* 672*/        return dataSize(s, true);
            }

            public static int paramSize(String s)
            {
/* 685*/        return -dataSize(s, false);
            }

            private static int dataSize(String s, boolean flag)
            {
/* 689*/        int i = 0;
                char c;
/* 690*/        if((c = s.charAt(0)) == '(')
                {
/* 692*/            int j = 1;
/* 694*/            do
                    {
/* 694*/                if((c = s.charAt(j)) == ')')
                        {
/* 696*/                    c = s.charAt(j + 1);
/* 697*/                    break;
                        }
/* 700*/                boolean flag1 = false;
/* 701*/                for(; c == '['; c = s.charAt(++j))
/* 702*/                    flag1 = true;

/* 706*/                if(c == 'L')
                        {
/* 707*/                    if((j = s.indexOf(';', j) + 1) <= 0)
/* 709*/                        throw new IndexOutOfBoundsException("bad descriptor");
                        } else
                        {
/* 712*/                    j++;
                        }
/* 714*/                if(!flag1 && (c == 'J' || c == 'D'))
/* 715*/                    i -= 2;
/* 717*/                else
/* 717*/                    i--;
                    } while(true);
                }
/* 721*/        if(flag)
/* 722*/            if(c == 'J' || c == 'D')
/* 723*/                i += 2;
/* 724*/            else
/* 724*/            if(c != 'V')
/* 725*/                i++;
/* 727*/        return i;
            }

            public static String toString(String s)
            {
/* 738*/        return PrettyPrinter.toString(s);
            }
}
