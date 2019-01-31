// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import jersey.repackaged.com.google.common.base.Converter;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            BiMap, Maps

static final class ull extends Converter
    implements Serializable
{

            protected final Object doForward(Object obj)
            {
/*1327*/        return convert(bimap, obj);
            }

            private static Object convert(BiMap bimap1, Object obj)
            {
/*1336*/        Preconditions.checkArgument((bimap1 = ((BiMap) (bimap1.get(obj)))) != null, "No non-null mapping present for input: %s", new Object[] {
/*1337*/            obj
                });
/*1338*/        return bimap1;
            }

            public final boolean equals(Object obj)
            {
/*1343*/        if(obj instanceof ment)
                {
/*1344*/            obj = (ment)obj;
/*1345*/            return bimap.equals(((bimap) (obj)).bimap);
                } else
                {
/*1347*/            return false;
                }
            }

            public final int hashCode()
            {
/*1352*/        return bimap.hashCode();
            }

            public final String toString()
            {
/*1358*/        String s = String.valueOf(String.valueOf(bimap));
/*1358*/        return (new StringBuilder(18 + s.length())).append("Maps.asConverter(").append(s).append(")").toString();
            }

            private final BiMap bimap;

            (BiMap bimap1)
            {
/*1322*/        bimap = (BiMap)Preconditions.checkNotNull(bimap1);
            }
}
