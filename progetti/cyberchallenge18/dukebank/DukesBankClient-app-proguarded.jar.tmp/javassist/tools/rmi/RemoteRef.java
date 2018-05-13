// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RemoteRef.java

package javassist.tools.rmi;

import java.io.Serializable;

public class RemoteRef
    implements Serializable
{

            public RemoteRef(int i)
            {
/*  28*/        oid = i;
/*  29*/        classname = null;
            }

            public RemoteRef(int i, String s)
            {
/*  33*/        oid = i;
/*  34*/        classname = s;
            }

            public int oid;
            public String classname;
}
