// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Joiner.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Joiner

class nit> extends Joiner
{

            CharSequence toString(Object obj)
            {
/* 221*/        if(obj == null)
/* 221*/            return val$nullText;
/* 221*/        else
/* 221*/            return Joiner.this.toString(obj);
            }

            public Joiner useForNull(String s)
            {
/* 225*/        throw new UnsupportedOperationException("already specified useForNull");
            }

            final String val$nullText;
            final Joiner this$0;

            (String s)
            {
/* 219*/        this$0 = final_joiner;
/* 219*/        val$nullText = s;
/* 219*/        super(Joiner.this, null);
            }
}
