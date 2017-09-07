function TiffViewer() {
	this.canvas = null;
	this.canvasDiv = null;
	this.root = null;
	this.counter = null;
	this.input = null;
	this.selectSize = null;
	this.bar = null;
	this.nextBtn = null;
	this.prevBtn = null;
	this.increaseBtn = null;
	this.decreaseBtn = null;
	this.selectSize = null;
	this.fileName = null;
	this.tiffFile = null;
	this.tiffPage = null;
	this.pageNumber = 0;
	this.pageCount = null;
	this.currentPage = 0;
	this.work = false;
	this.addAngleBtn = null;
	this.subAngleBtn = null;
	this.downloadBtn = null;
	this.angle = 0;
}
TiffViewer.prototype.showTiffPage = function(pageNumber) {
	var self = this;
	if (this.tiffFile == null) {
		this.counter.innerHTML = 0;
		this.input.value = 0;
		return;
	}

	if (pageNumber <= this.pageCount && pageNumber > 0) {
		this.currentPage = pageNumber;
		this.counter.innerHTML = this.pageCount;
		this.input.value = pageNumber;
		this.tiffFile.setDirectory(pageNumber-1);
		this.updateSize();
		window.dragscroll.reset();
		this.work = false

	}
	;
};
TiffViewer.prototype.downloadIt = function() {
	download(this.fileName);
}
TiffViewer.prototype.checkInput = function(value) {
	if (isNaN(value)) {
		this.input.value = 1;
	} else if (value < 1) {
		this.input.value = 1;
	} else if (value > this.pageCount) {
		this.input.value = this.pageCount;
	}
	this.showTiffPage(this.input.value);
};
TiffViewer.prototype.updateSize = function() {
	if (this.work) {
		return;
	}
	var value = parseFloat(this.selectSize.value);
	var width =0;
	if(value==0){
		width=this.root.offsetWidth-30+'px';
	}else{
		width=(parseInt(this.canvas.width)*value)+'px';
	}
	this.canvasDiv.removeChild(this.canvas);
	this.canvas = this.tiffFile.toCanvas();
	this.canvas.style.width = width;
	
	var deg = this.angle;
	this.canvas.style.webkitTransform = 'rotate('+deg+'deg)'; 
	this.canvas.style.mozTransform    = 'rotate('+deg+'deg)'; 
	this.canvas.style.msTransform     = 'rotate('+deg+'deg)'; 
	this.canvas.style.oTransform      = 'rotate('+deg+'deg)'; 
	this.canvas.style.transform       = 'rotate('+deg+'deg)'; 
    
	this.canvasDiv.appendChild(this.canvas);
	this.canvasDiv.style.height = this.root.offsetHeight - this.bar.offsetHeight + 'px';
};
TiffViewer.prototype.init = function() {
	var self = this;
	this.nextBtn.onclick = function() {
		self.showTiffPage(self.currentPage + 1)
	};
	this.prevBtn.onclick = function() {
		self.showTiffPage(self.currentPage - 1)
	};
	this.increaseBtn.onclick = function() {
		var list = self.selectSize;
		list.value = list.value == 0 ? 1 : list.value;
		if (list.selectedIndex < 15) {
			list.value = list.options[list.selectedIndex + 1].value;
			self.updateSize();
		}
	};
	this.decreaseBtn.onclick = function() {
		var list = self.selectSize;
		list.value = list.value == 0 ? 1 : list.value;
		if (list.selectedIndex > 1) {
			list.value = list.options[list.selectedIndex - 1].value;
			self.updateSize();
		}
	};
	this.input.onkeypress = function(e) {
		if (!e)
			e = window.event;
		var keyCode = e.keyCode || e.which;
		if (keyCode == '13') {
			var value = parseInt(e.target.value);
			self.showTiffPage(value);
			return false;
		}
	};
	this.input.addEventListener('blur', function(e) {
		var value = parseInt(e.target.value);
		self.showTiffPage(value);
	});
	this.selectSize.onchange = function() {
		self.updateSize();
	};

	this.addAngleBtn.onclick = function() {
		self.angle = self.angle + 90;
		if (self.angle == 360 || self.angle == -360) {
			self.angle = 0;
		}
		self.updateSize();
	};
	this.subAngleBtn.onclick = function() {
		self.angle = self.angle - 90;
		if (self.angle == 360 || self.angle == -360) {
			self.angle = 0;
		}
		self.updateSize();
	};
	this.downloadBtn.onclick=function(){
		self.downloadIt();
	}
};