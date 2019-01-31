// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassPoolTail.java

package javassist;

import java.io.*;
import java.net.*;

// Referenced classes of package javassist:
//            ClassPath

final class DirClassPath
    implements ClassPath
{

            DirClassPath(String s)
            {
/*  39*/        directory = s;
            }

            public final InputStream openClassfile(String s)
            {
/*  44*/        char c = File.separatorChar;
/*  45*/        s = (new StringBuilder()).append(directory).append(c).append(s.replace('.', c)).append(".class").toString();
/*  47*/        return new FileInputStream(s.toString());
/*  49*/        JVM INSTR pop ;
/*  50*/        break MISSING_BLOCK_LABEL_58;
/*  50*/        JVM INSTR pop ;
/*  51*/        return null;
            }

            public final URL find(String s)
            {
/*  55*/        char c = File.separatorChar;
/*  56*/        s = (new StringBuilder()).append(directory).append(c).append(s.replace('.', c)).append(".class").toString();
/*  58*/        if(!(s = new File(s)).exists())
/*  61*/            break MISSING_BLOCK_LABEL_73;
/*  61*/        return s.getCanonicalFile().toURI().toURL();
/*  63*/        JVM INSTR pop ;
/*  64*/        break MISSING_BLOCK_LABEL_73;
/*  64*/        JVM INSTR pop ;
/*  66*/        return null;
            }

            public final void close()
            {
            }

            public final String toString()
            {
/*  72*/        return directory;
            }

            String directory;
}
