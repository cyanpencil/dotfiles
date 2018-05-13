// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CollectPreconditions.java

package jersey.repackaged.com.google.common.collect;

import jersey.repackaged.com.google.common.base.Preconditions;

final class CollectPreconditions
{

            static void checkEntryNotNull(Object obj, Object obj1)
            {
/*  30*/        if(obj == null)
                {
/*  31*/            obj = String.valueOf(String.valueOf(obj1));
/*  31*/            throw new NullPointerException((new StringBuilder(24 + ((String) (obj)).length())).append("null key in entry: null=").append(((String) (obj))).toString());
                }
/*  32*/        if(obj1 == null)
                {
/*  33*/            obj = String.valueOf(String.valueOf(obj));
/*  33*/            throw new NullPointerException((new StringBuilder(26 + ((String) (obj)).length())).append("null value in entry: ").append(((String) (obj))).append("=null").toString());
                } else
                {
/*  35*/            return;
                }
            }

            static int checkNonnegative(int i, String s)
            {
/*  38*/        if(i < 0)
                {
/*  39*/            s = String.valueOf(String.valueOf(s));
/*  39*/            throw new IllegalArgumentException((new StringBuilder(40 + s.length())).append(s).append(" cannot be negative but was: ").append(i).toString());
                } else
                {
/*  41*/            return i;
                }
            }

            static void checkRemove(boolean flag)
            {
/*  49*/        Preconditions.checkState(flag, "no calls to next() since the last call to remove()");
            }
}
