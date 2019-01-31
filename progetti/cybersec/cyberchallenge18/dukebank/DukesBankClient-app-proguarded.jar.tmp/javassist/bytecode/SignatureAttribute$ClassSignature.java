// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static class <init>
{

            public <init>[] getParameters()
            {
/* 210*/        return params;
            }

            public params getSuperClass()
            {
/* 216*/        return superClass;
            }

            public superClass[] getInterfaces()
            {
/* 223*/        return interfaces;
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/* 229*/        oString(stringbuffer = new StringBuffer(), params);
/* 232*/        stringbuffer.append(" extends ").append(superClass);
/* 233*/        if(interfaces.length > 0)
                {
/* 234*/            stringbuffer.append(" implements ");
/* 235*/            interfaces(stringbuffer, interfaces);
                }
/* 238*/        return stringbuffer.toString();
            }

            public String encode()
            {
/* 245*/        StringBuffer stringbuffer = new StringBuffer();
/* 246*/        if(params.length > 0)
                {
/* 247*/            stringbuffer.append('<');
/* 248*/            for(int i = 0; i < params.length; i++)
/* 249*/                params[i].ncode(stringbuffer);

/* 251*/            stringbuffer.append('>');
                }
/* 254*/        superClass.e(stringbuffer);
/* 255*/        for(int j = 0; j < interfaces.length; j++)
/* 256*/            interfaces[j].e(stringbuffer);

/* 258*/        return stringbuffer.toString();
            }

            e params[];
            e superClass;
            e interfaces[];

            public ( a[],  ,  a1[])
            {
/* 190*/        params = a != null ? a : new params[0];
/* 191*/        superClass =  != null ?  : T;
/* 192*/        interfaces = a1 != null ? a1 : new interfaces[0];
            }

            public interfaces(interfaces ainterfaces[])
            {
/* 201*/        this(ainterfaces, null, null);
            }
}
