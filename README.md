# TiffViewer Add-on for Vaadin 7

TiffViewer is a UI component add-on for Vaadin 7.

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/tiffviewer
## Release notes

### Version 1.0-SNAPSHOT
- basic methods

### Version 1.1.1-SNAPSHOT
- add angle rotation
- add download button

### Version 1.1.2-SNAPSHOT
- add page change listener
- add angle change listener
- add download click listener

## Getting started
addComponent(new TiffViewer(new File(...tiffFile)));

## Additional method
- setPage(int i)
- setPreviousPageCaption(String htmlCaption)
- setNextPageCaption(String htmlCaption)
- setPageCaption(String htmlCaption)
- setToPageCaption(String htmlCaption)
- setIncreaseButtonCaption(String htmlCaption)
- setDecreaseButtonCaption(String htmlCaption)
- setNextAngleButtonCaption(String htmlCaption)
- setBackAngleButtonCaption(String htmlCaption)	
- setPrintButtonCaption(String htmlCaption)
- setDownloadButtonCaption(String htmlCaption) 
- setAngleButtonVisible(boolean visible)
- setDownloadBtnVisible(boolean visible)
- addPageChangeListere(PageChangeListener listener)
- removePageChangeListere(PageChangeListener listener)
- addAngleChangeListere(AngleChangeListener listener)
- removeAngleChangeListere(AngleChangeListener listener)
- addDownloadTiffListere(DownloadTiffListener listener)
- removeDownloadTiffListere(DownloadTiffListener listener)