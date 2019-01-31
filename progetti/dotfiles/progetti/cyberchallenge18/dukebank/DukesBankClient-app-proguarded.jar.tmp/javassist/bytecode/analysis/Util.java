// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Util.java

package javassist.bytecode.analysis;

import javassist.bytecode.CodeIterator;
import javassist.bytecode.Opcode;

public class Util
    implements Opcode
{

            public Util()
            {
            }

            public static int getJumpTarget(int i, CodeIterator codeiterator)
            {
/*  28*/        int j = codeiterator.byteAt(i);
/*  29*/        return i += j != 201 && j != 200 ? codeiterator.s16bitAt(i + 1) : codeiterator.s32bitAt(i + 1);
            }

            public static boolean isJumpInstruction(int i)
            {
/*  34*/        return i >= 153 && i <= 168 || i == 198 || i == 199 || i == 201 || i == 200;
            }

            public static boolean isGoto(int i)
            {
/*  38*/        return i == 167 || i == 200;
            }

            public static boolean isJsr(int i)
            {
/*  42*/        return i == 168 || i == 201;
            }

            public static boolean isReturn(int i)
            {
/*  46*/        return i >= 172 && i <= 177;
            }
}
