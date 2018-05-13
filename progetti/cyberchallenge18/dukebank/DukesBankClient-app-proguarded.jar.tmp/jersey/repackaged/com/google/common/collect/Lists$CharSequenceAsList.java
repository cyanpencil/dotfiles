// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.util.AbstractList;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Lists

static final class sequence extends AbstractList
{

            public final Character get(int i)
            {
/* 765*/        Preconditions.checkElementIndex(i, size());
/* 766*/        return Character.valueOf(sequence.charAt(i));
            }

            public final int size()
            {
/* 770*/        return sequence.length();
            }

            public final volatile Object get(int i)
            {
/* 756*/        return get(i);
            }

            private final CharSequence sequence;

            (CharSequence charsequence)
            {
/* 761*/        sequence = charsequence;
            }
}
