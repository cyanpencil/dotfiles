// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HotSwapper.java

package javassist.util;

import com.sun.jdi.event.*;
import java.io.PrintStream;

// Referenced classes of package javassist.util:
//            HotSwapper

class this._cls0 extends Thread
{

            private void errorMsg(Throwable throwable)
            {
/* 212*/        System.err.print("Exception in thread \"HotSwap\" ");
/* 213*/        throwable.printStackTrace(System.err);
            }

            public void run()
            {
                EventSet eventset;
/* 217*/label0:
                {
/* 217*/            eventset = null;
/* 219*/            try
                    {
/* 219*/                EventIterator eventiterator = (eventset = waitEvent()).eventIterator();
                        com.sun.jdi.event.Event event;
/* 221*/                do
/* 221*/                    if(!eventiterator.hasNext())
/* 222*/                        break label0;
/* 222*/                while(!((event = eventiterator.nextEvent()) instanceof MethodEntryEvent));
/* 224*/                hotswap();
                    }
/* 229*/            catch(Throwable throwable)
                    {
/* 230*/                errorMsg(throwable);
                    }
                }
/* 233*/        try
                {
/* 233*/            if(eventset != null)
/* 234*/                eventset.resume();
/* 238*/            return;
                }
/* 236*/        catch(Throwable throwable1)
                {
/* 237*/            errorMsg(throwable1);
                }
            }

            final HotSwapper this$0;

            yEvent()
            {
/* 210*/        this$0 = HotSwapper.this;
/* 210*/        super();
            }
}
