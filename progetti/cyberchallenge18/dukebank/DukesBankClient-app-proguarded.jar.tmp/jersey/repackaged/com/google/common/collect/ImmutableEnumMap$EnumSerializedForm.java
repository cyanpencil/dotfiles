// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableEnumMap.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableEnumMap, ImmutableSet, Iterators, UnmodifiableIterator

static class delegate
    implements Serializable
{

            final EnumMap _flddelegate;

            (EnumMap enummap)
            {
/* 144*/        _flddelegate = enummap;
            }

            // Unreferenced inner class jersey/repackaged/com/google/common/collect/ImmutableEnumMap$1

/* anonymous class */
    class ImmutableEnumMap._cls1 extends ImmutableSet
    {

                public boolean contains(Object obj)
                {
/*  63*/            return ImmutableEnumMap.access$000(ImmutableEnumMap.this).containsKey(obj);
                }

                public int size()
                {
/*  68*/            return ImmutableEnumMap.this.size();
                }

                public UnmodifiableIterator iterator()
                {
/*  73*/            return Iterators.unmodifiableIterator(ImmutableEnumMap.access$000(ImmutableEnumMap.this).keySet().iterator());
                }

                boolean isPartialView()
                {
/*  78*/            return true;
                }

                public volatile Iterator iterator()
                {
/*  59*/            return iterator();
                }

                final ImmutableEnumMap this$0;

                    
                    {
/*  59*/                this$0 = ImmutableEnumMap.this;
/*  59*/                super();
                    }
    }

}
