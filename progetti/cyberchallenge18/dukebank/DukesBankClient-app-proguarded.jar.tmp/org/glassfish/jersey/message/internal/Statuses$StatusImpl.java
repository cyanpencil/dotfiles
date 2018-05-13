// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Statuses.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.Response;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            Statuses

static final class family
    implements javax.ws.rs.core..Statuses.StatusImpl
{

            public final int getStatusCode()
            {
/*  68*/        return code;
            }

            public final String getReasonPhrase()
            {
/*  73*/        return reason;
            }

            public final String toString()
            {
/*  78*/        return reason;
            }

            public final javax.ws.rs.core.ly getFamily()
            {
/*  83*/        return family;
            }

            public final boolean equals(Object obj)
            {
/*  89*/        if(this == obj)
/*  90*/            return true;
/*  92*/        if(!(obj instanceof javax.ws.rs.core..Statuses.StatusImpl))
/*  93*/            return false;
/*  96*/        obj = (javax.ws.rs.core.)obj;
/*  98*/        if(code != ((javax.ws.rs.core..Statuses.StatusImpl.code) (obj)).getStatusCode())
/*  99*/            return false;
/* 101*/        if(family != ((javax.ws.rs.core..Statuses.StatusImpl.family) (obj)).getFamily())
/* 102*/            return false;
/* 104*/        return reason == null ? ((javax.ws.rs.core..Statuses.StatusImpl.reason) (obj)).getReasonPhrase() == null : reason.equals(((javax.ws.rs.core..Statuses.StatusImpl.reason) (obj)).getReasonPhrase());
            }

            public final int hashCode()
            {
/* 113*/        int i = code;
/* 114*/        i = i * 31 + (reason == null ? 0 : reason.hashCode());
/* 115*/        return i = i * 31 + family.hashCode();
            }

            private final int code;
            private final String reason;
            private final javax.ws.rs.core.ly family;

            private (int i, String s)
            {
/*  61*/        code = i;
/*  62*/        reason = s;
/*  63*/        family = javax.ws.rs.core.ly.familyOf(i);
            }

}
