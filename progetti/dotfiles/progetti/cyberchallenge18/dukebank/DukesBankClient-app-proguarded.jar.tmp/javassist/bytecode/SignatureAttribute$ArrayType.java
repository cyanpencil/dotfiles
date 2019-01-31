// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static class componentType extends 
{

            public int getDimension()
            {
/* 820*/        return dim;
            }

            public dim getComponentType()
            {
/* 826*/        return componentType;
            }

            public String toString()
            {
/* 833*/        StringBuffer stringbuffer = new StringBuffer(componentType.toString());
/* 834*/        for(int i = 0; i < dim; i++)
/* 835*/            stringbuffer.append("[]");

/* 837*/        return stringbuffer.toString();
            }

            void encode(StringBuffer stringbuffer)
            {
/* 841*/        for(int i = 0; i < dim; i++)
/* 842*/            stringbuffer.append('[');

/* 844*/        componentType.e(stringbuffer);
            }

            int dim;
            e componentType;

            public (int i,  )
            {
/* 813*/        dim = i;
/* 814*/        componentType = ;
            }
}
