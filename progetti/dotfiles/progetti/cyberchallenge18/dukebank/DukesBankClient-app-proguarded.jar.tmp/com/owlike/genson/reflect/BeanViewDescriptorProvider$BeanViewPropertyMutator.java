// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanViewDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.BeanView;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanViewDescriptorProvider, PropertyMutator

static class _view extends _view
{

            public void mutate(Object obj, Object obj1)
            {
/* 259*/        try
                {
/* 259*/            _setter.invoke(_view, new Object[] {
/* 259*/                obj1, obj
                    });
/* 266*/            return;
                }
                // Misplaced declaration of an exception variable
/* 260*/        catch(Object obj)
                {
/* 261*/            throw couldNotMutate(((Exception) (obj)));
                }
                // Misplaced declaration of an exception variable
/* 262*/        catch(Object obj)
                {
/* 263*/            throw couldNotMutate(((Exception) (obj)));
                }
                // Misplaced declaration of an exception variable
/* 264*/        catch(Object obj)
                {
/* 265*/            throw couldNotMutate(((Exception) (obj)));
                }
            }

            private final BeanView _view;

            public (String s, Method method, Type type, BeanView beanview, Class class1)
            {
/* 252*/        super(s, method, type, class1);
/* 253*/        _view = beanview;
            }
}
