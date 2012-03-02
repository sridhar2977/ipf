/*
 * Copyright 2012 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.ws.cxf.payload

import org.apache.cxf.binding.soap.SoapMessage
import org.apache.cxf.interceptor.AttachmentOutInterceptor
import org.apache.cxf.message.Message
import org.apache.cxf.phase.Phase
import org.apache.cxf.phase.PhaseInterceptor
import org.openehealth.ipf.commons.ihe.ws.cxf.AbstractSafeInterceptor

/**
 * CXF interceptor which stores outgoing HTTP payload
 * into files with user-defined name patterns.
 * <p>
 * Members of {@link WsPayloadLoggerBase} are mixed into
 * this class and can be accessed from Groovy code.
 *
 * @author Dmytro Rud
 */
@Mixin(WsPayloadLoggerBase.class)
class OutPayloadLoggerInterceptor extends AbstractSafeInterceptor {

    OutPayloadLoggerInterceptor(String fileNamePattern) {
        super(Phase.PRE_STREAM_ENDING)
        addAfter(AttachmentOutInterceptor.AttachmentOutEndingInterceptor.class.name);
        setFileNamePattern(fileNamePattern)
    }


    @Override
    Collection<PhaseInterceptor<? extends Message>> getAdditionalInterceptors() {
        return [new DisablePayloadCollectingDeactivationInterceptor(),
                new OutStreamSubstituteInterceptor()]
    }


    @Override
    void process(SoapMessage message) {
        if (canProcess()) {
            logPayload(message)
        }
    }

}
