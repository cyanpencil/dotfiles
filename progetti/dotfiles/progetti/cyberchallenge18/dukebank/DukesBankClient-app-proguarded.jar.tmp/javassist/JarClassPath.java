// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassPoolTail.java

package javassist;

import java.io.*;
import java.net.*;
import java.util.jar.JarFile;

// Referenced classes of package javassist:
//            ClassPath, NotFoundException

final class JarClassPath
    implements ClassPath
{

            JarClassPath(String s)
                throws NotFoundException
            {
/* 129*/        try
                {
/* 129*/            jarfile = new JarFile(s);
/* 130*/            jarfileURL = (new File(s)).getCanonicalFile().toURI().toURL().toString();
/* 132*/            return;
                }
/* 134*/        catch(IOException _ex)
                {
/* 135*/            throw new NotFoundException(s);
                }
            }

            public final InputStream openClassfile(String s)
                throws NotFoundException
            {
/* 142*/        s = (new StringBuilder()).append(s.replace('.', '/')).append(".class").toString();
/* 143*/        if((s = jarfile.getJarEntry(s)) != null)
/* 145*/            return jarfile.getInputStream(s);
/* 147*/        else
/* 147*/            return null;
/* 149*/        JVM INSTR pop ;
/* 150*/        throw new NotFoundException((new StringBuilder("broken jar file?: ")).append(jarfile.getName()).toString());
            }

            public final URL find(String s)
            {
/* 155*/        s = (new StringBuilder()).append(s.replace('.', '/')).append(".class").toString();
                java.util.jar.JarEntry jarentry;
/* 156*/        if((jarentry = jarfile.getJarEntry(s)) == null)
/* 159*/            break MISSING_BLOCK_LABEL_77;
/* 159*/        return new URL((new StringBuilder("jar:")).append(jarfileURL).append("!/").append(s).toString());
/* 161*/        JVM INSTR pop ;
/* 163*/        return null;
            }

            public final void close()
            {
/* 168*/        try
                {
/* 168*/            jarfile.close();
/* 169*/            jarfile = null;
/* 171*/            return;
                }
/* 171*/        catch(IOException _ex)
                {
/* 172*/            return;
                }
            }

            public final String toString()
            {
/* 175*/        if(jarfile == null)
/* 175*/            return "<null>";
/* 175*/        else
/* 175*/            return jarfile.toString();
            }

            JarFile jarfile;
            String jarfileURL;
}
