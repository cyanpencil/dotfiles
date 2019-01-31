// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator, ImmutableList, ImmutableMap, ImmutableSet, 
//            Sets

class this._cls0 extends UnmodifiableIterator
{

            public boolean hasNext()
            {
/*1249*/        return remainingSetBits != 0;
            }

            public Object next()
            {
                int i;
/*1254*/        if((i = Integer.numberOfTrailingZeros(remainingSetBits)) == 32)
                {
/*1256*/            throw new NoSuchElementException();
                } else
                {
/*1258*/            remainingSetBits &= ~(1 << i);
/*1259*/            return elements.get(i);
                }
            }

            final ImmutableList elements;
            int remainingSetBits;
            final get this$0;

            erator()
            {
/*1243*/        this$0 = this._cls0.this;
/*1243*/        super();
/*1244*/        elements = cess._mth100(this._cls0.this).keySet().asList();
/*1245*/        remainingSetBits = cess._mth200(this._cls0.this);
            }
}
