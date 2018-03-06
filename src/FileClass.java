
public abstract class FileClass {
	//abstract class serving as a template for SourceFile and DestinationFile
	protected String fileName = new String(); //used to identify the file

	protected abstract String getFileName(); //unimplemented

	protected abstract void setFileName(String s); //unimplemented
	
}
