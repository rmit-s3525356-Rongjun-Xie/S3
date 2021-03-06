package company.client;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import company.S3Const;

public abstract class S3Menu {
	protected Scanner scan = new Scanner(System.in);
	protected S3Application app;
	
	public S3Menu(S3Application application) {
		app = application;
	}
	
	protected double fetchDoubleFromInput(String initMsg, String errMsg) {
		double value = -1;
		Boolean flag = true;
		
		do {
			try {
				System.out.println(initMsg);
				value = scan.nextDouble();
				flag = false;
			} catch (Exception e) {
				if (errMsg != null) {
					System.out.println(errMsg);
				}
			}
		} while (flag);
		return value;
	}
	
	protected String fetchStringFromInput(String initMsg, String errMsg) {
		String value = null;
		Boolean flag = true;
		
		do {
			try {
				System.out.println(initMsg);
				value = scan.next();
				flag = false;
			} catch (Exception e) {
				if (errMsg != null) {
					System.out.println(errMsg);
				}
			}
		} while (flag);
		return value;
	}
		
	protected int fetchIntFromInput(String initMsg, String errMsg) {
		int value = -1;
		Boolean flag = true;
		
		do {
			try {
				System.out.println(initMsg);
				value = scan.nextInt();
				flag = false;
			} catch (Exception e) {
				if (errMsg != null) {
					System.out.println(errMsg);
				}
			}
		} while (flag);
		return value;
	}
	
	protected Date fetchDateFromInput(String initMsg, String errMsg) {
		Date value = null;
		Boolean flag = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		do {
			try {
				System.out.println(initMsg);
				String v = scan.next();
				value = sdf.parse(v);
				flag = false;
			} catch (Exception e) {
				if (errMsg != null) {
					System.out.println(errMsg);
				}
			}
		} while (flag);
		return value;
	}

	
	abstract void run() throws RemoteException, SQLException;
	abstract void onReceiveData(int taskType, List<?> data) throws RemoteException, SQLException;
}
