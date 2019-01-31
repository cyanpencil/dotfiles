// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Converter.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Function, Preconditions

public abstract class Converter
    implements Function
{

            protected Converter()
            {
/* 103*/        this(true);
            }

            Converter(boolean flag)
            {
/* 110*/        handleNullAutomatically = flag;
            }

            protected abstract Object doForward(Object obj);

            public final Object convert(Object obj)
            {
/* 147*/        return correctedDoForward(obj);
            }

            Object correctedDoForward(Object obj)
            {
/* 152*/        if(handleNullAutomatically)
                {
/* 154*/            if(obj == null)
/* 154*/                return null;
/* 154*/            else
/* 154*/                return Preconditions.checkNotNull(doForward(obj));
                } else
                {
/* 156*/            return doForward(obj);
                }
            }

            /**
             * @deprecated Method apply is deprecated
             */

            public final Object apply(Object obj)
            {
/* 367*/        return convert(obj);
            }

            public boolean equals(Object obj)
            {
/* 383*/        return super.equals(obj);
            }

            private final boolean handleNullAutomatically;
}
