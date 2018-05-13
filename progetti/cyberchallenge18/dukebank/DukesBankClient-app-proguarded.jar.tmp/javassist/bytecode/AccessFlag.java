// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AccessFlag.java

package javassist.bytecode;


public class AccessFlag
{

            public AccessFlag()
            {
            }

            public static int setPublic(int i)
            {
/*  52*/        return i & -7 | 1;
            }

            public static int setProtected(int i)
            {
/*  60*/        return i & -4 | 4;
            }

            public static int setPrivate(int i)
            {
/*  68*/        return i & -6 | 2;
            }

            public static int setPackage(int i)
            {
/*  75*/        return i & -8;
            }

            public static boolean isPublic(int i)
            {
/*  82*/        return (i & 1) != 0;
            }

            public static boolean isProtected(int i)
            {
/*  89*/        return (i & 4) != 0;
            }

            public static boolean isPrivate(int i)
            {
/*  96*/        return (i & 2) != 0;
            }

            public static boolean isPackage(int i)
            {
/* 104*/        return (i & 7) == 0;
            }

            public static int clear(int i, int j)
            {
/* 111*/        return i & ~j;
            }

            public static int of(int i)
            {
/* 121*/        return i;
            }

            public static int toModifier(int i)
            {
/* 131*/        return i;
            }

            public static final int PUBLIC = 1;
            public static final int PRIVATE = 2;
            public static final int PROTECTED = 4;
            public static final int STATIC = 8;
            public static final int FINAL = 16;
            public static final int SYNCHRONIZED = 32;
            public static final int VOLATILE = 64;
            public static final int BRIDGE = 64;
            public static final int TRANSIENT = 128;
            public static final int VARARGS = 128;
            public static final int NATIVE = 256;
            public static final int INTERFACE = 512;
            public static final int ABSTRACT = 1024;
            public static final int STRICT = 2048;
            public static final int SYNTHETIC = 4096;
            public static final int ANNOTATION = 8192;
            public static final int ENUM = 16384;
            public static final int SUPER = 32;
}
