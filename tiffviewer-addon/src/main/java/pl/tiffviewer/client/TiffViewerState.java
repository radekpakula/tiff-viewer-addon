package pl.tiffviewer.client;

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
	public String nextAngle;
	public String backAngle;
	public String downloadCaption;
	public boolean angleVisible;
	public boolean downloadVisible;
}