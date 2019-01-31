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

public static class array extends array
{

            public static TypeData make(TypeData typedata)
                throws BadBytecode
            {
/* 575*/        if(typedata instanceof array)
/* 576*/            return ((array)typedata).mentType();
/* 577*/        if(typedata instanceof mentType)
/* 578*/            return new <init>((<init>)typedata);
/* 579*/        if((typedata instanceof <init>) && !typedata.isNullType())
/* 581*/            return new it>(typeName(typedata.getName()));
/* 583*/        else
/* 583*/            throw new BadBytecode((new StringBuilder("bad AASTORE: ")).append(typedata).toString());
            }

            public void merge(TypeData typedata)
            {
/* 588*/        try
                {
/* 588*/            if(!typedata.isNullType())
/* 589*/                array.rge(e(typedata));
/* 594*/            return;
                }
                // Misplaced declaration of an exception variable
/* 591*/        catch(TypeData typedata)
                {
/* 593*/            throw new RuntimeException((new StringBuilder("fatal: ")).append(typedata).toString());
                }
            }

            public String getName()
            {
/* 598*/        return typeName(array.tName());
            }

            public tName arrayType()
            {
/* 601*/        return array;
            }

            public array isBasicType()
            {
/* 607*/        return null;
            }

            public boolean is2WordType()
            {
/* 609*/        return false;
            }

            private static String typeName(String s)
            {
/* 612*/        if(s.length() > 1 && s.charAt(0) == '[')
                {
                    char c;
/* 613*/            if((c = s.charAt(1)) == 'L')
/* 615*/                return s.substring(2, s.length() - 1).replace('/', '.');
/* 616*/            if(c == '[')
/* 617*/                return s.substring(1);
                }
/* 620*/        return "java.lang.Object";
            }

            public void setType(String s, ClassPool classpool)
                throws BadBytecode
            {
/* 624*/        array.tType(eName(s), classpool);
            }

            protected eName toTypeVar()
            {
/* 627*/        return array.TypeVar();
            }

            public int dfs(ArrayList arraylist, int i, ClassPool classpool)
                throws NotFoundException
            {
/* 630*/        return array.s(arraylist, i, classpool);
            }

            private s array;


            private ( )
            {
/* 571*/        array = ;
            }
}
