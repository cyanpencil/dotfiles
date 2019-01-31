// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassFileWriter.java

package javassist.bytecode;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package javassist.bytecode:
//            ByteStream, ClassFileWriter, StackMapTable

public static final class stackIndex
{

            public final void begin(int i, String s, String s1, String as[], er er)
            {
/* 347*/        s = constPool.addUtf8Info(s);
/* 348*/        s1 = constPool.addUtf8Info(s1);
/* 350*/        if(as == null)
/* 351*/            as = null;
/* 353*/        else
/* 353*/            as = constPool.addClassInfo(as);
/* 355*/        begin(i, s, s1, ((int []) (as)), er);
            }

            public final void begin(int i, int j, int k, int ai[], er er)
            {
/* 369*/        methodCount++;
/* 370*/        output.writeShort(i);
/* 371*/        output.writeShort(j);
/* 372*/        output.writeShort(k);
/* 373*/        isAbstract = (i & 0x400) != 0;
/* 375*/        i = isAbstract ? 0 : 1;
/* 376*/        if(ai != null)
/* 377*/            i++;
/* 379*/        ClassFileWriter.writeAttribute(output, er, i);
/* 381*/        if(ai != null)
/* 382*/            writeThrows(ai);
/* 384*/        if(!isAbstract)
                {
/* 385*/            if(codeIndex == 0)
/* 386*/                codeIndex = constPool.addUtf8Info("Code");
/* 388*/            startPos = output.getPos();
/* 389*/            output.writeShort(codeIndex);
/* 390*/            output.writeBlank(12);
                }
/* 393*/        catchPos = -1;
/* 394*/        catchCount = 0;
            }

            private void writeThrows(int ai[])
            {
/* 398*/        if(throwsIndex == 0)
/* 399*/            throwsIndex = constPool.addUtf8Info("Exceptions");
/* 401*/        output.writeShort(throwsIndex);
/* 402*/        output.writeInt((ai.length << 1) + 2);
/* 403*/        output.writeShort(ai.length);
/* 404*/        for(int i = 0; i < ai.length; i++)
/* 405*/            output.writeShort(ai[i]);

            }

            public final void add(int i)
            {
/* 414*/        output.write(i);
            }

            public final void add16(int i)
            {
/* 421*/        output.writeShort(i);
            }

            public final void add32(int i)
            {
/* 428*/        output.writeInt(i);
            }

            public final void addInvoke(int i, String s, String s1, String s2)
            {
/* 438*/        s = constPool.addClassInfo(s);
/* 439*/        s1 = constPool.addNameAndTypeInfo(s1, s2);
/* 440*/        s = constPool.addMethodrefInfo(s, s1);
/* 441*/        add(i);
/* 442*/        add16(s);
            }

            public final void codeEnd(int i, int j)
            {
/* 449*/        if(!isAbstract)
                {
/* 450*/            output.writeShort(startPos + 6, i);
/* 451*/            output.writeShort(startPos + 8, j);
/* 452*/            output.writeInt(startPos + 10, output.getPos() - startPos - 14);
/* 453*/            catchPos = output.getPos();
/* 454*/            catchCount = 0;
/* 455*/            output.writeShort(0);
                }
            }

            public final void addCatch(int i, int j, int k, int l)
            {
/* 467*/        catchCount++;
/* 468*/        output.writeShort(i);
/* 469*/        output.writeShort(j);
/* 470*/        output.writeShort(k);
/* 471*/        output.writeShort(l);
            }

            public final void end(er er, er er1)
            {
/* 483*/        if(isAbstract)
/* 484*/            return;
/* 487*/        output.writeShort(catchPos, catchCount);
/* 489*/        int i = er != null ? 1 : 0;
/* 490*/        ClassFileWriter.writeAttribute(output, er1, i);
/* 492*/        if(er != null)
                {
/* 493*/            if(stackIndex == 0)
/* 494*/                stackIndex = constPool.addUtf8Info("StackMapTable");
/* 496*/            output.writeShort(stackIndex);
/* 497*/            er = er.ray();
/* 498*/            output.writeInt(er.length);
/* 499*/            output.write(er);
                }
/* 503*/        output.writeInt(startPos + 2, output.getPos() - startPos - 6);
            }

            final int size()
            {
/* 506*/        return methodCount;
            }

            final int dataSize()
            {
/* 508*/        return output.size();
            }

            final void write(OutputStream outputstream)
                throws IOException
            {
/* 514*/        output.writeTo(outputstream);
            }

            protected ByteStream output;
            protected er constPool;
            private int methodCount;
            protected int codeIndex;
            protected int throwsIndex;
            protected int stackIndex;
            private int startPos;
            private boolean isAbstract;
            private int catchPos;
            private int catchCount;

            er(er er)
            {
/* 326*/        output = new ByteStream(256);
/* 327*/        constPool = er;
/* 328*/        methodCount = 0;
/* 329*/        codeIndex = 0;
/* 330*/        throwsIndex = 0;
/* 331*/        stackIndex = 0;
            }
}
