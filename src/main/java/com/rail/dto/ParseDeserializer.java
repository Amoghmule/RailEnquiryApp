package com.rail.dto;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


public class ParseDeserializer extends StdDeserializer<LocalTime>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParseDeserializer() {
        super(LocalTime.class);
    }

	@Override
	public LocalTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		return LocalTime.parse(p.getValueAsString(),DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	

}
