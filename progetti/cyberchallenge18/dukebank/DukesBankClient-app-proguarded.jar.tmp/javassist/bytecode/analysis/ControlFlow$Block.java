// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ControlFlow.java

package javassist.bytecode.analysis;

import java.util.ArrayList;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.stackmap.BasicBlock;

// Referenced classes of package javassist.bytecode.analysis:
//            ControlFlow

public static class method extends BasicBlock
{

            protected void toString2(StringBuffer stringbuffer)
            {
/* 245*/        super.toString2(stringbuffer);
/* 246*/        stringbuffer.append(", incoming{");
/* 247*/        for(int i = 0; i < entrances.length; i++)
/* 248*/            stringbuffer.append(entrances[i].position).append(", ");

/* 250*/        stringbuffer.append("}");
            }

            BasicBlock[] getExit()
            {
/* 253*/        return exit;
            }

            public int index()
            {
/* 262*/        return index;
            }

            public int position()
            {
/* 268*/        return position;
            }

            public int length()
            {
/* 273*/        return length;
            }

            public int incomings()
            {
/* 278*/        return incoming;
            }

            public incoming incoming(int i)
            {
/* 284*/        return entrances[i];
            }

            public int exits()
            {
/* 291*/        if(exit == null)
/* 291*/            return 0;
/* 291*/        else
/* 291*/            return exit.length;
            }

            public exit exit(int i)
            {
/* 299*/        return (exit)exit[i];
            }

            public r[] catchers()
            {
/* 306*/        ArrayList arraylist = new ArrayList();
/* 307*/        for(javassist.bytecode.stackmap.r r = toCatch; r != null; r = r.ext)
/* 309*/            arraylist.add(new r(r));

/* 313*/        return (r[])arraylist.toArray(new r[arraylist.size()]);
            }

            public Object clientData;
            int index;
            MethodInfo method;
            r entrances[];

            r(int i, MethodInfo methodinfo)
            {
/* 240*/        super(i);
/* 233*/        clientData = null;
/* 241*/        method = methodinfo;
            }
}
