// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   framedump.java

package javassist.tools;

import java.io.PrintStream;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.analysis.FramePrinter;

public class framedump
{

            private framedump()
            {
            }

            public static void main(String args[])
                throws Exception
            {
/*  38*/        if(args.length != 1)
                {
/*  39*/            System.err.println("Usage: java javassist.tools.framedump <class file name>");
/*  40*/            return;
                } else
                {
                    ClassPool classpool;
/*  43*/            args = (classpool = ClassPool.getDefault()).get(args[0]);
/*  45*/            System.out.println((new StringBuilder("Frame Dump of ")).append(args.getName()).append(":").toString());
/*  46*/            FramePrinter.print(args, System.out);
/*  47*/            return;
                }
            }
}
