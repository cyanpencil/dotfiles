// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static class <init>
{

            public String getName()
            {
/* 409*/        return name;
            }

            public name getClassBound()
            {
/* 415*/        return superClass;
            }

            public superClass[] getInterfaceBound()
            {
/* 422*/        return superInterfaces;
            }

            public String toString()
            {
/* 428*/        StringBuffer stringbuffer = new StringBuffer(getName());
/* 429*/        if(superClass != null)
/* 430*/            stringbuffer.append(" extends ").append(superClass.toString());
                int i;
/* 432*/        if((i = superInterfaces.length) > 0)
                {
/* 434*/            for(int j = 0; j < i; j++)
                    {
/* 435*/                if(j > 0 || superClass != null)
/* 436*/                    stringbuffer.append(" & ");
/* 438*/                else
/* 438*/                    stringbuffer.append(" extends ");
/* 440*/                stringbuffer.append(superInterfaces[j].toString());
                    }

                }
/* 444*/        return stringbuffer.toString();
            }

            static void toString(StringBuffer stringbuffer, superInterfaces asuperinterfaces[])
            {
/* 448*/        stringbuffer.append('<');
/* 449*/        for(int i = 0; i < asuperinterfaces.length; i++)
                {
/* 450*/            if(i > 0)
/* 451*/                stringbuffer.append(", ");
/* 453*/            stringbuffer.append(asuperinterfaces[i]);
                }

/* 456*/        stringbuffer.append('>');
            }

            void encode(StringBuffer stringbuffer)
            {
/* 460*/        stringbuffer.append(name);
/* 461*/        if(superClass == null)
                {
/* 462*/            stringbuffer.append(":Ljava/lang/Object;");
                } else
                {
/* 464*/            stringbuffer.append(':');
/* 465*/            superClass.ode(stringbuffer);
                }
/* 468*/        for(int i = 0; i < superInterfaces.length; i++)
                {
/* 469*/            stringbuffer.append(':');
/* 470*/            superInterfaces[i].ode(stringbuffer);
                }

            }

            String name;
            ode superClass;
            ode superInterfaces[];

            _cls9(String s, int i, int j, _cls9 _pcls9, _cls9 a_pcls9[])
            {
/* 373*/        name = s.substring(i, j);
/* 374*/        superClass = _pcls9;
/* 375*/        superInterfaces = a_pcls9;
            }

            public superInterfaces(String s, superInterfaces superinterfaces, superInterfaces asuperinterfaces[])
            {
/* 387*/        name = s;
/* 388*/        superClass = superinterfaces;
/* 389*/        if(asuperinterfaces == null)
                {
/* 390*/            superInterfaces = new superInterfaces[0];
/* 390*/            return;
                } else
                {
/* 392*/            superInterfaces = asuperinterfaces;
/* 393*/            return;
                }
            }

            public superInterfaces(String s)
            {
/* 402*/        this(s, null, null);
            }
}
