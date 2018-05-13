// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformAccessArrayField.java

package javassist.convert;

import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.analysis.*;

// Referenced classes of package javassist.convert:
//            Transformer

public final class TransformAccessArrayField extends Transformer
{

            public TransformAccessArrayField(Transformer transformer, String s, javassist.CodeConverter.ArrayAccessReplacementMethodNames arrayaccessreplacementmethodnames)
                throws NotFoundException
            {
/*  46*/        super(transformer);
/*  47*/        methodClassname = s;
/*  48*/        names = arrayaccessreplacementmethodnames;
            }

            public final void initialize(ConstPool constpool, CtClass ctclass, MethodInfo methodinfo)
                throws CannotCompileException
            {
/*  63*/        for(CodeIterator codeiterator = methodinfo.getCodeAttribute().iterator(); codeiterator.hasNext();)
/*  66*/            try
                    {
/*  66*/                int i = codeiterator.next();
                        int j;
/*  67*/                if((j = codeiterator.byteAt(i)) == 50)
/*  70*/                    initFrames(ctclass, methodinfo);
/*  72*/                if(j == 50 || j == 51 || j == 52 || j == 49 || j == 48 || j == 46 || j == 47 || j == 53)
/*  75*/                    replace(constpool, codeiterator, i, j, getLoadReplacementSignature(j));
/*  76*/                else
/*  76*/                if(j == 83 || j == 84 || j == 85 || j == 82 || j == 81 || j == 79 || j == 80 || j == 86)
/*  79*/                    replace(constpool, codeiterator, i, j, getStoreReplacementSignature(j));
                    }
/*  82*/            catch(Exception exception)
                    {
/*  83*/                throw new CannotCompileException(exception);
                    }

            }

            public final void clean()
            {
/*  89*/        frames = null;
/*  90*/        offset = -1;
            }

            public final int transform(CtClass ctclass, int i, CodeIterator codeiterator, ConstPool constpool)
                throws BadBytecode
            {
/*  96*/        return i;
            }

            private Frame getFrame(int i)
                throws BadBytecode
            {
/* 100*/        return frames[i - offset];
            }

            private void initFrames(CtClass ctclass, MethodInfo methodinfo)
                throws BadBytecode
            {
/* 104*/        if(frames == null)
                {
/* 105*/            frames = (new Analyzer()).analyze(ctclass, methodinfo);
/* 106*/            offset = 0;
                }
            }

            private int updatePos(int i, int j)
            {
/* 111*/        if(offset >= 0)
/* 112*/            offset += j;
/* 114*/        return i + j;
            }

            private String getTopType(int i)
                throws BadBytecode
            {
/* 118*/        if((i = getFrame(i)) == null)
/* 120*/            return null;
/* 122*/        if((i = i.peek().getCtClass()) != null)
/* 123*/            return Descriptor.toJvmName(i);
/* 123*/        else
/* 123*/            return null;
            }

            private int replace(ConstPool constpool, CodeIterator codeiterator, int i, int j, String s)
                throws BadBytecode
            {
/* 128*/        String s1 = null;
                String s2;
/* 129*/        if((s2 = getMethodName(j)) != null)
                {
/* 132*/            if(j == 50)
                    {
/* 133*/                if((s1 = getTopType(codeiterator.lookAhead())) == null)
/* 138*/                    return i;
/* 139*/                if("java/lang/Object".equals(s1))
/* 140*/                    s1 = null;
                    }
/* 145*/            codeiterator.writeByte(0, i);
/* 146*/            i = ((javassist.bytecode.CodeIterator.Gap) (j = codeiterator.insertGapAt(i, s1 == null ? 2 : 5, false))).position;
/* 149*/            int k = constpool.addClassInfo(methodClassname);
/* 150*/            s = constpool.addMethodrefInfo(k, s2, s);
/* 151*/            codeiterator.writeByte(184, i);
/* 152*/            codeiterator.write16bit(s, i + 1);
/* 154*/            if(s1 != null)
                    {
/* 155*/                constpool = constpool.addClassInfo(s1);
/* 156*/                codeiterator.writeByte(192, i + 3);
/* 157*/                codeiterator.write16bit(constpool, i + 4);
                    }
/* 160*/            i = updatePos(i, ((javassist.bytecode.CodeIterator.Gap) (j)).length);
                }
/* 163*/        return i;
            }

            private String getMethodName(int i)
            {
/* 167*/        String s = null;
/* 168*/        switch(i)
                {
/* 170*/        case 50: // '2'
/* 170*/            s = names.objectRead();
                    break;

/* 173*/        case 51: // '3'
/* 173*/            s = names.byteOrBooleanRead();
                    break;

/* 176*/        case 52: // '4'
/* 176*/            s = names.charRead();
                    break;

/* 179*/        case 49: // '1'
/* 179*/            s = names.doubleRead();
                    break;

/* 182*/        case 48: // '0'
/* 182*/            s = names.floatRead();
                    break;

/* 185*/        case 46: // '.'
/* 185*/            s = names.intRead();
                    break;

/* 188*/        case 53: // '5'
/* 188*/            s = names.shortRead();
                    break;

/* 191*/        case 47: // '/'
/* 191*/            s = names.longRead();
                    break;

/* 194*/        case 83: // 'S'
/* 194*/            s = names.objectWrite();
                    break;

/* 197*/        case 84: // 'T'
/* 197*/            s = names.byteOrBooleanWrite();
                    break;

/* 200*/        case 85: // 'U'
/* 200*/            s = names.charWrite();
                    break;

/* 203*/        case 82: // 'R'
/* 203*/            s = names.doubleWrite();
                    break;

/* 206*/        case 81: // 'Q'
/* 206*/            s = names.floatWrite();
                    break;

/* 209*/        case 79: // 'O'
/* 209*/            s = names.intWrite();
                    break;

/* 212*/        case 86: // 'V'
/* 212*/            s = names.shortWrite();
                    break;

/* 215*/        case 80: // 'P'
/* 215*/            s = names.longWrite();
                    break;
                }
/* 219*/        if(s.equals(""))
/* 220*/            s = null;
/* 222*/        return s;
            }

            private String getLoadReplacementSignature(int i)
                throws BadBytecode
            {
/* 226*/        switch(i)
                {
/* 228*/        case 50: // '2'
/* 228*/            return "(Ljava/lang/Object;I)Ljava/lang/Object;";

/* 230*/        case 51: // '3'
/* 230*/            return "(Ljava/lang/Object;I)B";

/* 232*/        case 52: // '4'
/* 232*/            return "(Ljava/lang/Object;I)C";

/* 234*/        case 49: // '1'
/* 234*/            return "(Ljava/lang/Object;I)D";

/* 236*/        case 48: // '0'
/* 236*/            return "(Ljava/lang/Object;I)F";

/* 238*/        case 46: // '.'
/* 238*/            return "(Ljava/lang/Object;I)I";

/* 240*/        case 53: // '5'
/* 240*/            return "(Ljava/lang/Object;I)S";

/* 242*/        case 47: // '/'
/* 242*/            return "(Ljava/lang/Object;I)J";
                }
/* 245*/        throw new BadBytecode(i);
            }

            private String getStoreReplacementSignature(int i)
                throws BadBytecode
            {
/* 249*/        switch(i)
                {
/* 251*/        case 83: // 'S'
/* 251*/            return "(Ljava/lang/Object;ILjava/lang/Object;)V";

/* 253*/        case 84: // 'T'
/* 253*/            return "(Ljava/lang/Object;IB)V";

/* 255*/        case 85: // 'U'
/* 255*/            return "(Ljava/lang/Object;IC)V";

/* 257*/        case 82: // 'R'
/* 257*/            return "(Ljava/lang/Object;ID)V";

/* 259*/        case 81: // 'Q'
/* 259*/            return "(Ljava/lang/Object;IF)V";

/* 261*/        case 79: // 'O'
/* 261*/            return "(Ljava/lang/Object;II)V";

/* 263*/        case 86: // 'V'
/* 263*/            return "(Ljava/lang/Object;IS)V";

/* 265*/        case 80: // 'P'
/* 265*/            return "(Ljava/lang/Object;IJ)V";
                }
/* 268*/        throw new BadBytecode(i);
            }

            private final String methodClassname;
            private final javassist.CodeConverter.ArrayAccessReplacementMethodNames names;
            private Frame frames[];
            private int offset;
}
