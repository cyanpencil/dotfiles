// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicBlock.java

package javassist.bytecode.stackmap;

import java.util.*;
import javassist.bytecode.*;

public class BasicBlock
{
    public static class Maker
    {

                protected BasicBlock makeBlock(int i)
                {
/* 142*/            return new BasicBlock(i);
                }

                protected BasicBlock[] makeArray(int i)
                {
/* 146*/            return new BasicBlock[i];
                }

                private BasicBlock[] makeArray(BasicBlock basicblock)
                {
                    BasicBlock abasicblock[];
/* 150*/            (abasicblock = makeArray(1))[0] = basicblock;
/* 152*/            return abasicblock;
                }

                private BasicBlock[] makeArray(BasicBlock basicblock, BasicBlock basicblock1)
                {
                    BasicBlock abasicblock[];
/* 156*/            (abasicblock = makeArray(2))[0] = basicblock;
/* 158*/            abasicblock[1] = basicblock1;
/* 159*/            return abasicblock;
                }

                public BasicBlock[] make(MethodInfo methodinfo)
                    throws BadBytecode
                {
/* 163*/            if((methodinfo = methodinfo.getCodeAttribute()) == null)
                    {
/* 165*/                return null;
                    } else
                    {
/* 167*/                CodeIterator codeiterator = methodinfo.iterator();
/* 168*/                return make(codeiterator, 0, codeiterator.getCodeLength(), methodinfo.getExceptionTable());
                    }
                }

                public BasicBlock[] make(CodeIterator codeiterator, int i, int j, ExceptionTable exceptiontable)
                    throws BadBytecode
                {
/* 175*/            codeiterator = makeMarks(codeiterator, i, j, exceptiontable);
/* 176*/            codeiterator = makeBlocks(codeiterator);
/* 177*/            addCatchers(codeiterator, exceptiontable);
/* 178*/            return codeiterator;
                }

                private Mark makeMark(HashMap hashmap, int i)
                {
/* 184*/            return makeMark0(hashmap, i, true, true);
                }

                private Mark makeMark(HashMap hashmap, int i, BasicBlock abasicblock[], int j, boolean flag)
                {
/* 192*/            (hashmap = makeMark0(hashmap, i, false, false)).setJump(abasicblock, j, flag);
/* 194*/            return hashmap;
                }

                private Mark makeMark0(HashMap hashmap, int i, boolean flag, boolean flag1)
                {
/* 199*/            Integer integer = new Integer(i);
                    Mark mark;
/* 200*/            if((mark = (Mark)hashmap.get(integer)) == null)
                    {
/* 202*/                mark = new Mark(i);
/* 203*/                hashmap.put(integer, mark);
                    }
/* 206*/            if(flag)
                    {
/* 207*/                if(mark.block == null)
/* 208*/                    mark.block = makeBlock(i);
/* 210*/                if(flag1)
/* 211*/                    mark.block.incoming++;
                    }
/* 214*/            return mark;
                }

                private HashMap makeMarks(CodeIterator codeiterator, int i, int j, ExceptionTable exceptiontable)
                    throws BadBytecode
                {
/* 221*/            codeiterator.begin();
/* 222*/            codeiterator.move(i);
/* 223*/            i = new HashMap();
/* 224*/label0:
/* 224*/            do
                    {
                        int k;
/* 224*/                while(codeiterator.hasNext() && (k = codeiterator.next()) < j) 
                        {
/* 229*/                    int i1 = codeiterator.byteAt(k);
/* 230*/                    if(153 <= i1 && i1 <= 166 || i1 == 198 || i1 == 199)
                            {
/* 232*/                        i1 = makeMark(i, k + codeiterator.s16bitAt(k + 1));
/* 233*/                        Mark mark = makeMark(i, k + 3);
/* 234*/                        makeMark(i, k, makeArray(((Mark) (i1)).block, mark.block), 3, false);
                            } else
                            {
/* 236*/                        if(167 <= i1 && i1 <= 171)
/* 237*/                            switch(i1)
                                    {
/* 239*/                            case 167: 
/* 239*/                                makeGoto(i, k, k + codeiterator.s16bitAt(k + 1), 3);
                                        break;

/* 242*/                            case 168: 
/* 242*/                                makeJsr(i, k, k + codeiterator.s16bitAt(k + 1), 3);
                                        break;

/* 245*/                            case 169: 
/* 245*/                                makeMark(i, k, null, 2, true);
                                        break;

/* 248*/                            case 170: 
/* 248*/                                i1 = (k & -4) + 4;
/* 249*/                                int j1 = codeiterator.s32bitAt(i1 + 4);
                                        int l1;
/* 250*/                                int i2 = ((l1 = codeiterator.s32bitAt(i1 + 8)) - j1) + 1;
                                        BasicBlock abasicblock[];
/* 252*/                                (abasicblock = makeArray(i2 + 1))[0] = makeMark(i, k + codeiterator.s32bitAt(i1)).block;
/* 254*/                                l1 = (i1 += 12) + (i2 << 2);
/* 256*/                                i2 = 1;
/* 257*/                                for(; i1 < l1; i1 += 4)
/* 258*/                                    abasicblock[i2++] = makeMark(i, k + codeiterator.s32bitAt(i1)).block;

/* 261*/                                makeMark(i, k, abasicblock, l1 - k, true);
                                        break;

/* 264*/                            case 171: 
/* 264*/                                i1 = (k & -4) + 4;
/* 265*/                                int k1 = codeiterator.s32bitAt(i1 + 4);
                                        BasicBlock abasicblock1[];
/* 266*/                                (abasicblock1 = makeArray(k1 + 1))[0] = makeMark(i, k + codeiterator.s32bitAt(i1)).block;
                                        int j2;
/* 268*/                                k1 = ((j2 = i1 + 8 + 4) + (k1 << 3)) - 4;
/* 270*/                                i1 = 1;
/* 271*/                                for(; j2 < k1; j2 += 8)
/* 272*/                                    abasicblock1[i1++] = makeMark(i, k + codeiterator.s32bitAt(j2)).block;

/* 275*/                                makeMark(i, k, abasicblock1, k1 - k, true);
                                        break;
                                    }
/* 278*/                        else
/* 278*/                        if(172 <= i1 && i1 <= 177 || i1 == 191)
/* 279*/                            makeMark(i, k, null, 1, true);
/* 280*/                        else
/* 280*/                        if(i1 == 200)
/* 281*/                            makeGoto(i, k, k + codeiterator.s32bitAt(k + 1), 5);
/* 282*/                        else
/* 282*/                        if(i1 == 201)
/* 283*/                            makeJsr(i, k, k + codeiterator.s32bitAt(k + 1), 5);
/* 284*/                        else
/* 284*/                        if(i1 == 196 && codeiterator.byteAt(k + 1) == 169)
/* 285*/                            makeMark(i, k, null, 4, true);
/* 286*/                        continue label0;
                            }
                        }
/* 288*/                if(exceptiontable != null)
                        {
/* 289*/                    for(int l = exceptiontable.size(); --l >= 0;)
                            {
/* 291*/                        makeMark0(i, exceptiontable.startPc(l), true, false);
/* 292*/                        makeMark(i, exceptiontable.handlerPc(l));
                            }

                        }
/* 296*/                return i;
                    } while(true);
                }

                private void makeGoto(HashMap hashmap, int i, int j, int k)
                {
/* 300*/            j = makeMark(hashmap, j);
/* 301*/            j = makeArray(((Mark) (j)).block);
/* 302*/            makeMark(hashmap, i, j, k, true);
                }

                protected void makeJsr(HashMap hashmap, int i, int j, int k)
                    throws BadBytecode
                {
/* 316*/            throw new JsrBytecode();
                }

                private BasicBlock[] makeBlocks(HashMap hashmap)
                {
/* 320*/            Arrays.sort(hashmap = (Mark[])hashmap.values().toArray(new Mark[hashmap.size()]));
/* 323*/            ArrayList arraylist = new ArrayList();
/* 324*/            int i = 0;
                    BasicBlock basicblock;
/* 326*/            if(hashmap.length > 0 && ((Mark) (hashmap[0])).position == 0 && ((Mark) (hashmap[0])).block != null)
                    {
/* 327*/                i++;
/* 327*/                basicblock = getBBlock(hashmap[0]);
                    } else
                    {
/* 329*/                basicblock = makeBlock(0);
                    }
/* 331*/            arraylist.add(basicblock);
                    Object obj;
                    BasicBlock basicblock1;
/* 332*/            while(i < hashmap.length) 
/* 333*/                if((basicblock1 = getBBlock(obj = hashmap[i++])) == null)
                        {
/* 337*/                    if(basicblock.length > 0)
                            {
/* 339*/                        basicblock = makeBlock(basicblock.position + basicblock.length);
/* 340*/                        arraylist.add(basicblock);
                            }
/* 343*/                    basicblock.length = (((Mark) (obj)).position + ((Mark) (obj)).size) - basicblock.position;
/* 344*/                    basicblock.exit = ((Mark) (obj)).jump;
/* 345*/                    basicblock.stop = ((Mark) (obj)).alwaysJmp;
                        } else
                        {
/* 349*/                    if(basicblock.length == 0)
                            {
/* 350*/                        basicblock.length = ((Mark) (obj)).position - basicblock.position;
/* 351*/                        basicblock1.incoming++;
/* 352*/                        basicblock.exit = makeArray(basicblock1);
                            } else
/* 356*/                    if(basicblock.position + basicblock.length < ((Mark) (obj)).position)
                            {
/* 358*/                        basicblock = makeBlock(basicblock.position + basicblock.length);
/* 359*/                        arraylist.add(basicblock);
/* 360*/                        basicblock.length = ((Mark) (obj)).position - basicblock.position;
/* 363*/                        basicblock.exit = makeArray(basicblock1);
                            }
/* 367*/                    arraylist.add(basicblock1);
/* 368*/                    basicblock = basicblock1;
                        }
/* 372*/            return (BasicBlock[])arraylist.toArray(makeArray(arraylist.size()));
                }

                private static BasicBlock getBBlock(Mark mark)
                {
                    BasicBlock basicblock;
/* 376*/            if((basicblock = mark.block) != null && mark.size > 0)
                    {
/* 378*/                basicblock.exit = mark.jump;
/* 379*/                basicblock.length = mark.size;
/* 380*/                basicblock.stop = mark.alwaysJmp;
                    }
/* 383*/            return basicblock;
                }

                private void addCatchers(BasicBlock abasicblock[], ExceptionTable exceptiontable)
                    throws BadBytecode
                {
/* 389*/            if(exceptiontable == null)
/* 390*/                return;
/* 392*/            for(int i = exceptiontable.size(); --i >= 0;)
                    {
/* 394*/                BasicBlock basicblock = BasicBlock.find(abasicblock, exceptiontable.handlerPc(i));
/* 395*/                int j = exceptiontable.startPc(i);
/* 396*/                int k = exceptiontable.endPc(i);
/* 397*/                int l = exceptiontable.catchType(i);
/* 398*/                basicblock.incoming--;
/* 399*/                int i1 = 0;
/* 399*/                while(i1 < abasicblock.length) 
                        {
                            BasicBlock basicblock1;
/* 400*/                    int j1 = (basicblock1 = abasicblock[i1]).position;
/* 402*/                    if(j <= j1 && j1 < k)
                            {
/* 403*/                        basicblock1.toCatch = new Catch(basicblock, l, basicblock1.toCatch);
/* 404*/                        basicblock.incoming++;
                            }
/* 399*/                    i1++;
                        }
                    }

                }

                public Maker()
                {
                }
    }

    static class Mark
        implements Comparable
    {

                public int compareTo(Object obj)
                {
/* 122*/            if(obj instanceof Mark)
                    {
/* 123*/                obj = ((Mark)obj).position;
/* 124*/                return position - obj;
                    } else
                    {
/* 127*/                return -1;
                    }
                }

                void setJump(BasicBlock abasicblock[], int i, boolean flag)
                {
/* 131*/            jump = abasicblock;
/* 132*/            size = i;
/* 133*/            alwaysJmp = flag;
                }

                int position;
                BasicBlock block;
                BasicBlock jump[];
                boolean alwaysJmp;
                int size;
                Catch catcher;

                Mark(int i)
                {
/* 113*/            position = i;
/* 114*/            block = null;
/* 115*/            jump = null;
/* 116*/            alwaysJmp = false;
/* 117*/            size = 0;
/* 118*/            catcher = null;
                }
    }

    public static class Catch
    {

                public Catch next;
                public BasicBlock body;
                public int typeIndex;

                Catch(BasicBlock basicblock, int i, Catch catch1)
                {
/*  63*/            body = basicblock;
/*  64*/            typeIndex = i;
/*  65*/            next = catch1;
                }
    }

    static class JsrBytecode extends BadBytecode
    {

                JsrBytecode()
                {
/*  31*/            super("JSR");
                }
    }


            protected BasicBlock(int i)
            {
/*  41*/        position = i;
/*  42*/        length = 0;
/*  43*/        incoming = 0;
            }

            public static BasicBlock find(BasicBlock abasicblock[], int i)
                throws BadBytecode
            {
/*  49*/        for(int j = 0; j < abasicblock.length; j++)
                {
                    int k;
/*  50*/            if((k = abasicblock[j].position) <= i && i < k + abasicblock[j].length)
/*  52*/                return abasicblock[j];
                }

/*  55*/        throw new BadBytecode((new StringBuilder("no basic block at ")).append(i).toString());
            }

            public String toString()
            {
/*  70*/        StringBuffer stringbuffer = new StringBuffer();
                String s;
/*  71*/        int i = (s = getClass().getName()).lastIndexOf('.');
/*  73*/        stringbuffer.append(i >= 0 ? s.substring(i + 1) : s);
/*  74*/        stringbuffer.append("[");
/*  75*/        toString2(stringbuffer);
/*  76*/        stringbuffer.append("]");
/*  77*/        return stringbuffer.toString();
            }

            protected void toString2(StringBuffer stringbuffer)
            {
/*  81*/        stringbuffer.append("pos=").append(position).append(", len=").append(length).append(", in=").append(incoming).append(", exit{");
/*  84*/        if(exit != null)
                {
/*  85*/            for(int i = 0; i < exit.length; i++)
/*  86*/                stringbuffer.append(exit[i].position).append(",");

                }
/*  89*/        stringbuffer.append("}, {");
/*  90*/        for(Catch catch1 = toCatch; catch1 != null; catch1 = catch1.next)
/*  92*/            stringbuffer.append("(").append(catch1.body.position).append(", ").append(catch1.typeIndex).append("), ");

/*  97*/        stringbuffer.append("}");
            }

            protected int position;
            protected int length;
            protected int incoming;
            protected BasicBlock exit[];
            protected boolean stop;
            protected Catch toCatch;
}
