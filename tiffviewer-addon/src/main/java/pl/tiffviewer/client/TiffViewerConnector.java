package pl.tiffviewer.client;

import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

import pl.tiffviewer.TiffViewer;

@Connect(TiffViewer.class)
public class TiffViewerConnector extends AbstractComponentConnector {

	private static final long serialVersionUID = 1L;
	TiffViewerServerRpc rpc = RpcProxy.create(TiffViewerServerRpc.class, this);
    public TiffViewerConnector() {
        registerRpc(TiffViewerClientRpc.class, new TiffViewerClientRpc() {
			private static final long serialVersionUID = 1L;

        });
    }

    @Override
    public TiffViewerWidget getWidget() {
        return (TiffViewerWidget) super.getWidget();
    }

    @Override
    public TiffViewerState getState() {
        return (TiffViewerState) super.getState();
    }
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
    }
    @OnStateChange("resourceFile")
	void updateResourceFile() {
	    getWidget().setResourceFile(getResourceUrl("resourceFile"));
	}
	@OnStateChange("page")
	void showPage() {
		getWidget().setPage(getState().page);
	}
	@OnStateChange("previousPageCaption")
	void setPreviousButtonCaption() {
		getWidget().setPreviousButtonCaption(getState().previousPageCaption);
	}
	@OnStateChange("nextPageCaption")
	void setNextButtonCaption() {
		getWidget().setNextButtonCaption(getState().nextPageCaption);
	}
	@OnStateChange("pageCaption")
	void setPageCaption() {
		getWidget().setPageCaption(getState().pageCaption);
	}
	@OnStateChange("toPageCaption")
	void setToPageCaption() {
		getWidget().setToPageCaption(getState().toPageCaption);
	}
	@OnStateChange("incraseCaption")
	void setIncreaseButtonCaption() {
		getWidget().setIncreaseButtonCaption(getState().incraseCaption);
	}
	@OnStateChange("decreaseCaption")
	void setDecreaseButtonCaption() {
		getWidget().setDecreaseButtonCaption(getState().decreaseCaption);
	}
	@OnStateChange("nextAngle")
	void setNextAngleButtonCaption() {
		getWidget().setNextAngleButtonCaption(getState().nextAngle);
	}
	@OnStateChange("backAngle")
	void setBackAngleButtonCaption() {
		getWidget().setBackAngleButtonCaption(getState().backAngle);
	}
	@OnStateChange("downloadCaption")
	void setDownloadButtonCaption() {
		getWidget().setDownloadButtonCaption(getState().downloadCaption);
	}
	@OnStateChange("angleVisible")
	void setAngleVisible() {
		getWidget().setAngleVisibility(getState().angleVisible);
	}
	@OnStateChange("downloadVisible")
	void setDownloadVisible() {
		getWidget().setAdditionalVisible(getState().downloadVisible);
	}
}
