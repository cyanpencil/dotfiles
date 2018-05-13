// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RemoteException.java

package javassist.tools.rmi;


public class RemoteException extends RuntimeException
{

            public RemoteException(String s)
            {
/*  25*/        super(s);
            }

            public RemoteException(Exception exception)
            {
/*  29*/        super((new StringBuilder("by ")).append(exception.toString()).toString());
            }
}
