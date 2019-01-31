// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

class previousAccess extends nceEntry
{

            public long getAccessTime()
            {
/*3736*/        return 0x7fffffffffffffffL;
            }

            public void setAccessTime(long l)
            {
            }

            public  getNextInAccessQueue()
            {
/*3746*/        return nextAccess;
            }

            public void setNextInAccessQueue( )
            {
/*3751*/        nextAccess = ;
            }

            public  getPreviousInAccessQueue()
            {
/*3758*/        return previousAccess;
            }

            public void setPreviousInAccessQueue( )
            {
/*3763*/        previousAccess = ;
            }

             nextAccess;
             previousAccess;
            final  this$0;

            ()
            {
/*3732*/        this$0 = this._cls0.this;
/*3732*/        super();
/*3742*/        nextAccess = this;
/*3754*/        previousAccess = this;
            }
}
