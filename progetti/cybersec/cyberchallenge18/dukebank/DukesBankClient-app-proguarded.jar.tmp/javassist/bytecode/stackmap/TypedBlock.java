// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypedBlock.java

package javassist.bytecode.stackmap;

import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.stackmap:
//            BasicBlock, TypeData, TypeTag

public class TypedBlock extends BasicBlock
{
    public static class Maker extends BasicBlock.Maker
    {

                protected BasicBlock makeBlock(int i)
                {
/* 114*/            return new TypedBlock(i);
                }

                protected BasicBlock[] makeArray(int i)
                {
/* 118*/            return new TypedBlock[i];
                }

                public Maker()
                {
                }
    }


            public static TypedBlock[] makeBlocks(MethodInfo methodinfo, CodeAttribute codeattribute, boolean flag)
                throws BadBytecode
            {
/*  39*/        TypedBlock atypedblock[] = (TypedBlock[])(new Maker()).make(methodinfo);
/*  40*/        if(flag && atypedblock.length < 2 && (atypedblock.length == 0 || atypedblock[0].incoming == 0))
                {
/*  42*/            return null;
                } else
                {
/*  44*/            flag = methodinfo.getConstPool();
/*  45*/            boolean flag1 = (methodinfo.getAccessFlags() & 8) != 0;
/*  46*/            atypedblock[0].initFirstBlock(codeattribute.getMaxStack(), codeattribute.getMaxLocals(), flag.getClassName(), methodinfo.getDescriptor(), flag1, methodinfo.isConstructor());
/*  49*/            return atypedblock;
                }
            }

            protected TypedBlock(int i)
            {
/*  53*/        super(i);
/*  54*/        localsTypes = null;
            }

            protected void toString2(StringBuffer stringbuffer)
            {
/*  58*/        super.toString2(stringbuffer);
/*  59*/        stringbuffer.append(",\n stack={");
/*  60*/        printTypes(stringbuffer, stackTop, stackTypes);
/*  61*/        stringbuffer.append("}, locals={");
/*  62*/        printTypes(stringbuffer, numLocals, localsTypes);
/*  63*/        stringbuffer.append('}');
            }

            private void printTypes(StringBuffer stringbuffer, int i, TypeData atypedata[])
            {
/*  68*/        if(atypedata == null)
/*  69*/            return;
/*  71*/        for(int j = 0; j < i; j++)
                {
/*  72*/            if(j > 0)
/*  73*/                stringbuffer.append(", ");
/*  75*/            TypeData typedata = atypedata[j];
/*  76*/            stringbuffer.append(typedata != null ? typedata.toString() : "<>");
                }

            }

            public boolean alreadySet()
            {
/*  81*/        return localsTypes != null;
            }

            public void setStackMap(int i, TypeData atypedata[], int j, TypeData atypedata1[])
                throws BadBytecode
            {
/*  87*/        stackTop = i;
/*  88*/        stackTypes = atypedata;
/*  89*/        numLocals = j;
/*  90*/        localsTypes = atypedata1;
            }

            public void resetNumLocals()
            {
/*  97*/        if(localsTypes != null)
                {
                    int i;
/*  98*/            for(i = localsTypes.length; i > 0 && localsTypes[i - 1].isBasicType() == TypeTag.TOP && (i <= 1 || !localsTypes[i - 2].is2WordType()); i--);
/* 108*/            numLocals = i;
                }
            }

            void initFirstBlock(int i, int j, String s, String s1, boolean flag, boolean flag1)
                throws BadBytecode
            {
/* 136*/        if(s1.charAt(0) != '(')
/* 137*/            throw new BadBytecode((new StringBuilder("no method descriptor: ")).append(s1).toString());
/* 139*/        stackTop = 0;
/* 140*/        stackTypes = TypeData.make(i);
/* 141*/        i = TypeData.make(j);
/* 142*/        if(flag1)
/* 143*/            i[0] = new TypeData.UninitThis(s);
/* 144*/        else
/* 144*/        if(!flag)
/* 145*/            i[0] = new TypeData.ClassName(s);
/* 147*/        j = flag ? -1 : 0;
/* 148*/        s = 1;
/* 150*/        do
/* 150*/            try
                    {
/* 150*/                if((s = descToTag(s1, s, ++j, i)) <= 0)
/* 151*/                    break;
/* 151*/                if(i[j].is2WordType())
/* 152*/                    i[++j] = TypeTag.TOP;
                    }
/* 154*/            catch(StringIndexOutOfBoundsException _ex)
                    {
/* 155*/                throw new BadBytecode((new StringBuilder("bad method descriptor: ")).append(s1).toString());
                    }
/* 152*/        while(true);
/* 159*/        numLocals = j;
/* 160*/        localsTypes = i;
            }

            private static int descToTag(String s, int i, int j, TypeData atypedata[])
                throws BadBytecode
            {
/* 167*/        int k = i;
/* 168*/        int l = 0;
                int i1;
/* 169*/        if((i1 = s.charAt(i)) == ')')
/* 171*/            return 0;
/* 173*/        for(; i1 == 91; i1 = s.charAt(++i))
/* 174*/            l++;

/* 178*/        if(i1 == 76)
                {
/* 179*/            i1 = s.indexOf(';', ++i);
/* 180*/            if(l > 0)
/* 181*/                atypedata[j] = new TypeData.ClassName(s.substring(k, ++i1));
/* 183*/            else
/* 183*/                atypedata[j] = new TypeData.ClassName(s.substring(k + 1, ++i1 - 1).replace('/', '.'));
/* 185*/            return i1;
                }
/* 187*/        if(l > 0)
                {
/* 188*/            atypedata[j] = new TypeData.ClassName(s.substring(k, ++i));
/* 189*/            return i;
                }
                TypeData typedata;
/* 192*/        if((typedata = toPrimitiveTag(i1)) == null)
                {
/* 194*/            throw new BadBytecode((new StringBuilder("bad method descriptor: ")).append(s).toString());
                } else
                {
/* 196*/            atypedata[j] = typedata;
/* 197*/            return i + 1;
                }
            }

            private static TypeData toPrimitiveTag(char c)
            {
/* 202*/        switch(c)
                {
/* 208*/        case 66: // 'B'
/* 208*/        case 67: // 'C'
/* 208*/        case 73: // 'I'
/* 208*/        case 83: // 'S'
/* 208*/        case 90: // 'Z'
/* 208*/            return TypeTag.INTEGER;

/* 210*/        case 74: // 'J'
/* 210*/            return TypeTag.LONG;

/* 212*/        case 70: // 'F'
/* 212*/            return TypeTag.FLOAT;

/* 214*/        case 68: // 'D'
/* 214*/            return TypeTag.DOUBLE;

/* 217*/        case 69: // 'E'
/* 217*/        case 71: // 'G'
/* 217*/        case 72: // 'H'
/* 217*/        case 75: // 'K'
/* 217*/        case 76: // 'L'
/* 217*/        case 77: // 'M'
/* 217*/        case 78: // 'N'
/* 217*/        case 79: // 'O'
/* 217*/        case 80: // 'P'
/* 217*/        case 81: // 'Q'
/* 217*/        case 82: // 'R'
/* 217*/        case 84: // 'T'
/* 217*/        case 85: // 'U'
/* 217*/        case 86: // 'V'
/* 217*/        case 87: // 'W'
/* 217*/        case 88: // 'X'
/* 217*/        case 89: // 'Y'
/* 217*/        default:
/* 217*/            return null;
                }
            }

            public static String getRetType(String s)
            {
                int i;
/* 222*/        if((i = s.indexOf(')')) < 0)
/* 224*/            return "java.lang.Object";
                char c;
/* 226*/        if((c = s.charAt(i + 1)) == '[')
/* 228*/            return s.substring(i + 1);
/* 229*/        if(c == 'L')
/* 230*/            return s.substring(i + 2, s.length() - 1).replace('/', '.');
/* 232*/        else
/* 232*/            return "java.lang.Object";
            }

            public int stackTop;
            public int numLocals;
            public TypeData localsTypes[];
            public TypeData stackTypes[];
}
