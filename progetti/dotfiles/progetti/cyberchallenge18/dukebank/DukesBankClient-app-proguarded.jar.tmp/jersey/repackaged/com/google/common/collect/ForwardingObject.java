// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingObject.java

package jersey.repackaged.com.google.common.collect;


public abstract class ForwardingObject
{

            protected ForwardingObject()
            {
            }

            protected abstract Object _mthdelegate();

            public String toString()
            {
/*  72*/        return _mthdelegate().toString();
            }
}
