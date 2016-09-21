package pl.tiffviewer;

import java.io.File;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.FileResource;
import com.vaadin.ui.AbstractComponent;

import pl.tiffviewer.widgetset.client.ui.TiffViewerServerRpc;
import pl.tiffviewer.widgetset.client.ui.TiffViewerState;

@JavaScript({"tiff.min.js","dragscroll.js"})
public class TiffViewer extends AbstractComponent{

	private static final long serialVersionUID = 1L;
	private TiffViewerServerRpc rpc = new TiffViewerServerRpc() {
		private static final long serialVersionUID = 1L;
	};
	
	public TiffViewer(File file) {
		registerRpc(rpc);
		loadFile(file);
	}
	public TiffViewer(FileResource file) {
		registerRpc(rpc);
		setResource("resourceFile", file);
	}
	public void setFile(File file){
		loadFile(file);
	}
	private void loadFile(File file) {
		FileResource resource = new FileResource(file);
		setResource("resourceFile", resource);
	}
	
	@Override
	public TiffViewerState getState() {
		return (TiffViewerState) super.getState();
	}
	
	public void setPage(int i){
		getState().page=i;
	}
	public void setPreviousPageCaption(String htmlCaption){
		getState().previousPageCaption=htmlCaption;
	}
	public void setNextPageCaption(String htmlCaption){
		getState().nextPageCaption=htmlCaption;
	}
	public void setPageCaption(String htmlCaption) {
		getState().pageCaption=htmlCaption;
	}
	public void setToPageCaption(String htmlCaption) {
		getState().toPageCaption=htmlCaption;
	}
	public void setIncreaseButtonCaption(String htmlCaption) {
		getState().incraseCaption=htmlCaption;
	}
	public void setDecreaseButtonCaption(String htmlCaption) {
		getState().decreaseCaption=htmlCaption;
	}
}
