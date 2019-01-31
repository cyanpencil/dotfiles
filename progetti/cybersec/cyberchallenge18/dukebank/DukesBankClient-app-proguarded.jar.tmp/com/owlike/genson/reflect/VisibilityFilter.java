// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VisibilityFilter.java

package com.owlike.genson.reflect;

import java.lang.reflect.Member;

public final class VisibilityFilter
{

            public transient VisibilityFilter(int ai[])
            {
/*  56*/        filter = 0;
/*  57*/        int i = (ai = ai).length;
/*  57*/        for(int j = 0; j < i; j++)
                {
                    int k;
/*  57*/            if(((k = ai[j]) & 0xfff) == 0)
/*  60*/                throw new IllegalArgumentException("One of the modifiers is not a standard java modifier.");
/*  62*/            filter = filter | k;
                }

            }

            public final boolean isVisible(Member member)
            {
/*  73*/        return isVisible(member.getModifiers());
            }

            public final boolean isVisible(int i)
            {
/*  77*/        return (i & filter) == 0;
            }

            private static final int JAVA_MODIFIERS = 4095;
            public static final VisibilityFilter ABSTRACT = new VisibilityFilter(new int[] {
/*  37*/        1024
            });
            public static final VisibilityFilter PRIVATE = new VisibilityFilter(new int[] {
/*  38*/        128, 256, 8
            });
            public static final VisibilityFilter ALL = new VisibilityFilter(new int[0]);
            public static final VisibilityFilter NONE = new VisibilityFilter(new int[] {
/*  41*/        4095
            });
            public static final VisibilityFilter PROTECTED = new VisibilityFilter(new int[] {
/*  42*/        128, 256, 8, 2
            });
            public static final VisibilityFilter PACKAGE_PUBLIC = new VisibilityFilter(new int[] {
/*  44*/        128, 256, 8, 2, 4
            });
            private int filter;

}
