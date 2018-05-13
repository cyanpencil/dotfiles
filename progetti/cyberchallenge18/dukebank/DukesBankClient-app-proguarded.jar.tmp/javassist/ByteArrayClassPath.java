// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByteArrayClassPath.java

package javassist;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

// Referenced classes of package javassist:
//            ClassPath

public class ByteArrayClassPath
    implements ClassPath
{

            public ByteArrayClassPath(String s, byte abyte0[])
            {
/*  61*/        classname = s;
/*  62*/        classfile = abyte0;
            }

            public void close()
            {
            }

            public String toString()
            {
/*  71*/        return (new StringBuilder("byte[]:")).append(classname).toString();
            }

            public InputStream openClassfile(String s)
            {
/*  78*/        if(classname.equals(s))
/*  79*/            return new ByteArrayInputStream(classfile);
/*  81*/        else
/*  81*/            return null;
            }

            public URL find(String s)
            {
/*  88*/        if(!classname.equals(s))
/*  89*/            break MISSING_BLOCK_LABEL_63;
/*  89*/        s = (new StringBuilder()).append(s.replace('.', '/')).append(".class").toString();
/*  92*/        return new URL((new StringBuilder("file:/ByteArrayClassPath/")).append(s).toString());
/*  94*/        JVM INSTR pop ;
/*  97*/        return null;
            }

            protected String classname;
            protected byte classfile[];
}
