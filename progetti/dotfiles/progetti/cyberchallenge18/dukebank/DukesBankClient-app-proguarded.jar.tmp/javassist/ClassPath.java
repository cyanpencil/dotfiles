// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassPath.java

package javassist;

import java.io.InputStream;
import java.net.URL;

// Referenced classes of package javassist:
//            NotFoundException

public interface ClassPath
{

    public abstract InputStream openClassfile(String s)
        throws NotFoundException;

    public abstract URL find(String s);

    public abstract void close();
}
