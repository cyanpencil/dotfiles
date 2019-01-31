// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionsAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool

public class ExceptionsAttribute extends AttributeInfo
{

            ExceptionsAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  35*/        super(constpool, i, datainputstream);
            }

            private ExceptionsAttribute(ConstPool constpool, ExceptionsAttribute exceptionsattribute, Map map)
            {
/*  46*/        super(constpool, "Exceptions");
/*  47*/        copyFrom(exceptionsattribute, map);
            }

            public ExceptionsAttribute(ConstPool constpool)
            {
/*  56*/        super(constpool, "Exceptions");
/*  57*/        (constpool = new byte[2])[0] = constpool[1] = 0;
/*  59*/        info = constpool;
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/*  71*/        return new ExceptionsAttribute(constpool, this, map);
            }

            private void copyFrom(ExceptionsAttribute exceptionsattribute, Map map)
            {
/*  83*/        ConstPool constpool = exceptionsattribute.constPool;
/*  84*/        ConstPool constpool1 = constPool;
                int i;
                byte abyte0[];
/*  85*/        (abyte0 = new byte[i = (exceptionsattribute = exceptionsattribute.info).length])[0] = exceptionsattribute[0];
/*  89*/        abyte0[1] = exceptionsattribute[1];
/*  90*/        for(int j = 2; j < i; j += 2)
                {
/*  91*/            int k = ByteArray.readU16bit(exceptionsattribute, j);
/*  92*/            ByteArray.write16bit(constpool.copy(k, constpool1, map), abyte0, j);
                }

/*  96*/        info = abyte0;
            }

            public int[] getExceptionIndexes()
            {
                byte abyte0[];
                int i;
/* 103*/        if((i = (abyte0 = info).length) <= 2)
/* 106*/            return null;
/* 108*/        int ai[] = new int[i / 2 - 1];
/* 109*/        int j = 0;
/* 110*/        for(int k = 2; k < i; k += 2)
/* 111*/            ai[j++] = (abyte0[k] & 0xff) << 8 | abyte0[k + 1] & 0xff;

/* 113*/        return ai;
            }

            public String[] getExceptions()
            {
                byte abyte0[];
                int i;
/* 120*/        if((i = (abyte0 = info).length) <= 2)
/* 123*/            return null;
/* 125*/        String as[] = new String[i / 2 - 1];
/* 126*/        int j = 0;
/* 127*/        for(int k = 2; k < i; k += 2)
                {
/* 128*/            int l = (abyte0[k] & 0xff) << 8 | abyte0[k + 1] & 0xff;
/* 129*/            as[j++] = constPool.getClassInfo(l);
                }

/* 132*/        return as;
            }

            public void setExceptionIndexes(int ai[])
            {
                int i;
/* 139*/        byte abyte0[] = new byte[((i = ai.length) << 1) + 2];
/* 141*/        ByteArray.write16bit(i, abyte0, 0);
/* 142*/        for(int j = 0; j < i; j++)
/* 143*/            ByteArray.write16bit(ai[j], abyte0, (j << 1) + 2);

/* 145*/        info = abyte0;
            }

            public void setExceptions(String as[])
            {
                int i;
/* 152*/        byte abyte0[] = new byte[((i = as.length) << 1) + 2];
/* 154*/        ByteArray.write16bit(i, abyte0, 0);
/* 155*/        for(int j = 0; j < i; j++)
/* 156*/            ByteArray.write16bit(constPool.addClassInfo(as[j]), abyte0, (j << 1) + 2);

/* 159*/        info = abyte0;
            }

            public int tableLength()
            {
/* 165*/        return info.length / 2 - 1;
            }

            public int getException(int i)
            {
/* 171*/        i = (i << 1) + 2;
/* 172*/        return (info[i] & 0xff) << 8 | info[i + 1] & 0xff;
            }

            public static final String tag = "Exceptions";
}
