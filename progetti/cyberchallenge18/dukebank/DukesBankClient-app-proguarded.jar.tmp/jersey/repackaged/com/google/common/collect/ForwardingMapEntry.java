// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingMapEntry.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import jersey.repackaged.com.google.common.base.Objects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingObject

public abstract class ForwardingMapEntry extends ForwardingObject
    implements java.util.Map.Entry
{

            protected ForwardingMapEntry()
            {
            }

            protected abstract java.util.Map.Entry _mthdelegate();

            public Object getKey()
            {
/*  66*/        return _mthdelegate().getKey();
            }

            public Object getValue()
            {
/*  71*/        return _mthdelegate().getValue();
            }

            public Object setValue(Object obj)
            {
/*  76*/        return _mthdelegate().setValue(obj);
            }

            public boolean equals(Object obj)
            {
/*  80*/        return _mthdelegate().equals(obj);
            }

            public int hashCode()
            {
/*  84*/        return _mthdelegate().hashCode();
            }

            protected boolean standardEquals(Object obj)
            {
/*  96*/        if(obj instanceof java.util.Map.Entry)
                {
/*  97*/            obj = (java.util.Map.Entry)obj;
/*  98*/            return Objects.equal(getKey(), ((java.util.Map.Entry) (obj)).getKey()) && Objects.equal(getValue(), ((java.util.Map.Entry) (obj)).getValue());
                } else
                {
/* 101*/            return false;
                }
            }

            protected volatile Object _mthdelegate()
            {
/*  54*/        return _mthdelegate();
            }
}
