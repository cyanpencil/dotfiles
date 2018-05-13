// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

class previousWrite extends enceEntry
{

            public long getWriteTime()
            {
/*3599*/        return 0x7fffffffffffffffL;
            }

            public void setWriteTime(long l)
            {
            }

            public y getNextInWriteQueue()
            {
/*3609*/        return nextWrite;
            }

            public void setNextInWriteQueue(y y)
            {
/*3614*/        nextWrite = y;
            }

            public y getPreviousInWriteQueue()
            {
/*3621*/        return previousWrite;
            }

            public void setPreviousInWriteQueue(y y)
            {
/*3626*/        previousWrite = y;
            }

            y nextWrite;
            y previousWrite;
            final y this$0;

            y()
            {
/*3595*/        this$0 = this._cls0.this;
/*3595*/        super();
/*3605*/        nextWrite = this;
/*3617*/        previousWrite = this;
            }
}
