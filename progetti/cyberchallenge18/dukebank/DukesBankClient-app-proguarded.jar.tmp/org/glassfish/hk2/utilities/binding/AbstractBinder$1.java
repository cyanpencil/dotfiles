// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBinder.java

package org.glassfish.hk2.utilities.binding;

import java.security.PrivilegedAction;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            AbstractBinder

class this._cls0
    implements PrivilegedAction
{

            public ClassLoader run()
            {
                ClassLoader classloader;
/* 346*/        if((classloader = getClass().getClassLoader()) == null)
/* 348*/            return ClassLoader.getSystemClassLoader();
/* 350*/        else
/* 350*/            return classloader;
            }

            public volatile Object run()
            {
/* 342*/        return run();
            }

            final AbstractBinder this$0;

            ()
            {
/* 342*/        this$0 = AbstractBinder.this;
/* 342*/        super();
            }
}
