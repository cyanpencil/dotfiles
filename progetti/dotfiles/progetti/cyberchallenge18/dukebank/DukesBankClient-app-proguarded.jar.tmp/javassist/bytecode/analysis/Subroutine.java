// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Subroutine.java

package javassist.bytecode.analysis;

import java.util.*;

public class Subroutine
{

            public Subroutine(int i, int j)
            {
/*  31*/        callers = new ArrayList();
/*  32*/        access = new HashSet();
/*  36*/        start = i;
/*  37*/        callers.add(new Integer(j));
            }

            public void addCaller(int i)
            {
/*  41*/        callers.add(new Integer(i));
            }

            public int start()
            {
/*  45*/        return start;
            }

            public void access(int i)
            {
/*  49*/        access.add(new Integer(i));
            }

            public boolean isAccessed(int i)
            {
/*  53*/        return access.contains(new Integer(i));
            }

            public Collection accessed()
            {
/*  57*/        return access;
            }

            public Collection callers()
            {
/*  61*/        return callers;
            }

            public String toString()
            {
/*  65*/        return (new StringBuilder("start = ")).append(start).append(" callers = ").append(callers.toString()).toString();
            }

            private List callers;
            private Set access;
            private int start;
}
