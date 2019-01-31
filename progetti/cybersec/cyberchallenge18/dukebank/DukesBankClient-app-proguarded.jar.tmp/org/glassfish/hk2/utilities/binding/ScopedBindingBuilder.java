// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScopedBindingBuilder.java

package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.util.List;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            BindingBuilder, ScopedNamedBindingBuilder

public interface ScopedBindingBuilder
    extends BindingBuilder
{

    public abstract ScopedBindingBuilder to(Class class1);

    public abstract ScopedBindingBuilder to(TypeLiteral typeliteral);

    public abstract ScopedBindingBuilder loadedBy(HK2Loader hk2loader);

    public abstract ScopedBindingBuilder withMetadata(String s, String s1);

    public abstract ScopedBindingBuilder withMetadata(String s, List list);

    public abstract ScopedBindingBuilder qualifiedBy(Annotation annotation);

    public abstract ScopedNamedBindingBuilder named(String s);

    public abstract void ranked(int i);

    public abstract ScopedBindingBuilder proxy(boolean flag);

    public abstract ScopedBindingBuilder proxyForSameScope(boolean flag);

    public abstract ScopedBindingBuilder analyzeWith(String s);
}
