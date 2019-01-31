// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CartesianList.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.math.IntMath;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList

final class CartesianList extends AbstractList
    implements RandomAccess
{

            static List create(List list)
            {
/*  41*/        ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
                Object obj;
/*  43*/        for(list = list.iterator(); list.hasNext(); builder.add(obj))
/*  43*/            if(((List) (obj = ImmutableList.copyOf(((java.util.Collection) (obj = (List)list.next()))))).isEmpty())
/*  46*/                return ImmutableList.of();

/*  50*/        return new CartesianList(builder.build());
            }

            CartesianList(ImmutableList immutablelist)
            {
/*  54*/        axes = immutablelist;
                int ai[];
/*  55*/        (ai = new int[immutablelist.size() + 1])[immutablelist.size()] = 1;
/*  58*/        try
                {
/*  58*/            for(int i = immutablelist.size() - 1; i >= 0; i--)
/*  59*/                ai[i] = IntMath.checkedMultiply(ai[i + 1], ((List)immutablelist.get(i)).size());

                }
/*  62*/        catch(ArithmeticException _ex)
                {
/*  63*/            throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
                }
/*  66*/        axesSizeProduct = ai;
            }

            private int getAxisIndexForProductIndex(int i, int j)
            {
/*  70*/        return (i / axesSizeProduct[j + 1]) % ((List)axes.get(j)).size();
            }

            public final ImmutableList get(final int index)
            {
/*  75*/        Preconditions.checkElementIndex(index, size());
/*  76*/        return new ImmutableList() {

                    public int size()
                    {
/*  80*/                return axes.size();
                    }

                    public Object get(int i)
                    {
/*  85*/                Preconditions.checkElementIndex(i, size());
/*  86*/                int j = getAxisIndexForProductIndex(index, i);
/*  87*/                return ((List)axes.get(i)).get(j);
                    }

                    boolean isPartialView()
                    {
/*  92*/                return true;
                    }

                    final int val$index;
                    final CartesianList this$0;

                    
                    {
/*  76*/                this$0 = CartesianList.this;
/*  76*/                index = i;
/*  76*/                super();
                    }
        };
            }

            public final int size()
            {
/*  99*/        return axesSizeProduct[0];
            }

            public final boolean contains(Object obj)
            {
/* 104*/        if(!(obj instanceof List))
/* 105*/            return false;
/* 107*/        if(((List) (obj = (List)obj)).size() != axes.size())
/* 109*/            return false;
/* 111*/        for(obj = ((List) (obj)).listIterator(); ((ListIterator) (obj)).hasNext();)
                {
/* 113*/            int i = ((ListIterator) (obj)).nextIndex();
/* 114*/            if(!((List)axes.get(i)).contains(((ListIterator) (obj)).next()))
/* 115*/                return false;
                }

/* 118*/        return true;
            }

            public final volatile Object get(int i)
            {
/*  34*/        return get(i);
            }

            private final transient ImmutableList axes;
            private final transient int axesSizeProduct[];


}
