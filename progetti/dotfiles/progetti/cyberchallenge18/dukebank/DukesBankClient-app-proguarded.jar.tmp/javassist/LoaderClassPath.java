// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LoaderClassPath.java

package javassist;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

// Referenced classes of package javassist:
//            ClassPath

public class LoaderClassPath
    implements ClassPath
{

            public LoaderClassPath(ClassLoader classloader)
            {
/*  49*/        clref = new WeakReference(classloader);
            }

            public String toString()
            {
/*  53*/        Object obj = null;
/*  54*/        if(clref != null)
/*  55*/            obj = clref.get();
/*  57*/        if(obj == null)
/*  57*/            return "<null>";
/*  57*/        else
/*  57*/            return obj.toString();
            }

            public InputStream openClassfile(String s)
            {
/*  66*/        s = (new StringBuilder()).append(s.replace('.', '/')).append(".class").toString();
                ClassLoader classloader;
/*  67*/        if((classloader = (ClassLoader)clref.get()) == null)
/*  69*/            return null;
/*  71*/        else
/*  71*/            return classloader.getResourceAsStream(s);
            }

            public URL find(String s)
            {
/*  82*/        s = (new StringBuilder()).append(s.replace('.', '/')).append(".class").toString();
                ClassLoader classloader;
/*  83*/        if((classloader = (ClassLoader)clref.get()) == null)
/*  85*/            return null;
/*  87*/        else
/*  87*/            return classloader.getResource(s);
            }

            public void close()
            {
/*  94*/        clref = null;
            }

            private WeakReference clref;
}
