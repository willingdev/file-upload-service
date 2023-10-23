package com.cpn.fileuploadapi.storage;

import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageProperties {

	private String location = "upload-files";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
