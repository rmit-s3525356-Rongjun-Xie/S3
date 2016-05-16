package company.client;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.server.S3ServerIF;

import company.S3Const;
import company.S3TableOPType;

public class S3ProductController {
	private S3ServerIF server;
	private String uuid;
	
	public S3ProductController(String cUUID, S3ServerIF s) {
		server = s;
		uuid = cUUID;
	}

	public void update(String productID, String key, Object value) throws RemoteException, SQLException {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(S3Const.TABLE_PRODUCT_ID, productID);
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put(key, value);
		
		server.doTask(uuid, S3Const.CLASS_TASK_NAME, S3Const.TABLE_PRODUCT, S3TableOPType.UPDATE, values, conditions);
	}
	
	public void update(String productID, int taskType, String key, Object value) throws RemoteException, SQLException {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(S3Const.TABLE_PRODUCT_ID, productID);
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put(key, value);
		
		server.doTask(uuid, taskType, S3Const.CLASS_TASK_NAME, S3Const.TABLE_PRODUCT, S3TableOPType.UPDATE, values, conditions);
	}
	
	public void updatePrice(String productID, double value, int taskType) throws RemoteException, SQLException {
		update(productID, taskType, S3Const.TABLE_PRODUCT_PRICE, value);
	}
	
	public void updatePrice(String productID, double value) throws RemoteException, SQLException {
		update(productID, S3Const.TABLE_PRODUCT_PRICE, value);
	}
	
	public void updateStockLevel(String productID, int value) throws RemoteException, SQLException {
		update(productID, S3Const.TABLE_PRODUCT_STOCK_LV, value);
	}
	
	public void updateReplenishLevel(String productID, int value) throws RemoteException, SQLException {
		update(productID, S3Const.TABLE_PRODUCT_REPLENISH_LV, value);
	}
	
	public void create(String productID, String name, double price, int stockLV, int replenishLV, int promotion, int discount) throws RemoteException, SQLException {
		List<Object> values = new ArrayList<Object>();
		values.add(productID);
		values.add(name); //name
		values.add(price); 	//PRICE
		values.add(stockLV);	//STOCKLV
		values.add(replenishLV);	//REPLENISHLV
		values.add(promotion);	//PROMOTION
		values.add(discount);	//DISCOUNT
		
		server.doTask(uuid, S3Const.CLASS_TASK_NAME, S3Const.TABLE_PRODUCT, S3TableOPType.INSERT, values, null);
	}

	public void postGetProductInfoByID(String productID, int taskType) throws RemoteException, SQLException {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put(S3Const.TABLE_PRODUCT_ID, productID);
		
		server.doTask(uuid, taskType, S3Const.CLASS_TASK_NAME, S3Const.TABLE_PRODUCT, S3TableOPType.SELECT, null, conditions);
	}
	
	public void postGetAllProduct(int taskType) throws RemoteException, SQLException {
		server.doTask( uuid, taskType, S3Const.CLASS_TASK_NAME, S3Const.TABLE_PRODUCT, S3TableOPType.SELECT, null, null );
	}
}
