
public class FACTORYMETHOD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentFactory wordFactory = new worddocumentfactory();
        Document word = wordFactory.createDocument();
        word.open();
        DocumentFactory pdfFactory = new pdfdocumentfactory();
        Document pdf = pdfFactory.createDocument();
        pdf.open();
        DocumentFactory excelFactory = new exceldocumentfactory();
        Document excel = excelFactory.createDocument();
        excel.open();
	}

}
