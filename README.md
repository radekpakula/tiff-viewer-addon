# tiff-viewer-addon
	-Addon works on client side.
	-Display file only with .tiff (tif) format
    -Supports multipage
    
-based on script https://github.com/seikichi/tiff.js
-dragandscroll http://github.com/asvd/intence

# Simple usage
     TiffViewer c = new TiffViewer(file);
	 layout.addComponent(c);

# Additional method
	setPage(int i)
	setPreviousPageCaption(String htmlCaption)
	setNextPageCaption(String htmlCaption)
	setPageCaption(String htmlCaption)
	setToPageCaption(String htmlCaption)
	setIncreaseButtonCaption(String htmlCaption)
	setDecreaseButtonCaption(String htmlCaption)