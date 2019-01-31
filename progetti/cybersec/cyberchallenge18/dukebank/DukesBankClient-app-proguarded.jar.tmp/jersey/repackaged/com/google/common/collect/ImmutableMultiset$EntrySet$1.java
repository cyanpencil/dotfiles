// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultiset.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableAsList, ImmutableMultiset, Multiset, ImmutableCollection

class this._cls1 extends ImmutableAsList
{

            public this._cls1 get(int i)
            {
/* 372*/        return getEntry(i);
            }

            ImmutableCollection delegateCollection()
            {
/* 377*/        return this._cls1.this;
            }

            public volatile Object get(int i)
            {
/* 369*/        return get(i);
            }

            final get this$1;

            ()
            {
/* 369*/        this$1 = this._cls1.this;
/* 369*/        super();
            }
}
