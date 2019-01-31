// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Frame.java

package javassist.bytecode.analysis;


// Referenced classes of package javassist.bytecode.analysis:
//            Type

public class Frame
{

            public Frame(int i, int j)
            {
/*  38*/        locals = new Type[i];
/*  39*/        stack = new Type[j];
            }

            public Type getLocal(int i)
            {
/*  49*/        return locals[i];
            }

            public void setLocal(int i, Type type)
            {
/*  59*/        locals[i] = type;
            }

            public Type getStack(int i)
            {
/*  70*/        return stack[i];
            }

            public void setStack(int i, Type type)
            {
/*  80*/        stack[i] = type;
            }

            public void clearStack()
            {
/*  87*/        top = 0;
            }

            public int getTopIndex()
            {
/*  99*/        return top - 1;
            }

            public int localsLength()
            {
/* 109*/        return locals.length;
            }

            public Type peek()
            {
/* 118*/        if(top <= 0)
/* 119*/            throw new IndexOutOfBoundsException("Stack is empty");
/* 121*/        else
/* 121*/            return stack[top - 1];
            }

            public Type pop()
            {
/* 130*/        if(top <= 0)
/* 131*/            throw new IndexOutOfBoundsException("Stack is empty");
/* 132*/        else
/* 132*/            return stack[--top];
            }

            public void push(Type type)
            {
/* 141*/        stack[top++] = type;
            }

            public Frame copy()
            {
/* 152*/        Frame frame = new Frame(locals.length, stack.length);
/* 153*/        System.arraycopy(locals, 0, frame.locals, 0, locals.length);
/* 154*/        System.arraycopy(stack, 0, frame.stack, 0, stack.length);
/* 155*/        frame.top = top;
/* 156*/        return frame;
            }

            public Frame copyStack()
            {
/* 166*/        Frame frame = new Frame(locals.length, stack.length);
/* 167*/        System.arraycopy(stack, 0, frame.stack, 0, stack.length);
/* 168*/        frame.top = top;
/* 169*/        return frame;
            }

            public boolean mergeStack(Frame frame)
            {
/* 180*/        boolean flag = false;
/* 181*/        if(top != frame.top)
/* 182*/            throw new RuntimeException("Operand stacks could not be merged, they are different sizes!");
/* 184*/        for(int i = 0; i < top; i++)
                {
/* 185*/            if(stack[i] == null)
/* 186*/                continue;
                    Type type;
                    Type type1;
/* 186*/            if((type1 = (type = stack[i]).merge(frame.stack[i])) == Type.BOGUS)
/* 189*/                throw new RuntimeException((new StringBuilder("Operand stacks could not be merged due to differing primitive types: pos = ")).append(i).toString());
/* 191*/            stack[i] = type1;
/* 193*/            if(!type1.equals(type) || type1.popChanged())
/* 194*/                flag = true;
                }

/* 199*/        return flag;
            }

            public boolean merge(Frame frame)
            {
/* 210*/        boolean flag = false;
/* 213*/        for(int i = 0; i < locals.length; i++)
                {
/* 214*/            if(locals[i] != null)
                    {
                        Type type;
/* 215*/                Type type1 = (type = locals[i]).merge(frame.locals[i]);
/* 218*/                locals[i] = type1;
/* 219*/                if(!type1.equals(type) || type1.popChanged())
/* 220*/                    flag = true;
/* 222*/                continue;
                    }
/* 222*/            if(frame.locals[i] != null)
                    {
/* 223*/                locals[i] = frame.locals[i];
/* 224*/                flag = true;
                    }
                }

/* 228*/        return flag |= mergeStack(frame);
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/* 233*/        (stringbuffer = new StringBuffer()).append("locals = [");
/* 236*/        for(int i = 0; i < locals.length; i++)
                {
/* 237*/            stringbuffer.append(locals[i] != null ? locals[i].toString() : "empty");
/* 238*/            if(i < locals.length - 1)
/* 239*/                stringbuffer.append(", ");
                }

/* 241*/        stringbuffer.append("] stack = [");
/* 242*/        for(int j = 0; j < top; j++)
                {
/* 243*/            stringbuffer.append(stack[j]);
/* 244*/            if(j < top - 1)
/* 245*/                stringbuffer.append(", ");
                }

/* 247*/        stringbuffer.append("]");
/* 249*/        return stringbuffer.toString();
            }

            boolean isJsrMerged()
            {
/* 258*/        return jsrMerged;
            }

            void setJsrMerged(boolean flag)
            {
/* 267*/        jsrMerged = flag;
            }

            boolean isRetMerged()
            {
/* 277*/        return retMerged;
            }

            void setRetMerged(boolean flag)
            {
/* 287*/        retMerged = flag;
            }

            private Type locals[];
            private Type stack[];
            private int top;
            private boolean jsrMerged;
            private boolean retMerged;
}
