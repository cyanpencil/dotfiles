// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationsAttribute.java

package javassist.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import javassist.bytecode.annotation.AnnotationsWriter;

// Referenced classes of package javassist.bytecode:
//            AnnotationsAttribute, ConstPool, Descriptor

static class classnames extends classnames
{

            byte[] close()
                throws IOException
            {
/* 482*/        writer.close();
/* 483*/        return output.toByteArray();
            }

            void parameters(int i, int j)
                throws Exception
            {
/* 487*/        writer.numParameters(i);
/* 488*/        super.parameters(i, j);
            }

            int annotationArray(int i, int j)
                throws Exception
            {
/* 492*/        writer.numAnnotations(j);
/* 493*/        return super.annotationArray(i, j);
            }

            int annotation(int i, int j, int k)
                throws Exception
            {
/* 497*/        writer.annotation(copyType(j), k);
/* 498*/        return super.annotation(i, j, k);
            }

            int memberValuePair(int i, int j)
                throws Exception
            {
/* 502*/        writer.memberValuePair(copy(j));
/* 503*/        return super.memberValuePair(i, j);
            }

            void constValueMember(int i, int j)
                throws Exception
            {
/* 507*/        writer.constValueIndex(i, copy(j));
/* 508*/        super.constValueMember(i, j);
            }

            void enumMemberValue(int i, int j, int k)
                throws Exception
            {
/* 514*/        writer.enumConstValue(copyType(j), copy(k));
/* 515*/        super.enumMemberValue(i, j, k);
            }

            void classMemberValue(int i, int j)
                throws Exception
            {
/* 519*/        writer.classInfoIndex(copyType(j));
/* 520*/        super.classMemberValue(i, j);
            }

            int annotationMemberValue(int i)
                throws Exception
            {
/* 524*/        writer.annotationValue();
/* 525*/        return super.annotationMemberValue(i);
            }

            int arrayMemberValue(int i, int j)
                throws Exception
            {
/* 529*/        writer.arrayValue(j);
/* 530*/        return super.arrayMemberValue(i, j);
            }

            int copy(int i)
            {
/* 543*/        return srcPool.copy(i, destPool, classnames);
            }

            int copyType(int i)
            {
/* 557*/        i = Descriptor.rename(i = srcPool.getUtf8Info(i), classnames);
/* 559*/        return destPool.addUtf8Info(i);
            }

            ByteArrayOutputStream output;
            AnnotationsWriter writer;
            ConstPool srcPool;
            ConstPool destPool;
            Map classnames;

            (byte abyte0[], ConstPool constpool, ConstPool constpool1, Map map)
            {
/* 473*/        super(abyte0);
/* 474*/        output = new ByteArrayOutputStream();
/* 475*/        writer = new AnnotationsWriter(output, constpool1);
/* 476*/        srcPool = constpool;
/* 477*/        destPool = constpool1;
/* 478*/        classnames = map;
            }
}
