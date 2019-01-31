// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AliasDescriptor.java

package org.glassfish.hk2.utilities;

import java.util.*;
import javax.inject.Named;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.general.GeneralUtilities;

// Referenced classes of package org.glassfish.hk2.utilities:
//            AbstractActiveDescriptor, NamedImpl

public class AliasDescriptor extends AbstractActiveDescriptor
{

            public AliasDescriptor()
            {
/* 118*/        initialized = false;
            }

            public AliasDescriptor(ServiceLocator servicelocator, ActiveDescriptor activedescriptor, String s, String s1)
            {
/* 156*/        super(EMPTY_CONTRACT_SET, null, s1, EMPTY_ANNOTATION_SET, activedescriptor.getDescriptorType(), activedescriptor.getDescriptorVisibility(), activedescriptor.getRanking(), activedescriptor.isProxiable(), activedescriptor.isProxyForSameScope(), activedescriptor.getClassAnalysisName(), activedescriptor.getMetadata());
/* 118*/        initialized = false;
/* 167*/        locator = servicelocator;
/* 168*/        descriptor = activedescriptor;
/* 169*/        contract = s;
/* 170*/        addAdvertisedContract(s);
/* 171*/        super.setScope(activedescriptor.getScope());
/* 172*/        super.addMetadata("__AliasOf", getAliasMetadataValue(activedescriptor));
            }

            private static String getAliasMetadataValue(ActiveDescriptor activedescriptor)
            {
/* 176*/        Long long1 = activedescriptor.getLocatorId();
/* 177*/        activedescriptor = activedescriptor.getServiceId();
/* 179*/        if(long1 == null || activedescriptor == null)
/* 179*/            return "FreeDescriptor";
/* 181*/        else
/* 181*/            return (new StringBuilder()).append(long1).append(".").append(activedescriptor).toString();
            }

            public Class getImplementationClass()
            {
/* 191*/        ensureInitialized();
/* 192*/        return descriptor.getImplementationClass();
            }

            public Object create(ServiceHandle servicehandle)
            {
/* 200*/        ensureInitialized();
/* 201*/        return locator.getServiceHandle(descriptor).getService();
            }

            public boolean isReified()
            {
/* 214*/        return true;
            }

            public String getImplementation()
            {
/* 222*/        return descriptor.getImplementation();
            }

            public Set getContractTypes()
            {
/* 230*/        ensureInitialized();
/* 231*/        return super.getContractTypes();
            }

            public Class getScopeAnnotation()
            {
/* 239*/        ensureInitialized();
/* 240*/        return descriptor.getScopeAnnotation();
            }

            public synchronized Set getQualifierAnnotations()
            {
/* 248*/        ensureInitialized();
/* 250*/        if(qualifiers == null)
                {
/* 251*/            qualifiers = new HashSet(descriptor.getQualifierAnnotations());
/* 252*/            if(getName() != null)
/* 253*/                qualifiers.add(new NamedImpl(getName()));
                }
/* 256*/        return qualifiers;
            }

            public synchronized Set getQualifiers()
            {
/* 261*/        if(qualifierNames != null)
/* 261*/            return qualifierNames;
/* 263*/        qualifierNames = new HashSet(descriptor.getQualifiers());
/* 264*/        if(getName() != null)
/* 265*/            qualifierNames.add(javax/inject/Named.getName());
/* 268*/        return qualifierNames;
            }

            public List getInjectees()
            {
/* 276*/        ensureInitialized();
/* 277*/        return descriptor.getInjectees();
            }

            public void dispose(Object obj)
            {
/* 285*/        ensureInitialized();
/* 286*/        descriptor.dispose(obj);
            }

            public ActiveDescriptor getDescriptor()
            {
/* 298*/        return descriptor;
            }

            private synchronized void ensureInitialized()
            {
/* 309*/        if(!initialized)
                {
/* 311*/            if(!descriptor.isReified())
/* 312*/                descriptor = locator.reifyDescriptor(descriptor);
/* 315*/            if(contract == null)
                    {
/* 316*/                initialized = true;
/* 317*/                return;
                    }
/* 320*/            HK2Loader hk2loader = descriptor.getLoader();
/* 322*/            Class class2 = null;
/* 324*/            try
                    {
/* 324*/                if(hk2loader != null)
                        {
/* 325*/                    class2 = hk2loader.loadClass(contract);
                        } else
                        {
/* 328*/                    Class class1 = descriptor.getImplementationClass();
/* 329*/                    ClassLoader classloader = null;
/* 330*/                    if(class1 != null)
/* 331*/                        classloader = class1.getClassLoader();
/* 333*/                    if(classloader == null)
/* 334*/                        classloader = ClassLoader.getSystemClassLoader();
/* 337*/                    class2 = classloader.loadClass(contract);
                        }
                    }
/* 340*/            catch(ClassNotFoundException _ex) { }
/* 344*/            super.addContractType(class2);
/* 346*/            initialized = true;
                }
            }

            public int hashCode()
            {
/* 353*/        synchronized(this)
                {
/* 354*/            i = descriptor.hashCode();
                }
/* 357*/        if(getName() != null)
/* 358*/            i ^= getName().hashCode();
/* 361*/        if(contract != null)
/* 362*/            i ^= contract.hashCode();
/* 365*/        return i;
            }

            public boolean equals(Object obj)
            {
/* 370*/        if(obj == null)
/* 370*/            return false;
/* 371*/        if(!(obj instanceof AliasDescriptor))
/* 371*/            return false;
/* 373*/        if(!((AliasDescriptor) (obj = (AliasDescriptor)obj)).descriptor.equals(descriptor))
/* 375*/            return false;
/* 376*/        if(!GeneralUtilities.safeEquals(((AliasDescriptor) (obj)).getName(), getName()))
/* 376*/            return false;
/* 377*/        else
/* 377*/            return GeneralUtilities.safeEquals(((AliasDescriptor) (obj)).contract, contract);
            }

            public static final String ALIAS_METADATA_MARKER = "__AliasOf";
            public static final String ALIAS_FREE_DESCRIPTOR = "FreeDescriptor";
            private static final long serialVersionUID = 0x2438361105ad8234L;
            private ServiceLocator locator;
            private ActiveDescriptor descriptor;
            private String contract;
            private Set qualifiers;
            private Set qualifierNames;
            private boolean initialized;
            private static final Set EMPTY_CONTRACT_SET = new HashSet();
            private static final Set EMPTY_ANNOTATION_SET = new HashSet();

}
