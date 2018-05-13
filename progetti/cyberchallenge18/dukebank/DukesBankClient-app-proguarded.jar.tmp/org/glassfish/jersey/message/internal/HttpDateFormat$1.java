// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpDateFormat.java

package org.glassfish.jersey.message.internal;

import java.util.List;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpDateFormat

static class  extends ThreadLocal
{

            protected final synchronized List initialValue()
            {
/*  80*/        return HttpDateFormat.access$000();
            }

            protected final volatile Object initialValue()
            {
/*  76*/        return initialValue();
            }

            ()
            {
            }
}
