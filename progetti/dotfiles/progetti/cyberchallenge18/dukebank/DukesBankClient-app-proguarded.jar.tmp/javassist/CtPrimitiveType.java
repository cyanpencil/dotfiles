// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtPrimitiveType.java

package javassist;


// Referenced classes of package javassist:
//            CtClass

public final class CtPrimitiveType extends CtClass
{

            CtPrimitiveType(String s, char c, String s1, String s2, String s3, int i, int j, 
                    int k)
            {
/*  35*/        super(s);
/*  36*/        descriptor = c;
/*  37*/        wrapperName = s1;
/*  38*/        getMethodName = s2;
/*  39*/        mDescriptor = s3;
/*  40*/        returnOp = i;
/*  41*/        arrayType = j;
/*  42*/        dataSize = k;
            }

            public final boolean isPrimitive()
            {
/*  50*/        return true;
            }

            public final int getModifiers()
            {
/*  59*/        return 17;
            }

            public final char getDescriptor()
            {
/*  66*/        return descriptor;
            }

            public final String getWrapperName()
            {
/*  73*/        return wrapperName;
            }

            public final String getGetMethodName()
            {
/*  81*/        return getMethodName;
            }

            public final String getGetMethodDescriptor()
            {
/*  89*/        return mDescriptor;
            }

            public final int getReturnOp()
            {
/*  96*/        return returnOp;
            }

            public final int getArrayType()
            {
/* 104*/        return arrayType;
            }

            public final int getDataSize()
            {
/* 111*/        return dataSize;
            }

            private char descriptor;
            private String wrapperName;
            private String getMethodName;
            private String mDescriptor;
            private int returnOp;
            private int arrayType;
            private int dataSize;
}
