// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PathPattern.java

package org.glassfish.jersey.uri;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.uri:
//            PathPattern, UriTemplate

static class 
    implements Comparator
{

            public final int compare(PathPattern pathpattern, PathPattern pathpattern1)
            {
/*  80*/        return UriTemplate.COMPARATOR.compare(PathPattern.access$000(pathpattern), PathPattern.access$000(pathpattern1));
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  76*/        return compare((PathPattern)obj, (PathPattern)obj1);
            }

            ()
            {
            }
}
