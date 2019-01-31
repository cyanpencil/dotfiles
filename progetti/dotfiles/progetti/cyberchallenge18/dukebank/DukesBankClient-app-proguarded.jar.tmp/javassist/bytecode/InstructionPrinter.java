// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InstructionPrinter.java

package javassist.bytecode;

import java.io.PrintStream;
import javassist.CtMethod;

// Referenced classes of package javassist.bytecode:
//            BadBytecode, CodeAttribute, CodeIterator, ConstPool, 
//            MethodInfo, Mnemonic, Opcode

public class InstructionPrinter
    implements Opcode
{

            public InstructionPrinter(PrintStream printstream)
            {
/*  36*/        stream = printstream;
            }

            public static void print(CtMethod ctmethod, PrintStream printstream)
            {
/*  43*/        (new InstructionPrinter(printstream)).print(ctmethod);
            }

            public void print(CtMethod ctmethod)
            {
/*  50*/        ConstPool constpool = (ctmethod = ctmethod.getMethodInfo2()).getConstPool();
/*  52*/        if((ctmethod = ctmethod.getCodeAttribute()) == null)
/*  54*/            return;
                int i;
/*  56*/        for(ctmethod = ctmethod.iterator(); ctmethod.hasNext(); stream.println((new StringBuilder()).append(i).append(": ").append(instructionString(ctmethod, i, constpool)).toString()))
/*  60*/            try
                    {
/*  60*/                i = ctmethod.next();
                    }
                    // Misplaced declaration of an exception variable
/*  61*/            catch(CtMethod ctmethod)
                    {
/*  62*/                throw new RuntimeException(ctmethod);
                    }

            }

            public static String instructionString(CodeIterator codeiterator, int i, ConstPool constpool)
            {
                int j;
/*  74*/        if((j = codeiterator.byteAt(i)) > opcodes.length || j < 0)
/*  77*/            throw new IllegalArgumentException((new StringBuilder("Invalid opcode, opcode: ")).append(j).append(" pos: ").append(i).toString());
/*  79*/        String s = opcodes[j];
/*  80*/        switch(j)
                {
/*  82*/        case 16: // '\020'
/*  82*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.byteAt(i + 1)).toString();

/*  84*/        case 17: // '\021'
/*  84*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.s16bitAt(i + 1)).toString();

/*  86*/        case 18: // '\022'
/*  86*/            return (new StringBuilder()).append(s).append(" ").append(ldc(constpool, codeiterator.byteAt(i + 1))).toString();

/*  89*/        case 19: // '\023'
/*  89*/        case 20: // '\024'
/*  89*/            return (new StringBuilder()).append(s).append(" ").append(ldc(constpool, codeiterator.u16bitAt(i + 1))).toString();

/* 100*/        case 21: // '\025'
/* 100*/        case 22: // '\026'
/* 100*/        case 23: // '\027'
/* 100*/        case 24: // '\030'
/* 100*/        case 25: // '\031'
/* 100*/        case 54: // '6'
/* 100*/        case 55: // '7'
/* 100*/        case 56: // '8'
/* 100*/        case 57: // '9'
/* 100*/        case 58: // ':'
/* 100*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.byteAt(i + 1)).toString();

/* 117*/        case 153: 
/* 117*/        case 154: 
/* 117*/        case 155: 
/* 117*/        case 156: 
/* 117*/        case 157: 
/* 117*/        case 158: 
/* 117*/        case 159: 
/* 117*/        case 160: 
/* 117*/        case 161: 
/* 117*/        case 162: 
/* 117*/        case 163: 
/* 117*/        case 164: 
/* 117*/        case 165: 
/* 117*/        case 166: 
/* 117*/        case 198: 
/* 117*/        case 199: 
/* 117*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.s16bitAt(i + 1) + i).toString();

/* 119*/        case 132: 
/* 119*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.byteAt(i + 1)).toString();

/* 122*/        case 167: 
/* 122*/        case 168: 
/* 122*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.s16bitAt(i + 1) + i).toString();

/* 124*/        case 169: 
/* 124*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.byteAt(i + 1)).toString();

/* 126*/        case 170: 
/* 126*/            return tableSwitch(codeiterator, i);

/* 128*/        case 171: 
/* 128*/            return lookupSwitch(codeiterator, i);

/* 133*/        case 178: 
/* 133*/        case 179: 
/* 133*/        case 180: 
/* 133*/        case 181: 
/* 133*/            return (new StringBuilder()).append(s).append(" ").append(fieldInfo(constpool, codeiterator.u16bitAt(i + 1))).toString();

/* 137*/        case 182: 
/* 137*/        case 183: 
/* 137*/        case 184: 
/* 137*/            return (new StringBuilder()).append(s).append(" ").append(methodInfo(constpool, codeiterator.u16bitAt(i + 1))).toString();

/* 139*/        case 185: 
/* 139*/            return (new StringBuilder()).append(s).append(" ").append(interfaceMethodInfo(constpool, codeiterator.u16bitAt(i + 1))).toString();

/* 141*/        case 186: 
/* 141*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.u16bitAt(i + 1)).toString();

/* 143*/        case 187: 
/* 143*/            return (new StringBuilder()).append(s).append(" ").append(classInfo(constpool, codeiterator.u16bitAt(i + 1))).toString();

/* 145*/        case 188: 
/* 145*/            return (new StringBuilder()).append(s).append(" ").append(arrayInfo(codeiterator.byteAt(i + 1))).toString();

/* 148*/        case 189: 
/* 148*/        case 192: 
/* 148*/            return (new StringBuilder()).append(s).append(" ").append(classInfo(constpool, codeiterator.u16bitAt(i + 1))).toString();

/* 150*/        case 196: 
/* 150*/            return wide(codeiterator, i);

/* 152*/        case 197: 
/* 152*/            return (new StringBuilder()).append(s).append(" ").append(classInfo(constpool, codeiterator.u16bitAt(i + 1))).toString();

/* 155*/        case 200: 
/* 155*/        case 201: 
/* 155*/            return (new StringBuilder()).append(s).append(" ").append(codeiterator.s32bitAt(i + 1) + i).toString();

/* 157*/        case 26: // '\032'
/* 157*/        case 27: // '\033'
/* 157*/        case 28: // '\034'
/* 157*/        case 29: // '\035'
/* 157*/        case 30: // '\036'
/* 157*/        case 31: // '\037'
/* 157*/        case 32: // ' '
/* 157*/        case 33: // '!'
/* 157*/        case 34: // '"'
/* 157*/        case 35: // '#'
/* 157*/        case 36: // '$'
/* 157*/        case 37: // '%'
/* 157*/        case 38: // '&'
/* 157*/        case 39: // '\''
/* 157*/        case 40: // '('
/* 157*/        case 41: // ')'
/* 157*/        case 42: // '*'
/* 157*/        case 43: // '+'
/* 157*/        case 44: // ','
/* 157*/        case 45: // '-'
/* 157*/        case 46: // '.'
/* 157*/        case 47: // '/'
/* 157*/        case 48: // '0'
/* 157*/        case 49: // '1'
/* 157*/        case 50: // '2'
/* 157*/        case 51: // '3'
/* 157*/        case 52: // '4'
/* 157*/        case 53: // '5'
/* 157*/        case 59: // ';'
/* 157*/        case 60: // '<'
/* 157*/        case 61: // '='
/* 157*/        case 62: // '>'
/* 157*/        case 63: // '?'
/* 157*/        case 64: // '@'
/* 157*/        case 65: // 'A'
/* 157*/        case 66: // 'B'
/* 157*/        case 67: // 'C'
/* 157*/        case 68: // 'D'
/* 157*/        case 69: // 'E'
/* 157*/        case 70: // 'F'
/* 157*/        case 71: // 'G'
/* 157*/        case 72: // 'H'
/* 157*/        case 73: // 'I'
/* 157*/        case 74: // 'J'
/* 157*/        case 75: // 'K'
/* 157*/        case 76: // 'L'
/* 157*/        case 77: // 'M'
/* 157*/        case 78: // 'N'
/* 157*/        case 79: // 'O'
/* 157*/        case 80: // 'P'
/* 157*/        case 81: // 'Q'
/* 157*/        case 82: // 'R'
/* 157*/        case 83: // 'S'
/* 157*/        case 84: // 'T'
/* 157*/        case 85: // 'U'
/* 157*/        case 86: // 'V'
/* 157*/        case 87: // 'W'
/* 157*/        case 88: // 'X'
/* 157*/        case 89: // 'Y'
/* 157*/        case 90: // 'Z'
/* 157*/        case 91: // '['
/* 157*/        case 92: // '\\'
/* 157*/        case 93: // ']'
/* 157*/        case 94: // '^'
/* 157*/        case 95: // '_'
/* 157*/        case 96: // '`'
/* 157*/        case 97: // 'a'
/* 157*/        case 98: // 'b'
/* 157*/        case 99: // 'c'
/* 157*/        case 100: // 'd'
/* 157*/        case 101: // 'e'
/* 157*/        case 102: // 'f'
/* 157*/        case 103: // 'g'
/* 157*/        case 104: // 'h'
/* 157*/        case 105: // 'i'
/* 157*/        case 106: // 'j'
/* 157*/        case 107: // 'k'
/* 157*/        case 108: // 'l'
/* 157*/        case 109: // 'm'
/* 157*/        case 110: // 'n'
/* 157*/        case 111: // 'o'
/* 157*/        case 112: // 'p'
/* 157*/        case 113: // 'q'
/* 157*/        case 114: // 'r'
/* 157*/        case 115: // 's'
/* 157*/        case 116: // 't'
/* 157*/        case 117: // 'u'
/* 157*/        case 118: // 'v'
/* 157*/        case 119: // 'w'
/* 157*/        case 120: // 'x'
/* 157*/        case 121: // 'y'
/* 157*/        case 122: // 'z'
/* 157*/        case 123: // '{'
/* 157*/        case 124: // '|'
/* 157*/        case 125: // '}'
/* 157*/        case 126: // '~'
/* 157*/        case 127: // '\177'
/* 157*/        case 128: 
/* 157*/        case 129: 
/* 157*/        case 130: 
/* 157*/        case 131: 
/* 157*/        case 133: 
/* 157*/        case 134: 
/* 157*/        case 135: 
/* 157*/        case 136: 
/* 157*/        case 137: 
/* 157*/        case 138: 
/* 157*/        case 139: 
/* 157*/        case 140: 
/* 157*/        case 141: 
/* 157*/        case 142: 
/* 157*/        case 143: 
/* 157*/        case 144: 
/* 157*/        case 145: 
/* 157*/        case 146: 
/* 157*/        case 147: 
/* 157*/        case 148: 
/* 157*/        case 149: 
/* 157*/        case 150: 
/* 157*/        case 151: 
/* 157*/        case 152: 
/* 157*/        case 172: 
/* 157*/        case 173: 
/* 157*/        case 174: 
/* 157*/        case 175: 
/* 157*/        case 176: 
/* 157*/        case 177: 
/* 157*/        case 190: 
/* 157*/        case 191: 
/* 157*/        case 193: 
/* 157*/        case 194: 
/* 157*/        case 195: 
/* 157*/        default:
/* 157*/            return s;
                }
            }

            private static String wide(CodeIterator codeiterator, int i)
            {
/* 163*/        int j = codeiterator.byteAt(i + 1);
/* 164*/        codeiterator = codeiterator.u16bitAt(i + 2);
/* 165*/        switch(j)
                {
/* 178*/        case 21: // '\025'
/* 178*/        case 22: // '\026'
/* 178*/        case 23: // '\027'
/* 178*/        case 24: // '\030'
/* 178*/        case 25: // '\031'
/* 178*/        case 54: // '6'
/* 178*/        case 55: // '7'
/* 178*/        case 56: // '8'
/* 178*/        case 57: // '9'
/* 178*/        case 58: // ':'
/* 178*/        case 132: 
/* 178*/        case 169: 
/* 178*/            return (new StringBuilder()).append(opcodes[j]).append(" ").append(codeiterator).toString();
                }
/* 180*/        throw new RuntimeException("Invalid WIDE operand");
            }

            private static String arrayInfo(int i)
            {
/* 186*/        switch(i)
                {
/* 188*/        case 4: // '\004'
/* 188*/            return "boolean";

/* 190*/        case 5: // '\005'
/* 190*/            return "char";

/* 192*/        case 8: // '\b'
/* 192*/            return "byte";

/* 194*/        case 9: // '\t'
/* 194*/            return "short";

/* 196*/        case 10: // '\n'
/* 196*/            return "int";

/* 198*/        case 11: // '\013'
/* 198*/            return "long";

/* 200*/        case 6: // '\006'
/* 200*/            return "float";

/* 202*/        case 7: // '\007'
/* 202*/            return "double";
                }
/* 204*/        throw new RuntimeException("Invalid array type");
            }

            private static String classInfo(ConstPool constpool, int i)
            {
/* 210*/        return (new StringBuilder("#")).append(i).append(" = Class ").append(constpool.getClassInfo(i)).toString();
            }

            private static String interfaceMethodInfo(ConstPool constpool, int i)
            {
/* 215*/        return (new StringBuilder("#")).append(i).append(" = Method ").append(constpool.getInterfaceMethodrefClassName(i)).append(".").append(constpool.getInterfaceMethodrefName(i)).append("(").append(constpool.getInterfaceMethodrefType(i)).append(")").toString();
            }

            private static String methodInfo(ConstPool constpool, int i)
            {
/* 222*/        return (new StringBuilder("#")).append(i).append(" = Method ").append(constpool.getMethodrefClassName(i)).append(".").append(constpool.getMethodrefName(i)).append("(").append(constpool.getMethodrefType(i)).append(")").toString();
            }

            private static String fieldInfo(ConstPool constpool, int i)
            {
/* 230*/        return (new StringBuilder("#")).append(i).append(" = Field ").append(constpool.getFieldrefClassName(i)).append(".").append(constpool.getFieldrefName(i)).append("(").append(constpool.getFieldrefType(i)).append(")").toString();
            }

            private static String lookupSwitch(CodeIterator codeiterator, int i)
            {
/* 238*/        StringBuffer stringbuffer = new StringBuffer("lookupswitch {\n");
/* 239*/        int j = (i & -4) + 4;
/* 241*/        stringbuffer.append("\t\tdefault: ").append(i + codeiterator.s32bitAt(j)).append("\n");
                int k;
/* 242*/        for(k = ((k = codeiterator.s32bitAt(j += 4)) << 3) + (j += 4); j < k; j += 8)
                {
/* 246*/            int l = codeiterator.s32bitAt(j);
/* 247*/            int i1 = codeiterator.s32bitAt(j + 4) + i;
/* 248*/            stringbuffer.append("\t\t").append(l).append(": ").append(i1).append("\n");
                }

/* 251*/        stringbuffer.setCharAt(stringbuffer.length() - 1, '}');
/* 252*/        return stringbuffer.toString();
            }

            private static String tableSwitch(CodeIterator codeiterator, int i)
            {
/* 257*/        StringBuffer stringbuffer = new StringBuffer("tableswitch {\n");
/* 258*/        int j = (i & -4) + 4;
/* 260*/        stringbuffer.append("\t\tdefault: ").append(i + codeiterator.s32bitAt(j)).append("\n");
/* 261*/        int k = codeiterator.s32bitAt(j += 4);
                int l;
/* 262*/        l = (((l = codeiterator.s32bitAt(j += 4)) - k) + 1 << 2) + (j += 4);
/* 266*/        for(k = k; j < l; k++)
                {
/* 267*/            int i1 = codeiterator.s32bitAt(j) + i;
/* 268*/            stringbuffer.append("\t\t").append(k).append(": ").append(i1).append("\n");
/* 266*/            j += 4;
                }

/* 271*/        stringbuffer.setCharAt(stringbuffer.length() - 1, '}');
/* 272*/        return stringbuffer.toString();
            }

            private static String ldc(ConstPool constpool, int i)
            {
                int j;
/* 277*/        switch(j = constpool.getTag(i))
                {
/* 280*/        case 8: // '\b'
/* 280*/            return (new StringBuilder("#")).append(i).append(" = \"").append(constpool.getStringInfo(i)).append("\"").toString();

/* 282*/        case 3: // '\003'
/* 282*/            return (new StringBuilder("#")).append(i).append(" = int ").append(constpool.getIntegerInfo(i)).toString();

/* 284*/        case 4: // '\004'
/* 284*/            return (new StringBuilder("#")).append(i).append(" = float ").append(constpool.getFloatInfo(i)).toString();

/* 286*/        case 5: // '\005'
/* 286*/            return (new StringBuilder("#")).append(i).append(" = long ").append(constpool.getLongInfo(i)).toString();

/* 288*/        case 6: // '\006'
/* 288*/            return (new StringBuilder("#")).append(i).append(" = int ").append(constpool.getDoubleInfo(i)).toString();

/* 290*/        case 7: // '\007'
/* 290*/            return classInfo(constpool, i);
                }
/* 292*/        throw new RuntimeException((new StringBuilder("bad LDC: ")).append(j).toString());
            }

            private static final String opcodes[];
            private final PrintStream stream;

            static 
            {
/*  29*/        opcodes = Mnemonic.OPCODE;
            }
}
