/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.requests;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata.Association;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata.Document;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata.Folder;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata.SubmissionSet;

/**
 * The data required for the Provide and register document set request.
 * <p>
 * All non-list members of this class are allowed to be <code>null</code>.
 * The lists are pre-created and can therefore never be <code>null</code>.
 * @author Jens Riemschneider
 */
public class ProvideAndRegisterDocumentSet {
    private SubmissionSet submissionSet;
    private final List<Folder> folders = new ArrayList<Folder>();
    private final List<Document> documents = new ArrayList<Document>();
    private final List<Association> associations = new ArrayList<Association>();
    
    /**
     * @return the submission set.
     */
    public SubmissionSet getSubmissionSet() {
        return submissionSet;
    }
    
    /**
     * @param submissionSet
     *          the submission set.
     */
    public void setSubmissionSet(SubmissionSet submissionSet) {
        this.submissionSet = submissionSet;
    }
    
    /**
     * @return the list of folders.
     */
    public List<Folder> getFolders() {
        return folders;
    }

    /**
     * @return the list of documents.
     */
    public List<Document> getDocuments() {
        return documents;
    }

    /**
     * @return the list of association.
     */
    public List<Association> getAssociations() {
        return associations;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((associations == null) ? 0 : associations.hashCode());
        result = prime * result + ((documents == null) ? 0 : documents.hashCode());
        result = prime * result + ((folders == null) ? 0 : folders.hashCode());
        result = prime * result + ((submissionSet == null) ? 0 : submissionSet.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProvideAndRegisterDocumentSet other = (ProvideAndRegisterDocumentSet) obj;
        if (associations == null) {
            if (other.associations != null)
                return false;
        } else if (!associations.equals(other.associations))
            return false;
        if (documents == null) {
            if (other.documents != null)
                return false;
        } else if (!documents.equals(other.documents))
            return false;
        if (folders == null) {
            if (other.folders != null)
                return false;
        } else if (!folders.equals(other.folders))
            return false;
        if (submissionSet == null) {
            if (other.submissionSet != null)
                return false;
        } else if (!submissionSet.equals(other.submissionSet))
            return false;
        return true;
    }    
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}