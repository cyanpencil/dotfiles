// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static final class parent extends parent
{

            final parent find(int i, Object obj)
            {
/*2113*/        return findTreeNode(i, obj, null);
            }

            final findTreeNode findTreeNode(int i, Object obj, Class class1)
            {
/*2121*/        if(obj != null)
                {
/*2122*/            Object obj1 = this;
/*2128*/            do
                    {
/*2128*/                findTreeNode findtreenode = ((findTreeNode) (obj1)).left;
/*2129*/                findTreeNode findtreenode1 = ((left) (obj1)).right;
                        int j;
/*2130*/                if((j = ((right) (obj1)).hash) <= i)
                        {
/*2132*/                    if(j < i)
                            {
/*2133*/                        obj1 = findtreenode1;
/*2133*/                        continue;
                            }
                            Object obj2;
/*2134*/                    if((obj2 = ((hash) (obj1)).key) == obj || obj2 != null && obj.equals(obj2))
/*2135*/                        return ((key) (obj1));
/*2136*/                    if(findtreenode == null)
                            {
/*2137*/                        obj1 = findtreenode1;
/*2137*/                        continue;
                            }
/*2138*/                    if(findtreenode1 != null)
                            {
/*2140*/                        if((class1 != null || (class1 = ConcurrentHashMapV8.comparableClassFor(obj)) != null) && (obj1 = ConcurrentHashMapV8.compareComparables(class1, obj, obj2)) != 0)
                                {
/*2143*/                            obj1 = obj1 >= 0 ? ((Object) (findtreenode1)) : ((Object) (findtreenode));
/*2143*/                            continue;
                                }
/*2144*/                        if((obj1 = findtreenode1.findTreeNode(i, obj, class1)) != null)
/*2145*/                            return ((findTreeNode) (obj1));
                            }
                        }
/*2147*/                obj1 = findtreenode;
                    } while(obj1 != null);
                }
/*2150*/        return null;
            }

            findTreeNode parent;
            findTreeNode left;
            findTreeNode right;
            findTreeNode prev;
            boolean red;

            (int i, Object obj, Object obj1,  ,  1)
            {
/*2108*/        super(i, obj, obj1, );
/*2109*/        parent = 1;
            }
}
