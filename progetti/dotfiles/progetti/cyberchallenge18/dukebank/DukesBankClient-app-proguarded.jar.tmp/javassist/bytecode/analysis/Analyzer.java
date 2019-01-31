// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Analyzer.java

package javassist.bytecode.analysis;

import java.util.Collection;
import java.util.Iterator;
import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.analysis:
//            Executor, Frame, IntQueue, Subroutine, 
//            SubroutineScanner, Type, Util

public class Analyzer
    implements Opcode
{
    static class ExceptionInfo
    {

                private int end;
                private int handler;
                private int start;
                private Type type;





                private ExceptionInfo(int i, int j, int k, Type type1)
                {
/* 100*/            start = i;
/* 101*/            end = j;
/* 102*/            handler = k;
/* 103*/            type = type1;
                }

    }


            public Analyzer()
            {
            }

            public Frame[] analyze(CtClass ctclass, MethodInfo methodinfo)
                throws BadBytecode
            {
/* 123*/        clazz = ctclass;
                Object obj;
/* 124*/        if((obj = methodinfo.getCodeAttribute()) == null)
/* 127*/            return null;
/* 129*/        int i = ((CodeAttribute) (obj)).getMaxLocals();
/* 130*/        int j = ((CodeAttribute) (obj)).getMaxStack();
/* 131*/        int k = ((CodeAttribute) (obj)).getCodeLength();
/* 133*/        obj = ((CodeAttribute) (obj)).iterator();
/* 134*/        IntQueue intqueue = new IntQueue();
/* 136*/        exceptions = buildExceptionInfo(methodinfo);
/* 137*/        subroutines = scanner.scan(methodinfo);
/* 139*/        ctclass = new Executor(ctclass.getClassPool(), methodinfo.getConstPool());
/* 140*/        frames = new Frame[k];
/* 141*/        frames[((CodeIterator) (obj)).lookAhead()] = firstFrame(methodinfo, i, j);
/* 142*/        intqueue.add(((CodeIterator) (obj)).next());
/* 143*/        for(; !intqueue.isEmpty(); analyzeNextEntry(methodinfo, ((CodeIterator) (obj)), intqueue, ctclass));
/* 147*/        return frames;
            }

            public Frame[] analyze(CtMethod ctmethod)
                throws BadBytecode
            {
/* 165*/        return analyze(ctmethod.getDeclaringClass(), ctmethod.getMethodInfo2());
            }

            private void analyzeNextEntry(MethodInfo methodinfo, CodeIterator codeiterator, IntQueue intqueue, Executor executor)
                throws BadBytecode
            {
/* 170*/        int i = intqueue.take();
/* 171*/        codeiterator.move(i);
/* 172*/        codeiterator.next();
/* 174*/        Frame frame = frames[i].copy();
/* 175*/        Subroutine subroutine = subroutines[i];
/* 178*/        try
                {
/* 178*/            executor.execute(methodinfo, i, codeiterator, frame, subroutine);
                }
                // Misplaced declaration of an exception variable
/* 179*/        catch(Executor executor)
                {
/* 180*/            throw new BadBytecode((new StringBuilder()).append(executor.getMessage()).append("[pos = ").append(i).append("]").toString(), executor);
                }
/* 183*/        if((executor = codeiterator.byteAt(i)) == 170)
/* 186*/            mergeTableSwitch(intqueue, i, codeiterator, frame);
/* 187*/        else
/* 187*/        if(executor == 171)
/* 188*/            mergeLookupSwitch(intqueue, i, codeiterator, frame);
/* 189*/        else
/* 189*/        if(executor == 169)
/* 190*/            mergeRet(intqueue, codeiterator, i, frame, subroutine);
/* 191*/        else
/* 191*/        if(Util.isJumpInstruction(executor))
                {
/* 192*/            int j = Util.getJumpTarget(i, codeiterator);
/* 194*/            if(Util.isJsr(executor))
/* 196*/                mergeJsr(intqueue, frames[i], subroutines[j], i, lookAhead(codeiterator, i));
/* 197*/            else
/* 197*/            if(!Util.isGoto(executor))
/* 198*/                merge(intqueue, frame, lookAhead(codeiterator, i));
/* 201*/            merge(intqueue, frame, j);
                } else
/* 202*/        if(executor != 191 && !Util.isReturn(executor))
/* 204*/            merge(intqueue, frame, lookAhead(codeiterator, i));
/* 210*/        mergeExceptionHandlers(intqueue, methodinfo, i, frame);
            }

            private ExceptionInfo[] buildExceptionInfo(MethodInfo methodinfo)
            {
/* 214*/        ConstPool constpool = methodinfo.getConstPool();
/* 215*/        ClassPool classpool = clazz.getClassPool();
/* 217*/        ExceptionInfo aexceptioninfo[] = new ExceptionInfo[(methodinfo = methodinfo.getCodeAttribute().getExceptionTable()).size()];
/* 219*/        for(int i = 0; i < methodinfo.size(); i++)
                {
/* 220*/            int j = methodinfo.catchType(i);
                    Type type;
/* 223*/            try
                    {
/* 223*/                type = j != 0 ? Type.get(classpool.get(constpool.getClassInfo(j))) : Type.THROWABLE;
                    }
                    // Misplaced declaration of an exception variable
/* 224*/            catch(MethodInfo methodinfo)
                    {
/* 225*/                throw new IllegalStateException(methodinfo.getMessage());
                    }
/* 228*/            aexceptioninfo[i] = new ExceptionInfo(methodinfo.startPc(i), methodinfo.endPc(i), methodinfo.handlerPc(i), type);
                }

/* 231*/        return aexceptioninfo;
            }

            private Frame firstFrame(MethodInfo methodinfo, int i, int j)
            {
/* 235*/        int k = 0;
/* 237*/        i = new Frame(i, j);
/* 238*/        if((methodinfo.getAccessFlags() & 8) == 0)
                {
/* 239*/            k++;
/* 239*/            i.setLocal(0, Type.get(clazz));
                }
/* 244*/        try
                {
/* 244*/            methodinfo = Descriptor.getParameterTypes(methodinfo.getDescriptor(), clazz.getClassPool());
                }
                // Misplaced declaration of an exception variable
/* 245*/        catch(int j)
                {
/* 246*/            throw new RuntimeException(j);
                }
/* 249*/        for(j = 0; j < methodinfo.length; j++)
                {
/* 250*/            Type type = zeroExtend(Type.get(methodinfo[j]));
/* 251*/            i.setLocal(k++, type);
/* 252*/            if(type.getSize() == 2)
/* 253*/                i.setLocal(k++, Type.TOP);
                }

/* 256*/        return i;
            }

            private int getNext(CodeIterator codeiterator, int i, int j)
                throws BadBytecode
            {
/* 260*/        codeiterator.move(i);
/* 261*/        codeiterator.next();
/* 262*/        i = codeiterator.lookAhead();
/* 263*/        codeiterator.move(j);
/* 264*/        codeiterator.next();
/* 266*/        return i;
            }

            private int lookAhead(CodeIterator codeiterator, int i)
                throws BadBytecode
            {
/* 270*/        if(!codeiterator.hasNext())
/* 271*/            throw new BadBytecode((new StringBuilder("Execution falls off end! [pos = ")).append(i).append("]").toString());
/* 273*/        else
/* 273*/            return codeiterator.lookAhead();
            }

            private void merge(IntQueue intqueue, Frame frame, int i)
            {
                Frame frame1;
/* 278*/        if((frame1 = frames[i]) == null)
                {
/* 282*/            frames[i] = frame.copy();
/* 283*/            frame = 1;
                } else
                {
/* 285*/            frame = frame1.merge(frame);
                }
/* 288*/        if(frame != 0)
/* 289*/            intqueue.add(i);
            }

            private void mergeExceptionHandlers(IntQueue intqueue, MethodInfo methodinfo, int i, Frame frame)
            {
/* 294*/        for(methodinfo = 0; methodinfo < exceptions.length; methodinfo++)
                {
/* 295*/            ExceptionInfo exceptioninfo = exceptions[methodinfo];
/* 298*/            if(i >= exceptioninfo.start && i < exceptioninfo.end)
                    {
                        Frame frame1;
/* 299*/                (frame1 = frame.copy()).clearStack();
/* 301*/                frame1.push(exceptioninfo.type);
/* 302*/                merge(intqueue, frame1, exceptioninfo.handler);
                    }
                }

            }

            private void mergeJsr(IntQueue intqueue, Frame frame, Subroutine subroutine, int i, int j)
                throws BadBytecode
            {
/* 308*/        if(subroutine == null)
/* 309*/            throw new BadBytecode((new StringBuilder("No subroutine at jsr target! [pos = ")).append(i).append("]").toString());
/* 311*/        i = frames[j];
/* 312*/        boolean flag = false;
/* 314*/        if(i == null)
                {
/* 315*/            i = frames[j] = frame.copy();
/* 316*/            flag = true;
                } else
                {
/* 318*/            for(int k = 0; k < frame.localsLength(); k++)
                    {
/* 320*/                if(subroutine.isAccessed(k))
/* 321*/                    continue;
/* 321*/                Type type = i.getLocal(k);
/* 322*/                Type type1 = frame.getLocal(k);
/* 323*/                if(type == null)
                        {
/* 324*/                    i.setLocal(k, type1);
                        } else
                        {
/* 329*/                    type1 = type.merge(type1);
/* 331*/                    i.setLocal(k, type1);
/* 332*/                    if(type1.equals(type) && !type1.popChanged())
/* 333*/                        continue;
                        }
/* 333*/                flag = true;
                    }

                }
/* 338*/        if(!i.isJsrMerged())
                {
/* 339*/            i.setJsrMerged(true);
/* 340*/            flag = true;
                }
/* 343*/        if(flag && i.isRetMerged())
/* 344*/            intqueue.add(j);
            }

            private void mergeLookupSwitch(IntQueue intqueue, int i, CodeIterator codeiterator, Frame frame)
                throws BadBytecode
            {
/* 349*/        int j = (i & -4) + 4;
/* 351*/        merge(intqueue, frame, i + codeiterator.s32bitAt(j));
                int k;
/* 352*/        k = ((k = codeiterator.s32bitAt(j += 4)) << 3) + (j += 4);
/* 356*/        for(j += 4; j < k; j += 8)
                {
/* 357*/            int l = codeiterator.s32bitAt(j) + i;
/* 358*/            merge(intqueue, frame, l);
                }

            }

            private void mergeRet(IntQueue intqueue, CodeIterator codeiterator, int i, Frame frame, Subroutine subroutine)
                throws BadBytecode
            {
/* 363*/        if(subroutine == null)
/* 364*/            throw new BadBytecode((new StringBuilder("Ret on no subroutine! [pos = ")).append(i).append("]").toString());
/* 366*/        Iterator iterator = subroutine.callers().iterator();
/* 367*/        do
                {
/* 367*/            if(!iterator.hasNext())
/* 368*/                break;
/* 368*/            int j = ((Integer)iterator.next()).intValue();
/* 369*/            j = getNext(codeiterator, j, i);
                    boolean flag;
                    Frame frame1;
/* 372*/            if((frame1 = frames[j]) == null)
                    {
/* 374*/                frame1 = frames[j] = frame.copyStack();
/* 375*/                flag = true;
                    } else
                    {
/* 377*/                flag = frame1.mergeStack(frame);
                    }
/* 380*/            Iterator iterator1 = subroutine.accessed().iterator();
/* 380*/            do
                    {
/* 380*/                if(!iterator1.hasNext())
/* 381*/                    break;
/* 381*/                int k = ((Integer)iterator1.next()).intValue();
/* 382*/                Type type = frame1.getLocal(k);
/* 383*/                Type type1 = frame.getLocal(k);
/* 384*/                if(type != type1)
                        {
/* 385*/                    frame1.setLocal(k, type1);
/* 386*/                    flag = true;
                        }
                    } while(true);
/* 390*/            if(!frame1.isRetMerged())
                    {
/* 391*/                frame1.setRetMerged(true);
/* 392*/                flag = true;
                    }
/* 395*/            if(flag && frame1.isJsrMerged())
/* 396*/                intqueue.add(j);
                } while(true);
            }

            private void mergeTableSwitch(IntQueue intqueue, int i, CodeIterator codeiterator, Frame frame)
                throws BadBytecode
            {
/* 403*/        int j = (i & -4) + 4;
/* 405*/        merge(intqueue, frame, i + codeiterator.s32bitAt(j));
/* 406*/        int k = codeiterator.s32bitAt(j += 4);
                int l;
/* 407*/        for(k = (((l = codeiterator.s32bitAt(j += 4)) - k) + 1 << 2) + (j += 4); j < k; j += 4)
                {
/* 412*/            int i1 = codeiterator.s32bitAt(j) + i;
/* 413*/            merge(intqueue, frame, i1);
                }

            }

            private Type zeroExtend(Type type)
            {
/* 418*/        if(type == Type.SHORT || type == Type.BYTE || type == Type.CHAR || type == Type.BOOLEAN)
/* 419*/            return Type.INTEGER;
/* 421*/        else
/* 421*/            return type;
            }

            private final SubroutineScanner scanner = new SubroutineScanner();
            private CtClass clazz;
            private ExceptionInfo exceptions[];
            private Frame frames[];
            private Subroutine subroutines[];
}
