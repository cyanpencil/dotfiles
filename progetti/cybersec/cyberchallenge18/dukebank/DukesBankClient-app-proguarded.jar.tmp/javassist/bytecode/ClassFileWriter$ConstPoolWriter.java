// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassFileWriter.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            ByteStream, ClassFileWriter

public static final class output
{

            public final int[] addClassInfo(String as[])
            {
                int i;
/* 539*/        int ai[] = new int[i = as.length];
/* 541*/        for(int j = 0; j < i; j++)
/* 542*/            ai[j] = addClassInfo(as[j]);

/* 544*/        return ai;
            }

            public final int addClassInfo(String s)
            {
/* 558*/        s = addUtf8Info(s);
/* 559*/        output.write(7);
/* 560*/        output.writeShort(s);
/* 561*/        return num++;
            }

            public final int addClassInfo(int i)
            {
/* 571*/        output.write(7);
/* 572*/        output.writeShort(i);
/* 573*/        return num++;
            }

            public final int addNameAndTypeInfo(String s, String s1)
            {
/* 584*/        return addNameAndTypeInfo(addUtf8Info(s), addUtf8Info(s1));
            }

            public final int addNameAndTypeInfo(int i, int j)
            {
/* 595*/        output.write(12);
/* 596*/        output.writeShort(i);
/* 597*/        output.writeShort(j);
/* 598*/        return num++;
            }

            public final int addFieldrefInfo(int i, int j)
            {
/* 609*/        output.write(9);
/* 610*/        output.writeShort(i);
/* 611*/        output.writeShort(j);
/* 612*/        return num++;
            }

            public final int addMethodrefInfo(int i, int j)
            {
/* 623*/        output.write(10);
/* 624*/        output.writeShort(i);
/* 625*/        output.writeShort(j);
/* 626*/        return num++;
            }

            public final int addInterfaceMethodrefInfo(int i, int j)
            {
/* 639*/        output.write(11);
/* 640*/        output.writeShort(i);
/* 641*/        output.writeShort(j);
/* 642*/        return num++;
            }

            public final int addMethodHandleInfo(int i, int j)
            {
/* 657*/        output.write(15);
/* 658*/        output.write(i);
/* 659*/        output.writeShort(j);
/* 660*/        return num++;
            }

            public final int addMethodTypeInfo(int i)
            {
/* 673*/        output.write(16);
/* 674*/        output.writeShort(i);
/* 675*/        return num++;
            }

            public final int addInvokeDynamicInfo(int i, int j)
            {
/* 690*/        output.write(18);
/* 691*/        output.writeShort(i);
/* 692*/        output.writeShort(j);
/* 693*/        return num++;
            }

            public final int addStringInfo(String s)
            {
/* 706*/        s = addUtf8Info(s);
/* 707*/        output.write(8);
/* 708*/        output.writeShort(s);
/* 709*/        return num++;
            }

            public final int addIntegerInfo(int i)
            {
/* 719*/        output.write(3);
/* 720*/        output.writeInt(i);
/* 721*/        return num++;
            }

            public final int addFloatInfo(float f)
            {
/* 731*/        output.write(4);
/* 732*/        output.writeFloat(f);
/* 733*/        return num++;
            }

            public final int addLongInfo(long l)
            {
/* 743*/        output.write(5);
/* 744*/        output.writeLong(l);
/* 745*/        l = num;
/* 746*/        num += 2;
/* 747*/        return l;
            }

            public final int addDoubleInfo(double d)
            {
/* 757*/        output.write(6);
/* 758*/        output.writeDouble(d);
/* 759*/        d = num;
/* 760*/        num += 2;
/* 761*/        return d;
            }

            public final int addUtf8Info(String s)
            {
/* 771*/        output.write(1);
/* 772*/        output.writeUTF(s);
/* 773*/        return num++;
            }

            final void end()
            {
/* 780*/        output.writeShort(startPos, num);
            }

            ByteStream output;
            protected int startPos;
            protected int num;

            (ByteStream bytestream)
            {
/* 527*/        output = bytestream;
/* 528*/        startPos = bytestream.getPos();
/* 529*/        num = 1;
/* 530*/        output.writeShort(1);
            }
}
