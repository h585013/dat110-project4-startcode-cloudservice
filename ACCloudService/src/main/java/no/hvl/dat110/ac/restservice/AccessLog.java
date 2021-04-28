package no.hvl.dat110.ac.restservice;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class AccessLog {
	
	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// TODO: add an access entry to the log for the provided message and return assigned id
	public int add(String message) {
		
		int id = 0;
		id=cid.addAndGet(1);
		AccessEntry a= new AccessEntry(id,message);
		this.log.put(id, a);
		return id;
	}
		
	// TODO: retrieve a specific access entry from the log
	public AccessEntry get(int id) {
		return this.log.get(id);
		
		
	}
	
	// TODO: clear the access entry log
	public void clear() {
		this.log.clear();
	}
	
	// TODO: return JSON representation of the access log
	public String toJson () {
		Enumeration<AccessEntry> e= this.log.elements();
		String json = "";
		Gson gson = new Gson(); 
		while (e.hasMoreElements()) {
			AccessEntry entry = e.nextElement();
			 json += gson.toJson(entry);
		}
		
	

		
    	
    	return json;
    }
}
