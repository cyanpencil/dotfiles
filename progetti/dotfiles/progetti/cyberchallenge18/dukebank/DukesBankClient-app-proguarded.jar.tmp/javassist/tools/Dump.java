// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Dump.java

package javassist.tools;

import java.io.*;
import javassist.bytecode.*;

public class Dump
{

            private Dump()
            {
            }

            public static void main(String args[])
                throws Exception
            {
/*  43*/        if(args.length != 1)
                {
/*  44*/            System.err.println("Usage: java Dump <class file name>");
/*  45*/            return;
                } else
                {
/*  48*/            args = new DataInputStream(new FileInputStream(args[0]));
/*  50*/            args = new ClassFile(args);
                    PrintWriter printwriter;
/*  51*/            (printwriter = new PrintWriter(System.out, true)).println("*** constant pool ***");
/*  53*/            args.getConstPool().print(printwriter);
/*  54*/            printwriter.println();
/*  55*/            printwriter.println("*** members ***");
/*  56*/            ClassFilePrinter.print(args, printwriter);
/*  57*/            return;
                }
            }
}
