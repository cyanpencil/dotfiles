// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Compiler.java

package javassist.tools.reflect;

import java.io.PrintStream;
import javassist.ClassPool;
import javassist.CtClass;

// Referenced classes of package javassist.tools.reflect:
//            CompiledClass, Reflection

public class Compiler
{

            public Compiler()
            {
            }

            public static void main(String args[])
                throws Exception
            {
/*  74*/        if(args.length == 0)
                {
/*  75*/            help(System.err);
/*  76*/            return;
                }
/*  79*/        CompiledClass acompiledclass[] = new CompiledClass[args.length];
/*  80*/        if((args = parse(args, acompiledclass)) <= 0)
                {
/*  83*/            System.err.println("bad parameter.");
/*  84*/            return;
                } else
                {
/*  87*/            processClasses(acompiledclass, args);
/*  88*/            return;
                }
            }

            private static void processClasses(CompiledClass acompiledclass[], int i)
                throws Exception
            {
/*  93*/        Reflection reflection = new Reflection();
/*  94*/        ClassPool classpool = ClassPool.getDefault();
/*  95*/        reflection.start(classpool);
/*  97*/        for(int j = 0; j < i; j++)
                {
/*  98*/            CtClass ctclass = classpool.get(acompiledclass[j].classname);
/*  99*/            if(acompiledclass[j].metaobject != null || acompiledclass[j].classobject != null)
                    {
                        String s;
/* 103*/                if(acompiledclass[j].metaobject == null)
/* 104*/                    s = "javassist.tools.reflect.Metaobject";
/* 106*/                else
/* 106*/                    s = acompiledclass[j].metaobject;
                        String s1;
/* 108*/                if(acompiledclass[j].classobject == null)
/* 109*/                    s1 = "javassist.tools.reflect.ClassMetaobject";
/* 111*/                else
/* 111*/                    s1 = acompiledclass[j].classobject;
/* 113*/                if(!reflection.makeReflective(ctclass, classpool.get(s), classpool.get(s1)))
/* 115*/                    System.err.println((new StringBuilder("Warning: ")).append(ctclass.getName()).append(" is reflective.  It was not changed.").toString());
/* 118*/                System.err.println((new StringBuilder()).append(ctclass.getName()).append(": ").append(s).append(", ").append(s1).toString());
                    } else
                    {
/* 122*/                System.err.println((new StringBuilder()).append(ctclass.getName()).append(": not reflective").toString());
                    }
                }

/* 125*/        for(int k = 0; k < i; k++)
                {
/* 126*/            reflection.onLoad(classpool, acompiledclass[k].classname);
/* 127*/            classpool.get(acompiledclass[k].classname).writeFile();
                }

            }

            private static int parse(String as[], CompiledClass acompiledclass[])
            {
/* 132*/        int i = -1;
/* 133*/        for(int j = 0; j < as.length; j++)
                {
                    String s;
/* 134*/            if((s = as[j]).equals("-m"))
                    {
/* 136*/                if(i < 0 || j + 1 > as.length)
/* 137*/                    return -1;
/* 139*/                acompiledclass[i].metaobject = as[++j];
/* 139*/                continue;
                    }
/* 140*/            if(s.equals("-c"))
                    {
/* 141*/                if(i < 0 || j + 1 > as.length)
/* 142*/                    return -1;
/* 144*/                acompiledclass[i].classobject = as[++j];
/* 144*/                continue;
                    }
/* 145*/            if(s.charAt(0) == '-')
/* 146*/                return -1;
                    CompiledClass compiledclass;
/* 148*/            (compiledclass = new CompiledClass()).classname = s;
/* 150*/            compiledclass.metaobject = null;
/* 151*/            compiledclass.classobject = null;
/* 152*/            acompiledclass[++i] = compiledclass;
                }

/* 156*/        return i + 1;
            }

            private static void help(PrintStream printstream)
            {
/* 160*/        printstream.println("Usage: java javassist.tools.reflect.Compiler");
/* 161*/        printstream.println("            (<class> [-m <metaobject>] [-c <class metaobject>])+");
            }
}
