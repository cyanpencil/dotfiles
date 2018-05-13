// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SerializingExecutor.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            SerializingExecutor

class this._cls0
    implements Runnable
{

            public void run()
            {
/* 132*/        boolean flag = true;
_L2:
/* 135*/label0:
                {
/* 135*/            Preconditions.checkState(SerializingExecutor.access$100(SerializingExecutor.this));
/* 137*/            synchronized(SerializingExecutor.access$200(SerializingExecutor.this))
                    {
/* 138*/                if((obj2 = (Runnable)SerializingExecutor.access$300(SerializingExecutor.this).poll()) != null)
/* 140*/                    break label0;
/* 140*/                SerializingExecutor.access$102(SerializingExecutor.this, false);
/* 141*/                flag = false;
                    }
/* 142*/            break; /* Loop/switch isn't completed */
                }
/* 144*/        obj1;
/* 144*/        JVM INSTR monitorexit ;
                String s;
/* 148*/        try
                {
/* 148*/            ((Runnable) (obj2)).run();
                }
/* 149*/        catch(RuntimeException runtimeexception)
                {
/* 151*/            SerializingExecutor.access$400().log(Level.SEVERE, (new StringBuilder(35 + (s = String.valueOf(String.valueOf(obj2))).length())).append("Exception while executing runnable ").append(s).toString(), runtimeexception);
                }
/* 154*/        if(true) goto _L2; else goto _L1
/* 156*/        exception;
/* 156*/        if(flag)
/* 160*/            synchronized(SerializingExecutor.access$200(SerializingExecutor.this))
                    {
/* 161*/                SerializingExecutor.access$102(SerializingExecutor.this, false);
                    }
/* 162*/        throw exception;
_L1:
            }

            final SerializingExecutor this$0;

            private ()
            {
/* 129*/        this$0 = SerializingExecutor.this;
/* 129*/        super();
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/util/concurrent/SerializingExecutor$1

/* anonymous class */
    class SerializingExecutor._cls1
    {

                public String toString()
                {
/*  83*/            "SerializingExecutor lock: ";
/*  83*/            String s = String.valueOf(super.toString());
/*  83*/            s;
/*  83*/            if(s.length() == 0) goto _L2; else goto _L1
_L1:
/*  83*/            concat();
/*  83*/            return;
_L2:
/*  83*/            JVM INSTR pop ;
/*  83*/            JVM INSTR new #3   <Class String>;
/*  83*/            JVM INSTR dup_x1 ;
/*  83*/            JVM INSTR swap ;
/*  83*/            String();
/*  83*/            return;
                }

                final SerializingExecutor this$0;

                    
                    {
/*  81*/                this$0 = SerializingExecutor.this;
/*  81*/                super();
                    }
    }

}
