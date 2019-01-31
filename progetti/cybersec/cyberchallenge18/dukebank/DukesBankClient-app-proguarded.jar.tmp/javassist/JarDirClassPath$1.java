// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassPoolTail.java

package javassist;

import java.io.File;
import java.io.FilenameFilter;

// Referenced classes of package javassist:
//            JarDirClassPath

class this._cls0
    implements FilenameFilter
{

            public boolean accept(File file, String s)
            {
/*  82*/        return (s = s.toLowerCase()).endsWith(".jar") || s.endsWith(".zip");
            }

            final JarDirClassPath this$0;

            ()
            {
/*  80*/        this$0 = JarDirClassPath.this;
/*  80*/        super();
            }
}
