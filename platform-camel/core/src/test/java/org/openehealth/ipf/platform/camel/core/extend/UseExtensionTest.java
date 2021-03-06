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
package org.openehealth.ipf.platform.camel.core.extend;

import static org.junit.Assert.assertEquals;

import org.apache.camel.EndpointInject;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Jens Riemschneider
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context-core-extend-use.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class UseExtensionTest {
    
    @Autowired
    protected ProducerTemplate producerTemplate;
    
    @EndpointInject(uri="mock:output")
    protected MockEndpoint mockOutput;
    
    @EndpointInject(uri="mock:error")
    protected MockEndpoint mockError;
    
    @After
    public void tearDown() throws Exception {
        mockOutput.reset();
        mockError.reset();
    }

    @Test
    public void testSetBodyClosure() throws InterruptedException {
        mockOutput.expectedBodiesReceived("blah");
        producerTemplate.sendBodyAndHeader("direct:input1", null, "foo", "blah");
        mockOutput.assertIsSatisfied();
    }

    @Test
    public void testMarshalObject() throws Exception {
        String result = (String) producerTemplate.sendBody("direct:internal1",
                ExchangePattern.InOut, "message");
        assertEquals("message", result);
    }
}