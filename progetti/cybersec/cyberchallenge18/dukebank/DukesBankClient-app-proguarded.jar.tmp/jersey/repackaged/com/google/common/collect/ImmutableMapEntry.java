// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMapEntry.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableEntry, CollectPreconditions

abstract class ImmutableMapEntry extends ImmutableEntry
{
    static final class TerminalEntry extends ImmutableMapEntry
    {

                final ImmutableMapEntry getNextInKeyBucket()
                {
/*  63*/            return null;
                }

                TerminalEntry(Object obj, Object obj1)
                {
/*  57*/            super(obj, obj1);
                }
    }


            ImmutableMapEntry(Object obj, Object obj1)
            {
/*  36*/        super(obj, obj1);
/*  37*/        CollectPreconditions.checkEntryNotNull(obj, obj1);
            }

            ImmutableMapEntry(ImmutableMapEntry immutablemapentry)
            {
/*  41*/        super(immutablemapentry.getKey(), immutablemapentry.getValue());
            }

            abstract ImmutableMapEntry getNextInKeyBucket();
}
