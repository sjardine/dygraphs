package org.danvk.client;

import java.util.Date;

import org.danvk.CsvDygraph;
import org.danvk.DygraphClickHandler;
import org.danvk.DygraphHighlightHandler;
import org.danvk.DygraphOptions;
import org.danvk.Dygraphs;
import org.danvk.MouseEvent;
import org.danvk.Point;
import org.danvk.Points;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DygraphsGwtTest implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Install the dygraphs code.
		Dygraphs.install();

		// Create the graph.
		DygraphOptions options = new DygraphOptions();
		options.setWidth(800);
		options.setHeight(320);
		options.setRollPeriod(14);
		options.setShowRoller(true);
		options.setCustomBars(true);
		options.setyAxisLabelWidth(25);
		options.setColors(new String[] { "red", "orange" });
		options.setIncludeZero(true);

		CsvDygraph graph = new CsvDygraph("/ny-vs-sf.txt", options);
		graph.setCanHandleClickEvents(true);
		graph.addClickHandler(new DygraphClickHandler() {
			@Override
			public void onClick(MouseEvent event, Date date, Points points) {
				String msg = "onClick: [ MouseEvent: [x = " + event.getX()
						+ ", y = " + event.getY() + "], Date : " + date
						+ ", Points [";
				for (int index = 0; index < points.length(); index++) {
					Point point = points.get(index);
					msg += "Point: [name: " + point.getName()
							+ ". yAxisValue: " + point.getYValue() + "], ";
				}
				msg = msg.substring(0, msg.lastIndexOf(", "));
				msg += "] ]";
				System.out.println(msg);
			}
		});
		graph.setCanHandleHighlightEvents(true);
		graph.addHighlightHandler(new DygraphHighlightHandler() {
			@Override
			public void onHighlight(MouseEvent event, Date date, Points points) {
				String msg = "onHighlight: [ MouseEvent: [x = " + event.getX()
						+ ", y = " + event.getY() + "], Date : " + date
						+ ", Points [";
				for (int index = 0; index < points.length(); index++) {
					Point point = points.get(index);
					msg += "Point: [name: " + point.getName()
							+ ". yAxisValue: " + point.getYValue() + "], ";
				}
				msg = msg.substring(0, msg.lastIndexOf(", "));
				msg += "] ]";
				System.out.println(msg);
			}

			@Override
			public void onUnhighlight(MouseEvent event) {
				String msg = "onUnhighlight: [ MouseEvent: [x = "
						+ event.getX() + ", y = " + event.getY() + "]";
				System.out.println(msg);
			}
		});

		// Add it to the root panel.
		RootPanel.get().add(graph);
	}

}
