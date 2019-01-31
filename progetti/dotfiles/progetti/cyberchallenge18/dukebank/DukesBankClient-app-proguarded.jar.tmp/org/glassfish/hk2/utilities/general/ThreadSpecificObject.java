// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ThreadSpecificObject.java

package org.glassfish.hk2.utilities.general;


// Referenced classes of package org.glassfish.hk2.utilities.general:
//            GeneralUtilities

public class ThreadSpecificObject
{

            public ThreadSpecificObject(Object obj)
            {
/*  57*/        incoming = obj;
/*  60*/        obj = (obj = obj != null ? ((Object) (obj.hashCode())) : 0) ^ Long.valueOf(tid).hashCode();
/*  63*/        hash = ((int) (obj));
            }

            public long getThreadIdentifier()
            {
/*  71*/        return tid;
            }

            public Object getIncomingObject()
            {
/*  79*/        return incoming;
            }

            public int hashCode()
            {
/*  84*/        return hash;
            }

            public boolean equals(Object obj)
            {
/*  89*/        if(obj == null)
/*  89*/            return false;
/*  90*/        if(!(obj instanceof ThreadSpecificObject))
/*  90*/            return false;
/*  91*/        obj = (ThreadSpecificObject)obj;
/*  93*/        if(tid != ((ThreadSpecificObject) (obj)).tid)
/*  93*/            return false;
/*  94*/        else
/*  94*/            return GeneralUtilities.safeEquals(incoming, ((ThreadSpecificObject) (obj)).incoming);
            }

            private final Object incoming;
            private final long tid = Thread.currentThread().getId();
            private final int hash;
}
