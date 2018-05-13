// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassClassPath.java

package javassist;

import java.io.InputStream;
import java.net.URL;

// Referenced classes of package javassist:
//            ClassPath

public class ClassClassPath
    implements ClassPath
{

            public ClassClassPath(Class class1)
            {
/*  55*/        thisClass = class1;
            }

            ClassClassPath()
            {
/*  67*/        this(java/lang/Object);
            }

            public InputStream openClassfile(String s)
            {
/*  74*/        s = (new StringBuilder("/")).append(s.replace('.', '/')).append(".class").toString();
/*  75*/        return thisClass.getResourceAsStream(s);
            }

            public URL find(String s)
            {
/*  84*/        s = (new StringBuilder("/")).append(s.replace('.', '/')).append(".class").toString();
/*  85*/        return thisClass.getResource(s);
            }

            public void close()
            {
            }

            public String toString()
            {
/*  95*/        return (new StringBuilder()).append(thisClass.getName()).append(".class").toString();
            }

            private Class thisClass;
}
