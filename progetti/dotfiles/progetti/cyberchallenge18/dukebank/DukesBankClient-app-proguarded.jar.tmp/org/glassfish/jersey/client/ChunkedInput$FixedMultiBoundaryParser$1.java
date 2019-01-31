// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChunkedInput.java

package org.glassfish.jersey.client;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.client:
//            ChunkedInput

class this._cls0
    implements Comparator
{

            public int compare(byte abyte0[], byte abyte1[])
            {
/* 295*/        return Integer.compare(abyte0.length, abyte1.length);
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 292*/        return compare((byte[])obj, (byte[])obj1);
            }

            final compare this$0;

            I()
            {
/* 292*/        this$0 = this._cls0.this;
/* 292*/        super();
            }
}
