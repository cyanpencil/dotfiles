// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataSourceDefinition.java

package javax.annotation.sql;

import java.lang.annotation.Annotation;

public interface DataSourceDefinition
    extends Annotation
{

    public abstract String name();

    public abstract String className();

    public abstract String description();

    public abstract String url();

    public abstract String user();

    public abstract String password();

    public abstract String databaseName();

    public abstract int portNumber();

    public abstract String serverName();

    public abstract int isolationLevel();

    public abstract boolean transactional();

    public abstract int initialPoolSize();

    public abstract int maxPoolSize();

    public abstract int minPoolSize();

    public abstract int maxIdleTime();

    public abstract int maxStatements();

    public abstract String[] properties();

    public abstract int loginTimeout();
}
