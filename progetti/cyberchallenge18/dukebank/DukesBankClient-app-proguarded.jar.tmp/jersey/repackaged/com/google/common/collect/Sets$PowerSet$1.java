// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractIndexedListIterator, Sets

class istIterator extends AbstractIndexedListIterator
{

            protected Set get(int i)
            {
/*1301*/        return new t>(putSet, i);
            }

            protected volatile Object get(int i)
            {
/*1299*/        return get(i);
            }

            final get this$0;

            istIterator(int i)
            {
/*1299*/        this$0 = this._cls0.this;
/*1299*/        super(i);
            }
}
