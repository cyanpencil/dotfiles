// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Sets, StandardTable

abstract class <init> extends t
{

            public boolean isEmpty()
            {
/* 199*/        return backingMap.isEmpty();
            }

            public void clear()
            {
/* 203*/        backingMap.clear();
            }

            final StandardTable this$0;

            private t()
            {
/* 197*/        this$0 = StandardTable.this;
/* 197*/        super();
            }

            t(t t)
            {
/* 197*/        this();
            }
}
