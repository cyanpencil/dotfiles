// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodeIterator.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            BadBytecode, CodeAttribute, CodeIterator, ExceptionTable, 
//            LineNumberAttribute, LocalVariableAttribute, StackMap, StackMapTable

static class bute
{

            void shiftPc(int i, int j, boolean flag)
                throws BadBytecode
            {
/*1030*/        if(i < cursor || i == cursor && flag)
/*1031*/            cursor += j;
/*1033*/        if(i < mark || i == mark && flag)
/*1034*/            mark += j;
/*1036*/        if(i < mark0 || i == mark0 && flag)
/*1037*/            mark0 += j;
/*1039*/        etable.shiftPc(i, j, flag);
/*1040*/        if(line != null)
/*1041*/            line.shiftPc(i, j, flag);
/*1043*/        if(vars != null)
/*1044*/            vars.shiftPc(i, j, flag);
/*1046*/        if(types != null)
/*1047*/            types.shiftPc(i, j, flag);
/*1049*/        if(stack != null)
/*1050*/            stack.shiftPc(i, j, flag);
/*1052*/        if(stack2 != null)
/*1053*/            stack2.shiftPc(i, j, flag);
            }

            void shiftForSwitch(int i, int j)
                throws BadBytecode
            {
/*1057*/        if(stack != null)
/*1058*/            stack.shiftForSwitch(i, j);
/*1060*/        if(stack2 != null)
/*1061*/            stack2.shiftForSwitch(i, j);
            }

            int cursor;
            int mark0;
            int mark;
            ExceptionTable etable;
            LineNumberAttribute line;
            LocalVariableAttribute vars;
            LocalVariableAttribute types;
            StackMapTable stack;
            StackMap stack2;

            (int i, int j, int k, ExceptionTable exceptiontable, CodeAttribute codeattribute)
            {
/*1018*/        cursor = i;
/*1019*/        mark = j;
/*1020*/        mark0 = k;
/*1021*/        etable = exceptiontable;
/*1022*/        line = (LineNumberAttribute)codeattribute.getAttribute("LineNumberTable");
/*1023*/        vars = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTable");
/*1024*/        types = (LocalVariableAttribute)codeattribute.getAttribute("LocalVariableTypeTable");
/*1025*/        stack = (StackMapTable)codeattribute.getAttribute("StackMapTable");
/*1026*/        stack2 = (StackMap)codeattribute.getAttribute("StackMap");
            }
}
