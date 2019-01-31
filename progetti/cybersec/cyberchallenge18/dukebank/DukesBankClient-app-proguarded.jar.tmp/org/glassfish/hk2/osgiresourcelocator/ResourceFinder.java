// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResourceFinder.java

package org.glassfish.hk2.osgiresourcelocator;

import java.net.URL;
import java.util.List;

public abstract class ResourceFinder
{

            public ResourceFinder()
            {
            }

            public static void initialize(ResourceFinder resourcefinder)
            {
/*  56*/        if(resourcefinder == null)
/*  56*/            throw new NullPointerException("Did you intend to call reset()?");
/*  57*/        if(_me != null)
                {
/*  57*/            throw new IllegalStateException((new StringBuilder("Already initialzed with [")).append(_me).append("]").toString());
                } else
                {
/*  58*/            _me = resourcefinder;
/*  59*/            return;
                }
            }

            public static synchronized void reset()
            {
/*  62*/        if(_me == null)
                {
/*  63*/            throw new IllegalStateException("Not yet initialized");
                } else
                {
/*  65*/            _me = null;
/*  66*/            return;
                }
            }

            public static URL findEntry(String s)
            {
/*  70*/        if(_me == null)
/*  70*/            return null;
/*  71*/        else
/*  71*/            return _me.findEntry1(s);
            }

            public static List findEntries(String s)
            {
/*  75*/        if(_me == null)
/*  75*/            return null;
/*  76*/        else
/*  76*/            return _me.findEntries1(s);
            }

            abstract URL findEntry1(String s);

            abstract List findEntries1(String s);

            private static ResourceFinder _me;
}
