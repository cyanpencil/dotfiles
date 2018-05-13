// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResourceFinderImpl.java

package org.glassfish.hk2.osgiresourcelocator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.osgi.framework.*;

// Referenced classes of package org.glassfish.hk2.osgiresourcelocator:
//            ResourceFinder

public class ResourceFinderImpl extends ResourceFinder
{

            public ResourceFinderImpl()
            {
                ClassLoader classloader;
/*  56*/        if((classloader = getClass().getClassLoader()) instanceof BundleReference)
/*  58*/            bundleContext = ((BundleReference)org/osgi/framework/BundleReference.cast(classloader)).getBundle().getBundleContext();
/*  60*/        if(bundleContext == null)
/*  61*/            throw new RuntimeException("There is no bundle context available yet. Instatiate this class in STARTING or ACTIVE state only");
/*  64*/        else
/*  64*/            return;
            }

            URL findEntry1(String s)
            {
                Bundle abundle[];
/*  67*/        int i = (abundle = bundleContext.getBundles()).length;
/*  67*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*  67*/            if((obj = ((Bundle) (obj = abundle[j])).getEntry(s)) != null)
/*  69*/                return ((URL) (obj));
                }

/*  71*/        return null;
            }

            List findEntries1(String s)
            {
/*  75*/        ArrayList arraylist = new ArrayList();
                Bundle abundle[];
/*  76*/        int i = (abundle = bundleContext.getBundles()).length;
/*  76*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*  76*/            if((obj = ((Bundle) (obj = abundle[j])).getEntry(s)) != null)
/*  78*/                arraylist.add(obj);
                }

/*  80*/        return arraylist;
            }

            private BundleContext bundleContext;
}
