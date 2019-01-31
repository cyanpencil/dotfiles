// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientConfig.java

package org.glassfish.jersey.client;

import java.util.*;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.*;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ScopedBindingBuilder;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.ExtendedConfig;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.client.spi.ConnectorProvider;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.internal.inject.ProviderBinder;
import org.glassfish.jersey.internal.util.collection.*;
import org.glassfish.jersey.model.internal.CommonConfig;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.process.internal.ExecutorProviders;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRuntime, JerseyClient, ClientBinder, HttpUrlConnectorProvider

public class ClientConfig
    implements Configurable, ExtendedConfig
{
    static class State
        implements Configurable, ExtendedConfig
    {
        static interface StateChangeStrategy
        {

            public abstract State onChange(State state1);
        }


                State copy()
                {
/* 181*/            return new State(client, this);
                }

                State copy(JerseyClient jerseyclient)
                {
/* 192*/            return new State(jerseyclient, this);
                }

                void markAsShared()
                {
/* 196*/            strategy = COPY_ON_CHANGE;
                }

                State preInitialize()
                {
                    State state1;
/* 200*/            (state1 = strategy.onChange(this)).strategy = COPY_ON_CHANGE;
/* 202*/            ((ClientRuntime)state1.runtime.get()).preInitialize();
/* 203*/            return state1;
                }

                public State property(String s, Object obj)
                {
                    State state1;
/* 209*/            (state1 = strategy.onChange(this)).commonConfig.property(s, obj);
/* 211*/            return state1;
                }

                public State loadFrom(Configuration configuration)
                {
                    State state1;
/* 215*/            (state1 = strategy.onChange(this)).commonConfig.loadFrom(configuration);
/* 217*/            return state1;
                }

                public State register(Class class1)
                {
                    State state1;
/* 222*/            (state1 = strategy.onChange(this)).commonConfig.register(class1);
/* 224*/            return state1;
                }

                public State register(Object obj)
                {
                    State state1;
/* 229*/            (state1 = strategy.onChange(this)).commonConfig.register(obj);
/* 231*/            return state1;
                }

                public State register(Class class1, int i)
                {
                    State state1;
/* 236*/            (state1 = strategy.onChange(this)).commonConfig.register(class1, i);
/* 238*/            return state1;
                }

                public transient State register(Class class1, Class aclass[])
                {
                    State state1;
/* 243*/            (state1 = strategy.onChange(this)).commonConfig.register(class1, aclass);
/* 245*/            return state1;
                }

                public State register(Class class1, Map map)
                {
                    State state1;
/* 250*/            (state1 = strategy.onChange(this)).commonConfig.register(class1, map);
/* 252*/            return state1;
                }

                public State register(Object obj, int i)
                {
                    State state1;
/* 257*/            (state1 = strategy.onChange(this)).commonConfig.register(obj, i);
/* 259*/            return state1;
                }

                public transient State register(Object obj, Class aclass[])
                {
                    State state1;
/* 264*/            (state1 = strategy.onChange(this)).commonConfig.register(obj, aclass);
/* 266*/            return state1;
                }

                public State register(Object obj, Map map)
                {
                    State state1;
/* 271*/            (state1 = strategy.onChange(this)).commonConfig.register(obj, map);
/* 273*/            return state1;
                }

                State connectorProvider(ConnectorProvider connectorprovider)
                {
/* 277*/            if(connectorprovider == null)
                    {
/* 278*/                throw new NullPointerException(LocalizationMessages.NULL_CONNECTOR_PROVIDER());
                    } else
                    {
                        State state1;
/* 280*/                (state1 = strategy.onChange(this)).connectorProvider = connectorprovider;
/* 282*/                return state1;
                    }
                }

                Connector getConnector()
                {
/* 287*/            if(runtime.isInitialized())
/* 287*/                return ((ClientRuntime)runtime.get()).getConnector();
/* 287*/            else
/* 287*/                return null;
                }

                ConnectorProvider getConnectorProvider()
                {
/* 291*/            return connectorProvider;
                }

                JerseyClient getClient()
                {
/* 295*/            return client;
                }

                public State getConfiguration()
                {
/* 300*/            return this;
                }

                public RuntimeType getRuntimeType()
                {
/* 305*/            return commonConfig.getConfiguration().getRuntimeType();
                }

                public Map getProperties()
                {
/* 310*/            return commonConfig.getConfiguration().getProperties();
                }

                public Object getProperty(String s)
                {
/* 315*/            return commonConfig.getConfiguration().getProperty(s);
                }

                public Collection getPropertyNames()
                {
/* 320*/            return commonConfig.getConfiguration().getPropertyNames();
                }

                public boolean isProperty(String s)
                {
/* 325*/            return commonConfig.getConfiguration().isProperty(s);
                }

                public boolean isEnabled(Feature feature)
                {
/* 330*/            return commonConfig.getConfiguration().isEnabled(feature);
                }

                public boolean isEnabled(Class class1)
                {
/* 335*/            return commonConfig.getConfiguration().isEnabled(class1);
                }

                public boolean isRegistered(Object obj)
                {
/* 340*/            return commonConfig.getConfiguration().isRegistered(obj);
                }

                public boolean isRegistered(Class class1)
                {
/* 345*/            return commonConfig.getConfiguration().isRegistered(class1);
                }

                public Map getContracts(Class class1)
                {
/* 350*/            return commonConfig.getConfiguration().getContracts(class1);
                }

                public Set getClasses()
                {
/* 355*/            return commonConfig.getConfiguration().getClasses();
                }

                public Set getInstances()
                {
/* 360*/            return commonConfig.getConfiguration().getInstances();
                }

                public void configureAutoDiscoverableProviders(ServiceLocator servicelocator)
                {
/* 364*/            commonConfig.configureAutoDiscoverableProviders(servicelocator, false);
                }

                public void configureForcedAutoDiscoverableProviders(ServiceLocator servicelocator)
                {
/* 368*/            commonConfig.configureAutoDiscoverableProviders(servicelocator, true);
                }

                public void configureMetaProviders(ServiceLocator servicelocator)
                {
/* 372*/            commonConfig.configureMetaProviders(servicelocator);
                }

                public ComponentBag getComponentBag()
                {
/* 376*/            return commonConfig.getComponentBag();
                }

                private ClientRuntime initRuntime()
                {
/* 388*/            markAsShared();
                    final State runtimeCfgState;
/* 390*/            (runtimeCfgState = copy()).markAsShared();
                    ServiceLocator servicelocator;
/* 393*/            (servicelocator = Injections.createLocator(new Binder[] {
/* 393*/                new ClientBinder(runtimeCfgState.getProperties())
                    })).setDefaultClassAnalyzerName("JerseyClassAnalyzer");
/* 397*/            if(!((Boolean)CommonProperties.getValue(runtimeCfgState.getProperties(), RuntimeType.CLIENT, "jersey.config.disableAutoDiscovery", Boolean.FALSE, java/lang/Boolean)).booleanValue())
/* 399*/                runtimeCfgState.configureAutoDiscoverableProviders(servicelocator);
/* 401*/            else
/* 401*/                runtimeCfgState.configureForcedAutoDiscoverableProviders(servicelocator);
/* 405*/            runtimeCfgState.configureMetaProviders(servicelocator);
/* 408*/            Object obj = new AbstractBinder() {

                        protected void configure()
                        {
/* 411*/                    bind(runtimeCfgState).to(javax/ws/rs/core/Configuration);
                        }

                        final State val$runtimeCfgState;
                        final State this$0;

                        
                        {
/* 408*/                    this$0 = State.this;
/* 408*/                    runtimeCfgState = state2;
/* 408*/                    super();
                        }
            };
/* 414*/            DynamicConfiguration dynamicconfiguration = Injections.getConfiguration(servicelocator);
/* 415*/            ((AbstractBinder) (obj)).bind(dynamicconfiguration);
/* 416*/            dynamicconfiguration.commit();
/* 419*/            ProviderBinder.bindProviders(runtimeCfgState.getComponentBag(), RuntimeType.CLIENT, null, servicelocator);
/* 422*/            ExecutorProviders.createInjectionBindings(servicelocator);
/* 424*/            runtimeCfgState = new ClientConfig(runtimeCfgState);
/* 425*/            obj = connectorProvider.getConnector(client, runtimeCfgState);
/* 426*/            runtimeCfgState = new ClientRuntime(runtimeCfgState, ((Connector) (obj)), servicelocator);
/* 428*/            client.registerShutdownHook(runtimeCfgState);
/* 430*/            return runtimeCfgState;
                }

                public boolean equals(Object obj)
                {
/* 435*/            if(this == obj)
/* 436*/                return true;
/* 438*/            if(obj == null || getClass() != obj.getClass())
/* 439*/                return false;
/* 442*/            obj = (State)obj;
/* 444*/            if(client == null ? ((State) (obj)).client != null : !client.equals(((State) (obj)).client))
/* 445*/                return false;
/* 447*/            if(!commonConfig.equals(((State) (obj)).commonConfig))
/* 448*/                return false;
/* 450*/            if(connectorProvider == null)
/* 450*/                return ((State) (obj)).connectorProvider == null;
/* 450*/            else
/* 450*/                return connectorProvider.equals(((State) (obj)).connectorProvider);
                }

                public int hashCode()
                {
/* 456*/            int i = commonConfig.hashCode();
/* 457*/            i = i * 31 + (client == null ? 0 : client.hashCode());
/* 458*/            return i = i * 31 + (connectorProvider == null ? 0 : connectorProvider.hashCode());
                }

                public volatile Configurable register(Object obj, Map map)
                {
/*  90*/            return register(obj, map);
                }

                public volatile Configurable register(Object obj, Class aclass[])
                {
/*  90*/            return register(obj, aclass);
                }

                public volatile Configurable register(Object obj, int i)
                {
/*  90*/            return register(obj, i);
                }

                public volatile Configurable register(Object obj)
                {
/*  90*/            return register(obj);
                }

                public volatile Configurable register(Class class1, Map map)
                {
/*  90*/            return register(class1, map);
                }

                public volatile Configurable register(Class class1, Class aclass[])
                {
/*  90*/            return register(class1, aclass);
                }

                public volatile Configurable register(Class class1, int i)
                {
/*  90*/            return register(class1, i);
                }

                public volatile Configurable register(Class class1)
                {
/*  90*/            return register(class1);
                }

                public volatile Configurable property(String s, Object obj)
                {
/*  90*/            return property(s, obj);
                }

                public volatile Configuration getConfiguration()
                {
/*  90*/            return getConfiguration();
                }

                private static final StateChangeStrategy IDENTITY = new StateChangeStrategy() {

                    public final State onChange(State state1)
                    {
/*  99*/                return state1;
                    }

        };
                private static final StateChangeStrategy COPY_ON_CHANGE = new StateChangeStrategy() {

                    public final State onChange(State state1)
                    {
/* 109*/                return state1.copy();
                    }

        };
                private volatile StateChangeStrategy strategy;
                private final CommonConfig commonConfig;
                private final JerseyClient client;
                private volatile ConnectorProvider connectorProvider;
                private final LazyValue runtime;




                State(JerseyClient jerseyclient)
                {
/* 119*/            runtime = Values.lazy(new Value() {

                        public ClientRuntime get()
                        {
/* 122*/                    return initRuntime();
                        }

                        public volatile Object get()
                        {
/* 119*/                    return get();
                        }

                        final State this$0;

                        
                        {
/* 119*/                    this$0 = State.this;
/* 119*/                    super();
                        }
            });
/* 149*/            strategy = IDENTITY;
/* 150*/            commonConfig = new CommonConfig(RuntimeType.CLIENT, ComponentBag.EXCLUDE_EMPTY);
/* 151*/            client = jerseyclient;
/* 152*/            if((jerseyclient = ServiceFinder.find(org/glassfish/jersey/client/spi/ConnectorProvider).iterator()).hasNext())
                    {
/* 154*/                connectorProvider = (ConnectorProvider)jerseyclient.next();
/* 154*/                return;
                    } else
                    {
/* 156*/                connectorProvider = new HttpUrlConnectorProvider();
/* 158*/                return;
                    }
                }

                private State(JerseyClient jerseyclient, State state1)
                {
/* 119*/            runtime = Values.lazy(new _cls3());
/* 168*/            strategy = IDENTITY;
/* 169*/            client = jerseyclient;
/* 170*/            commonConfig = new CommonConfig(state1.commonConfig);
/* 171*/            connectorProvider = state1.connectorProvider;
                }
    }


            public ClientConfig()
            {
/* 468*/        state = new State(null);
            }

            public transient ClientConfig(Class aclass[])
            {
/* 477*/        this();
/* 478*/        int i = (aclass = aclass).length;
/* 478*/        for(int j = 0; j < i; j++)
                {
/* 478*/            Class class1 = aclass[j];
/* 479*/            state.register(class1);
                }

            }

            public transient ClientConfig(Object aobj[])
            {
/* 489*/        this();
/* 490*/        int i = (aobj = aobj).length;
/* 490*/        for(int j = 0; j < i; j++)
                {
/* 490*/            Object obj = aobj[j];
/* 491*/            state.register(obj);
                }

            }

            ClientConfig(JerseyClient jerseyclient)
            {
/* 502*/        state = new State(jerseyclient);
            }

            ClientConfig(JerseyClient jerseyclient, Configuration configuration)
            {
/* 513*/        if(configuration instanceof ClientConfig)
                {
/* 514*/            state = ((ClientConfig)configuration).state.copy(jerseyclient);
/* 514*/            return;
                } else
                {
/* 516*/            state = new State(jerseyclient);
/* 517*/            state.loadFrom(configuration);
/* 519*/            return;
                }
            }

            private ClientConfig(State state1)
            {
/* 527*/        state = state1;
            }

            ClientConfig snapshot()
            {
/* 541*/        state.markAsShared();
/* 542*/        return new ClientConfig(state);
            }

            public ClientConfig loadFrom(Configuration configuration)
            {
/* 556*/        if(configuration instanceof ClientConfig)
/* 557*/            state = ((ClientConfig)configuration).state.copy();
/* 559*/        else
/* 559*/            state.loadFrom(configuration);
/* 561*/        return this;
            }

            public ClientConfig register(Class class1)
            {
/* 566*/        state = state.register(class1);
/* 567*/        return this;
            }

            public ClientConfig register(Object obj)
            {
/* 572*/        state = state.register(obj);
/* 573*/        return this;
            }

            public ClientConfig register(Class class1, int i)
            {
/* 578*/        state = state.register(class1, i);
/* 579*/        return this;
            }

            public transient ClientConfig register(Class class1, Class aclass[])
            {
/* 584*/        state = state.register(class1, aclass);
/* 585*/        return this;
            }

            public ClientConfig register(Class class1, Map map)
            {
/* 590*/        state = state.register(class1, map);
/* 591*/        return this;
            }

            public ClientConfig register(Object obj, int i)
            {
/* 596*/        state = state.register(obj, i);
/* 597*/        return this;
            }

            public transient ClientConfig register(Object obj, Class aclass[])
            {
/* 602*/        state = state.register(obj, aclass);
/* 603*/        return this;
            }

            public ClientConfig register(Object obj, Map map)
            {
/* 608*/        state = state.register(obj, map);
/* 609*/        return this;
            }

            public ClientConfig property(String s, Object obj)
            {
/* 614*/        state = state.property(s, obj);
/* 615*/        return this;
            }

            public ClientConfig getConfiguration()
            {
/* 620*/        return this;
            }

            public RuntimeType getRuntimeType()
            {
/* 625*/        return state.getRuntimeType();
            }

            public Map getProperties()
            {
/* 630*/        return state.getProperties();
            }

            public Object getProperty(String s)
            {
/* 635*/        return state.getProperty(s);
            }

            public Collection getPropertyNames()
            {
/* 640*/        return state.getPropertyNames();
            }

            public boolean isProperty(String s)
            {
/* 645*/        return state.isProperty(s);
            }

            public boolean isEnabled(Feature feature)
            {
/* 650*/        return state.isEnabled(feature);
            }

            public boolean isEnabled(Class class1)
            {
/* 655*/        return state.isEnabled(class1);
            }

            public boolean isRegistered(Object obj)
            {
/* 660*/        return state.isRegistered(obj);
            }

            public Map getContracts(Class class1)
            {
/* 665*/        return state.getContracts(class1);
            }

            public boolean isRegistered(Class class1)
            {
/* 670*/        return state.isRegistered(class1);
            }

            public Set getClasses()
            {
/* 675*/        return state.getClasses();
            }

            public Set getInstances()
            {
/* 680*/        return state.getInstances();
            }

            public ClientConfig connectorProvider(ConnectorProvider connectorprovider)
            {
/* 698*/        state = state.connectorProvider(connectorprovider);
/* 699*/        return this;
            }

            public Connector getConnector()
            {
/* 710*/        return state.getConnector();
            }

            public ConnectorProvider getConnectorProvider()
            {
/* 724*/        return state.getConnectorProvider();
            }

            ClientRuntime getRuntime()
            {
/* 733*/        return (ClientRuntime)state.runtime.get();
            }

            public JerseyClient getClient()
            {
/* 744*/        return state.getClient();
            }

            ClientConfig preInitialize()
            {
/* 759*/        state = state.preInitialize();
/* 760*/        return this;
            }

            void checkClient()
                throws IllegalStateException
            {
/* 770*/        if(getClient() == null)
/* 771*/            throw new IllegalStateException("Client configuration does not contain a parent client instance.");
/* 773*/        else
/* 773*/            return;
            }

            public boolean equals(Object obj)
            {
/* 777*/        if(obj == null)
/* 778*/            return false;
/* 780*/        if(getClass() != obj.getClass())
/* 781*/            return false;
/* 783*/        obj = (ClientConfig)obj;
/* 784*/        return state == ((ClientConfig) (obj)).state || state != null && state.equals(((ClientConfig) (obj)).state);
            }

            public int hashCode()
            {
                int i;
/* 790*/        return i = 329 + (state == null ? 0 : state.hashCode());
            }

            public volatile Configurable register(Object obj, Map map)
            {
/*  81*/        return register(obj, map);
            }

            public volatile Configurable register(Object obj, Class aclass[])
            {
/*  81*/        return register(obj, aclass);
            }

            public volatile Configurable register(Object obj, int i)
            {
/*  81*/        return register(obj, i);
            }

            public volatile Configurable register(Object obj)
            {
/*  81*/        return register(obj);
            }

            public volatile Configurable register(Class class1, Map map)
            {
/*  81*/        return register(class1, map);
            }

            public volatile Configurable register(Class class1, Class aclass[])
            {
/*  81*/        return register(class1, aclass);
            }

            public volatile Configurable register(Class class1, int i)
            {
/*  81*/        return register(class1, i);
            }

            public volatile Configurable register(Class class1)
            {
/*  81*/        return register(class1);
            }

            public volatile Configurable property(String s, Object obj)
            {
/*  81*/        return property(s, obj);
            }

            public volatile Configuration getConfiguration()
            {
/*  81*/        return getConfiguration();
            }


            private State state;
}
