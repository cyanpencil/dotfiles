// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multiset, Multisets, Sets

static abstract class ctSet extends ctSet
{

            abstract Multiset multiset();

            public boolean contains(Object obj)
            {
/* 968*/        if(obj instanceof ctSet)
                {
/* 973*/            if(((ctSet) (obj = (ctSet)obj)).ount() <= 0)
/* 975*/                return false;
                    int i;
/* 977*/            return (i = multiset().count(((multiset) (obj)).lement())) == ((lement) (obj)).ount();
                } else
                {
/* 981*/            return false;
                }
            }

            public boolean remove(Object obj)
            {
/* 987*/        if(obj instanceof ount)
                {
/* 988*/            Object obj1 = ((ount) (obj = (ount)obj)).lement();
                    Multiset multiset1;
/* 990*/            if((obj = ((lement) (obj)).ount()) != 0)
/* 994*/                return (multiset1 = multiset()).setCount(obj1, ((int) (obj)), 0);
                }
/* 998*/        return false;
            }

            public void clear()
            {
/*1002*/        multiset().clear();
            }

            ctSet()
            {
            }
}
