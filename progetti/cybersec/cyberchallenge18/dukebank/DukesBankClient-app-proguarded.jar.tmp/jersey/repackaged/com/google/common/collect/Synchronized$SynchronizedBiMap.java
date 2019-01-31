// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            BiMap, Synchronized

static class <init> extends <init>
    implements Serializable, BiMap
{

            BiMap _mthdelegate()
            {
/*1123*/        return (BiMap)super.legate();
            }

            public Set values()
            {
/*1127*/        Object obj = mutex;
/*1127*/        JVM INSTR monitorenter ;
/*1128*/        if(valueSet == null)
/*1129*/            valueSet = Synchronized.set(_mthdelegate().values(), mutex);
/*1131*/        return valueSet;
                Exception exception;
/*1132*/        exception;
/*1132*/        throw exception;
            }

            public BiMap inverse()
            {
/*1144*/        Object obj = mutex;
/*1144*/        JVM INSTR monitorenter ;
/*1145*/        if(inverse == null)
/*1146*/            inverse = new <init>(_mthdelegate().inverse(), mutex, this);
/*1149*/        return inverse;
                Exception exception;
/*1150*/        exception;
/*1150*/        throw exception;
            }

            public volatile Collection values()
            {
/*1111*/        return values();
            }

            volatile Map _mthdelegate()
            {
/*1111*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/*1111*/        return _mthdelegate();
            }

            private transient Set valueSet;
            private transient BiMap inverse;

            private (BiMap bimap, Object obj, BiMap bimap1)
            {
/*1118*/        super(bimap, obj);
/*1119*/        inverse = bimap1;
            }

            inverse(BiMap bimap, Object obj, BiMap bimap1, inverse inverse1)
            {
/*1111*/        this(bimap, obj, bimap1);
            }
}
