// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeConverter.java

package javassist;

import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.convert.TransformAccessArrayField;
import javassist.convert.TransformAfter;
import javassist.convert.TransformBefore;
import javassist.convert.TransformCall;
import javassist.convert.TransformFieldAccess;
import javassist.convert.TransformNew;
import javassist.convert.TransformNewClass;
import javassist.convert.TransformReadField;
import javassist.convert.TransformWriteField;
import javassist.convert.Transformer;

// Referenced classes of package javassist:
//            CannotCompileException, CtClass, CtMethod, Modifier, 
//            NotFoundException, CtField

public class CodeConverter
{
    public static class DefaultArrayAccessReplacementMethodNames
        implements ArrayAccessReplacementMethodNames
    {

                public String byteOrBooleanRead()
                {
/* 670*/            return "arrayReadByteOrBoolean";
                }

                public String byteOrBooleanWrite()
                {
/* 679*/            return "arrayWriteByteOrBoolean";
                }

                public String charRead()
                {
/* 688*/            return "arrayReadChar";
                }

                public String charWrite()
                {
/* 697*/            return "arrayWriteChar";
                }

                public String doubleRead()
                {
/* 706*/            return "arrayReadDouble";
                }

                public String doubleWrite()
                {
/* 715*/            return "arrayWriteDouble";
                }

                public String floatRead()
                {
/* 724*/            return "arrayReadFloat";
                }

                public String floatWrite()
                {
/* 733*/            return "arrayWriteFloat";
                }

                public String intRead()
                {
/* 742*/            return "arrayReadInt";
                }

                public String intWrite()
                {
/* 751*/            return "arrayWriteInt";
                }

                public String longRead()
                {
/* 760*/            return "arrayReadLong";
                }

                public String longWrite()
                {
/* 769*/            return "arrayWriteLong";
                }

                public String objectRead()
                {
/* 778*/            return "arrayReadObject";
                }

                public String objectWrite()
                {
/* 787*/            return "arrayWriteObject";
                }

                public String shortRead()
                {
/* 796*/            return "arrayReadShort";
                }

                public String shortWrite()
                {
/* 805*/            return "arrayWriteShort";
                }

                public DefaultArrayAccessReplacementMethodNames()
                {
                }
    }

    public static interface ArrayAccessReplacementMethodNames
    {

        public abstract String byteOrBooleanRead();

        public abstract String byteOrBooleanWrite();

        public abstract String charRead();

        public abstract String charWrite();

        public abstract String doubleRead();

        public abstract String doubleWrite();

        public abstract String floatRead();

        public abstract String floatWrite();

        public abstract String intRead();

        public abstract String intWrite();

        public abstract String longRead();

        public abstract String longWrite();

        public abstract String objectRead();

        public abstract String objectWrite();

        public abstract String shortRead();

        public abstract String shortWrite();
    }


            public CodeConverter()
            {
/*  51*/        transformers = null;
            }

            public void replaceNew(CtClass ctclass, CtClass ctclass1, String s)
            {
/*  97*/        transformers = new TransformNew(transformers, ctclass.getName(), ctclass1.getName(), s);
            }

            public void replaceNew(CtClass ctclass, CtClass ctclass1)
            {
/* 123*/        transformers = new TransformNewClass(transformers, ctclass.getName(), ctclass1.getName());
            }

            public void redirectFieldAccess(CtField ctfield, CtClass ctclass, String s)
            {
/* 146*/        transformers = new TransformFieldAccess(transformers, ctfield, ctclass.getName(), s);
            }

            public void replaceFieldRead(CtField ctfield, CtClass ctclass, String s)
            {
/* 186*/        transformers = new TransformReadField(transformers, ctfield, ctclass.getName(), s);
            }

            public void replaceFieldWrite(CtField ctfield, CtClass ctclass, String s)
            {
/* 227*/        transformers = new TransformWriteField(transformers, ctfield, ctclass.getName(), s);
            }

            public void replaceArrayAccess(CtClass ctclass, ArrayAccessReplacementMethodNames arrayaccessreplacementmethodnames)
                throws NotFoundException
            {
/* 330*/        transformers = new TransformAccessArrayField(transformers, ctclass.getName(), arrayaccessreplacementmethodnames);
            }

            public void redirectMethodCall(CtMethod ctmethod, CtMethod ctmethod1)
                throws CannotCompileException
            {
/* 352*/        String s = ctmethod.getMethodInfo2().getDescriptor();
/* 353*/        String s1 = ctmethod1.getMethodInfo2().getDescriptor();
/* 354*/        if(!s.equals(s1))
/* 355*/            throw new CannotCompileException((new StringBuilder("signature mismatch: ")).append(ctmethod1.getLongName()).toString());
/* 358*/        int i = ctmethod.getModifiers();
/* 359*/        int j = ctmethod1.getModifiers();
/* 360*/        if(Modifier.isStatic(i) != Modifier.isStatic(j) || Modifier.isPrivate(i) && !Modifier.isPrivate(j) || ctmethod.getDeclaringClass().isInterface() != ctmethod1.getDeclaringClass().isInterface())
                {
/* 364*/            throw new CannotCompileException((new StringBuilder("invoke-type mismatch ")).append(ctmethod1.getLongName()).toString());
                } else
                {
/* 367*/            transformers = new TransformCall(transformers, ctmethod, ctmethod1);
/* 369*/            return;
                }
            }

            public void redirectMethodCall(String s, CtMethod ctmethod)
                throws CannotCompileException
            {
/* 392*/        transformers = new TransformCall(transformers, s, ctmethod);
            }

            public void insertBeforeMethod(CtMethod ctmethod, CtMethod ctmethod1)
                throws CannotCompileException
            {
/* 435*/        try
                {
/* 435*/            transformers = new TransformBefore(transformers, ctmethod, ctmethod1);
/* 440*/            return;
                }
                // Misplaced declaration of an exception variable
/* 438*/        catch(CtMethod ctmethod)
                {
/* 439*/            throw new CannotCompileException(ctmethod);
                }
            }

            public void insertAfterMethod(CtMethod ctmethod, CtMethod ctmethod1)
                throws CannotCompileException
            {
/* 482*/        try
                {
/* 482*/            transformers = new TransformAfter(transformers, ctmethod, ctmethod1);
/* 487*/            return;
                }
                // Misplaced declaration of an exception variable
/* 485*/        catch(CtMethod ctmethod)
                {
/* 486*/            throw new CannotCompileException(ctmethod);
                }
            }

            protected void doit(CtClass ctclass, MethodInfo methodinfo, ConstPool constpool)
                throws CannotCompileException
            {
                CodeAttribute codeattribute;
/* 497*/        if((codeattribute = methodinfo.getCodeAttribute()) == null || transformers == null)
/* 499*/            return;
/* 500*/        for(Transformer transformer = transformers; transformer != null; transformer = transformer.getNext())
/* 501*/            transformer.initialize(constpool, ctclass, methodinfo);

/* 503*/        for(CodeIterator codeiterator = codeattribute.iterator(); codeiterator.hasNext();)
/* 506*/            try
                    {
/* 506*/                int j = codeiterator.next();
/* 507*/                Transformer transformer1 = transformers;
/* 507*/                while(transformer1 != null) 
                        {
/* 508*/                    j = transformer1.transform(ctclass, j, codeiterator, constpool);
/* 507*/                    transformer1 = transformer1.getNext();
                        }
                    }
/* 510*/            catch(BadBytecode badbytecode1)
                    {
/* 511*/                throw new CannotCompileException(badbytecode1);
                    }

/* 515*/        int k = 0;
/* 516*/        constpool = 0;
/* 517*/        for(Transformer transformer2 = transformers; transformer2 != null; transformer2 = transformer2.getNext())
                {
                    int i;
/* 518*/            if((i = transformer2.extraLocals()) > k)
/* 520*/                k = i;
/* 522*/            if((i = transformer2.extraStack()) > constpool)
/* 524*/                constpool = i;
                }

/* 527*/        for(Transformer transformer3 = transformers; transformer3 != null; transformer3 = transformer3.getNext())
/* 528*/            transformer3.clean();

/* 530*/        if(k > 0)
/* 531*/            codeattribute.setMaxLocals(codeattribute.getMaxLocals() + k);
/* 533*/        if(constpool > 0)
/* 534*/            codeattribute.setMaxStack(codeattribute.getMaxStack() + constpool);
/* 537*/        try
                {
/* 537*/            methodinfo.rebuildStackMapIf6(ctclass.getClassPool(), ctclass.getClassFile2());
/* 542*/            return;
                }
/* 540*/        catch(BadBytecode badbytecode)
                {
/* 541*/            throw new CannotCompileException(badbytecode.getMessage(), badbytecode);
                }
            }

            protected Transformer transformers;
}
