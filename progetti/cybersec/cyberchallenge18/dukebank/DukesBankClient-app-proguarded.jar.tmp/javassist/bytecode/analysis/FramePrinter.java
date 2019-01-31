// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FramePrinter.java

package javassist.bytecode.analysis;

import java.io.PrintStream;
import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.analysis:
//            Analyzer, Frame, Type

public final class FramePrinter
{

            public FramePrinter(PrintStream printstream)
            {
/*  45*/        stream = printstream;
            }

            public static void print(CtClass ctclass, PrintStream printstream)
            {
/*  52*/        (new FramePrinter(printstream)).print(ctclass);
            }

            public final void print(CtClass ctclass)
            {
/*  59*/        ctclass = ctclass.getDeclaredMethods();
/*  60*/        for(int i = 0; i < ctclass.length; i++)
/*  61*/            print(ctclass[i]);

            }

            private String getMethodString(CtMethod ctmethod)
            {
/*  67*/        return (new StringBuilder()).append(Modifier.toString(ctmethod.getModifiers())).append(" ").append(ctmethod.getReturnType().getName()).append(" ").append(ctmethod.getName()).append(Descriptor.toString(ctmethod.getSignature())).append(";").toString();
/*  70*/        ctmethod;
/*  71*/        throw new RuntimeException(ctmethod);
            }

            public final void print(CtMethod ctmethod)
            {
/*  79*/        stream.println((new StringBuilder("\n")).append(getMethodString(ctmethod)).toString());
                MethodInfo methodinfo;
/*  80*/        ConstPool constpool = (methodinfo = ctmethod.getMethodInfo2()).getConstPool();
                Object obj;
/*  82*/        if((obj = methodinfo.getCodeAttribute()) == null)
/*  84*/            return;
/*  88*/        try
                {
/*  88*/            ctmethod = (new Analyzer()).analyze(ctmethod.getDeclaringClass(), methodinfo);
                }
/*  89*/        catch(BadBytecode badbytecode)
                {
/*  90*/            throw new RuntimeException(badbytecode);
                }
/*  93*/        int i = String.valueOf(((CodeAttribute) (obj)).getCodeLength()).length();
/*  95*/        for(obj = ((CodeAttribute) (obj)).iterator(); ((CodeIterator) (obj)).hasNext();)
                {
                    int j;
/*  99*/            try
                    {
/*  99*/                j = ((CodeIterator) (obj)).next();
                    }
                    // Misplaced declaration of an exception variable
/* 100*/            catch(int j)
                    {
/* 101*/                throw new RuntimeException(j);
                    }
/* 104*/            stream.println((new StringBuilder()).append(j).append(": ").append(InstructionPrinter.instructionString(((CodeIterator) (obj)), j, constpool)).toString());
/* 106*/            addSpacing(i + 3);
                    Frame frame;
/* 107*/            if((frame = ctmethod[j]) == null)
                    {
/* 109*/                stream.println("--DEAD CODE--");
                    } else
                    {
/* 112*/                printStack(frame);
/* 114*/                addSpacing(i + 3);
/* 115*/                printLocals(frame);
                    }
                }

            }

            private void printStack(Frame frame)
            {
/* 121*/        stream.print("stack [");
/* 122*/        int i = frame.getTopIndex();
/* 123*/        for(int j = 0; j <= i; j++)
                {
/* 124*/            if(j > 0)
/* 125*/                stream.print(", ");
/* 126*/            Type type = frame.getStack(j);
/* 127*/            stream.print(type);
                }

/* 129*/        stream.println("]");
            }

            private void printLocals(Frame frame)
            {
/* 133*/        stream.print("locals [");
/* 134*/        int i = frame.localsLength();
/* 135*/        for(int j = 0; j < i; j++)
                {
/* 136*/            if(j > 0)
/* 137*/                stream.print(", ");
/* 138*/            Type type = frame.getLocal(j);
/* 139*/            stream.print(type != null ? type.toString() : "empty");
                }

/* 141*/        stream.println("]");
            }

            private void addSpacing(int i)
            {
/* 145*/        while(i-- > 0) 
/* 146*/            stream.print(' ');
            }

            private final PrintStream stream;
}
