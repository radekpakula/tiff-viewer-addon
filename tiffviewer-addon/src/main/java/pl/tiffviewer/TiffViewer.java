package pl.tiffviewer;

import java.io.File;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.FileResource;

import pl.tiffviewer.client.TiffViewerServerRpc;
import pl.tiffviewer.client.TiffViewerState;

@JavaScript({ "tiff.viewer.js", "tiff.min.js", "dragscroll.js"})
public class TiffViewer extends com.vaadin.ui.AbstractComponent {
	private static final long serialVersionUID = 1L;
	private TiffViewerServerRpc rpc = new TiffViewerServerRpc() {
		private static final long serialVersionUID = 1L;
	};

	public TiffViewer(File file) {
		registerRpc(rpc);
		loadFile(file);
		configure();
	}

	private void configure() {
        setAngleButtonVisible(true);
        setDownloadBtnVisible(true);
	}

	public TiffViewer(FileResource file) {
		registerRpc(rpc);
		setResource("resourceFile", file);
		configure();
	}

	public void setFile(File file) {
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

	public void setPage(int i) {
		getState().page = i;
	}

	public void setPreviousPageCaption(String htmlCaption) {
		getState().previousPageCaption = htmlCaption;
	}

	public void setNextPageCaption(String htmlCaption) {
		getState().nextPageCaption = htmlCaption;
	}

	public void setPageCaption(String htmlCaption) {
		getState().pageCaption = htmlCaption;
	}

	public void setToPageCaption(String htmlCaption) {
		getState().toPageCaption = htmlCaption;
	}

	public void setIncreaseButtonCaption(String htmlCaption) {
		getState().incraseCaption = htmlCaption;
	}

	public void setDecreaseButtonCaption(String htmlCaption) {
		getState().decreaseCaption = htmlCaption;
	}

	public void setNextAngleButtonCaption(String htmlCaption) {
		getState().nextAngle = htmlCaption;
	}

	public void setBackAngleButtonCaption(String htmlCaption) {
		getState().backAngle = htmlCaption;
	}

	public void setDownloadButtonCaption(String htmlCaption) {
		getState().downloadCaption = htmlCaption;
	}

	public void setAngleButtonVisible(boolean visible) {
		getState().angleVisible = visible;
	}

	public void setDownloadBtnVisible(boolean visible) {
		getState().downloadVisible = visible;
	}
}
