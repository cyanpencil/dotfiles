// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassFileWriter.java

package javassist.bytecode;

import java.io.*;

// Referenced classes of package javassist.bytecode:
//            ByteStream, StackMapTable

public class ClassFileWriter
{
    public static final class ConstPoolWriter
    {

                public final int[] addClassInfo(String as[])
                {
                    int i;
/* 539*/            int ai[] = new int[i = as.length];
/* 541*/            for(int j = 0; j < i; j++)
/* 542*/                ai[j] = addClassInfo(as[j]);

/* 544*/            return ai;
                }

                public final int addClassInfo(String s)
                {
/* 558*/            s = addUtf8Info(s);
/* 559*/            output.write(7);
/* 560*/            output.writeShort(s);
/* 561*/            return num++;
                }

                public final int addClassInfo(int i)
                {
/* 571*/            output.write(7);
/* 572*/            output.writeShort(i);
/* 573*/            return num++;
                }

                public final int addNameAndTypeInfo(String s, String s1)
                {
/* 584*/            return addNameAndTypeInfo(addUtf8Info(s), addUtf8Info(s1));
                }

                public final int addNameAndTypeInfo(int i, int j)
                {
/* 595*/            output.write(12);
/* 596*/            output.writeShort(i);
/* 597*/            output.writeShort(j);
/* 598*/            return num++;
                }

                public final int addFieldrefInfo(int i, int j)
                {
/* 609*/            output.write(9);
/* 610*/            output.writeShort(i);
/* 611*/            output.writeShort(j);
/* 612*/            return num++;
                }

                public final int addMethodrefInfo(int i, int j)
                {
/* 623*/            output.write(10);
/* 624*/            output.writeShort(i);
/* 625*/            output.writeShort(j);
/* 626*/            return num++;
                }

                public final int addInterfaceMethodrefInfo(int i, int j)
                {
/* 639*/            output.write(11);
/* 640*/            output.writeShort(i);
/* 641*/            output.writeShort(j);
/* 642*/            return num++;
                }

                public final int addMethodHandleInfo(int i, int j)
                {
/* 657*/            output.write(15);
/* 658*/            output.write(i);
/* 659*/            output.writeShort(j);
/* 660*/            return num++;
                }

                public final int addMethodTypeInfo(int i)
                {
/* 673*/            output.write(16);
/* 674*/            output.writeShort(i);
/* 675*/            return num++;
                }

                public final int addInvokeDynamicInfo(int i, int j)
                {
/* 690*/            output.write(18);
/* 691*/            output.writeShort(i);
/* 692*/            output.writeShort(j);
/* 693*/            return num++;
                }

                public final int addStringInfo(String s)
                {
/* 706*/            s = addUtf8Info(s);
/* 707*/            output.write(8);
/* 708*/            output.writeShort(s);
/* 709*/            return num++;
                }

                public final int addIntegerInfo(int i)
                {
/* 719*/            output.write(3);
/* 720*/            output.writeInt(i);
/* 721*/            return num++;
                }

                public final int addFloatInfo(float f)
                {
/* 731*/            output.write(4);
/* 732*/            output.writeFloat(f);
/* 733*/            return num++;
                }

                public final int addLongInfo(long l)
                {
/* 743*/            output.write(5);
/* 744*/            output.writeLong(l);
/* 745*/            l = num;
/* 746*/            num += 2;
/* 747*/            return l;
                }

                public final int addDoubleInfo(double d)
                {
/* 757*/            output.write(6);
/* 758*/            output.writeDouble(d);
/* 759*/            d = num;
/* 760*/            num += 2;
/* 761*/            return d;
                }

                public final int addUtf8Info(String s)
                {
/* 771*/            output.write(1);
/* 772*/            output.writeUTF(s);
/* 773*/            return num++;
                }

                final void end()
                {
/* 780*/            output.writeShort(startPos, num);
                }

                ByteStream output;
                protected int startPos;
                protected int num;

                ConstPoolWriter(ByteStream bytestream)
                {
/* 527*/            output = bytestream;
/* 528*/            startPos = bytestream.getPos();
/* 529*/            num = 1;
/* 530*/            output.writeShort(1);
                }
    }

    public static final class MethodWriter
    {

                public final void begin(int i, String s, String s1, String as[], AttributeWriter attributewriter)
                {
/* 347*/            s = constPool.addUtf8Info(s);
/* 348*/            s1 = constPool.addUtf8Info(s1);
/* 350*/            if(as == null)
/* 351*/                as = null;
/* 353*/            else
/* 353*/                as = constPool.addClassInfo(as);
/* 355*/            begin(i, s, s1, ((int []) (as)), attributewriter);
                }

                public final void begin(int i, int j, int k, int ai[], AttributeWriter attributewriter)
                {
/* 369*/            methodCount++;
/* 370*/            output.writeShort(i);
/* 371*/            output.writeShort(j);
/* 372*/            output.writeShort(k);
/* 373*/            isAbstract = (i & 0x400) != 0;
/* 375*/            i = isAbstract ? 0 : 1;
/* 376*/            if(ai != null)
/* 377*/                i++;
/* 379*/            ClassFileWriter.writeAttribute(output, attributewriter, i);
/* 381*/            if(ai != null)
/* 382*/                writeThrows(ai);
/* 384*/            if(!isAbstract)
                    {
/* 385*/                if(codeIndex == 0)
/* 386*/                    codeIndex = constPool.addUtf8Info("Code");
/* 388*/                startPos = output.getPos();
/* 389*/                output.writeShort(codeIndex);
/* 390*/                output.writeBlank(12);
                    }
/* 393*/            catchPos = -1;
/* 394*/            catchCount = 0;
                }

                private void writeThrows(int ai[])
                {
/* 398*/            if(throwsIndex == 0)
/* 399*/                throwsIndex = constPool.addUtf8Info("Exceptions");
/* 401*/            output.writeShort(throwsIndex);
/* 402*/            output.writeInt((ai.length << 1) + 2);
/* 403*/            output.writeShort(ai.length);
/* 404*/            for(int i = 0; i < ai.length; i++)
/* 405*/                output.writeShort(ai[i]);

                }

                public final void add(int i)
                {
/* 414*/            output.write(i);
                }

                public final void add16(int i)
                {
/* 421*/            output.writeShort(i);
                }

                public final void add32(int i)
                {
/* 428*/            output.writeInt(i);
                }

                public final void addInvoke(int i, String s, String s1, String s2)
                {
/* 438*/            s = constPool.addClassInfo(s);
/* 439*/            s1 = constPool.addNameAndTypeInfo(s1, s2);
/* 440*/            s = constPool.addMethodrefInfo(s, s1);
/* 441*/            add(i);
/* 442*/            add16(s);
                }

                public final void codeEnd(int i, int j)
                {
/* 449*/            if(!isAbstract)
                    {
/* 450*/                output.writeShort(startPos + 6, i);
/* 451*/                output.writeShort(startPos + 8, j);
/* 452*/                output.writeInt(startPos + 10, output.getPos() - startPos - 14);
/* 453*/                catchPos = output.getPos();
/* 454*/                catchCount = 0;
/* 455*/                output.writeShort(0);
                    }
                }

                public final void addCatch(int i, int j, int k, int l)
                {
/* 467*/            catchCount++;
/* 468*/            output.writeShort(i);
/* 469*/            output.writeShort(j);
/* 470*/            output.writeShort(k);
/* 471*/            output.writeShort(l);
                }

                public final void end(StackMapTable.Writer writer, AttributeWriter attributewriter)
                {
/* 483*/            if(isAbstract)
/* 484*/                return;
/* 487*/            output.writeShort(catchPos, catchCount);
/* 489*/            int i = writer != null ? 1 : 0;
/* 490*/            ClassFileWriter.writeAttribute(output, attributewriter, i);
/* 492*/            if(writer != null)
                    {
/* 493*/                if(stackIndex == 0)
/* 494*/                    stackIndex = constPool.addUtf8Info("StackMapTable");
/* 496*/                output.writeShort(stackIndex);
/* 497*/                writer = writer.toByteArray();
/* 498*/                output.writeInt(writer.length);
/* 499*/                output.write(writer);
                    }
/* 503*/            output.writeInt(startPos + 2, output.getPos() - startPos - 6);
                }

                final int size()
                {
/* 506*/            return methodCount;
                }

                final int dataSize()
                {
/* 508*/            return output.size();
                }

                final void write(OutputStream outputstream)
                    throws IOException
                {
/* 514*/            output.writeTo(outputstream);
                }

                protected ByteStream output;
                protected ConstPoolWriter constPool;
                private int methodCount;
                protected int codeIndex;
                protected int throwsIndex;
                protected int stackIndex;
                private int startPos;
                private boolean isAbstract;
                private int catchPos;
                private int catchCount;

                MethodWriter(ConstPoolWriter constpoolwriter)
                {
/* 326*/            output = new ByteStream(256);
/* 327*/            constPool = constpoolwriter;
/* 328*/            methodCount = 0;
/* 329*/            codeIndex = 0;
/* 330*/            throwsIndex = 0;
/* 331*/            stackIndex = 0;
                }
    }

    public static final class FieldWriter
    {

                public final void add(int i, String s, String s1, AttributeWriter attributewriter)
                {
/* 275*/            s = constPool.addUtf8Info(s);
/* 276*/            s1 = constPool.addUtf8Info(s1);
/* 277*/            add(i, s, s1, attributewriter);
                }

                public final void add(int i, int j, int k, AttributeWriter attributewriter)
                {
/* 290*/            fieldCount++;
/* 291*/            output.writeShort(i);
/* 292*/            output.writeShort(j);
/* 293*/            output.writeShort(k);
/* 294*/            ClassFileWriter.writeAttribute(output, attributewriter, 0);
                }

                final int size()
                {
/* 297*/            return fieldCount;
                }

                final int dataSize()
                {
/* 299*/            return output.size();
                }

                final void write(OutputStream outputstream)
                    throws IOException
                {
/* 305*/            output.writeTo(outputstream);
                }

                protected ByteStream output;
                protected ConstPoolWriter constPool;
                private int fieldCount;

                FieldWriter(ConstPoolWriter constpoolwriter)
                {
/* 260*/            output = new ByteStream(128);
/* 261*/            constPool = constpoolwriter;
/* 262*/            fieldCount = 0;
                }
    }

    public static interface AttributeWriter
    {

        public abstract int size();

        public abstract void write(DataOutputStream dataoutputstream)
            throws IOException;
    }


            public ClassFileWriter(int i, int j)
            {
/*  90*/        output = new ByteStream(512);
/*  91*/        output.writeInt(0xcafebabe);
/*  92*/        output.writeShort(j);
/*  93*/        output.writeShort(i);
/*  94*/        constPool = new ConstPoolWriter(output);
/*  95*/        fields = new FieldWriter(constPool);
/*  96*/        methods = new MethodWriter(constPool);
            }

            public ConstPoolWriter getConstPool()
            {
/* 103*/        return constPool;
            }

            public FieldWriter getFieldWriter()
            {
/* 108*/        return fields;
            }

            public MethodWriter getMethodWriter()
            {
/* 113*/        return methods;
            }

            public byte[] end(int i, int j, int k, int ai[], AttributeWriter attributewriter)
            {
/* 130*/        constPool.end();
/* 131*/        output.writeShort(i);
/* 132*/        output.writeShort(j);
/* 133*/        output.writeShort(k);
/* 134*/        if(ai == null)
                {
/* 135*/            output.writeShort(0);
                } else
                {
/* 137*/            i = ai.length;
/* 138*/            output.writeShort(i);
/* 139*/            for(j = 0; j < i; j++)
/* 140*/                output.writeShort(ai[j]);

                }
/* 143*/        output.enlarge(fields.dataSize() + methods.dataSize() + 6);
/* 145*/        try
                {
/* 145*/            output.writeShort(fields.size());
/* 146*/            fields.write(output);
/* 148*/            output.writeShort(methods.size());
/* 149*/            methods.write(output);
                }
/* 151*/        catch(IOException _ex) { }
/* 153*/        writeAttribute(output, attributewriter, 0);
/* 154*/        return output.toByteArray();
            }

            public void end(DataOutputStream dataoutputstream, int i, int j, int k, int ai[], AttributeWriter attributewriter)
                throws IOException
            {
/* 176*/        constPool.end();
/* 177*/        output.writeTo(dataoutputstream);
/* 178*/        dataoutputstream.writeShort(i);
/* 179*/        dataoutputstream.writeShort(j);
/* 180*/        dataoutputstream.writeShort(k);
/* 181*/        if(ai == null)
                {
/* 182*/            dataoutputstream.writeShort(0);
                } else
                {
/* 184*/            i = ai.length;
/* 185*/            dataoutputstream.writeShort(i);
/* 186*/            for(j = 0; j < i; j++)
/* 187*/                dataoutputstream.writeShort(ai[j]);

                }
/* 190*/        dataoutputstream.writeShort(fields.size());
/* 191*/        fields.write(dataoutputstream);
/* 193*/        dataoutputstream.writeShort(methods.size());
/* 194*/        methods.write(dataoutputstream);
/* 195*/        if(attributewriter == null)
                {
/* 196*/            dataoutputstream.writeShort(0);
/* 196*/            return;
                } else
                {
/* 198*/            dataoutputstream.writeShort(attributewriter.size());
/* 199*/            attributewriter.write(dataoutputstream);
/* 201*/            return;
                }
            }

            static void writeAttribute(ByteStream bytestream, AttributeWriter attributewriter, int i)
            {
/* 237*/        if(attributewriter == null)
                {
/* 238*/            bytestream.writeShort(i);
/* 239*/            return;
                }
/* 242*/        bytestream.writeShort(attributewriter.size() + i);
/* 243*/        bytestream = new DataOutputStream(bytestream);
/* 245*/        try
                {
/* 245*/            attributewriter.write(bytestream);
/* 246*/            bytestream.flush();
/* 248*/            return;
                }
/* 248*/        catch(IOException _ex)
                {
/* 249*/            return;
                }
            }

            private ByteStream output;
            private ConstPoolWriter constPool;
            private FieldWriter fields;
            private MethodWriter methods;
            int thisClass;
            int superClass;
}
