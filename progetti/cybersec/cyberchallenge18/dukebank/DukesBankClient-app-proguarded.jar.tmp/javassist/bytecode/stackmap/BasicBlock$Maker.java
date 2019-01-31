// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicBlock.java

package javassist.bytecode.stackmap;

import java.util.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.stackmap:
//            BasicBlock

public static class ecode
{

            protected BasicBlock makeBlock(int i)
            {
/* 142*/        return new BasicBlock(i);
            }

            protected BasicBlock[] makeArray(int i)
            {
/* 146*/        return new BasicBlock[i];
            }

            private BasicBlock[] makeArray(BasicBlock basicblock)
            {
                BasicBlock abasicblock[];
/* 150*/        (abasicblock = makeArray(1))[0] = basicblock;
/* 152*/        return abasicblock;
            }

            private BasicBlock[] makeArray(BasicBlock basicblock, BasicBlock basicblock1)
            {
                BasicBlock abasicblock[];
/* 156*/        (abasicblock = makeArray(2))[0] = basicblock;
/* 158*/        abasicblock[1] = basicblock1;
/* 159*/        return abasicblock;
            }

            public BasicBlock[] make(MethodInfo methodinfo)
                throws BadBytecode
            {
/* 163*/        if((methodinfo = methodinfo.getCodeAttribute()) == null)
                {
/* 165*/            return null;
                } else
                {
/* 167*/            CodeIterator codeiterator = methodinfo.iterator();
/* 168*/            return make(codeiterator, 0, codeiterator.getCodeLength(), methodinfo.getExceptionTable());
                }
            }

            public BasicBlock[] make(CodeIterator codeiterator, int i, int j, ExceptionTable exceptiontable)
                throws BadBytecode
            {
/* 175*/        codeiterator = makeMarks(codeiterator, i, j, exceptiontable);
/* 176*/        codeiterator = makeBlocks(codeiterator);
/* 177*/        addCatchers(codeiterator, exceptiontable);
/* 178*/        return codeiterator;
            }

            private addCatchers makeMark(HashMap hashmap, int i)
            {
/* 184*/        return makeMark0(hashmap, i, true, true);
            }

            private makeMark0 makeMark(HashMap hashmap, int i, BasicBlock abasicblock[], int j, boolean flag)
            {
/* 192*/        (hashmap = makeMark0(hashmap, i, false, false)).etJump(abasicblock, j, flag);
/* 194*/        return hashmap;
            }

            private etJump makeMark0(HashMap hashmap, int i, boolean flag, boolean flag1)
            {
/* 199*/        Integer integer = new Integer(i);
                etJump etjump;
/* 200*/        if((etjump = (etJump)hashmap.get(integer)) == null)
                {
/* 202*/            etjump = new init>(i);
/* 203*/            hashmap.put(integer, etjump);
                }
/* 206*/        if(flag)
                {
/* 207*/            if(etjump.lock == null)
/* 208*/                etjump.lock = makeBlock(i);
/* 210*/            if(flag1)
/* 211*/                etjump.lock.incoming++;
                }
/* 214*/        return etjump;
            }

            private HashMap makeMarks(CodeIterator codeiterator, int i, int j, ExceptionTable exceptiontable)
                throws BadBytecode
            {
/* 221*/        codeiterator.begin();
/* 222*/        codeiterator.move(i);
/* 223*/        i = new HashMap();
/* 224*/label0:
/* 224*/        do
                {
                    int k;
/* 224*/            while(codeiterator.hasNext() && (k = codeiterator.next()) < j) 
                    {
/* 229*/                int i1 = codeiterator.byteAt(k);
/* 230*/                if(153 <= i1 && i1 <= 166 || i1 == 198 || i1 == 199)
                        {
/* 232*/                    i1 = makeMark(i, k + codeiterator.s16bitAt(k + 1));
/* 233*/                    ng ng = makeMark(i, k + 3);
/* 234*/                    makeMark(i, k, makeArray(((makeArray) (i1)).lock, ng.lock), 3, false);
                        } else
                        {
/* 236*/                    if(167 <= i1 && i1 <= 171)
/* 237*/                        switch(i1)
                                {
/* 239*/                        case 167: 
/* 239*/                            makeGoto(i, k, k + codeiterator.s16bitAt(k + 1), 3);
                                    break;

/* 242*/                        case 168: 
/* 242*/                            makeJsr(i, k, k + codeiterator.s16bitAt(k + 1), 3);
                                    break;

/* 245*/                        case 169: 
/* 245*/                            makeMark(i, k, null, 2, true);
                                    break;

/* 248*/                        case 170: 
/* 248*/                            i1 = (k & -4) + 4;
/* 249*/                            int j1 = codeiterator.s32bitAt(i1 + 4);
                                    int l1;
/* 250*/                            int i2 = ((l1 = codeiterator.s32bitAt(i1 + 8)) - j1) + 1;
                                    BasicBlock abasicblock[];
/* 252*/                            (abasicblock = makeArray(i2 + 1))[0] = makeMark(i, k + codeiterator.s32bitAt(i1)).lock;
/* 254*/                            l1 = (i1 += 12) + (i2 << 2);
/* 256*/                            i2 = 1;
/* 257*/                            for(; i1 < l1; i1 += 4)
/* 258*/                                abasicblock[i2++] = makeMark(i, k + codeiterator.s32bitAt(i1)).lock;

/* 261*/                            makeMark(i, k, abasicblock, l1 - k, true);
                                    break;

/* 264*/                        case 171: 
/* 264*/                            i1 = (k & -4) + 4;
/* 265*/                            int k1 = codeiterator.s32bitAt(i1 + 4);
                                    BasicBlock abasicblock1[];
/* 266*/                            (abasicblock1 = makeArray(k1 + 1))[0] = makeMark(i, k + codeiterator.s32bitAt(i1)).lock;
                                    int j2;
/* 268*/                            k1 = ((j2 = i1 + 8 + 4) + (k1 << 3)) - 4;
/* 270*/                            i1 = 1;
/* 271*/                            for(; j2 < k1; j2 += 8)
/* 272*/                                abasicblock1[i1++] = makeMark(i, k + codeiterator.s32bitAt(j2)).lock;

/* 275*/                            makeMark(i, k, abasicblock1, k1 - k, true);
                                    break;
                                }
/* 278*/                    else
/* 278*/                    if(172 <= i1 && i1 <= 177 || i1 == 191)
/* 279*/                        makeMark(i, k, null, 1, true);
/* 280*/                    else
/* 280*/                    if(i1 == 200)
/* 281*/                        makeGoto(i, k, k + codeiterator.s32bitAt(k + 1), 5);
/* 282*/                    else
/* 282*/                    if(i1 == 201)
/* 283*/                        makeJsr(i, k, k + codeiterator.s32bitAt(k + 1), 5);
/* 284*/                    else
/* 284*/                    if(i1 == 196 && codeiterator.byteAt(k + 1) == 169)
/* 285*/                        makeMark(i, k, null, 4, true);
/* 286*/                    continue label0;
                        }
                    }
/* 288*/            if(exceptiontable != null)
                    {
/* 289*/                for(int l = exceptiontable.size(); --l >= 0;)
                        {
/* 291*/                    makeMark0(i, exceptiontable.startPc(l), true, false);
/* 292*/                    makeMark(i, exceptiontable.handlerPc(l));
                        }

                    }
/* 296*/            return i;
                } while(true);
            }

            private void makeGoto(HashMap hashmap, int i, int j, int k)
            {
/* 300*/        j = makeMark(hashmap, j);
/* 301*/        j = makeArray(((makeArray) (j)).lock);
/* 302*/        makeMark(hashmap, i, j, k, true);
            }

            protected void makeJsr(HashMap hashmap, int i, int j, int k)
                throws BadBytecode
            {
/* 316*/        throw new ecode();
            }

            private BasicBlock[] makeBlocks(HashMap hashmap)
            {
/* 320*/        Arrays.sort(hashmap = (ecode[])hashmap.values().toArray(new ecode[hashmap.size()]));
/* 323*/        ArrayList arraylist = new ArrayList();
/* 324*/        int i = 0;
                BasicBlock basicblock;
/* 326*/        if(hashmap.length > 0 && ((ecode) (hashmap[0])).osition == 0 && ((osition) (hashmap[0])).lock != null)
                {
/* 327*/            i++;
/* 327*/            basicblock = getBBlock(hashmap[0]);
                } else
                {
/* 329*/            basicblock = makeBlock(0);
                }
/* 331*/        arraylist.add(basicblock);
                Object obj;
                BasicBlock basicblock1;
/* 332*/        while(i < hashmap.length) 
/* 333*/            if((basicblock1 = getBBlock(obj = hashmap[i++])) == null)
                    {
/* 337*/                if(basicblock.length > 0)
                        {
/* 339*/                    basicblock = makeBlock(basicblock.position + basicblock.length);
/* 340*/                    arraylist.add(basicblock);
                        }
/* 343*/                basicblock.length = ((() (obj)).osition + ((osition) (obj)).ize) - basicblock.position;
/* 344*/                basicblock.exit = ((on) (obj)).ump;
/* 345*/                basicblock.stop = ((ump) (obj)).lwaysJmp;
                    } else
                    {
/* 349*/                if(basicblock.length == 0)
                        {
/* 350*/                    basicblock.length = (() (obj)).osition - basicblock.position;
/* 351*/                    basicblock1.incoming++;
/* 352*/                    basicblock.exit = makeArray(basicblock1);
                        } else
/* 356*/                if(basicblock.position + basicblock.length < (() (obj)).osition)
                        {
/* 358*/                    basicblock = makeBlock(basicblock.position + basicblock.length);
/* 359*/                    arraylist.add(basicblock);
/* 360*/                    basicblock.length = (() (obj)).osition - basicblock.position;
/* 363*/                    basicblock.exit = makeArray(basicblock1);
                        }
/* 367*/                arraylist.add(basicblock1);
/* 368*/                basicblock = basicblock1;
                    }
/* 372*/        return (BasicBlock[])arraylist.toArray(makeArray(arraylist.size()));
            }

            private static BasicBlock getBBlock(makeArray makearray)
            {
                BasicBlock basicblock;
/* 376*/        if((basicblock = makearray.lock) != null && makearray.ize > 0)
                {
/* 378*/            basicblock.exit = makearray.ump;
/* 379*/            basicblock.length = makearray.ize;
/* 380*/            basicblock.stop = makearray.lwaysJmp;
                }
/* 383*/        return basicblock;
            }

            private void addCatchers(BasicBlock abasicblock[], ExceptionTable exceptiontable)
                throws BadBytecode
            {
/* 389*/        if(exceptiontable == null)
/* 390*/            return;
/* 392*/        for(int i = exceptiontable.size(); --i >= 0;)
                {
/* 394*/            BasicBlock basicblock = BasicBlock.find(abasicblock, exceptiontable.handlerPc(i));
/* 395*/            int j = exceptiontable.startPc(i);
/* 396*/            int k = exceptiontable.endPc(i);
/* 397*/            int l = exceptiontable.catchType(i);
/* 398*/            basicblock.incoming--;
/* 399*/            int i1 = 0;
/* 399*/            while(i1 < abasicblock.length) 
                    {
                        BasicBlock basicblock1;
/* 400*/                int j1 = (basicblock1 = abasicblock[i1]).position;
/* 402*/                if(j <= j1 && j1 < k)
                        {
/* 403*/                    basicblock1.toCatch = new <init>(basicblock, l, basicblock1.toCatch);
/* 404*/                    basicblock.incoming++;
                        }
/* 399*/                i1++;
                    }
                }

            }

            public ecode()
            {
            }
}
