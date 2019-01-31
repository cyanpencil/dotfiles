// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MemberResolver.java

package javassist.compiler;

import java.lang.ref.WeakReference;
import java.util.*;
import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Keyword;
import javassist.compiler.ast.Symbol;

// Referenced classes of package javassist.compiler:
//            CompileError, NoFieldException, TokenId

public class MemberResolver
    implements TokenId
{
    public static class Method
    {

                public boolean isStatic()
                {
                    int i;
/*  58*/            return ((i = info.getAccessFlags()) & 8) != 0;
                }

                public CtClass declaring;
                public MethodInfo info;
                public int notmatch;

                public Method(CtClass ctclass, MethodInfo methodinfo, int i)
                {
/*  49*/            declaring = ctclass;
/*  50*/            info = methodinfo;
/*  51*/            notmatch = i;
                }
    }


            public MemberResolver(ClassPool classpool)
            {
/* 421*/        invalidNames = null;
/*  34*/        classPool = classpool;
            }

            public ClassPool getClassPool()
            {
/*  37*/        return classPool;
            }

            private static void fatal()
                throws CompileError
            {
/*  40*/        throw new CompileError("fatal");
            }

            public Method lookupMethod(CtClass ctclass, CtClass ctclass1, MethodInfo methodinfo, String s, int ai[], int ai1[], String as[])
                throws CompileError
            {
/*  69*/        MethodInfo methodinfo1 = null;
/*  71*/        if(methodinfo != null && ctclass == ctclass1 && methodinfo.getName().equals(s) && (ctclass1 = compareSignature(methodinfo.getDescriptor(), ai, ai1, as)) != -1)
                {
/*  76*/            methodinfo = new Method(ctclass, methodinfo, ctclass1);
/*  77*/            if(ctclass1 == 0)
/*  78*/                return methodinfo;
/*  80*/            methodinfo1 = methodinfo;
                }
/*  84*/        if((ctclass1 = lookupMethod(ctclass, s, ai, ai1, as, methodinfo1 != null)) != null)
/*  87*/            return ctclass1;
/*  89*/        else
/*  89*/            return methodinfo1;
            }

            private Method lookupMethod(CtClass ctclass, String s, int ai[], int ai1[], String as[], boolean flag)
                throws CompileError
            {
                Method method;
                int i;
                boolean flag1;
/*  97*/        method = null;
                Object obj;
/*  98*/        if((obj = ctclass.getClassFile2()) != null)
                {
/* 102*/            int j = ((List) (obj = ((ClassFile) (obj)).getMethods())).size();
/* 104*/            for(int k = 0; k < j; k++)
                    {
                        MethodInfo methodinfo;
                        int i1;
/* 105*/                if(!(methodinfo = (MethodInfo)((List) (obj)).get(k)).getName().equals(s) || (i1 = compareSignature(methodinfo.getDescriptor(), ai, ai1, as)) == -1)
/* 110*/                    continue;
/* 110*/                Method method2 = new Method(ctclass, methodinfo, i1);
/* 111*/                if(i1 == 0)
/* 112*/                    return method2;
/* 113*/                if(method == null || method.notmatch > i1)
/* 114*/                    method = method2;
                    }

                }
/* 120*/        if(flag)
/* 121*/            method = null;
/* 123*/        else
/* 123*/            flag = method != null;
/* 125*/        flag1 = Modifier.isInterface(i = ctclass.getModifiers());
                CtClass ctclass1;
                Method method1;
/* 129*/        if(!flag1 && (ctclass1 = ctclass.getSuperclass()) != null && (method1 = lookupMethod(ctclass1, s, ai, ai1, as, flag)) != null)
/* 135*/            return method1;
/* 139*/        break MISSING_BLOCK_LABEL_208;
/* 139*/        JVM INSTR pop ;
/* 141*/        if(!flag1 && !Modifier.isAbstract(i))
/* 143*/            break MISSING_BLOCK_LABEL_315;
                CtClass actclass[];
                int l;
                int j1;
/* 143*/        l = (actclass = ctclass.getInterfaces()).length;
/* 145*/        j1 = 0;
_L1:
/* 145*/        if(j1 >= l)
/* 146*/            break MISSING_BLOCK_LABEL_273;
                Method method3;
/* 146*/        if((method3 = lookupMethod(actclass[j1], s, ai, ai1, as, flag)) != null)
/* 150*/            return method3;
/* 145*/        j1++;
                  goto _L1
                CtClass ctclass2;
                Method method4;
/* 153*/        if(flag1 && (ctclass2 = ctclass.getSuperclass()) != null && (method4 = lookupMethod(ctclass2, s, ai, ai1, as, flag)) != null)
/* 160*/            return method4;
/* 164*/        break MISSING_BLOCK_LABEL_315;
/* 164*/        JVM INSTR pop ;
/* 166*/        return method;
            }

            private int compareSignature(String s, int ai[], int ai1[], String as[])
                throws CompileError
            {
                int i;
                int j;
                int k;
                int l;
                int i1;
/* 188*/        i = 0;
/* 189*/        j = 1;
/* 190*/        if((k = ai.length) != Descriptor.numOfParameters(s))
/* 192*/            return -1;
/* 194*/        l = s.length();
/* 195*/        i1 = 0;
_L3:
/* 195*/        if(j >= l) goto _L2; else goto _L1
_L1:
                int j1;
                String s1;
/* 196*/        if((j1 = s.charAt(j++)) == ')')
/* 198*/            if(i1 == k)
/* 198*/                return i;
/* 198*/            else
/* 198*/                return -1;
/* 199*/        if(i1 >= k)
/* 200*/            return -1;
/* 202*/        int k1 = 0;
/* 203*/        for(; j1 == 91; j1 = s.charAt(j++))
/* 204*/            k1++;

/* 208*/        if(ai[i1] == 412)
                {
/* 209*/            if(k1 == 0 && j1 != 76)
/* 210*/                return -1;
/* 212*/            if(j1 == 76)
/* 213*/                j = s.indexOf(';', j) + 1;
/* 213*/            continue; /* Loop/switch isn't completed */
                }
/* 215*/        if(ai1[i1] != k1)
                {
/* 216*/            if(k1 != 0 || j1 != 76 || !s.startsWith("java/lang/Object;", j))
/* 218*/                return -1;
/* 221*/            j = s.indexOf(';', j) + 1;
/* 222*/            i++;
/* 223*/            if(j <= 0)
/* 224*/                return -1;
/* 226*/            continue; /* Loop/switch isn't completed */
                }
/* 226*/        if(j1 != 76)
/* 227*/            break MISSING_BLOCK_LABEL_304;
/* 227*/        if((j1 = s.indexOf(';', j)) < 0 || ai[i1] != 307)
/* 229*/            return -1;
/* 231*/        if((s1 = s.substring(j, j1)).equals(as[i1]))
/* 233*/            break MISSING_BLOCK_LABEL_295;
/* 233*/        j = lookupClassByJvmName(as[i1]);
/* 235*/        if(j.subtypeOf(lookupClassByJvmName(s1)))
                {
/* 236*/            i++;
/* 236*/            break MISSING_BLOCK_LABEL_295;
                }
/* 238*/        return -1;
/* 240*/        JVM INSTR pop ;
/* 241*/        i++;
/* 245*/        j = j1 + 1;
/* 246*/        continue; /* Loop/switch isn't completed */
/* 248*/        j1 = descToType(j1);
/* 249*/        int l1 = ai[i1];
/* 250*/        if(j1 == l1)
/* 251*/            continue; /* Loop/switch isn't completed */
/* 251*/        if(j1 == 324 && (l1 == 334 || l1 == 303 || l1 == 306))
/* 253*/            i++;
/* 255*/        else
/* 255*/            return -1;
/* 195*/        i1++;
                  goto _L3
_L2:
/* 259*/        return -1;
            }

            public CtField lookupFieldByJvmName2(String s, Symbol symbol, ASTree astree)
                throws NoFieldException
            {
                CtClass ctclass;
/* 271*/        symbol = symbol.get();
/* 274*/        try
                {
/* 274*/            ctclass = lookupClass(jvmToJavaName(s), true);
                }
/* 276*/        catch(CompileError _ex)
                {
/* 278*/            throw new NoFieldException((new StringBuilder()).append(s).append("/").append(symbol).toString(), astree);
                }
/* 282*/        return ctclass.getField(symbol);
/* 284*/        JVM INSTR pop ;
/* 286*/        s = javaToJvmName(ctclass.getName());
/* 287*/        throw new NoFieldException((new StringBuilder()).append(s).append("$").append(symbol).toString(), astree);
            }

            public CtField lookupFieldByJvmName(String s, Symbol symbol)
                throws CompileError
            {
/* 297*/        return lookupField(jvmToJavaName(s), symbol);
            }

            public CtField lookupField(String s, Symbol symbol)
                throws CompileError
            {
/* 306*/        s = lookupClass(s, false);
/* 308*/        return s.getField(symbol.get());
/* 310*/        JVM INSTR pop ;
/* 311*/        throw new CompileError((new StringBuilder("no such field: ")).append(symbol.get()).toString());
            }

            public CtClass lookupClassByName(ASTList astlist)
                throws CompileError
            {
/* 315*/        return lookupClass(Declarator.astToClassName(astlist, '.'), false);
            }

            public CtClass lookupClassByJvmName(String s)
                throws CompileError
            {
/* 319*/        return lookupClass(jvmToJavaName(s), false);
            }

            public CtClass lookupClass(Declarator declarator)
                throws CompileError
            {
/* 323*/        return lookupClass(declarator.getType(), declarator.getArrayDim(), declarator.getClassName());
            }

            public CtClass lookupClass(int i, int j, String s)
                throws CompileError
            {
/* 335*/        if(i == 307)
                {
/* 336*/            i = lookupClassByJvmName(s);
/* 337*/            if(j > 0)
/* 338*/                i = i.getName();
/* 340*/            else
/* 340*/                return i;
                } else
                {
/* 343*/            i = getTypeName(i);
                }
/* 345*/        while(j-- > 0) 
/* 346*/            i = (new StringBuilder()).append(i).append("[]").toString();
/* 348*/        return lookupClass(i, false);
            }

            static String getTypeName(int i)
                throws CompileError
            {
/* 355*/        String s = "";
/* 356*/        switch(i)
                {
/* 358*/        case 301: 
/* 358*/            s = "boolean";
                    break;

/* 361*/        case 306: 
/* 361*/            s = "char";
                    break;

/* 364*/        case 303: 
/* 364*/            s = "byte";
                    break;

/* 367*/        case 334: 
/* 367*/            s = "short";
                    break;

/* 370*/        case 324: 
/* 370*/            s = "int";
                    break;

/* 373*/        case 326: 
/* 373*/            s = "long";
                    break;

/* 376*/        case 317: 
/* 376*/            s = "float";
                    break;

/* 379*/        case 312: 
/* 379*/            s = "double";
                    break;

/* 382*/        case 344: 
/* 382*/            s = "void";
                    break;

/* 385*/        default:
/* 385*/            fatal();
                    break;
                }
/* 388*/        return s;
            }

            public CtClass lookupClass(String s, boolean flag)
                throws CompileError
            {
                Hashtable hashtable;
                Object obj;
/* 397*/        if((obj = (hashtable = getInvalidNames()).get(s)) == "<invalid>")
/* 400*/            throw new CompileError((new StringBuilder("no such class: ")).append(s).toString());
/* 401*/        if(obj == null)
/* 403*/            break MISSING_BLOCK_LABEL_61;
/* 403*/        return classPool.get((String)obj);
/* 405*/        JVM INSTR pop ;
/* 409*/        try
                {
/* 409*/            flag = lookupClass0(s, flag);
                }
/* 411*/        catch(NotFoundException _ex)
                {
/* 412*/            flag = searchImports(s);
                }
/* 415*/        hashtable.put(s, flag.getName());
/* 416*/        return flag;
            }

            public static int getInvalidMapSize()
            {
/* 424*/        return invalidNamesMap.size();
            }

            private Hashtable getInvalidNames()
            {
/* 427*/        if((obj = invalidNames) == null)
                {
/* 429*/            synchronized(javassist/compiler/MemberResolver)
                    {
                        WeakReference weakreference;
/* 430*/                if((weakreference = (WeakReference)invalidNamesMap.get(classPool)) != null)
/* 432*/                    obj = (Hashtable)weakreference.get();
/* 434*/                if(obj == null)
                        {
/* 435*/                    obj = new Hashtable();
/* 436*/                    invalidNamesMap.put(classPool, new WeakReference(obj));
                        }
                    }
/* 440*/            invalidNames = ((Hashtable) (obj));
                }
/* 443*/        return ((Hashtable) (obj));
            }

            private CtClass searchImports(String s)
                throws CompileError
            {
/* 449*/        if(s.indexOf('.') >= 0) goto _L2; else goto _L1
_L1:
/* 450*/        Iterator iterator = classPool.getImportedPackages();
_L3:
                String s1;
                String s2;
/* 451*/        if(!iterator.hasNext())
/* 452*/            break; /* Loop/switch isn't completed */
/* 452*/        s1 = (String)iterator.next();
/* 453*/        s2 = (new StringBuilder()).append(s1).append('.').append(s).toString();
/* 455*/        return classPool.get(s2);
/* 457*/        JVM INSTR pop ;
/* 459*/        if(s1.endsWith((new StringBuilder(".")).append(s).toString()))
/* 460*/            return classPool.get(s1);
/* 462*/        continue; /* Loop/switch isn't completed */
/* 462*/        JVM INSTR pop ;
/* 464*/        if(true) goto _L3; else goto _L2
_L2:
/* 467*/        getInvalidNames().put(s, "<invalid>");
/* 468*/        throw new CompileError((new StringBuilder("no such class: ")).append(s).toString());
            }

            private CtClass lookupClass0(String s, boolean flag)
                throws NotFoundException
            {
/* 474*/        CtClass ctclass = null;
/* 477*/        do
/* 477*/            try
                    {
/* 477*/                ctclass = classPool.get(s);
                    }
/* 479*/            catch(NotFoundException notfoundexception)
                    {
/* 480*/                int i = s.lastIndexOf('.');
/* 481*/                if(flag || i < 0)
/* 482*/                    throw notfoundexception;
/* 484*/                (s = new StringBuffer(s)).setCharAt(i, '$');
/* 486*/                s = s.toString();
                    }
/* 489*/        while(ctclass == null);
/* 490*/        return ctclass;
            }

            public String resolveClassName(ASTList astlist)
                throws CompileError
            {
/* 499*/        if(astlist == null)
/* 500*/            return null;
/* 502*/        else
/* 502*/            return javaToJvmName(lookupClassByName(astlist).getName());
            }

            public String resolveJvmClassName(String s)
                throws CompileError
            {
/* 509*/        if(s == null)
/* 510*/            return null;
/* 512*/        else
/* 512*/            return javaToJvmName(lookupClassByJvmName(s).getName());
            }

            public static CtClass getSuperclass(CtClass ctclass)
                throws CompileError
            {
                CtClass ctclass1;
/* 517*/        if((ctclass1 = ctclass.getSuperclass()) != null)
/* 519*/            return ctclass1;
/* 521*/        break MISSING_BLOCK_LABEL_15;
/* 521*/        JVM INSTR pop ;
/* 522*/        throw new CompileError((new StringBuilder("cannot find the super class of ")).append(ctclass.getName()).toString());
            }

            public static String javaToJvmName(String s)
            {
/* 527*/        return s.replace('.', '/');
            }

            public static String jvmToJavaName(String s)
            {
/* 531*/        return s.replace('/', '.');
            }

            public static int descToType(char c)
                throws CompileError
            {
/* 535*/        switch(c)
                {
/* 537*/        case 90: // 'Z'
/* 537*/            return 301;

/* 539*/        case 67: // 'C'
/* 539*/            return 306;

/* 541*/        case 66: // 'B'
/* 541*/            return 303;

/* 543*/        case 83: // 'S'
/* 543*/            return 334;

/* 545*/        case 73: // 'I'
/* 545*/            return 324;

/* 547*/        case 74: // 'J'
/* 547*/            return 326;

/* 549*/        case 70: // 'F'
/* 549*/            return 317;

/* 551*/        case 68: // 'D'
/* 551*/            return 312;

/* 553*/        case 86: // 'V'
/* 553*/            return 344;

/* 556*/        case 76: // 'L'
/* 556*/        case 91: // '['
/* 556*/            return 307;

/* 558*/        case 69: // 'E'
/* 558*/        case 71: // 'G'
/* 558*/        case 72: // 'H'
/* 558*/        case 75: // 'K'
/* 558*/        case 77: // 'M'
/* 558*/        case 78: // 'N'
/* 558*/        case 79: // 'O'
/* 558*/        case 80: // 'P'
/* 558*/        case 81: // 'Q'
/* 558*/        case 82: // 'R'
/* 558*/        case 84: // 'T'
/* 558*/        case 85: // 'U'
/* 558*/        case 87: // 'W'
/* 558*/        case 88: // 'X'
/* 558*/        case 89: // 'Y'
/* 558*/        default:
/* 558*/            fatal();
                    break;
                }
/* 559*/        return 344;
            }

            public static int getModifiers(ASTList astlist)
            {
/* 564*/        int i = 0;
/* 565*/        do
/* 565*/            if(astlist != null)
                    {
/* 566*/                Keyword keyword = (Keyword)astlist.head();
/* 567*/                astlist = astlist.tail();
/* 568*/                switch(keyword.get())
                        {
/* 570*/                case 335: 
/* 570*/                    i |= 8;
                            break;

/* 573*/                case 315: 
/* 573*/                    i |= 0x10;
                            break;

/* 576*/                case 338: 
/* 576*/                    i |= 0x20;
                            break;

/* 579*/                case 300: 
/* 579*/                    i |= 0x400;
                            break;

/* 582*/                case 332: 
/* 582*/                    i |= 1;
                            break;

/* 585*/                case 331: 
/* 585*/                    i |= 4;
                            break;

/* 588*/                case 330: 
/* 588*/                    i |= 2;
                            break;

/* 591*/                case 345: 
/* 591*/                    i |= 0x40;
                            break;

/* 594*/                case 342: 
/* 594*/                    i |= 0x80;
                            break;

/* 597*/                case 347: 
/* 597*/                    i |= 0x800;
                            break;
                        }
                    } else
                    {
/* 602*/                return i;
                    }
/* 602*/        while(true);
            }

            private ClassPool classPool;
            private static final int YES = 0;
            private static final int NO = -1;
            private static final String INVALID = "<invalid>";
            private static WeakHashMap invalidNamesMap = new WeakHashMap();
            private Hashtable invalidNames;

}
