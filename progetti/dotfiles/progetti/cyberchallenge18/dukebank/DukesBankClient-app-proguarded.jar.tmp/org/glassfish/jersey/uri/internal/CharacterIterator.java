// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharacterIterator.java

package org.glassfish.jersey.uri.internal;

import java.util.NoSuchElementException;

final class CharacterIterator
{

            public CharacterIterator(String s1)
            {
/*  60*/        s = s1;
/*  61*/        pos = -1;
            }

            public final boolean hasNext()
            {
/*  70*/        return pos < s.length() - 1;
            }

            public final char next()
            {
/*  80*/        if(!hasNext())
/*  81*/            throw new NoSuchElementException();
/*  83*/        else
/*  83*/            return s.charAt(++pos);
            }

            public final char peek()
            {
/*  93*/        if(!hasNext())
/*  94*/            throw new NoSuchElementException();
/*  97*/        else
/*  97*/            return s.charAt(pos + 1);
            }

            public final int pos()
            {
/* 107*/        return pos;
            }

            public final String getInput()
            {
/* 116*/        return s;
            }

            public final void setPosition(int i)
            {
/* 125*/        if(i > s.length() - 1)
                {
/* 126*/            throw new IndexOutOfBoundsException((new StringBuilder("Given position ")).append(i).append(" is outside the input string range.").toString());
                } else
                {
/* 128*/            pos = i;
/* 129*/            return;
                }
            }

            public final char current()
            {
/* 137*/        if(pos == -1)
/* 138*/            throw new IllegalStateException("Iterator not used yet.");
/* 140*/        else
/* 140*/            return s.charAt(pos);
            }

            private int pos;
            private String s;
}
