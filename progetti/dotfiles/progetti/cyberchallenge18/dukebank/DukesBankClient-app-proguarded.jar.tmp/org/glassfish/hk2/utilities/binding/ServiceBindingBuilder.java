// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceBindingBuilder.java

package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            BindingBuilder, ScopedBindingBuilder, NamedBindingBuilder

public interface ServiceBindingBuilder
    extends BindingBuilder
{

    public abstract ServiceBindingBuilder to(Class class1);

    public abstract ServiceBindingBuilder to(TypeLiteral typeliteral);

    public abstract ServiceBindingBuilder to(Type type);

    public abstract ServiceBindingBuilder loadedBy(HK2Loader hk2loader);

    public abstract ServiceBindingBuilder withMetadata(String s, String s1);

    public abstract ServiceBindingBuilder withMetadata(String s, List list);

    public abstract ServiceBindingBuilder qualifiedBy(Annotation annotation);

    public abstract ScopedBindingBuilder in(Annotation annotation);

    public abstract ScopedBindingBuilder in(Class class1);

    public abstract NamedBindingBuilder named(String s);

    public abstract void ranked(int i);

    public abstract ServiceBindingBuilder proxy(boolean flag);

    public abstract ServiceBindingBuilder proxyForSameScope(boolean flag);

    public abstract ServiceBindingBuilder analyzeWith(String s);
}
