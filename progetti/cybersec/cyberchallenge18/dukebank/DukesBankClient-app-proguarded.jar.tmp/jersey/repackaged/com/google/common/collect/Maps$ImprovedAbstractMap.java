// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps

static abstract class  extends AbstractMap
{

            abstract Set createEntrySet();

            public Set entrySet()
            {
                Set set;
/*3319*/        if((set = entrySet) == null)
/*3320*/            return entrySet = createEntrySet();
/*3320*/        else
/*3320*/            return set;
            }

            public Set keySet()
            {
                Set set;
/*3326*/        if((set = keySet) == null)
/*3327*/            return keySet = createKeySet();
/*3327*/        else
/*3327*/            return set;
            }

            Set createKeySet()
            {
/*3331*/        return new createKeySet(this);
            }

            public Collection values()
            {
                Collection collection;
/*3337*/        if((collection = values) == null)
/*3338*/            return values = createValues();
/*3338*/        else
/*3338*/            return collection;
            }

            Collection createValues()
            {
/*3342*/        return new createValues(this);
            }

            private transient Set entrySet;
            private transient Set keySet;
            private transient Collection values;

            ()
            {
            }
}
