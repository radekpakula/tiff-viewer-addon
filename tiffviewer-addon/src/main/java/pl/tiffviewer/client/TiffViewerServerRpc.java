package pl.tiffviewer.client;

import com.vaadin.shared.communication.ServerRpc;

public interface TiffViewerServerRpc extends ServerRpc {

	public void angleChange(Integer angle);
	public void pageChange(Integer page);
	public void download();
}
