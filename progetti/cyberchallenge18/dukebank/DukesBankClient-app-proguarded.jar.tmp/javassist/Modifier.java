// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Modifier.java

package javassist;


public class Modifier
{

            public Modifier()
            {
            }

            public static boolean isPublic(int i)
            {
/*  53*/        return (i & 1) != 0;
            }

            public static boolean isPrivate(int i)
            {
/*  61*/        return (i & 2) != 0;
            }

            public static boolean isProtected(int i)
            {
/*  69*/        return (i & 4) != 0;
            }

            public static boolean isPackage(int i)
            {
/*  77*/        return (i & 7) == 0;
            }

            public static boolean isStatic(int i)
            {
/*  85*/        return (i & 8) != 0;
            }

            public static boolean isFinal(int i)
            {
/*  93*/        return (i & 0x10) != 0;
            }

            public static boolean isSynchronized(int i)
            {
/* 101*/        return (i & 0x20) != 0;
            }

            public static boolean isVolatile(int i)
            {
/* 109*/        return (i & 0x40) != 0;
            }

            public static boolean isTransient(int i)
            {
/* 117*/        return (i & 0x80) != 0;
            }

            public static boolean isNative(int i)
            {
/* 125*/        return (i & 0x100) != 0;
            }

            public static boolean isInterface(int i)
            {
/* 133*/        return (i & 0x200) != 0;
            }

            public static boolean isAnnotation(int i)
            {
/* 143*/        return (i & 0x2000) != 0;
            }

            public static boolean isEnum(int i)
            {
/* 153*/        return (i & 0x4000) != 0;
            }

            public static boolean isAbstract(int i)
            {
/* 161*/        return (i & 0x400) != 0;
            }

            public static boolean isStrict(int i)
            {
/* 169*/        return (i & 0x800) != 0;
            }

            public static int setPublic(int i)
            {
/* 177*/        return i & -7 | 1;
            }

            public static int setProtected(int i)
            {
/* 185*/        return i & -4 | 4;
            }

            public static int setPrivate(int i)
            {
/* 193*/        return i & -6 | 2;
            }

            public static int setPackage(int i)
            {
/* 200*/        return i & -8;
            }

            public static int clear(int i, int j)
            {
/* 207*/        return i & ~j;
            }

            public static String toString(int i)
            {
/* 217*/        return java.lang.reflect.Modifier.toString(i);
            }

            public static final int PUBLIC = 1;
            public static final int PRIVATE = 2;
            public static final int PROTECTED = 4;
            public static final int STATIC = 8;
            public static final int FINAL = 16;
            public static final int SYNCHRONIZED = 32;
            public static final int VOLATILE = 64;
            public static final int VARARGS = 128;
            public static final int TRANSIENT = 128;
            public static final int NATIVE = 256;
            public static final int INTERFACE = 512;
            public static final int ABSTRACT = 1024;
            public static final int STRICT = 2048;
            public static final int ANNOTATION = 8192;
            public static final int ENUM = 16384;
}
