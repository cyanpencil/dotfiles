// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SymbolTable.java

package javassist.compiler;

import java.util.HashMap;
import javassist.compiler.ast.Declarator;

public final class SymbolTable extends HashMap
{

            public SymbolTable()
            {
/*  25*/        this(null);
            }

            public SymbolTable(SymbolTable symboltable)
            {
/*  29*/        parent = symboltable;
            }

            public final SymbolTable getParent()
            {
/*  32*/        return parent;
            }

            public final Declarator lookup(String s)
            {
                Declarator declarator;
/*  35*/        for(; (declarator = (Declarator)get(s)) == null && parent != null; this = parent);
/*  39*/        return declarator;
            }

            public final void append(String s, Declarator declarator)
            {
/*  43*/        put(s, declarator);
            }

            private SymbolTable parent;
}
