// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NamedBindingBuilder.java

package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.util.List;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;

// Referenced classes of package org.glassfish.hk2.utilities.binding:
//            BindingBuilder, ScopedNamedBindingBuilder

public interface NamedBindingBuilder
    extends BindingBuilder
{

    public abstract NamedBindingBuilder to(Class class1);

    public abstract NamedBindingBuilder to(TypeLiteral typeliteral);

    public abstract NamedBindingBuilder loadedBy(HK2Loader hk2loader);

    public abstract NamedBindingBuilder withMetadata(String s, String s1);

    public abstract NamedBindingBuilder withMetadata(String s, List list);

    public abstract NamedBindingBuilder qualifiedBy(Annotation annotation);

    public abstract ScopedNamedBindingBuilder in(Class class1);

    public abstract void ranked(int i);

    public abstract NamedBindingBuilder proxy(boolean flag);
}
