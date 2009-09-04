/*
 * Copyright 2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.flow.model;

import org.apache.camel.Processor;
import org.apache.camel.model.OutputDefinition;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spi.RouteContext;
import org.openehealth.ipf.platform.camel.flow.PlatformMessageRenderer;
import org.openehealth.ipf.platform.camel.flow.process.FlowProcessor;

/**
 * @author Martin Krasser
 */
public abstract class FlowProcessorDefinition extends OutputDefinition<ProcessorDefinition> {

    private PlatformMessageRenderer messageRenderer; 
    
    private String messageRendererBeanName;

    private DataFormat inFormat;
    private DataFormat outFormat;
    
    private Class<?> inType;
    private Class<?> outType;
    
    private boolean outConversion = true;
    
    public FlowProcessorDefinition inType(Class<?> inType) {
        this.inType = inType;
        return this;
    }
    
    public FlowProcessorDefinition outType(Class<?> outType) {
        this.outType = outType;
        return this;
    }
    
    public FlowProcessorDefinition inFormat(DataFormat inFormat) {
        this.inFormat = inFormat;
        return this;
    }
    
    public FlowProcessorDefinition outFormat(DataFormat outFormat) {
        this.outFormat = outFormat;
        return this;
    }
    
    public FlowProcessorDefinition outConversion(boolean outConversion) {
        this.outConversion = outConversion;
        return this;
    }
    
    public FlowProcessorDefinition renderer(PlatformMessageRenderer messageRenderer) {
        this.messageRenderer = messageRenderer;
        return this;
    }
    
    public FlowProcessorDefinition renderer(String messageRendererBeanName) {
        this.messageRendererBeanName = messageRendererBeanName;
        return this;
    }
    
    @Override
    public Processor createProcessor(RouteContext routeContext) throws Exception {
        FlowProcessor processor = doCreateProcessor(routeContext);
        processor
            .inType(inType)
            .outType(outType)
            .inFormat(inFormat)
            .outFormat(outFormat)
            .outConversion(outConversion)
            .renderer(renderer(routeContext, processor.getMessageRenderer()))
            .setProcessor(routeContext.createProcessor(this));
        return processor;
    }
    
    protected abstract FlowProcessor doCreateProcessor(RouteContext routeContext) throws Exception;

    private PlatformMessageRenderer renderer(RouteContext routeContext, PlatformMessageRenderer defaultRenderer) {
        if (messageRenderer != null) {
            return messageRenderer;
        } else if (messageRendererBeanName != null) {
            return routeContext.lookup(messageRendererBeanName, PlatformMessageRenderer.class);
        } else {
            return defaultRenderer;
        }
    }
}