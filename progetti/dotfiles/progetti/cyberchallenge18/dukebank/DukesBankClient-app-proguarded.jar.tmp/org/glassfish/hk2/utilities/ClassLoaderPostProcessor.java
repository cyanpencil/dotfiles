// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassLoaderPostProcessor.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.*;

// Referenced classes of package org.glassfish.hk2.utilities:
//            DescriptorImpl, HK2LoaderImpl

public class ClassLoaderPostProcessor
    implements PopulatorPostProcessor
{

            public ClassLoaderPostProcessor(ClassLoader classloader, boolean flag)
            {
/*  70*/        loader = new HK2LoaderImpl(classloader);
/*  71*/        force = flag;
            }

            public ClassLoaderPostProcessor(ClassLoader classloader)
            {
/*  84*/        this(classloader, false);
            }

            public DescriptorImpl process(ServiceLocator servicelocator, DescriptorImpl descriptorimpl)
            {
/*  90*/        if(force)
                {
/*  92*/            descriptorimpl.setLoader(loader);
/*  93*/            return descriptorimpl;
                }
/*  96*/        if(descriptorimpl.getLoader() != null)
                {
/*  98*/            return descriptorimpl;
                } else
                {
/* 102*/            descriptorimpl.setLoader(loader);
/* 103*/            return descriptorimpl;
                }
            }

            private final HK2Loader loader;
            private final boolean force;
}
