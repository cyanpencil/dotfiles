// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sample.java

package javassist.tools.rmi;


// Referenced classes of package javassist.tools.rmi:
//            ObjectImporter, RemoteException

public class Sample
{

            public Sample()
            {
            }

            public Object forward(Object aobj[], int i)
            {
/*  29*/        return importer.call(objectId, i, aobj);
            }

            public static Object forwardStatic(Object aobj[], int i)
                throws RemoteException
            {
/*  35*/        throw new RemoteException("cannot call a static method.");
            }

            private ObjectImporter importer;
            private int objectId;
}
