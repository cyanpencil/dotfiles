// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScopedNamedBindingBuilder.java

package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.util.List;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            BindingBuilder

public interface ScopedNamedBindingBuilder
    extends BindingBuilder
{

    public abstract ScopedNamedBindingBuilder to(Class class1);

    public abstract ScopedNamedBindingBuilder to(TypeLiteral typeliteral);

    public abstract ScopedNamedBindingBuilder loadedBy(HK2Loader hk2loader);

    public abstract ScopedNamedBindingBuilder withMetadata(String s, String s1);

    public abstract ScopedNamedBindingBuilder withMetadata(String s, List list);

    public abstract ScopedNamedBindingBuilder qualifiedBy(Annotation annotation);

    public abstract void ranked(int i);

    public abstract ScopedNamedBindingBuilder proxy(boolean flag);

    public abstract ScopedNamedBindingBuilder analyzeWith(String s);
}
