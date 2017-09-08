package pl.tiffviewer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.FileResource;

import pl.tiffviewer.client.TiffViewerServerRpc;
import pl.tiffviewer.client.TiffViewerState;
import pl.tiffviewer.listener.AngleChangeListener;
import pl.tiffviewer.listener.DownloadTiffListener;
import pl.tiffviewer.listener.PageChangeListener;

@JavaScript({ "tiff.viewer.js", "tiff.min.js", "dragscroll.js","print.js"})
public class TiffViewer extends com.vaadin.ui.AbstractComponent {
	private List<PageChangeListener> pageChangeListener = new ArrayList<>();
	private List<AngleChangeListener> angleChangeListener = new ArrayList<>();
	private List<DownloadTiffListener> downloadListener = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private TiffViewerServerRpc rpc = new TiffViewerServerRpc() {
		private static final long serialVersionUID = 1L;
		@Override
		public void angleChange(Integer angle) {
			angleChangeListener.forEach(e->{
				getState().angle=angle;
				e.angleChange(angle);
			});
		}
		@Override
		public void pageChange(Integer page) {
			pageChangeListener.forEach(e->{
				getState().page=page;
				e.pageChange(page);
			});
		}
		@Override
		public void download() {
			downloadListener.forEach(e->{
				e.download();
			});
		}
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
	public void addPageChangeListere(PageChangeListener listener){
		pageChangeListener.add(listener);
	}
	public void removePageChangeListere(PageChangeListener listener){
		pageChangeListener.remove(listener);
	}
	public void addAngleChangeListere(AngleChangeListener listener){
		angleChangeListener.add(listener);
	}
	public void removeAngleChangeListere(AngleChangeListener listener){
		angleChangeListener.remove(listener);
	}
	public void addDownloadTiffListere(DownloadTiffListener listener){
		downloadListener.add(listener);
	}
	public void removeDownloadTiffListere(DownloadTiffListener listener){
		downloadListener.remove(listener);
	}
	public int getPage() {
		return getState().page;
	}
}
