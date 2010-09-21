/**
 * $Id: Dygraphs.java 9 2010-08-26 16:41:49Z steven.jardine $
 * Copyright (c) 2009 Dan Vanderkam
 * Copyright (c) 2010 Steven Jardine, MJN Services, Inc.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 */
package org.danvk;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Methods for installing Dygraphs source in a GWT document.
 * 
 * @author flooey@google.com (Adam Vartanian)
 */
public final class Dygraphs {

    /**
     * Resource bundle for Dygraphs.
     * 
     * @author flooey@google.com (Adam Vartanian)
     */
    public interface Resources extends ClientBundle {
	/**
	 * @return the dygraph-combined source.
	 */
	@Source("org/danvk/dygraph-combined.js")
	TextResource dygraphs();
    }

    private static boolean installed = false;

    private static final Resources RESOURCES = GWT.create(Resources.class);

    /**
     * Install the Dygraphs JavaScript source into the current document. This
     * method is idempotent.
     */
    public static synchronized void install() {
	if (!installed) {
	    ScriptElement e = Document.get().createScriptElement();
	    e.setText(RESOURCES.dygraphs().getText());
	    Document.get().getBody().appendChild(e);
	    installed = true;
	}
    }

    /**
     * Prevent construction.
     */
    private Dygraphs() {
	super();
    }

}
