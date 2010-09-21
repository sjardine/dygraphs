/**
 * $Id: DygraphOptions.java 21 2010-09-07 15:29:38Z steven.jardine $ 
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

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

/**
 * The options class for the dygraph.
 * 
 * We do not send the height and width to the dygraph, rather, we set the height
 * and width of the div element that contains the dygraph. The dygraph uses the
 * div elements size to create the graph.
 * 
 * @version $Rev: 21 $
 * @author Steven Jardine
 */
public class DygraphOptions {

    private Integer height;

    private AssociativeArray jsOptions;

    private Integer width;

    /**
     * Default constructor.
     */
    public DygraphOptions() {
	super();
	jsOptions = AssociativeArray.create();
    }

    /**
     * @return the height
     */
    public final Integer getHeight() {
	return height;
    }

    /**
     * @return the JavaScriptObject options object.
     */
    public final JavaScriptObject getJsObject() {
	return jsOptions;
    };

    /**
     * @return the width
     */
    public final Integer getWidth() {
	return width;
    };

    /**
     * Sets a string value based on a given key.
     * 
     * @param key
     *            the key to use.
     * @param value
     *            the value to set.
     */
    public final void set(final String key, final boolean value) {
	jsOptions.set(key, value);
    };

    /**
     * Sets a int value based on a given key.
     * 
     * @param key
     *            the key to use.
     * @param value
     *            the value to set.
     */
    public final void set(final String key, final int value) {
	jsOptions.set(key, value);
    }

    /**
     * Sets a string value based on a given key.
     * 
     * @param key
     *            the key to use.
     * @param value
     *            the value to set.
     */
    public final void set(final String key, final String value) {
	jsOptions.set(key, value);
    }

    /**
     * Sets a string value based on a given key.
     * 
     * @param <T>
     *            the type of the value.
     * @param key
     *            the key to use.
     * @param value
     *            the value to set.
     */
    public final <T> void set(final String key, final T value) {
	jsOptions.set(key, value);
    };

    /**
     * @param axisLabelFontSize
     *            the axisLabelFontSize to set
     */
    public final void setAxisLabelFontSize(final int axisLabelFontSize) {
	jsOptions.set("axisLabelFontSize", axisLabelFontSize);
    }

    /**
     * @param colors
     *            the colors to set
     */
    private void setColors(final JavaScriptObject colors) {
	jsOptions.set("colors", colors);
    }

    /**
     * @param colors
     *            the colors to set
     */
    public final void setColors(final String... colors) {
	setColors(ArrayUtils.toJsArray(colors));
    };

    /**
     * @param colorSaturation
     *            the colorSaturation to set
     */
    public final void setColorSaturation(final float colorSaturation) {
	jsOptions.set("colorSaturation", colorSaturation);
    };

    /**
     * @param colorValue
     *            the colorValue to set
     */
    public final void setColorValue(final float colorValue) {
	jsOptions.set("colorValue", colorValue);
    }

    /**
     * @param customBars
     *            the customBars to set
     */
    public final void setCustomBars(final boolean customBars) {
	jsOptions.set("customBars", customBars);
    };

    /**
     * @param startDate
     *            the startDate to set.
     * @param endDate
     *            the endDate to set.
     */
    public final void setDateWindow(final Date startDate, final Date endDate) {
	setDateWindow(ArrayUtils.toJsArray(new Long[] { startDate.getTime(),
		endDate.getTime() }));
    };

    /**
     * @param dateWindow
     *            the dateWindow to set.
     */
    private void setDateWindow(final JavaScriptObject dateWindow) {
	jsOptions.set("dateWindow", dateWindow);
    };

    /**
     * @param drawPoints
     *            the drawPoints to set
     */
    public final void setDrawPoints(final boolean drawPoints) {
	jsOptions.set("drawPoints", drawPoints);
    };

    /**
     * @param errorBars
     *            the errorBars to set
     */
    public final void setErrorBars(final boolean errorBars) {
	jsOptions.set("errorBars", errorBars);
    };

    /**
     * @param fillGraph
     *            the fillGraph to set
     */
    public final void setFillGraph(final boolean fillGraph) {
	jsOptions.set("fillGraph", fillGraph);
    };

    /**
     * @param fractions
     *            the fractions to set
     */
    public final void setFractions(final boolean fractions) {
	jsOptions.set("fractions", fractions);
    };

    /**
     * @param gridLineColor
     *            the gridLineColor to set
     */
    public final void setGridLineColor(final String gridLineColor) {
	jsOptions.set("gridLineColor", gridLineColor);
    }

    /**
     * @param height
     *            the height to set
     */
    public final void setHeight(final int height) {
	this.height = height;
    }

    /**
     * @param highlightCircleSize
     *            the highlightCircleSize to set
     */
    public final void setHighlightCircleSize(final int highlightCircleSize) {
	jsOptions.set("highlightCircleSize", highlightCircleSize);
    };

    /**
     * @param includeZero
     *            the includeZero to set
     */
    public final void setIncludeZero(final boolean includeZero) {
	jsOptions.set("includeZero", includeZero);
    };

    /**
     * @param labelsDiv
     *            the labelsDiv to set
     */
    public final void setLabelsDiv(final String labelsDiv) {
	Element element = DOM.getElementById(labelsDiv);
	jsOptions.set("labelsDiv", element != null ? element : labelsDiv);
    };

    /**
     * @param labelsDivStyles
     *            the labelsDivStyles to set
     */
    public final void setLabelsDivStyles(final AssociativeArray labelsDivStyles) {
	jsOptions.set("labelsDivStyles", labelsDivStyles);
    };

    /**
     * @param labelsDivWidth
     *            the labelsDivWidth to set
     */
    public final void setLabelsDivWidth(final int labelsDivWidth) {
	jsOptions.set("labelsDivWidth", labelsDivWidth);
    };

    /**
     * @param labelsKMB
     *            the labelsKMB to set
     */
    public final void setLabelsKMB(final boolean labelsKMB) {
	jsOptions.set("labelsKMB", labelsKMB);
    };

    /**
     * @param labelsKMG2
     *            the labelsKMG2 to set
     */
    public final void setLabelsKMG2(final boolean labelsKMG2) {
	jsOptions.set("labelsKMG2", labelsKMG2);
    };

    /**
     * @param labelsSeparateLines
     *            the labelsSeparateLines to set
     */
    public final void setLabelsSeparateLines(final boolean labelsSeparateLines) {
	jsOptions.set("labelsSeparateLines", labelsSeparateLines);
    };

    /**
     * @param pixelsPerXLabel
     *            the pixelsPerXLabel to set
     */
    public final void setPixelsPerXLabel(final int pixelsPerXLabel) {
	jsOptions.set("pixelsPerXLabel", pixelsPerXLabel);
    };

    /**
     * @param pixelsPerYLabel
     *            the pixelsPerYLabel to set
     */
    public final void setPixelsPerYLabel(final int pixelsPerYLabel) {
	jsOptions.set("pixelsPerYLabel", pixelsPerYLabel);
    };

    /**
     * @param pointSize
     *            the pointSize to set
     */
    public final void setPointSize(final int pointSize) {
	jsOptions.set("pointSize", pointSize);
    };

    /**
     * @param rightGap
     *            the rightGap to set
     */
    public final void setRightGap(final int rightGap) {
	jsOptions.set("rightGap", rightGap);
    };

    /**
     * @param rollPeriod
     *            the rollPeriod to set
     */
    public final void setRollPeriod(final int rollPeriod) {
	jsOptions.set("rollPeriod", rollPeriod);
    };

    /**
     * @param showRoller
     *            the showRoller to set
     */
    public final void setShowRoller(final boolean showRoller) {
	jsOptions.set("showRoller", showRoller);
    };

    /**
     * @param sigma
     *            the sigma to set
     */
    public final void setSigma(final int sigma) {
	jsOptions.set("sigma", sigma);
    };

    /**
     * @param strokeWidth
     *            the strokWidth to set
     */
    public final void setStrokeWidth(final int strokeWidth) {
	jsOptions.set("strokeWidth", strokeWidth);
    };

    /**
     * @param minValue
     *            the minValue to set.
     * @param maxValue
     *            the maxValue to set.
     */
    public final void setValueRange(final int minValue, final int maxValue) {
	setValueRange(ArrayUtils.toJsArray(new int[] { minValue, maxValue }));
    }

    /**
     * @param valueRange
     *            the JavaScriptObject array value range.
     */
    private void setValueRange(final JavaScriptObject valueRange) {
	jsOptions.set("valueRange", valueRange);
    };

    /**
     * @param visibility
     *            the visibility to set
     */
    public final void setVisibility(final boolean... visibility) {
	setVisibility(ArrayUtils.toJsArray(visibility));
    }

    /**
     * @param visibility
     *            the visibility to set
     */
    private void setVisibility(final JavaScriptObject visibility) {
	jsOptions.set("visibility", visibility);
    };

    /**
     * @param width
     *            the width to set
     */
    public final void setWidth(final int width) {
	this.width = width;
    };

    /**
     * @param wilsonInterval
     *            the wilsonInterval to set
     */
    public final void setWilsonInterval(final boolean wilsonInterval) {
	jsOptions.set("wilsonInterval", wilsonInterval);
    };

    /**
     * @param xAxisLabelWidth
     *            the xAxisLabelWidth to set
     */
    public final void setxAxisLabelWidth(final int xAxisLabelWidth) {
	jsOptions.set("xAxisLabelWidth", xAxisLabelWidth);
    };

    /**
     * @param yAxisLabelWidth
     *            the yAxisLabelWidth to set
     */
    public final void setyAxisLabelWidth(final int yAxisLabelWidth) {
	jsOptions.set("yAxisLabelWidth", yAxisLabelWidth);
    };

}
