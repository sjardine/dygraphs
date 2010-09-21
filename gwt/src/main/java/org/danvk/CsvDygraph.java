/**
 * $Id: CsvDygraph.java 14 2010-08-31 21:46:16Z steven.jardine $ 
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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Timer;

/**
 * A GWT wrapper for the Dygraph implementation. This wrapper handles only a CSV
 * style Dygraph.
 * 
 * @version $Rev: 14 $
 * @author Steven Jardine
 */
public class CsvDygraph extends AbstractDygraph {

    private static int idCount = 0;

    /**
     * Draw the dygraph.
     * 
     * @param elementId
     *            the elementId of the div to draw the graph.
     * @param csvUrl
     *            the <b>ENCODED</b> url for the dygraph data.
     * @param options
     *            the dygraph options to set.
     * @return the JavaScript object.
     */
    private static native JavaScriptObject drawDygraph(final String elementId,
	    final String csvUrl, final JavaScriptObject options) /*-{
        var g = new $wnd.Dygraph($wnd.document.getElementById(elementId), csvUrl, options);
        g.id = elementId+"-graph";
        return g;
    }-*/;

    /**
     * @return the next id for the csv dygraph.
     */
    private static String getNextId() {
	return "csv-dygraph-" + idCount++;
    }

    /**
     * Resize the graphs div.
     * 
     * @param divId
     *            the div containing the graph.
     * @param height
     *            the height to resize to.
     * @param width
     *            the width to resize to.
     */
    public static native void resizeDiv(final String divId, final int height,
	    final int width) /*-{
        var div = $wnd.document.getElementById(divId);
        div.style.height = height + "px";
        div.style.width = width + "px";
    }-*/;

    private Boolean canAutoReload = false;

    private String csvReloadUrl = null;

    private String csvUrl = null;

    private JavaScriptObject dygraph = null;

    private boolean hasDrawn = false;

    private Integer reloadInterval = null;

    private Timer reloadTimer = null;

    /**
     * Default constructor.
     * 
     * @param csvUrl
     *            the url of the csv data.
     * @param options
     *            the graph options.
     */
    public CsvDygraph(final String csvUrl, final DygraphOptions options) {
	super(getNextId(), options);
	this.csvUrl = csvUrl;
	if (options.getHeight() != null) {
	    setHeight(options.getHeight() + "px");
	}
	if (options.getWidth() != null) {
	    setWidth(options.getWidth() + "px");
	}
    }

    /**
     * Creates a timer that will reload the graph on a set interval.
     */
    private void createReloadTimer() {
	if (!hasDrawn) {
	    return;
	}
	if (canAutoReload && reloadInterval != null) {
	    Integer interval = getReloadInterval();
	    if (interval != null) {
		if (reloadTimer != null) {
		    reloadTimer.cancel();
		}
		reloadTimer = new Timer() {
		    public void run() {
			String url = getCsvReloadUrl();
			if (url == null) {
			    // If csvReloadUrl is not set try and load with the
			    // regular csvUrl.
			    // We require a reloadUrl because of a defect in
			    // dygraphs that loads the csv
			    // header as data on a reload.
			    url = csvUrl;
			}
			if (url != null) {
			    reload(url);
			}
		    }
		};
		reloadTimer.scheduleRepeating(interval);
	    }
	} else if (reloadTimer != null) {
	    reloadTimer.cancel();
	    reloadTimer = null;
	}
    }

    /**
     * @return the canAutoReload
     */
    public final Boolean getCanAutoReload() {
	return canAutoReload;
    }

    /**
     * @return the csvReloadUrl
     */
    public final String getCsvReloadUrl() {
	return csvReloadUrl;
    }

    /**
     * @return the reloadInterval
     */
    public final Integer getReloadInterval() {
	return reloadInterval;
    }

    /** {@inheritDoc} */
    @Override
    public final void onLoad() {
	if (getAutoDraw()) {
	    draw();
	}
    }

    /**
     * Native reload function.
     * 
     * @param graph
     *            the graph javascript object.
     * @param url
     *            the url of the data csv file.
     */
    private native void reload(final JavaScriptObject graph, final String url) /*-{
        graph.updateOptions( {
        'file': url
        } );
    }-*/;

    /**
     * Reload the function.
     * 
     * @param url
     *            the url of the data csv file.
     */
    private void reload(final String url) {
	reload(dygraph, url);
    }

    /**
     * @param canAutoReload
     *            the canAutoReload to set
     */
    public final void setCanAutoReload(final Boolean canAutoReload) {
	this.canAutoReload = canAutoReload;
	createReloadTimer();
    }

    /**
     * @param csvReloadUrl
     *            the csvReloadUrl to set
     */
    public final void setCsvReloadUrl(final String csvReloadUrl) {
	this.csvReloadUrl = csvReloadUrl;
	createReloadTimer();
    }

    /**
     * @param reloadInterval
     *            the reloadInterval to set
     */
    public final void setReloadInterval(final Integer reloadInterval) {
	this.reloadInterval = reloadInterval;
	createReloadTimer();
    }

    /** {@inheritDoc} */
    @Override
    public final void draw() {
	JavaScriptObject jsOptions = null;
	if (options != null) {
	    jsOptions = options.getJsObject();
	}
	dygraph = drawDygraph(getId(), csvUrl, jsOptions);
	hasDrawn = true;
	createReloadTimer();
    }

}
