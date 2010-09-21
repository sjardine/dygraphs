/**
 * $Id: AbstractDygraph.java 28 2010-09-10 01:38:03Z steven.jardine $ 
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Event handler implementation for the dygraph. Should be the basis for all
 * dygraph wrapper implementations.
 * 
 * @version $Rev: 28 $
 * @author Steven Jardine
 */
public abstract class AbstractDygraph extends Widget {

    private boolean attached = false;

    private boolean autoDraw = true;

    private Boolean canFormatYValue = false;

    private Boolean canHandleClickEvents = false;

    private Boolean canHandleDrawEvents = false;

    private Boolean canHandleHighlightEvents = false;

    private Boolean canHandleZoomEvents = false;

    private final List<DygraphClickHandler> clickHandlers = new ArrayList<DygraphClickHandler>();

    private final List<DygraphDrawHandler> drawHandlers = new ArrayList<DygraphDrawHandler>();

    private final List<DygraphHighlightHandler> highlightHandlers = new ArrayList<DygraphHighlightHandler>();

    protected String id = null;

    protected DygraphOptions options = null;

    private YValueFormatter yValueFormatter = null;

    private final List<DygraphZoomHandler> zoomHandlers = new ArrayList<DygraphZoomHandler>();

    /**
     * Default constructor.
     * 
     * @param id
     *            the id of the div element to hold the graph.
     * @param options
     *            the dygraph options.
     */
    public AbstractDygraph(final String id, final DygraphOptions options) {
	super();
	this.id = id;
	this.options = options;

	Element element = Document.get().createDivElement();
	if (id != null) {
	    element.setId(id);
	}
	setElement(element);
    }

    /**
     * Adds a click handler to the dygraph. If the dygraph hasn't been attached
     * we will assume the user wants to auto inject the handler code for this
     * event.
     * 
     * @param handler
     *            the handler to add.
     */
    public final void addClickHandler(final DygraphClickHandler handler) {
	clickHandlers.add(handler);
	if (handler != null) {
	    clickHandlers.add(handler);
	}
	if (!attached && clickHandlers.size() > 0) {
	    setCanHandleClickEvents(true);
	}
    }

    /**
     * Adds a click handler to the dygraph. If the dygraph hasn't been attached
     * we will assume the user wants to auto inject the handler code for this
     * event.
     * 
     * @param handler
     *            the handler to add.
     */
    public final void addDrawHandler(final DygraphDrawHandler handler) {
	if (handler != null) {
	    drawHandlers.add(handler);
	}
	if (!attached && drawHandlers.size() > 0) {
	    setCanHandleDrawEvents(true);
	}
    }

    /**
     * Adds handlers to the graph.
     */
    protected final void addHandlers() {
	JavaScriptObject jsOptions = options.getJsObject();
	if (canFormatYValue) {
	    setYValueFormatter(this, jsOptions);
	}
	if (canHandleClickEvents) {
	    setClickCallback(this, jsOptions);
	}
	if (canHandleDrawEvents) {
	    setDrawCallback(this, jsOptions);
	}
	if (canHandleZoomEvents) {
	    setZoomCallback(this, jsOptions);
	}
	if (canHandleHighlightEvents) {
	    setHighlightCallback(this, jsOptions);
	}
    }

    /**
     * Adds a click handler to the dygraph. If the dygraph hasn't been attached
     * we will assume the user wants to auto inject the handler code for this
     * event.
     * 
     * @param handler
     *            the handler to add.
     */
    public final void addHighlightHandler(final DygraphHighlightHandler handler) {
	if (handler != null) {
	    highlightHandlers.add(handler);
	}
	if (!attached && highlightHandlers.size() > 0) {
	    setCanHandleHighlightEvents(true);
	}
    }

    /**
     * Adds a click handler to the dygraph. If the dygraph hasn't been attached
     * we will assume the user wants to auto inject the handler code for this
     * event.
     * 
     * @param handler
     *            the handler to add.
     */
    public final void addZoomHandler(final DygraphZoomHandler handler) {
	if (handler != null) {
	    zoomHandlers.add(handler);
	}
	if (!attached && zoomHandlers.size() > 0) {
	    setCanHandleZoomEvents(true);
	}
    }

    /**
     * Responsible for actually drawing the dygraph. If autoDraw is false then
     * this method must be called to render the dygraph.
     */
    public abstract void draw();

    /**
     * Format the y value.
     * 
     * @param yValue
     *            the value to format
     * @return the formatter value.
     */
    protected final String formatYValue(final double yValue) {
	String result = null;
	if (yValueFormatter != null) {
	    result = yValueFormatter.format(yValue);
	} else {
	    // CHECKSTYLE:OFF
	    result = String.valueOf((double) Math.round(yValue * 100) / 100);
	    // CHECKSTYLE:ON
	}
	return result;
    }

    /**
     * @return the autoDraw
     */
    public final boolean getAutoDraw() {
	return autoDraw;
    }

    /**
     * @return the canFormatYValue
     */
    public final Boolean getCanFormatYValue() {
	return canFormatYValue;
    }

    /**
     * @return the canHandleClickEvents
     */
    public final Boolean getCanHandleClickEvents() {
	return canHandleClickEvents;
    }

    /**
     * @return the canHandleDrawEvents
     */
    public final Boolean getCanHandleDrawEvents() {
	return canHandleDrawEvents;
    }

    /**
     * @return the canHandleHighlightEvents
     */
    public final Boolean getCanHandleHighlightEvents() {
	return canHandleHighlightEvents;
    }

    /**
     * @return the canHandleZoomEvents
     */
    public final Boolean getCanHandleZoomEvents() {
	return canHandleZoomEvents;
    }

    /**
     * @return the id to get.
     */
    public final String getId() {
	return id;
    }

    /** {@inheritDoc} */
    @Override
    protected final void onAttach() {
	attached = true;
	addHandlers();
	super.onAttach();
    }

    /**
     * Handle the onclick event.
     * 
     * @param event
     *            the event.
     * @param dateDbl
     *            the date.
     * @param points
     *            the points on the y axis.
     */
    protected final void onClick(final MouseEvent event, final double dateDbl,
	    final Points points) {
	Date date = new Date(Math.round(dateDbl));
	for (DygraphClickHandler handler : clickHandlers) {
	    handler.onClick(event, date, points);
	}
    }

    /**
     * Handle the onDraw event.
     * 
     * @param startDateDbl
     *            the start date.
     * @param endDateDbl
     *            the end date.
     * @param isInitial
     *            is this the initial draw?
     */
    protected final void onDraw(final double startDateDbl,
	    final double endDateDbl, final boolean isInitial) {
	Date startDate = new Date(Math.round(startDateDbl));
	Date endDate = new Date(Math.round(endDateDbl));
	for (DygraphDrawHandler handler : drawHandlers) {
	    handler.onDraw(startDate, endDate);
	}
    }

    /**
     * Handle the onHighlight event.
     * 
     * @param event
     *            the event.
     * @param dateDbl
     *            the date.
     * @param points
     *            the points on the y axis.
     */
    protected final void onHighlight(final MouseEvent event,
	    final double dateDbl, final Points points) {
	Date date = new Date(Math.round(dateDbl));
	for (DygraphHighlightHandler handler : highlightHandlers) {
	    handler.onHighlight(event, date, points);
	}
    }

    /**
     * Handle the onHighlight event.
     * 
     * @param event
     *            the event.
     */
    protected final void onUnhighlight(final MouseEvent event) {
	for (DygraphHighlightHandler handler : highlightHandlers) {
	    handler.onUnhighlight(event);
	}
    }

    /**
     * Handle the onZoom event.
     * 
     * @param startDateDbl
     *            the start date.
     * @param endDateDbl
     *            the end date.
     */
    protected final void onZoom(final double startDateDbl,
	    final double endDateDbl) {
	Date startDate = new Date(Math.round(startDateDbl));
	Date endDate = new Date(Math.round(endDateDbl));
	for (DygraphZoomHandler handler : zoomHandlers) {
	    handler.onZoom(startDate, endDate);
	}
    }

    /**
     * @param autoDraw
     *            the autoDraw to set
     */
    public final void setAutoDraw(final boolean autoDraw) {
	this.autoDraw = autoDraw;
    }

    /**
     * @param canFormatYValue
     *            the canFormatYValue to set
     */
    public final void setCanFormatYValue(final Boolean canFormatYValue) {
	this.canFormatYValue = canFormatYValue;
    }

    /**
     * @param canHandleClickEvents
     *            the canHandleClickEvents to set
     */
    public final void setCanHandleClickEvents(final Boolean canHandleClickEvents) {
	this.canHandleClickEvents = canHandleClickEvents;
    }

    /**
     * @param canHandleDrawEvents
     *            the canHandleDrawEvents to set
     */
    public final void setCanHandleDrawEvents(final Boolean canHandleDrawEvents) {
	this.canHandleDrawEvents = canHandleDrawEvents;
    }

    /**
     * @param canHandleHighlightEvents
     *            the canHandleHighlightEvents to set
     */
    public final void setCanHandleHighlightEvents(
	    final Boolean canHandleHighlightEvents) {
	this.canHandleHighlightEvents = canHandleHighlightEvents;
    }

    /**
     * @param canHandleZoomEvents
     *            the canHandleZoomEvents to set
     */
    public final void setCanHandleZoomEvents(final Boolean canHandleZoomEvents) {
	this.canHandleZoomEvents = canHandleZoomEvents;
    }

    /**
     * Sets the click callback.
     * 
     * @param graph
     *            the graph to set.
     * @param opts
     *            the options to use.
     */
    private native void setClickCallback(final AbstractDygraph graph,
	    final JavaScriptObject opts)/*-{
        opts["clickCallback"]=function(e, x, pts){
        graph.@org.danvk.AbstractDygraph::onClick(Lorg/danvk/MouseEvent;DLorg/danvk/Points;)(e,x,pts);
        }
    }-*/;

    /**
     * Sets the draw callback.
     * 
     * @param graph
     *            the graph to set.
     * @param opts
     *            the options to use.
     */
    private native void setDrawCallback(final AbstractDygraph graph,
	    final JavaScriptObject opts)/*-{
        opts["drawCallback"]=function(dygraph, isInitial){
        var min = dygraph.xAxisRange()[0];
        var max = dygraph.xAxisRange()[1];
        graph.@org.danvk.AbstractDygraph::onDraw(DDZ)(min,max,isInitial);
        }
    }-*/;

    /**
     * Sets the zoom callback.
     * 
     * @param graph
     *            the graph to set.
     * @param opts
     *            the options to use.
     */
    private native void setHighlightCallback(final AbstractDygraph graph,
	    final JavaScriptObject opts)/*-{
        opts["highlightCallback"]=function(e, x, pts){
        graph.@org.danvk.AbstractDygraph::onHighlight(Lorg/danvk/MouseEvent;DLorg/danvk/Points;)(e,x,pts);
        }
        opts["unhighlightCallback"]=function(e){
        graph.@org.danvk.AbstractDygraph::onUnhighlight(Lorg/danvk/MouseEvent;)(e);
        }
    }-*/;

    /**
     * Sets the yValueFormatter.
     * 
     * @param graph
     *            the graph to set.
     * @param opts
     *            the options to set.
     */
    private native void setYValueFormatter(final AbstractDygraph graph,
	    final JavaScriptObject opts)/*-{
        opts["yValueFormatter"]=function(x){
        return graph.@org.danvk.AbstractDygraph::formatYValue(D)(x);
        }
    }-*/;

    /**
     * Sets the Y value formatter. If the dygraph hasn't been attached we will
     * assume the user wants to auto inject the formatter code for this event.
     * 
     * @param formatter
     *            the y value formatter to set.
     */
    public final void setYValueFormatter(final YValueFormatter formatter) {
	if (formatter != null) {
	    yValueFormatter = formatter;
	    if (!attached) {
		setCanFormatYValue(true);
	    }
	}
    }

    /**
     * Sets the zoom callback.
     * 
     * @param graph
     *            the graph to set.
     * @param opts
     *            the options to use.
     */
    private native void setZoomCallback(final AbstractDygraph graph,
	    final JavaScriptObject opts)/*-{
        opts["zoomCallback"]=function(min, max){
        graph.@org.danvk.AbstractDygraph::onZoom(DD)(min,max);
        }
    }-*/;

}
