// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static class nt extends 
{

            static nt make(String s, int i, int j, nt ant[], nt nt)
            {
/* 663*/        if(nt == null)
/* 664*/            return new <init>(s, i, j, ant);
/* 666*/        else
/* 666*/            return new sType(s, i, j, ant, nt);
            }

            public String getName()
            {
/* 705*/        return name;
            }

            public nt[] getTypeArguments()
            {
/* 713*/        return arguments;
            }

            public arguments getDeclaringClass()
            {
/* 721*/        return null;
            }

            public String toString()
            {
/* 727*/        StringBuffer stringbuffer = new StringBuffer();
                arguments arguments1;
/* 728*/        if((arguments1 = getDeclaringClass()) != null)
/* 730*/            stringbuffer.append(arguments1.toString()).append('.');
/* 732*/        stringbuffer.append(name);
/* 733*/        if(arguments != null)
                {
/* 734*/            stringbuffer.append('<');
/* 735*/            int i = arguments.length;
/* 736*/            for(int j = 0; j < i; j++)
                    {
/* 737*/                if(j > 0)
/* 738*/                    stringbuffer.append(", ");
/* 740*/                stringbuffer.append(arguments[j].toString());
                    }

/* 743*/            stringbuffer.append('>');
                }
/* 746*/        return stringbuffer.toString();
            }

            void encode(StringBuffer stringbuffer)
            {
/* 750*/        stringbuffer.append('L');
/* 751*/        encode2(stringbuffer);
/* 752*/        stringbuffer.append(';');
            }

            void encode2(StringBuffer stringbuffer)
            {
                nt nt;
/* 756*/        if((nt = getDeclaringClass()) != null)
                {
/* 758*/            nt.encode2(stringbuffer);
/* 759*/            stringbuffer.append('$');
                }
/* 762*/        stringbuffer.append(name.replace('.', '/'));
/* 763*/        if(arguments != null)
/* 764*/            javassist.bytecode.nt.encode(stringbuffer, arguments);
            }

            String name;
            nt arguments[];
            public static nt OBJECT = new <init>("java.lang.Object", null);


            nt(String s, int i, int j, nt ant[])
            {
/* 670*/        name = s.substring(i, j).replace('/', '.');
/* 671*/        arguments = ant;
            }

            public nt(String s, nt ant[])
            {
/* 687*/        name = s;
/* 688*/        arguments = ant;
            }

            public nt(String s)
            {
/* 698*/        this(s, null);
            }
}
