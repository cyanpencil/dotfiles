// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanViewDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.BeanView;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanViewDescriptorProvider, PropertyAccessor

static class _view extends _view
{

            public Object access(Object obj)
            {
/* 236*/        return _getter.invoke(_view, new Object[] {
/* 236*/            obj
                });
/* 237*/        obj;
/* 238*/        throw couldNotAccess(((Exception) (obj)));
/* 239*/        obj;
/* 240*/        throw couldNotAccess(((Exception) (obj)));
/* 241*/        obj;
/* 242*/        throw couldNotAccess(((Exception) (obj)));
            }

            private final BeanView _view;

            public (String s, Method method, Type type, BeanView beanview, Class class1)
            {
/* 229*/        super(s, method, type, class1);
/* 230*/        _view = beanview;
            }
}
