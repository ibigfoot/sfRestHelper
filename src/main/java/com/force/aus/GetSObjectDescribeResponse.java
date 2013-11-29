package com.force.aus;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Encapsulates the response from a describe call on the rest api.
 * Parses the fields and recordtype collections.
 * 
 * TODO - add ChildRelationships element.
 * @author tsellers
 *
 */
public class GetSObjectDescribeResponse extends RestResponse {

	private SObject sObject;
	private List<RecordTypeInfo> recordTypeInfos;
	private List<SObjectField> fields;
	
	@Override
	protected void processResponseContent() throws JSONException {
		
		json = new JSONObject(responseContent);
		sObject.setResponseContent(responseContent);
		
		JSONArray jsonFields = json.getJSONArray("fields");
		fields = new ArrayList<SObjectField>();
		
		for(int i=0 ; i<jsonFields.length() ; i++) {
			JSONObject obj = jsonFields.getJSONObject(i);
			fields.add(new SObjectField(obj.toString()));
		}
		
		JSONArray jsonRecordTypeInfos = json.getJSONArray("recordTypeInfos");
		recordTypeInfos = new ArrayList<RecordTypeInfo>();
		for(int i=0 ; i<jsonRecordTypeInfos.length() ; i++) {
			JSONObject obj = jsonRecordTypeInfos.getJSONObject(i);
			recordTypeInfos.add(new RecordTypeInfo(obj.toString()));
		}		
	}
	
	public void setSObject(SObject sObject) {
		this.sObject = sObject;
	}
	
	public SObject getSObject() {
		return sObject;
	}
	
	public List<RecordTypeInfo> getRecordTypeInfos() {
		return recordTypeInfos;
	}
	
	public List<SObjectField> getFields() {
		return fields;
	}
}
