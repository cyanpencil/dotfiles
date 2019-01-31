// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimaps, Sets, CollectPreconditions

class  extends 
{

            public Iterator iterator()
            {
/* 959*/        return new Iterator() {

                    public boolean hasNext()
                    {
/* 964*/                return i == 0 && map.containsKey(key);
                    }

                    public Object next()
                    {
/* 969*/                if(!hasNext())
                        {
/* 970*/                    throw new NoSuchElementException();
                        } else
                        {
/* 972*/                    i++;
/* 973*/                    return map.get(key);
                        }
                    }

                    public void remove()
                    {
/* 978*/                CollectPreconditions.checkRemove(i == 1);
/* 979*/                i = -1;
/* 980*/                map.remove(key);
                    }

                    int i;
                    final Multimaps.MapMultimap._cls1 this$1;

                    
                    {
/* 959*/                this$1 = Multimaps.MapMultimap._cls1.this;
/* 959*/                super();
                    }
        };
            }

            public int size()
            {
/* 986*/        return !p.containsKey(val$key) ? 0 : 1;
            }

            final Object val$key;
            final val.key this$0;

            _cls1.this._cls1()
            {
/* 957*/        this$0 = final__pcls1;
/* 957*/        val$key = Object.this;
/* 957*/        super();
            }
}
