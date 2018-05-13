// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GeneralUtilities.java

package org.glassfish.hk2.utilities.general;

import java.lang.reflect.Array;
import org.glassfish.hk2.utilities.general.internal.WeakHashClockImpl;
import org.glassfish.hk2.utilities.general.internal.WeakHashLRUImpl;

// Referenced classes of package org.glassfish.hk2.utilities.general:
//            WeakHashClock, WeakHashLRU

public class GeneralUtilities
{

            public GeneralUtilities()
            {
            }

            public static boolean safeEquals(Object obj, Object obj1)
            {
/*  65*/        if(obj == obj1)
/*  65*/            return true;
/*  66*/        if(obj == null)
/*  66*/            return false;
/*  67*/        if(obj1 == null)
/*  67*/            return false;
/*  69*/        else
/*  69*/            return obj.equals(obj1);
            }

            private static Class loadArrayClass(ClassLoader classloader, String s)
            {
/*  73*/        int i = null;
/*  74*/        int ai[] = null;
/*  76*/        int j = 0;
                char c;
/*  77*/        while(i == null) 
/*  78*/            if((c = s.charAt(j)) == '[')
                    {
/*  80*/                j++;
                    } else
                    {
/*  84*/                ai = new int[j];
/*  85*/                for(i = 0; i < j; i++)
/*  86*/                    ai[i] = 0;

/*  89*/                if(c == 'B')
/*  90*/                    i = Byte.TYPE;
/*  92*/                else
/*  92*/                if(c == 'I')
/*  93*/                    i = Integer.TYPE;
/*  95*/                else
/*  95*/                if(c == 'J')
/*  96*/                    i = Long.TYPE;
/*  98*/                else
/*  98*/                if(c == 'Z')
/*  99*/                    i = Boolean.TYPE;
/* 101*/                else
/* 101*/                if(c == 'S')
/* 102*/                    i = Short.TYPE;
/* 104*/                else
/* 104*/                if(c == 'C')
/* 105*/                    i = Character.TYPE;
/* 107*/                else
/* 107*/                if(c == 'D')
/* 108*/                    i = Double.TYPE;
/* 110*/                else
/* 110*/                if(c == 'F')
                        {
/* 111*/                    i = Float.TYPE;
                        } else
                        {
/* 114*/                    if(c != 'L')
/* 115*/                        throw new IllegalArgumentException((new StringBuilder("Unknown array type ")).append(s).toString());
/* 118*/                    if(s.charAt(s.length() - 1) != ';')
/* 119*/                        throw new IllegalArgumentException((new StringBuilder("Badly formed L array expresion: ")).append(s).toString());
/* 122*/                    i = s.substring(j + 1, s.length() - 1);
/* 124*/                    if((i = loadClass(classloader, i)) == null)
/* 125*/                        return null;
                        }
                    }
                Object obj;
/* 129*/        return (obj = Array.newInstance(i, ai)).getClass();
            }

            public static Class loadClass(ClassLoader classloader, String s)
            {
/* 143*/        if(s.startsWith("["))
/* 144*/            return loadArrayClass(classloader, s);
/* 148*/        return classloader.loadClass(s);
/* 150*/        JVM INSTR pop ;
/* 151*/        return null;
            }

            public static WeakHashClock getWeakHashClock(boolean flag)
            {
/* 163*/        return new WeakHashClockImpl(flag);
            }

            public static WeakHashLRU getWeakHashLRU(boolean flag)
            {
/* 174*/        return new WeakHashLRUImpl(flag);
            }
}
