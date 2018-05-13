// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap, Sets

class <init> extends <init>
    implements Set
{

            public int hashCode()
            {
/* 134*/        return Sets.hashCodeImpl(this);
            }

            public boolean equals(Object obj)
            {
/* 139*/        return Sets.equalsImpl(this, obj);
            }

            final AbstractMultimap this$0;

            private ()
            {
/* 131*/        this$0 = AbstractMultimap.this;
/* 131*/        super(AbstractMultimap.this, null);
            }

            init>(init> init>)
            {
/* 131*/        this();
            }
}
