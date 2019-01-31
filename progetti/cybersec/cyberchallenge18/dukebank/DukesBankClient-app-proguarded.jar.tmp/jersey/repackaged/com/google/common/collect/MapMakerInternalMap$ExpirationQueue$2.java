// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractSequentialIterator, MapMakerInternalMap

class this._cls0 extends AbstractSequentialIterator
{

            protected this._cls0 computeNext(this._cls0 _pcls0)
            {
/*3357*/        if((_pcls0 = _pcls0.NextExpirable()) == ad)
/*3358*/            return null;
/*3358*/        else
/*3358*/            return _pcls0;
            }

            protected volatile Object computeNext(Object obj)
            {
/*3354*/        return computeNext((computeNext)obj);
            }

            final computeNext this$0;

            ( 1)
            {
/*3354*/        this$0 = this._cls0.this;
/*3354*/        super(1);
            }
}
