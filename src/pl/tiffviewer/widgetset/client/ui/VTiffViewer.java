package pl.tiffviewer.widgetset.client.ui;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.HTML;

public class VTiffViewer extends HTML {
	private static final String CLASSNAME = "tiff-viewer";
	private Element root;
	private Element canvas;
	private Element buttonBar;
	private Element counter;
	private Element previousBtn;
	private Element nextBtn;
	private Element counterBox;
	private Element sizeBox;
	private String fileResource;
	private Element inputCounter;
	private Element pageText;
	private Element toText;
	private SelectElement selectList;
	private DivElement increaseBtn;
	private DivElement decreaseBtn;
	
	public VTiffViewer() {
		root = Document.get().createDivElement();
		root.setClassName(CLASSNAME);
		root.getStyle().setVisibility(Visibility.VISIBLE); // required for FF to show in popup windows repeatedly
		canvas = Document.get().createDivElement();
		canvas.setClassName(CLASSNAME+"-canvas dragscroll");
		canvas.getStyle().setOverflow(Overflow.AUTO);
		canvas.getStyle().setProperty("width","100%");
		canvas.getStyle().setProperty("height","100%");
		buttonBar = Document.get().createDivElement();
		buttonBar.setClassName(CLASSNAME+"-button-bar");
		
		Element groupSpan = Document.get().createSpanElement();
		groupSpan.setClassName(CLASSNAME+"-navigation");
		
		previousBtn = Document.get().createDivElement();
		previousBtn.setInnerHTML("Previous");
		previousBtn.addClassName("v-button v-widget v-button-previous");
		
		nextBtn = Document.get().createDivElement();
		nextBtn.setInnerHTML("Next");
		nextBtn.addClassName("v-button v-widget v-button-next");

		counterBox = Document.get().createDivElement(); 
		counterBox.setClassName(CLASSNAME+"-counter-box");
		
		pageText = Document.get().createSpanElement();
		pageText.setClassName("page");
		pageText.setInnerText("Page: ");
		
		toText = Document.get().createSpanElement();
		toText.setClassName("to-page");
		toText.setInnerText(" from ");
		
		inputCounter = Document.get().createTextInputElement();
		inputCounter.setClassName("v-textfield v-widget input-counter");
		counter = Document.get().createSpanElement();
		counter.setClassName("counter");
		
		sizeBox = Document.get().createDivElement(); 
		sizeBox.setClassName(CLASSNAME+"-size-box");
		 
		selectList = Document.get().createSelectElement();
		String[][] vals = new String[][]{
			{"0"," Auto "},{"0.25"," 25%"},{"0.5"," 50%"},{"0.75"," 75%"},
			{"1"," 100%"},{"1.25"," 125%"},{"1.5"," 150%"},{"1.75"," 175%"},
			{"2"," 200%"},{"2.25"," 225%"},{"2.5"," 250%"}};
		for (String[] e : vals) {
			OptionElement el =Document.get().createOptionElement();
			el.setValue(e[0]);
			el.setInnerHTML(e[1]);
			selectList.appendChild(el);
		}
		increaseBtn = Document.get().createDivElement();
		increaseBtn.setInnerHTML("+");
		increaseBtn.addClassName("v-button v-widget v-button-increase");
		
		decreaseBtn = Document.get().createDivElement();
		decreaseBtn.setInnerHTML("-");
		decreaseBtn.addClassName("v-button v-widget v-button-decrease");
		
		sizeBox.appendChild(decreaseBtn);
		sizeBox.appendChild(increaseBtn);
		sizeBox.appendChild(selectList);
		
		counterBox.appendChild(pageText);
		counterBox.appendChild(inputCounter);
		counterBox.appendChild(toText);
		counterBox.appendChild(counter);
		
		groupSpan.appendChild(previousBtn);
		groupSpan.appendChild(nextBtn);
		buttonBar.appendChild(groupSpan);
		buttonBar.appendChild(counterBox);
		buttonBar.appendChild(sizeBox);
		
		
		inputCounter.setInnerText("1");
		
		root.appendChild(buttonBar);
		root.appendChild(canvas);
		setElement(root);
		initTiff(this);
	}
	public native void initTiff(VTiffViewer instance)/*-{
		$wnd.Tiff.initialize({TOTAL_MEMORY: 16777216 * 10});
		window.el=instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::canvas;
		window.root=instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::root;
		window.counter=instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::counter;
		window.input=instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::inputCounter;
		window.setPage=function(page){
			if(window.tiff==null){
				window.counter.innerHTML=0;
				window.input.value=0;
				return;
			}
			if(page<window.pageCount && page>=0){
				window.tiff.setDirectory(page);
				var canvas = window.tiff.toCanvas();
				window.canvas=canvas;
				window.el.innerHTML='';
				window.el.appendChild(canvas);
				window.counter.innerHTML=window.pageCount;
				window.currentPage=page;
				window.input.value=page+1
				$wnd.dragscroll.reset();
				window.updateSize();
			}
		};
		
		var nextBtn =instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::nextBtn;
		nextBtn.onclick=function(){window.setPage(window.currentPage+1)};
		
		var prevBtn =instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::previousBtn;
		prevBtn.onclick=function(){window.setPage(window.currentPage-1)};
		
		var increaseBtn=instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::increaseBtn;
		increaseBtn.onclick=function(){
			if(window.selectList.value==0){
				window.selectList.value='1';
			}
			if(parseFloat(window.selectList.value)==2.5){
				return;
			}
			window.selectList.value=parseFloat(window.selectList.value)+0.25;
			window.updateSize();
		};
		var decreaseBtn=instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::decreaseBtn;
		decreaseBtn.onclick=function(){
			if(window.selectList.value==0){
				window.selectList.value='1';
			}
			if(parseFloat(window.selectList.value)==0.25){
				return;
			}
			window.selectList.value=parseFloat(window.selectList.value)-0.25;
			window.updateSize();
		};
		
		window.checkInput=function(){
		    var value = parseInt(window.input.value);
  			if(isNaN(value)){
  				window.input.value=1;
  			}else if(value<1){
    			window.input.value=1;
			}else if(value>window.pageCount){
				window.input.value=window.pageCount;
			}
			window.setPage(window.input.value-1);
		};
		window.input.onkeypress = function(e){
		    if (!e) e = window.event;
		    var keyCode = e.keyCode || e.which;
		    if (keyCode == '13'){
		      window.checkInput();
		      return false;
		    }
	    };
	    
		window.input.addEventListener('blur', function() { 
			window.checkInput();
		});
		
		window.selectList=instance.@pl.tiffviewer.widgetset.client.ui.VTiffViewer::selectList;
		
		window.selectList.onchange=function(){
			window.updateSize();
		};
		
		window.updateSize=function(){
			if(window.selectList.value==0){
				window.canvas.style.width=window.root.offsetWidth-30+'px';
			}else{
				window.canvas.style.width=(parseInt(canvas.width)*parseFloat(window.selectList.value))+'px';
			}
		};
	}-*/;
	public native void loadResource(String fileName)/*-{
		if(window.fileName==null || window.fileName!=fileName){
			  window.fileName=fileName;
			  var xhr = new XMLHttpRequest();
			  xhr.open('GET', window.fileName);
			  xhr.responseType = 'arraybuffer';
			  xhr.onload = function (e) {
			    var buffer = xhr.response;
			    window.tiff = new $wnd.Tiff({buffer: buffer});
			    window.pageCount=window.tiff.countDirectory();
			    window.setPage(0);
			  };
			  xhr.send();
		  }
	}-*/;
	public void setResourceFile(String fileResource) {
		this.fileResource=fileResource;
		loadResource(fileResource);
		updatePage(0);
	}
	public void setPage(int page) {
		updatePage(page);
	}
	public native void updatePage(int page)/*-{
		window.setPage(page);
	}-*/;
	public void setPreviousButtonCaption(String caption) {
		updateInnerHtml(caption,previousBtn);
	}
	public void setNextButtonCaption(String caption) {
		updateInnerHtml(caption,nextBtn);
	}
	public void setPageCaption(String caption) {
		updateInnerHtml(caption,pageText);
	}
	public void setToPageCaption(String caption) {
		updateInnerHtml(caption,toText);
	}
	public native void updateInnerHtml(String caption, Element elem)/*-{
		if(caption!=null && caption!=''){
			elem.innerHTML=caption;
		}
	}-*/;
	public void setIncreaseButtonCaption(String caption) {
		updateInnerHtml(caption,increaseBtn);
	}
	public void setDecreaseButtonCaption(String caption) {
		updateInnerHtml(caption,decreaseBtn);
	}
}