// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Splitter.java

package jersey.repackaged.com.google.common.base;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Joiner, Splitter

class val.sequence
    implements Iterable
{

            public Iterator iterator()
            {
/* 390*/        return Splitter.access$000(Splitter.this, val$sequence);
            }

            public String toString()
            {
/* 393*/        return Joiner.on(", ").appendTo(new StringBuilder("["), this).append(']').toString();
            }

            final CharSequence val$sequence;
            final Splitter this$0;

            _cls9()
            {
/* 388*/        this$0 = final_splitter;
/* 388*/        val$sequence = CharSequence.this;
/* 388*/        super();
            }
}
