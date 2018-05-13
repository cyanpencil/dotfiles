// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JsonBindingException.java

package com.owlike.genson;


public class JsonBindingException extends RuntimeException
{

            public JsonBindingException(String s)
            {
/*   6*/        super(s);
            }

            public JsonBindingException(String s, Throwable throwable)
            {
/*  10*/        super(s, throwable);
            }
}
