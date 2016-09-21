package pl.tiffviewer.widgetset.client.ui;


public class TiffViewerState extends com.vaadin.shared.AbstractComponentState {

	private static final long serialVersionUID = 1L;
	public String resourceFile;
	public int page=0;
	public String previousPageCaption;
	public String nextPageCaption;
	public String pageCaption;
	public String toPageCaption;
	public String incraseCaption;
	public String decreaseCaption;

}