// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassPoolTail.java

package javassist;


// Referenced classes of package javassist:
//            ClassPath

final class ClassPathList
{

            ClassPathList(ClassPath classpath, ClassPathList classpathlist)
            {
/*  30*/        next = classpathlist;
/*  31*/        path = classpath;
            }

            ClassPathList next;
            ClassPath path;
}
