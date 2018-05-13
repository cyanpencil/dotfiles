// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriComponent.java

package org.glassfish.jersey.uri;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

// Referenced classes of package org.glassfish.jersey.uri:
//            UriComponent

static final class matrixParameters
    implements PathSegment
{

            public final String getPath()
            {
/* 623*/        return path;
            }

            public final MultivaluedMap getMatrixParameters()
            {
/* 628*/        return matrixParameters;
            }

            public final String toString()
            {
/* 633*/        return path;
            }

            private static final PathSegment EMPTY_PATH_SEGMENT = new <init>("", false);
            private final String path;
            private final MultivaluedMap matrixParameters;



            uedStringMap(String s, boolean flag)
            {
/* 613*/        this(s, flag, ((MultivaluedMap) (new MultivaluedStringMap())));
            }

            uedStringMap(String s, boolean flag, MultivaluedMap multivaluedmap)
            {
/* 617*/        path = flag ? UriComponent.decode(s, T) : s;
/* 618*/        matrixParameters = multivaluedmap;
            }
}
