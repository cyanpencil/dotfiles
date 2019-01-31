// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AppletServer.java

package javassist.tools.rmi;

import java.lang.reflect.Method;

class ExportedObject
{

            ExportedObject()
            {
            }

            public int identifier;
            public Object object;
            public Method methods[];
}
