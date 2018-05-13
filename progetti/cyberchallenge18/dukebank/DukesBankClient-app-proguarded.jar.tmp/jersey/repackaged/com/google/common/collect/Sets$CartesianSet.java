// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingCollection, CartesianList, ImmutableList, ImmutableSet, 
//            Sets

static final class delegate extends ForwardingCollection
    implements Set
{

            static Set create(List list)
            {
/*1124*/        der der = new der(list.size());
                Object obj;
/*1126*/        for(list = list.iterator(); list.hasNext(); der.add(obj))
/*1126*/            if(((ImmutableSet) (obj = ImmutableSet.copyOf(((Collection) (obj = (Set)list.next()))))).isEmpty())
/*1129*/                return ImmutableSet.of();

/*1133*/        list = der.build();
/*1134*/        ImmutableList immutablelist = new ImmutableList(list) {

                    public final int size()
                    {
/*1138*/                return axes.size();
                    }

                    public final List get(int i)
                    {
/*1143*/                return ((ImmutableSet)axes.get(i)).asList();
                    }

                    final boolean isPartialView()
                    {
/*1148*/                return true;
                    }

                    public final volatile Object get(int i)
                    {
/*1134*/                return get(i);
                    }

                    final ImmutableList val$axes;

                    
                    {
/*1134*/                axes = immutablelist;
/*1134*/                super();
                    }
        };
/*1151*/        return new <init>(list, new CartesianList(immutablelist));
            }

            protected final Collection _mthdelegate()
            {
/*1162*/        return _flddelegate;
            }

            public final boolean equals(Object obj)
            {
/*1168*/        if(obj instanceof delegate)
                {
/*1169*/            obj = (delegate)obj;
/*1170*/            return axes.equals(((ls) (obj)).axes);
                } else
                {
/*1172*/            return super.equals(obj);
                }
            }

            public final int hashCode()
            {
/*1181*/        int i = size() - 1;
/*1182*/        for(int j = 0; j < axes.size(); j++)
/*1183*/            i = ~~(i *= 31);

/*1187*/        int k = 1;
/*1188*/        for(Iterator iterator = axes.iterator(); iterator.hasNext();)
                {
/*1188*/            Set set = (Set)iterator.next();
/*1189*/            k = ~~(k = k * 31 + (size() / set.size()) * set.hashCode());
                }

/*1193*/        return ~~(k += i);
            }

            protected final volatile Object _mthdelegate()
            {
/*1118*/        return _mthdelegate();
            }

            private final transient ImmutableList axes;
            private final transient CartesianList _flddelegate;

            private t>(ImmutableList immutablelist, CartesianList cartesianlist)
            {
/*1156*/        axes = immutablelist;
/*1157*/        _flddelegate = cartesianlist;
            }
}
