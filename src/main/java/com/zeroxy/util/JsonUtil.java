package com.zeroxy.util;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private final static ObjectMapper mapper = new ObjectMapper();

	private final static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);  
	
	public static String objectToJson(Object object)
	{
		
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(),e);
			LOG.error("Json convert error:"+e.getMessage());
		}
		return null;
	}
	
	public static <T> T jsonToObject(String json,Class<T> c)throws IOException
	{
		return mapper.readValue(json,c);
	}
	public static <T> Set<T> jsonToSet(String json,@SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<T> elementType) throws JsonParseException, JsonMappingException, IOException {   
		JavaType javaType = mapper.getTypeFactory().constructCollectionType(collectionClass, elementType);
		return mapper.readValue(json,javaType);
	}  
	public static <T> List<T> jsonToList(String json,@SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<T> elementType) throws JsonParseException, JsonMappingException, IOException {   
		JavaType javaType = mapper.getTypeFactory().constructCollectionType(collectionClass, elementType);
		return mapper.readValue(json,javaType);
	}  
}
