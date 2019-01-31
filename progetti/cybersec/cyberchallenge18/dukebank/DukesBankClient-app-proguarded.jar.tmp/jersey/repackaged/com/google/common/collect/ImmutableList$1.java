// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableList.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractIndexedListIterator, ImmutableList

class istIterator extends AbstractIndexedListIterator
{

            protected Object get(int i)
            {
/* 351*/        return ImmutableList.this.get(i);
            }

            final ImmutableList this$0;

            istIterator(int i, int j)
            {
/* 348*/        this$0 = ImmutableList.this;
/* 348*/        super(i, j);
            }
}
