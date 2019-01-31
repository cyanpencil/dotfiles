// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Preconditions.java

package jersey.repackaged.com.google.common.base;


public final class Preconditions
{

            private Preconditions()
            {
            }

            public static void checkArgument(boolean flag)
            {
/* 107*/        if(!flag)
/* 108*/            throw new IllegalArgumentException();
/* 110*/        else
/* 110*/            return;
            }

            public static void checkArgument(boolean flag, Object obj)
            {
/* 121*/        if(!flag)
/* 122*/            throw new IllegalArgumentException(String.valueOf(obj));
/* 124*/        else
/* 124*/            return;
            }

            public static transient void checkArgument(boolean flag, String s, Object aobj[])
            {
/* 144*/        if(!flag)
/* 145*/            throw new IllegalArgumentException(format(s, aobj));
/* 147*/        else
/* 147*/            return;
            }

            public static void checkState(boolean flag)
            {
/* 157*/        if(!flag)
/* 158*/            throw new IllegalStateException();
/* 160*/        else
/* 160*/            return;
            }

            public static void checkState(boolean flag, Object obj)
            {
/* 172*/        if(!flag)
/* 173*/            throw new IllegalStateException(String.valueOf(obj));
/* 175*/        else
/* 175*/            return;
            }

            public static transient void checkState(boolean flag, String s, Object aobj[])
            {
/* 196*/        if(!flag)
/* 197*/            throw new IllegalStateException(format(s, aobj));
/* 199*/        else
/* 199*/            return;
            }

            public static Object checkNotNull(Object obj)
            {
/* 209*/        if(obj == null)
/* 210*/            throw new NullPointerException();
/* 212*/        else
/* 212*/            return obj;
            }

            public static Object checkNotNull(Object obj, Object obj1)
            {
/* 225*/        if(obj == null)
/* 226*/            throw new NullPointerException(String.valueOf(obj1));
/* 228*/        else
/* 228*/            return obj;
            }

            public static transient Object checkNotNull(Object obj, String s, Object aobj[])
            {
/* 248*/        if(obj == null)
/* 250*/            throw new NullPointerException(format(s, aobj));
/* 252*/        else
/* 252*/            return obj;
            }

            public static int checkElementIndex(int i, int j)
            {
/* 292*/        return checkElementIndex(i, j, "index");
            }

            public static int checkElementIndex(int i, int j, String s)
            {
/* 309*/        if(i < 0 || i >= j)
/* 310*/            throw new IndexOutOfBoundsException(badElementIndex(i, j, s));
/* 312*/        else
/* 312*/            return i;
            }

            private static String badElementIndex(int i, int j, String s)
            {
/* 316*/        if(i < 0)
/* 317*/            return format("%s (%s) must not be negative", new Object[] {
/* 317*/                s, Integer.valueOf(i)
                    });
/* 318*/        if(j < 0)
/* 319*/            throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(j).toString());
/* 321*/        else
/* 321*/            return format("%s (%s) must be less than size (%s)", new Object[] {
/* 321*/                s, Integer.valueOf(i), Integer.valueOf(j)
                    });
            }

            public static int checkPositionIndex(int i, int j)
            {
/* 336*/        return checkPositionIndex(i, j, "index");
            }

            public static int checkPositionIndex(int i, int j, String s)
            {
/* 352*/        if(i < 0 || i > j)
/* 353*/            throw new IndexOutOfBoundsException(badPositionIndex(i, j, s));
/* 355*/        else
/* 355*/            return i;
            }

            private static String badPositionIndex(int i, int j, String s)
            {
/* 359*/        if(i < 0)
/* 360*/            return format("%s (%s) must not be negative", new Object[] {
/* 360*/                s, Integer.valueOf(i)
                    });
/* 361*/        if(j < 0)
/* 362*/            throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(j).toString());
/* 364*/        else
/* 364*/            return format("%s (%s) must not be greater than size (%s)", new Object[] {
/* 364*/                s, Integer.valueOf(i), Integer.valueOf(j)
                    });
            }

            public static void checkPositionIndexes(int i, int j, int k)
            {
/* 382*/        if(i < 0 || j < i || j > k)
/* 383*/            throw new IndexOutOfBoundsException(badPositionIndexes(i, j, k));
/* 385*/        else
/* 385*/            return;
            }

            private static String badPositionIndexes(int i, int j, int k)
            {
/* 388*/        if(i < 0 || i > k)
/* 389*/            return badPositionIndex(i, k, "start index");
/* 391*/        if(j < 0 || j > k)
/* 392*/            return badPositionIndex(j, k, "end index");
/* 395*/        else
/* 395*/            return format("end index (%s) must not be less than start index (%s)", new Object[] {
/* 395*/                Integer.valueOf(j), Integer.valueOf(i)
                    });
            }

            static transient String format(String s, Object aobj[])
            {
/* 410*/        s = String.valueOf(s);
/* 413*/        StringBuilder stringbuilder = new StringBuilder(s.length() + 16 * aobj.length);
/* 414*/        int i = 0;
                int j;
                int k;
/* 415*/        for(j = 0; j < aobj.length && (k = s.indexOf("%s", i)) != -1;)
                {
/* 421*/            stringbuilder.append(s.substring(i, k));
/* 422*/            stringbuilder.append(aobj[j++]);
/* 423*/            i = k + 2;
                }

/* 425*/        stringbuilder.append(s.substring(i));
/* 428*/        if(j < aobj.length)
                {
/* 429*/            stringbuilder.append(" [");
/* 430*/            stringbuilder.append(aobj[j++]);
/* 431*/            while(j < aobj.length) 
                    {
/* 432*/                stringbuilder.append(", ");
/* 433*/                stringbuilder.append(aobj[j++]);
                    }
/* 435*/            stringbuilder.append(']');
                }
/* 438*/        return stringbuilder.toString();
            }
}
