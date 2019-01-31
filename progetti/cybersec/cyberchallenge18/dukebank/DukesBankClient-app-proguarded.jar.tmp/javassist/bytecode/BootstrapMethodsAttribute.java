// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BootstrapMethodsAttribute.java

package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ByteArray, ConstPool

public class BootstrapMethodsAttribute extends AttributeInfo
{
    public static class BootstrapMethod
    {

                public int methodRef;
                public int arguments[];

                public BootstrapMethod(int i, int ai[])
                {
/*  24*/            methodRef = i;
/*  25*/            arguments = ai;
                }
    }


            BootstrapMethodsAttribute(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  43*/        super(constpool, i, datainputstream);
            }

            public BootstrapMethodsAttribute(ConstPool constpool, BootstrapMethod abootstrapmethod[])
            {
/*  53*/        super(constpool, "BootstrapMethods");
/*  54*/        constpool = 2;
/*  55*/        for(int i = 0; i < abootstrapmethod.length; i++)
/*  56*/            constpool += 4 + (abootstrapmethod[i].arguments.length << 1);

/*  58*/        byte abyte0[] = new byte[constpool];
/*  59*/        ByteArray.write16bit(abootstrapmethod.length, abyte0, 0);
/*  60*/        constpool = 2;
/*  61*/        for(int j = 0; j < abootstrapmethod.length; j++)
                {
/*  62*/            ByteArray.write16bit(abootstrapmethod[j].methodRef, abyte0, constpool);
/*  63*/            ByteArray.write16bit(abootstrapmethod[j].arguments.length, abyte0, constpool + 2);
/*  64*/            int ai[] = abootstrapmethod[j].arguments;
/*  65*/            constpool += 4;
/*  66*/            for(int k = 0; k < ai.length; k++)
                    {
/*  67*/                ByteArray.write16bit(ai[k], abyte0, constpool);
/*  68*/                constpool += 2;
                    }

                }

/*  72*/        set(abyte0);
            }

            public BootstrapMethod[] getMethods()
            {
                byte abyte0[];
                int i;
/*  83*/        BootstrapMethod abootstrapmethod[] = new BootstrapMethod[i = ByteArray.readU16bit(abyte0 = get(), 0)];
/*  86*/        int j = 2;
/*  87*/        for(int k = 0; k < i; k++)
                {
/*  88*/            int l = ByteArray.readU16bit(abyte0, j);
                    int i1;
/*  89*/            int ai[] = new int[i1 = ByteArray.readU16bit(abyte0, j + 2)];
/*  91*/            j += 4;
/*  92*/            for(int j1 = 0; j1 < i1; j1++)
                    {
/*  93*/                ai[j1] = ByteArray.readU16bit(abyte0, j);
/*  94*/                j += 2;
                    }

/*  97*/            abootstrapmethod[k] = new BootstrapMethod(l, ai);
                }

/* 100*/        return abootstrapmethod;
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/* 112*/        BootstrapMethod abootstrapmethod[] = getMethods();
/* 113*/        ConstPool constpool1 = getConstPool();
/* 114*/        for(int i = 0; i < abootstrapmethod.length; i++)
                {
                    BootstrapMethod bootstrapmethod;
/* 115*/            (bootstrapmethod = abootstrapmethod[i]).methodRef = constpool1.copy(bootstrapmethod.methodRef, constpool, map);
/* 117*/            for(int j = 0; j < bootstrapmethod.arguments.length; j++)
/* 118*/                bootstrapmethod.arguments[j] = constpool1.copy(bootstrapmethod.arguments[j], constpool, map);

                }

/* 121*/        return new BootstrapMethodsAttribute(constpool, abootstrapmethod);
            }

            public static final String tag = "BootstrapMethods";
}
