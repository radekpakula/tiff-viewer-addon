package pl.tiffviewer.demo;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import pl.tiffviewer.TiffViewer;
import pl.tiffviewer.listener.AngleChangeListener;
import pl.tiffviewer.listener.DownloadTiffListener;
import pl.tiffviewer.listener.PageChangeListener;

@Theme("demo")
@Title("TiffViewer Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final TiffViewer component = new TiffViewer(new File("/home/radek/Pobrane/204.tif"));
        component.addPageChangeListener(new PageChangeListener() {
			@Override
			public void pageChange(Integer page) {
				System.out.println("PAGE CHANGE: "+page+" "+component.getPage());
			}
		});
        component.addAngleChangeListener(new AngleChangeListener() {
			@Override
			public void angleChange(Integer value) {
				System.out.println("ANGLE CHANGE: "+value+" "+component.getPage());
				component.setPage(3);
			}
		});
        component.addDownloadTiffListener(new DownloadTiffListener() {
			@Override
			public void download() {
				System.out.println("TIFF WAS DOWNLOAD");
			}
		});
        component.setSizeFull();
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(component);
        layout.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
}
