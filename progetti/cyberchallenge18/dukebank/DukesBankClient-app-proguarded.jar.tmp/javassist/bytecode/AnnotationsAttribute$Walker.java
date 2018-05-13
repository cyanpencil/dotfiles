// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationsAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            AnnotationsAttribute, ByteArray

static class info
{

            final void parameters()
                throws Exception
            {
/* 315*/        int i = info[0] & 0xff;
/* 316*/        parameters(i, 1);
            }

            void parameters(int i, int j)
                throws Exception
            {
/* 320*/        for(int k = 0; k < i; k++)
/* 321*/            j = annotationArray(j);

            }

            final void annotationArray()
                throws Exception
            {
/* 325*/        annotationArray(0);
            }

            final int annotationArray(int i)
                throws Exception
            {
/* 329*/        int j = ByteArray.readU16bit(info, i);
/* 330*/        return annotationArray(i + 2, j);
            }

            int annotationArray(int i, int j)
                throws Exception
            {
/* 334*/        for(int k = 0; k < j; k++)
/* 335*/            i = annotation(i);

/* 337*/        return i;
            }

            final int annotation(int i)
                throws Exception
            {
/* 341*/        int j = ByteArray.readU16bit(info, i);
/* 342*/        int k = ByteArray.readU16bit(info, i + 2);
/* 343*/        return annotation(i + 4, j, k);
            }

            int annotation(int i, int j, int k)
                throws Exception
            {
/* 347*/        for(j = 0; j < k; j++)
/* 348*/            i = memberValuePair(i);

/* 350*/        return i;
            }

            final int memberValuePair(int i)
                throws Exception
            {
/* 354*/        int j = ByteArray.readU16bit(info, i);
/* 355*/        return memberValuePair(i + 2, j);
            }

            int memberValuePair(int i, int j)
                throws Exception
            {
/* 359*/        return memberValue(i);
            }

            final int memberValue(int i)
                throws Exception
            {
                int j;
/* 363*/        if((j = info[i] & 0xff) == 101)
                {
/* 365*/            int k = ByteArray.readU16bit(info, i + 1);
/* 366*/            j = ByteArray.readU16bit(info, i + 3);
/* 367*/            enumMemberValue(i, k, j);
/* 368*/            return i + 5;
                }
/* 370*/        if(j == 99)
                {
/* 371*/            int l = ByteArray.readU16bit(info, i + 1);
/* 372*/            classMemberValue(i, l);
/* 373*/            return i + 3;
                }
/* 375*/        if(j == 64)
/* 376*/            return annotationMemberValue(i + 1);
/* 377*/        if(j == 91)
                {
/* 378*/            int i1 = ByteArray.readU16bit(info, i + 1);
/* 379*/            return arrayMemberValue(i + 3, i1);
                } else
                {
/* 382*/            int j1 = ByteArray.readU16bit(info, i + 1);
/* 383*/            constValueMember(j, j1);
/* 384*/            return i + 3;
                }
            }

            void constValueMember(int i, int j)
                throws Exception
            {
            }

            void enumMemberValue(int i, int j, int k)
                throws Exception
            {
            }

            void classMemberValue(int i, int j)
                throws Exception
            {
            }

            int annotationMemberValue(int i)
                throws Exception
            {
/* 397*/        return annotation(i);
            }

            int arrayMemberValue(int i, int j)
                throws Exception
            {
/* 401*/        for(int k = 0; k < j; k++)
/* 402*/            i = memberValue(i);

/* 405*/        return i;
            }

            byte info[];

            (byte abyte0[])
            {
/* 311*/        info = abyte0;
            }
}
