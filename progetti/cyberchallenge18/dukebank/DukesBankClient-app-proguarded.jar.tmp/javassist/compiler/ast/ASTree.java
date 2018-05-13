// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ASTree.java

package javassist.compiler.ast;

import java.io.Serializable;
import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            Visitor

public abstract class ASTree
    implements Serializable
{

            public ASTree()
            {
            }

            public ASTree getLeft()
            {
/*  28*/        return null;
            }

            public ASTree getRight()
            {
/*  30*/        return null;
            }

            public void setLeft(ASTree astree)
            {
            }

            public void setRight(ASTree astree)
            {
            }

            public abstract void accept(Visitor visitor)
                throws CompileError;

            public String toString()
            {
                StringBuffer stringbuffer;
/*  44*/        (stringbuffer = new StringBuffer()).append('<');
/*  46*/        stringbuffer.append(getTag());
/*  47*/        stringbuffer.append('>');
/*  48*/        return stringbuffer.toString();
            }

            protected String getTag()
            {
                String s;
/*  56*/        return (s = getClass().getName()).substring(s.lastIndexOf('.') + 1);
            }
}
