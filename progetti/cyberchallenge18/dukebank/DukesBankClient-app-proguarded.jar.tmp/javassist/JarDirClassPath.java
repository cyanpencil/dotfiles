// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassPoolTail.java

package javassist;

import java.io.*;
import java.net.URL;

// Referenced classes of package javassist:
//            ClassPath, JarClassPath, NotFoundException

final class JarDirClassPath
    implements ClassPath
{

            JarDirClassPath(String s)
                throws NotFoundException
            {
/*  80*/        if((s = (new File(s)).listFiles(new FilenameFilter() {

                public boolean accept(File file, String s1)
                {
/*  82*/            return (s1 = s1.toLowerCase()).endsWith(".jar") || s1.endsWith(".zip");
                }

                final JarDirClassPath this$0;

                    
                    {
/*  80*/                this$0 = JarDirClassPath.this;
/*  80*/                super();
                    }
    })) != null)
                {
/*  88*/            jars = new JarClassPath[s.length];
/*  89*/            for(int i = 0; i < s.length; i++)
/*  90*/                jars[i] = new JarClassPath(s[i].getPath());

                }
            }

            public final InputStream openClassfile(String s)
                throws NotFoundException
            {
/*  95*/        if(jars != null)
                {
/*  96*/            for(int i = 0; i < jars.length; i++)
                    {
                        InputStream inputstream;
/*  97*/                if((inputstream = jars[i].openClassfile(s)) != null)
/*  99*/                    return inputstream;
                    }

                }
/* 102*/        return null;
            }

            public final URL find(String s)
            {
/* 106*/        if(jars != null)
                {
/* 107*/            for(int i = 0; i < jars.length; i++)
                    {
                        URL url;
/* 108*/                if((url = jars[i].find(s)) != null)
/* 110*/                    return url;
                    }

                }
/* 113*/        return null;
            }

            public final void close()
            {
/* 117*/        if(jars != null)
                {
/* 118*/            for(int i = 0; i < jars.length; i++)
/* 119*/                jars[i].close();

                }
            }

            JarClassPath jars[];
}
