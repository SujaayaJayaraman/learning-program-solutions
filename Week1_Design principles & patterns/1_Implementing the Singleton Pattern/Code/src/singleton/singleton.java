package singleton;

public class singleton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger1 = Logger.getInstance();
        logger1.log("This is the first log message.");
        Logger logger2 = Logger.getInstance();
        logger2.log("This is the second log message.");
        if (logger1 == logger2) {
            System.out.println("Same Logger instance used.");
        } else {
            System.out.println("Different Logger instances!");
        }
	}
}
