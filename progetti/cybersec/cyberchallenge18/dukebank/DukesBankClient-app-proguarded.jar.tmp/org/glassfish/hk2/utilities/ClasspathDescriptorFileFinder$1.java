// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClasspathDescriptorFileFinder.java

package org.glassfish.hk2.utilities;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.utilities:
//            ClasspathDescriptorFileFinder

static class A
    implements PrivilegedAction
{

            public final Boolean run()
            {
/*  69*/        return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.debug.descriptor.file.finder", "false")));
            }

            public final volatile Object run()
            {
/*  66*/        return run();
            }

            A()
            {
            }
}
