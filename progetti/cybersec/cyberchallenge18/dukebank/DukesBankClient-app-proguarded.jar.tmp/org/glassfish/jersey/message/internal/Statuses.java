// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Statuses.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.Response;

public final class Statuses
{
    static final class StatusImpl
        implements javax.ws.rs.core.Response.StatusType
    {

                public final int getStatusCode()
                {
/*  68*/            return code;
                }

                public final String getReasonPhrase()
                {
/*  73*/            return reason;
                }

                public final String toString()
                {
/*  78*/            return reason;
                }

                public final javax.ws.rs.core.Response.Status.Family getFamily()
                {
/*  83*/            return family;
                }

                public final boolean equals(Object obj)
                {
/*  89*/            if(this == obj)
/*  90*/                return true;
/*  92*/            if(!(obj instanceof javax.ws.rs.core.Response.StatusType))
/*  93*/                return false;
/*  96*/            obj = (javax.ws.rs.core.Response.StatusType)obj;
/*  98*/            if(code != ((javax.ws.rs.core.Response.StatusType) (obj)).getStatusCode())
/*  99*/                return false;
/* 101*/            if(family != ((javax.ws.rs.core.Response.StatusType) (obj)).getFamily())
/* 102*/                return false;
/* 104*/            return reason == null ? ((javax.ws.rs.core.Response.StatusType) (obj)).getReasonPhrase() == null : reason.equals(((javax.ws.rs.core.Response.StatusType) (obj)).getReasonPhrase());
                }

                public final int hashCode()
                {
/* 113*/            int i = code;
/* 114*/            i = i * 31 + (reason == null ? 0 : reason.hashCode());
/* 115*/            return i = i * 31 + family.hashCode();
                }

                private final int code;
                private final String reason;
                private final javax.ws.rs.core.Response.Status.Family family;

                private StatusImpl(int i, String s)
                {
/*  61*/            code = i;
/*  62*/            reason = s;
/*  63*/            family = javax.ws.rs.core.Response.Status.Family.familyOf(i);
                }

    }


            public static javax.ws.rs.core.Response.StatusType from(int i)
            {
                javax.ws.rs.core.Response.Status status;
/* 131*/        if((status = javax.ws.rs.core.Response.Status.fromStatusCode(i)) != null)
/* 132*/            return status;
/* 132*/        else
/* 132*/            return new StatusImpl(i, "");
            }

            public static javax.ws.rs.core.Response.StatusType from(int i, String s)
            {
/* 143*/        return new StatusImpl(i, s);
            }

            public static javax.ws.rs.core.Response.StatusType from(javax.ws.rs.core.Response.StatusType statustype, String s)
            {
/* 154*/        return new StatusImpl(statustype.getStatusCode(), s);
            }

            private Statuses()
            {
/* 161*/        throw new AssertionError("Instantiation not allowed.");
            }
}
