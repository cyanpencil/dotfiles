// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassFileWriter.java

package javassist.bytecode;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package javassist.bytecode:
//            ByteStream, ClassFileWriter

public static final class fieldCount
{

            public final void add(int i, String s, String s1, ter ter)
            {
/* 275*/        s = constPool.addUtf8Info(s);
/* 276*/        s1 = constPool.addUtf8Info(s1);
/* 277*/        add(i, s, s1, ter);
            }

            public final void add(int i, int j, int k, ter ter)
            {
/* 290*/        fieldCount++;
/* 291*/        output.writeShort(i);
/* 292*/        output.writeShort(j);
/* 293*/        output.writeShort(k);
/* 294*/        ClassFileWriter.writeAttribute(output, ter, 0);
            }

            final int size()
            {
/* 297*/        return fieldCount;
            }

            final int dataSize()
            {
/* 299*/        return output.size();
            }

            final void write(OutputStream outputstream)
                throws IOException
            {
/* 305*/        output.writeTo(outputstream);
            }

            protected ByteStream output;
            protected ter constPool;
            private int fieldCount;

            ter(ter ter)
            {
/* 260*/        output = new ByteStream(128);
/* 261*/        constPool = ter;
/* 262*/        fieldCount = 0;
            }
}
