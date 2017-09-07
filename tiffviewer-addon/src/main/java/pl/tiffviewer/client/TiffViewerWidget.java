package pl.tiffviewer.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.HTML;

// Extend any GWT Widget
public class TiffViewerWidget extends HTML {
	private static final String CLASSNAME = "tiff-viewer";
	private Element root;
	private DivElement canvasDiv;
	private DivElement buttonBar;
	private Element counter;
	private DivElement previousBtn;
	private DivElement nextBtn;
	private DivElement counterBox;
	private DivElement sizeBox;
	private DivElement angleBox;
	private DivElement additionalBox;
	private Element inputCounter;
	private Element pageText;
	private Element toText;
	private SelectElement selectSize;
	private DivElement increaseBtn;
	private DivElement decreaseBtn;
	private DivElement nextAngleBtn;
	private DivElement backAngleBtn;
	private DivElement downloadBtn;
	private CanvasElement canvas;
	private String fileName;
	private String pageCount;
	private String inputValue;
	private String selectSizeValue;
	private JavaScriptObject jsObject;
	private boolean angleVisible;
	private boolean downloadVisible;

	public TiffViewerWidget() {
		root = Document.get().createDivElement();
		root.setClassName(CLASSNAME);
		root.getStyle().setVisibility(Visibility.VISIBLE); // required for FF to
															// show in popup
															// windows
															// repeatedly
		canvasDiv = Document.get().createDivElement();
		canvasDiv.setClassName(CLASSNAME + "-canvas dragscroll");
		canvasDiv.getStyle().setOverflow(Overflow.AUTO);
		canvasDiv.getStyle().setProperty("width", "100%");
		canvasDiv.getStyle().setProperty("height", "100%");
		canvas = Document.get().createCanvasElement();
		canvasDiv.appendChild(canvas);
		buttonBar = Document.get().createDivElement();
		buttonBar.setClassName(CLASSNAME + "-button-bar");
		Element groupSpan = Document.get().createDivElement();
		groupSpan.setClassName(CLASSNAME + "-navigation");
		previousBtn = Document.get().createDivElement();
		previousBtn.setInnerHTML("Previous");
		previousBtn.addClassName("v-button v-widget v-button-previous");
		nextBtn = Document.get().createDivElement();
		nextBtn.setInnerHTML("Next");
		nextBtn.addClassName("v-button v-widget v-button-next");
		counterBox = Document.get().createDivElement();
		counterBox.setClassName(CLASSNAME + "-counter-box");
		pageText = Document.get().createSpanElement();
		pageText.setClassName("page");
		pageText.setInnerText("Page: ");
		toText = Document.get().createSpanElement();
		toText.setClassName("to-page");
		toText.setInnerText(" from ");
		inputCounter = Document.get().createTextInputElement();
		inputCounter.setClassName("v-textfield v-widget input-counter");
		additionalBox = Document.get().createDivElement();
		additionalBox.setClassName(CLASSNAME + "-additional-box");
		;
		counter = Document.get().createSpanElement();
		counter.setClassName("counter");
		sizeBox = Document.get().createDivElement();
		sizeBox.setClassName(CLASSNAME + "-size-box");
		angleBox = Document.get().createDivElement();
		angleBox.setClassName(CLASSNAME + "-angle-box");
		selectSize = Document.get().createSelectElement();
		selectSize.setClassName(CLASSNAME + "-select-size v-widget v-select-select");
		String[][] vals = new String[][] { { "0", " Auto " }, { "0.25", " 25%" }, { "0.5", " 50%" }, { "0.75", " 75%" }, { "1", " 100%" }, { "1.25", " 125%" },
				{ "1.5", " 150%" }, { "1.75", " 175%" }, { "2", " 200%" }, { "2.25", " 225%" }, { "2.5", " 250%" }, { "3", " 300%" }, { "4", " 400%" },
				{ "5", " 500%" }, { "10", " 1000%" } };
		for (String[] e : vals) {
			OptionElement el = Document.get().createOptionElement();
			el.setValue(e[0]);
			el.setInnerHTML(e[1]);
			selectSize.appendChild(el);
		}
		increaseBtn = Document.get().createDivElement();
		increaseBtn.setInnerHTML("<span class=\"v-icon\" style=\"font-family: FontAwesome;\">&#xf00e;</span>");
		increaseBtn.addClassName("v-button v-widget v-button-increase");
		decreaseBtn = Document.get().createDivElement();
		decreaseBtn.setInnerHTML("<span class=\"v-icon\" style=\"font-family: FontAwesome;\">&#xf010;</span>");
		decreaseBtn.addClassName("v-button v-widget v-button-decrease");
		nextAngleBtn = Document.get().createDivElement();
		nextAngleBtn.setInnerHTML("<span class=\"v-icon\" style=\"font-family: FontAwesome;\">&#xf0e2;</span>");
		nextAngleBtn.addClassName("v-button v-widget v-button-angle-add");
		backAngleBtn = Document.get().createDivElement();
		backAngleBtn.setInnerHTML("<span class=\"v-icon\" style=\"font-family: FontAwesome;\">&#xf01e;</span>");
		backAngleBtn.addClassName("v-button v-widget v-button-angle-dec");
		downloadBtn = Document.get().createDivElement();
		downloadBtn.setInnerHTML("<span class=\"v-icon\" style=\"font-family: FontAwesome;\">&#xf019;</span>");
		downloadBtn.addClassName("v-button v-widget v-button-download");
		sizeBox.appendChild(decreaseBtn);
		sizeBox.appendChild(increaseBtn);
		sizeBox.appendChild(selectSize);
		counterBox.appendChild(pageText);
		counterBox.appendChild(inputCounter);
		counterBox.appendChild(toText);
		counterBox.appendChild(counter);
		groupSpan.appendChild(previousBtn);
		groupSpan.appendChild(nextBtn);
		buttonBar.appendChild(groupSpan);
		buttonBar.appendChild(counterBox);
		buttonBar.appendChild(sizeBox);
		if (angleVisible) {
			angleBox.appendChild(backAngleBtn);
			angleBox.appendChild(nextAngleBtn);
		}
		buttonBar.appendChild(angleBox);
		if (downloadVisible) {
			additionalBox.appendChild(downloadBtn);
		}
		buttonBar.appendChild(additionalBox);
		inputCounter.setInnerText("1");
		root.appendChild(buttonBar);
		root.appendChild(canvasDiv);
		setElement(root);
		initTiff(this);
	}

	public native void initTiff(TiffViewerWidget instance)/*-{
		$wnd.Tiff.initialize({TOTAL_MEMORY: 16777216 * 10});
		var tiffviewer=new $wnd.TiffViewer();
		tiffviewer.canvas = instance.@pl.tiffviewer.client.TiffViewerWidget::canvas;
		tiffviewer.canvasDiv=instance.@pl.tiffviewer.client.TiffViewerWidget::canvasDiv;
		tiffviewer.root=instance.@pl.tiffviewer.client.TiffViewerWidget::root;
		tiffviewer.counter=instance.@pl.tiffviewer.client.TiffViewerWidget::counter;
		tiffviewer.input=instance.@pl.tiffviewer.client.TiffViewerWidget::inputCounter;
		tiffviewer.selectSize=instance.@pl.tiffviewer.client.TiffViewerWidget::selectSize;
		tiffviewer.bar=instance.@pl.tiffviewer.client.TiffViewerWidget::buttonBar;
		tiffviewer.nextBtn =instance.@pl.tiffviewer.client.TiffViewerWidget::nextBtn;
		tiffviewer.prevBtn =instance.@pl.tiffviewer.client.TiffViewerWidget::previousBtn;
		tiffviewer.increaseBtn=instance.@pl.tiffviewer.client.TiffViewerWidget::increaseBtn;
		tiffviewer.decreaseBtn=instance.@pl.tiffviewer.client.TiffViewerWidget::decreaseBtn;
		tiffviewer.selectSize=instance.@pl.tiffviewer.client.TiffViewerWidget::selectSize;
		tiffviewer.addAngleBtn=instance.@pl.tiffviewer.client.TiffViewerWidget::nextAngleBtn;
		tiffviewer.subAngleBtn=instance.@pl.tiffviewer.client.TiffViewerWidget::backAngleBtn;
		tiffviewer.downloadBtn=instance.@pl.tiffviewer.client.TiffViewerWidget::downloadBtn;
		instance.@pl.tiffviewer.client.TiffViewerWidget::setJsObject(Lcom/google/gwt/core/client/JavaScriptObject;)(tiffviewer);
		tiffviewer.init();
	}-*/;

	public native void loadResource(String fileName, TiffViewerWidget instance)/*-{
		var tiffviewer = instance.@pl.tiffviewer.client.TiffViewerWidget::jsObject;
		tiffviewer.work=false;
		if((tiffviewer.fileName==null || tiffviewer.fileName!=fileName) && tiffviewer!=null){
			 tiffviewer.fileName=fileName;
			 var xhr = new XMLHttpRequest();
			 xhr.open('GET', fileName);
			 xhr.responseType = 'arraybuffer';
			 xhr.onload = function (e) {
			    var buffer = xhr.response;
			    tiffviewer.tiffFile = new $wnd.Tiff({buffer: buffer});
			    tiffviewer.pageCount=tiffviewer.tiffFile.countDirectory();
			    tiffviewer.showTiffPage(1);
			  };
			  xhr.send();
		}
	}-*/;

	public void setResourceFile(String fileName) {
		this.fileName = fileName;
		loadResource(fileName, this);
	}

	public void setPage(int page) {
		updatePage(page);
	}

	public native void updatePage(int page)/*-{
											}-*/;

	public void setPreviousButtonCaption(String caption) {
		updateInnerHtml(caption, previousBtn);
	}

	public void setNextButtonCaption(String caption) {
		updateInnerHtml(caption, nextBtn);
	}

	public void setPageCaption(String caption) {
		updateInnerHtml(caption, pageText);
	}

	public void setToPageCaption(String caption) {
		updateInnerHtml(caption, toText);
	}

	public native void updateInnerHtml(String caption, Element elem)/*-{
		if(caption!=null && caption!=''){
			elem.innerHTML=caption;
		}
	}-*/;
	public void setAdditionalVisible(boolean visible) {
		this.downloadVisible=visible;
		if(downloadVisible){
			additionalBox.removeAllChildren();
			additionalBox.appendChild(downloadBtn);
		}else{
			additionalBox.removeAllChildren();
		}
	}
	public void setNextAngleButtonCaption(String caption) {
		updateInnerHtml(caption, nextAngleBtn);
	}

	public void setBackAngleButtonCaption(String caption) {
		updateInnerHtml(caption, backAngleBtn);
	}

	public void setDownloadButtonCaption(String caption) {
		updateInnerHtml(caption, downloadBtn);
	}

	public void setAngleVisibility(boolean visible) {
		this.angleVisible=visible;
		if(angleVisible){
			angleBox.removeAllChildren();
			angleBox.appendChild(backAngleBtn);
			angleBox.appendChild(nextAngleBtn);
			
		}else{
			angleBox.removeAllChildren();
		}
	}
	public void setIncreaseButtonCaption(String caption) {
		updateInnerHtml(caption, increaseBtn);
	}

	public void setDecreaseButtonCaption(String caption) {
		updateInnerHtml(caption, decreaseBtn);
	}

	public JavaScriptObject getJsObject() {
		return jsObject;
	}

	public void setJsObject(JavaScriptObject jsObject) {
		this.jsObject = jsObject;
	}
}