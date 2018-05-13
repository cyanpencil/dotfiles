// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            Label

class Handler
{

    Handler()
    {
    }

    static Handler a(Handler handler, Label label, Label label1)
    {
        if(handler == null)
            return null;
        handler.f = a(handler.f, label, label1);
        int i = handler.a.c;
        int j = handler.b.c;
        int k = label.c;
        int l = label1 != null ? label1.c : 0x7fffffff;
        if(k < j && l > i)
            if(k <= i)
            {
                if(l >= j)
                    handler = handler.f;
                else
                    handler.a = label1;
            } else
            if(l >= j)
            {
                handler.b = label;
            } else
            {
                Handler handler1;
                (handler1 = new Handler()).a = label1;
                handler1.b = handler.b;
                handler1.c = handler.c;
                handler1.d = handler.d;
                handler1.e = handler.e;
                handler1.f = handler.f;
                handler.b = label;
                handler.f = handler1;
            }
        return handler;
    }

    Label a;
    Label b;
    Label c;
    String d;
    int e;
    Handler f;
}
