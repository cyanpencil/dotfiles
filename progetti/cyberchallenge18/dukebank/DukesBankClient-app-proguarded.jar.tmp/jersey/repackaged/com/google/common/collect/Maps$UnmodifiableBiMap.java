// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingMap, BiMap, Maps

static class inverse extends ForwardingMap
    implements Serializable, BiMap
{

            protected Map _mthdelegate()
            {
/*1430*/        return unmodifiableMap;
            }

            public BiMap inverse()
            {
                BiMap bimap;
/*1440*/        if((bimap = inverse) == null)
/*1441*/            return inverse = new <init>(_flddelegate.inverse(), this);
/*1441*/        else
/*1441*/            return bimap;
            }

            public Set values()
            {
                Set set;
/*1447*/        if((set = values) == null)
/*1448*/            return values = Collections.unmodifiableSet(_flddelegate.values());
/*1448*/        else
/*1448*/            return set;
            }

            public volatile Collection values()
            {
/*1415*/        return values();
            }

            protected volatile Object _mthdelegate()
            {
/*1415*/        return _mthdelegate();
            }

            final Map unmodifiableMap;
            final BiMap _flddelegate;
            BiMap inverse;
            transient Set values;

            (BiMap bimap, BiMap bimap1)
            {
/*1424*/        unmodifiableMap = Collections.unmodifiableMap(bimap);
/*1425*/        _flddelegate = bimap;
/*1426*/        inverse = bimap1;
            }
}
