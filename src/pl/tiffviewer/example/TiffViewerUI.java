package pl.tiffviewer.example;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import pl.tiffviewer.TiffViewer;

@SuppressWarnings("serial")
@Theme("tiffviewer")
@PreserveOnRefresh
public class TiffViewerUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TiffViewerUI.class, widgetset = "pl.tiffviewer.widgetset.TiffViewerWidgetset")
	public static class Servlet extends VaadinServlet {
	}
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		File file = new File("sample.tif");
		TiffViewer c = new TiffViewer(file);
		c.setWidth(1000,Unit.PIXELS);
		c.setHeight(800,Unit.PIXELS);
		layout.addComponent(c);
	}
}