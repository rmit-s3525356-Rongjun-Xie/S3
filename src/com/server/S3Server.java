package com.server;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.client.S3ClientIF;
import com.common.S3TaskIF;

public class S3Server extends UnicastRemoteObject implements S3ServerIF {
	private static final long serialVersionUID = 1L;
	//private ArrayList<S3ClientIF> clients;
	
	private S3DBMan dbMan = null;
	private HashMap<String, S3ClientIF> clients;
	
	public S3Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		//clients = new ArrayList<S3ClientIF>();
		
		clients = new HashMap<String, S3ClientIF>();
		
		dbMan = new S3DBMan();
	}
	
	public void shutdown() {
		dbMan.close();
	}
	
	public void start() {
		dbMan.init();
		dbMan.connect();
	}

	@Override
	public void registerClient(String uuid, S3ClientIF client) throws RemoteException {
		// TODO Auto-generated method stub
		//clients.add( client );
		
		clients.put( uuid, client );
		System.out.println( "Add Client " + uuid );
	}

	@Override
	public void broadcastMessage(Map<?, ?> args) throws Exception {
		// TODO Auto-generated method stub
		Iterator<String> itr = clients.keySet().iterator();
		int taskType = (int)args.get("taskType");
		Object data = args.get("data");
		
		while(itr.hasNext()) {
			String k = itr.next();
			S3ClientIF client = clients.get(k);
			client.receiveBordercastData(taskType, data);
		}
	}
	
	public S3DBMan getDBMan() {
		return dbMan;
	}

	@Override
	public void doTask(String UUID, String className, Object... args) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		S3ClientIF client = clients.get(UUID);
		
		if (client == null) {
			return;
		}
		
		try {
			Class<?> taskClass = Class.forName(className);
			Constructor<?>[] tcCons = taskClass.getConstructors();
//			
//			Object[] params = new Object[1 + args.length];
//			params[0] = UUID;
//			for ( int i = 0; i < args.length; i++ ) {
//				params[i + 1] = args[i];
//			}
//			
			S3TaskIF task = (S3TaskIF)tcCons[0].newInstance( args );
			task.run(this);
			client.receiveData(-1, task.getResult());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void unregisterClient(String uuid) throws RemoteException {
		// TODO Auto-generated method stub
		clients.remove(uuid);
		
		assert ( clients.get(uuid) == null );
		System.out.println( "Remove Client " + uuid );
	}

	@Override
	public void doTask(String UUID, int taskType, String className, Object... args)
			throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		S3ClientIF client = clients.get(UUID);
		
		if (client == null) {
			return;
		}
		
		try {
			Class<?> taskClass = Class.forName(className);
			Constructor<?>[] tcCons = taskClass.getConstructors();
			
//			Object[] params = new Object[1 + args.length];
//			params[0] = UUID;
//			for ( int i = 0; i < args.length; i++ ) {
//				params[i + 1] = args[i];
//			}
			
			S3TaskIF task = (S3TaskIF)tcCons[0].newInstance( args );
			task.run(this);
			client.receiveData(taskType, task.getResult());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
