// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeAnalyzer.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            BadBytecode, CodeAttribute, CodeIterator, ConstPool, 
//            Descriptor, ExceptionTable, Opcode

class CodeAnalyzer
    implements Opcode
{

            public CodeAnalyzer(CodeAttribute codeattribute)
            {
/*  27*/        codeAttr = codeattribute;
/*  28*/        constPool = codeattribute.getConstPool();
            }

            public int computeMaxStack()
                throws BadBytecode
            {
                CodeIterator codeiterator;
                int j;
/*  39*/        int ai[] = new int[j = (codeiterator = codeAttr.iterator()).getCodeLength()];
/*  42*/        constPool = codeAttr.getConstPool();
/*  43*/        initStack(ai, codeAttr);
                boolean flag;
/*  46*/        do
                {
/*  46*/            flag = false;
/*  47*/            for(int k = 0; k < j; k++)
/*  48*/                if(ai[k] < 0)
                        {
/*  49*/                    flag = true;
/*  50*/                    visitBytecode(codeiterator, ai, k);
                        }

                } while(flag);
/*  54*/        int l = 1;
/*  55*/        for(int i = 0; i < j; i++)
/*  56*/            if(ai[i] > l)
/*  57*/                l = ai[i];

/*  59*/        return l - 1;
            }

            private void initStack(int ai[], CodeAttribute codeattribute)
            {
/*  63*/        ai[0] = -1;
/*  64*/        if((codeattribute = codeattribute.getExceptionTable()) != null)
                {
/*  66*/            int i = codeattribute.size();
/*  67*/            for(int j = 0; j < i; j++)
/*  68*/                ai[codeattribute.handlerPc(j)] = -2;

                }
            }

            private void visitBytecode(CodeIterator codeiterator, int ai[], int i)
                throws BadBytecode
            {
/*  75*/        int j = ai.length;
/*  76*/        codeiterator.move(i);
/*  77*/        int k = -ai[i];
                int ai1[];
/*  78*/        (ai1 = new int[1])[0] = -1;
/*  80*/        do
                {
/*  80*/            if(!codeiterator.hasNext())
/*  81*/                break;
/*  81*/            i = codeiterator.next();
/*  82*/            ai[i] = k;
/*  83*/            int l = codeiterator.byteAt(i);
/*  84*/            if((k = visitInst(l, codeiterator, i, k)) <= 0)
/*  86*/                throw new BadBytecode((new StringBuilder("stack underflow at ")).append(i).toString());
/*  88*/            if(processBranch(l, codeiterator, i, j, ai, k, ai1) || isEnd(l))
/*  94*/                break;
/*  94*/            if(l == 168 || l == 201)
/*  95*/                k--;
                } while(true);
            }

            private boolean processBranch(int i, CodeIterator codeiterator, int j, int k, int ai[], int l, int ai1[])
                throws BadBytecode
            {
/* 103*/        if(153 <= i && i <= 166 || i == 198 || i == 199)
                {
/* 105*/            int i1 = j + codeiterator.s16bitAt(j + 1);
/* 106*/            checkTarget(j, i1, k, ai, l);
                } else
                {
/* 110*/            switch(i)
                    {
/* 112*/            case 167: 
/* 112*/                int j1 = j + codeiterator.s16bitAt(j + 1);
/* 113*/                checkTarget(j, j1, k, ai, l);
/* 114*/                return true;

/* 116*/            case 200: 
/* 116*/                int k1 = j + codeiterator.s32bitAt(j + 1);
/* 117*/                checkTarget(j, k1, k, ai, l);
/* 118*/                return true;

/* 121*/            case 168: 
/* 121*/            case 201: 
                        int l1;
/* 121*/                if(i == 168)
/* 122*/                    l1 = j + codeiterator.s16bitAt(j + 1);
/* 124*/                else
/* 124*/                    l1 = j + codeiterator.s32bitAt(j + 1);
/* 126*/                checkTarget(j, l1, k, ai, l);
/* 134*/                if(ai1[0] < 0)
                        {
/* 135*/                    ai1[0] = l;
/* 136*/                    return false;
                        }
/* 138*/                if(l == ai1[0])
/* 139*/                    return false;
/* 141*/                else
/* 141*/                    throw new BadBytecode((new StringBuilder("sorry, cannot compute this data flow due to JSR: ")).append(l).append(",").append(ai1[0]).toString());

/* 145*/            case 169: 
/* 145*/                if(ai1[0] < 0)
                        {
/* 146*/                    ai1[0] = l + 1;
/* 147*/                    return false;
                        }
/* 149*/                if(l + 1 == ai1[0])
/* 150*/                    return true;
/* 152*/                else
/* 152*/                    throw new BadBytecode((new StringBuilder("sorry, cannot compute this data flow due to RET: ")).append(l).append(",").append(ai1[0]).toString());

/* 157*/            case 170: 
/* 157*/            case 171: 
/* 157*/                ai1 = (j & -4) + 4;
/* 158*/                int i2 = j + codeiterator.s32bitAt(ai1);
/* 159*/                checkTarget(j, i2, k, ai, l);
/* 160*/                if(i == 171)
                        {
/* 161*/                    i = codeiterator.s32bitAt(ai1 + 4);
/* 162*/                    ai1 += 12;
/* 163*/                    for(int l2 = 0; l2 < i; l2++)
                            {
/* 164*/                        int j2 = j + codeiterator.s32bitAt(ai1);
/* 165*/                        checkTarget(j, j2, k, ai, l);
/* 167*/                        ai1 += 8;
                            }

                        } else
                        {
/* 171*/                    i = codeiterator.s32bitAt(ai1 + 4);
                            int i3;
/* 172*/                    i = ((i3 = codeiterator.s32bitAt(ai1 + 8)) - i) + 1;
/* 174*/                    ai1 += 12;
/* 175*/                    for(int j3 = 0; j3 < i; j3++)
                            {
/* 176*/                        int k2 = j + codeiterator.s32bitAt(ai1);
/* 177*/                        checkTarget(j, k2, k, ai, l);
/* 179*/                        ai1 += 4;
                            }

                        }
/* 183*/                return true;
                    }
                }
/* 187*/        return false;
            }

            private void checkTarget(int i, int j, int k, int ai[], int l)
                throws BadBytecode
            {
/* 194*/        if(j < 0 || k <= j)
/* 195*/            throw new BadBytecode((new StringBuilder("bad branch offset at ")).append(i).toString());
/* 197*/        if((k = ai[j]) == 0)
                {
/* 199*/            ai[j] = -l;
/* 199*/            return;
                }
/* 200*/        if(k != l && k != -l)
/* 201*/            throw new BadBytecode((new StringBuilder("verification error (")).append(l).append(",").append(k).append(") at ").append(i).toString());
/* 203*/        else
/* 203*/            return;
            }

            private static boolean isEnd(int i)
            {
/* 206*/        return 172 <= i && i <= 177 || i == 191;
            }

            private int visitInst(int i, CodeIterator codeiterator, int j, int k)
                throws BadBytecode
            {
/* 216*/        switch(i)
                {
/* 218*/        case 180: 
/* 218*/            k += getFieldSize(codeiterator, j) - 1;
                    break;

/* 221*/        case 181: 
/* 221*/            k -= getFieldSize(codeiterator, j) + 1;
                    break;

/* 224*/        case 178: 
/* 224*/            k += getFieldSize(codeiterator, j);
                    break;

/* 227*/        case 179: 
/* 227*/            k -= getFieldSize(codeiterator, j);
                    break;

/* 231*/        case 182: 
/* 231*/        case 183: 
/* 231*/            i = constPool.getMethodrefType(codeiterator.u16bitAt(j + 1));
/* 232*/            k += Descriptor.dataSize(i) - 1;
                    break;

/* 235*/        case 184: 
/* 235*/            i = constPool.getMethodrefType(codeiterator.u16bitAt(j + 1));
/* 236*/            k += Descriptor.dataSize(i);
                    break;

/* 239*/        case 185: 
/* 239*/            i = constPool.getInterfaceMethodrefType(codeiterator.u16bitAt(j + 1));
/* 241*/            k += Descriptor.dataSize(i) - 1;
                    break;

/* 244*/        case 186: 
/* 244*/            i = constPool.getInvokeDynamicType(codeiterator.u16bitAt(j + 1));
/* 245*/            k += Descriptor.dataSize(i);
                    break;

/* 248*/        case 191: 
/* 248*/            k = 1;
                    break;

/* 251*/        case 197: 
/* 251*/            k += 1 - codeiterator.byteAt(j + 3);
                    break;

/* 254*/        case 196: 
/* 254*/            i = codeiterator.byteAt(j + 1);
                    // fall through

/* 257*/        case 187: 
/* 257*/        case 188: 
/* 257*/        case 189: 
/* 257*/        case 190: 
/* 257*/        case 192: 
/* 257*/        case 193: 
/* 257*/        case 194: 
/* 257*/        case 195: 
/* 257*/        default:
/* 257*/            k += STACK_GROW[i];
                    break;
                }
/* 260*/        return k;
            }

            private int getFieldSize(CodeIterator codeiterator, int i)
            {
/* 264*/        return Descriptor.dataSize(codeiterator = constPool.getFieldrefType(codeiterator.u16bitAt(i + 1)));
            }

            private ConstPool constPool;
            private CodeAttribute codeAttr;
}
