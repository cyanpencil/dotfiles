// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataSourceDefinitions.java

package javax.annotation.sql;

import java.lang.annotation.Annotation;

// Referenced classes of package javax.annotation.sql:
//            DataSourceDefinition

public interface DataSourceDefinitions
    extends Annotation
{

    public abstract DataSourceDefinition[] value();
}
