package company;

public abstract class S3Const {
	public static final String TABLE_PREFIX = "S3T_";
	public static final String TABLE_PRODUCT = "PRODUCT";
	public static final String TABLE_CUSTOMER = "CUSTOMER";
	public static final String TABLE_STAFF = "STAFF";
	public static final String TABLE_TRANSACTION = "TRANSACTION";
	public static final String TABLE_ORDERITEM = "ORDERITEM";
	
	
	public static final String TABLE_PRODUCT_ID = "BARCODE";
	public static final String TABLE_PRODUCT_NAME = "NAME";
	public static final String TABLE_PRODUCT_PRICE = "PRICE";
	public static final String TABLE_PRODUCT_STOCK_LV = "STOCKLV";
	public static final String TABLE_PRODUCT_REPLENISH_LV = "REPLENISHLV";
	public static final String TABLE_PRODUCT_PROMOTION = "PROMOTION";
	public static final String TABLE_PRODUCT_DISCOUNT = "DISCOUNT";
	public static final String TABLE_PRODUCT_SUPPLIER = "SUPPLIER";
	
	public static final String TABLE_USER_ID = "ID";
	public static final String TABLE_CUSTOMER_POINT = "POINT";
	public static final String TABLE_CUSTOMER_CASH = "CASH";
	
	public static final String TABLE_TRANSACTION_ID = "ID";
	public static final String TABLE_TRANSACTION_COST = "COST";
	public static final String TABLE_TRANSACTION_DATE = "TRANS_DATE";
	public static final String TABLE_TRANSACTION_TIME = "TRANS_TIME";
	public static final String TABLE_TRANSACTION_CUST_ID = "CUST_ID";
	
	public static final String TABLE_ORDERITEM_QTY = "QUANTITY";
	
	public static final String CLASS_TASK_NAME = "company.server.S3TableControlTask";
	
	
	public static final int TASK_SHOW_ALL_PRODUCTS = 0;
	public static final int TASK_LOGIN = 1;
	public static final int TASK_SHOW_PROD_BY_ID = 2;
	public static final int TASK_SHOW_CUSTOMER_BY_ID = 3;
}
