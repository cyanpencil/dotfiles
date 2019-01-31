// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeData.java

package javassist.bytecode.stackmap;

import java.util.ArrayList;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;

// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData

public static class element extends 
{

            static TypeData make(TypeData typedata)
                throws BadBytecode
            {
/* 512*/        if(typedata instanceof nt)
/* 513*/            return ((nt)typedata).arrayType();
/* 514*/        if(typedata instanceof )
/* 515*/            return new <init>(()typedata);
/* 516*/        if((typedata instanceof ) && !typedata.isNullType())
/* 518*/            return new <init>(typeName(typedata.getName()));
/* 520*/        else
/* 520*/            throw new BadBytecode((new StringBuilder("bad AASTORE: ")).append(typedata).toString());
            }

            public void merge(TypeData typedata)
            {
/* 525*/        try
                {
/* 525*/            if(!typedata.isNullType())
/* 526*/                element.merge(nt.make(typedata));
/* 531*/            return;
                }
                // Misplaced declaration of an exception variable
/* 528*/        catch(TypeData typedata)
                {
/* 530*/            throw new RuntimeException((new StringBuilder("fatal: ")).append(typedata).toString());
                }
            }

            public String getName()
            {
/* 535*/        return typeName(element.getName());
            }

            public  elementType()
            {
/* 538*/        return element;
            }

            public element isBasicType()
            {
/* 540*/        return null;
            }

            public boolean is2WordType()
            {
/* 541*/        return false;
            }

            public static String typeName(String s)
            {
/* 547*/        if(s.charAt(0) == '[')
/* 548*/            return (new StringBuilder("[")).append(s).toString();
/* 550*/        else
/* 550*/            return (new StringBuilder("[L")).append(s.replace('.', '/')).append(";").toString();
            }

            public void setType(String s, ClassPool classpool)
                throws BadBytecode
            {
/* 554*/        element.setType(nt.access._mth000(s), classpool);
            }

            protected nt.access._cls000 toTypeVar()
            {
/* 557*/        return element.toTypeVar();
            }

            public int dfs(ArrayList arraylist, int i, ClassPool classpool)
                throws NotFoundException
            {
/* 560*/        return element.dfs(arraylist, i, classpool);
            }

            private  element;

            private ( )
            {
/* 508*/        element = ;
            }
}
