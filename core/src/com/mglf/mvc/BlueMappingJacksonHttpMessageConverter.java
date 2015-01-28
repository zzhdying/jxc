package com.mglf.mvc;

import java.io.IOException;

import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;

import com.mglf.interceptor.LogInterceptor;

public class BlueMappingJacksonHttpMessageConverter extends
		MappingJacksonHttpMessageConverter {

	boolean nonHtmlEscape = false;

	public boolean isNonHtmlEscape() {
		return nonHtmlEscape;
	}



	public void setNonHtmlEscape(boolean nonHtmlEscape) {
		this.nonHtmlEscape = nonHtmlEscape;
	}



	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		if(isNonHtmlEscape()){
			LogInterceptor.setRetNonHtmlEscape(true);
		}
		
		
		if (object instanceof String) {
			String strVal = (String) object;
			
    		if(!LogInterceptor.getRetNonHtmlEscape()){
    			object = StringEscapeUtils.escapeHtml(strVal);
//    			object = StringEscapeUtils.escapeJavaScript(strVal); 			
    		}    		
			
		} else {

		}

		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders()
				.getContentType());

		JsonGenerator jsonGenerator = this.getObjectMapper().getJsonFactory()
				.createJsonGenerator(outputMessage.getBody(), encoding);

		// A workaround for JsonGenerators not applying serialization features
		// https://github.com/FasterXML/jackson-databind/issues/12
		if (this.getObjectMapper().getSerializationConfig()
				.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
			jsonGenerator.useDefaultPrettyPrinter();
		}

		try {
			// if (this.getj jsonPrefix != null) {
			// jsonGenerator.writeRaw("{} && ");
			// }
			this.getObjectMapper().writeValue(jsonGenerator, object);
		} catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: "
					+ ex.getMessage(), ex);
		}
	}

}
