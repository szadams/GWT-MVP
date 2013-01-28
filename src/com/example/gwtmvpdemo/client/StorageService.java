package com.example.gwtmvpdemo.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Image;

public class StorageService {
	private List<Image> imgs = new ArrayList<Image>();

	public void addImage(String path) {
		Image i = new Image();
		i.setUrl(path);
		imgs.add(i);
	}

	public List<Image> getAllImages() {
		return imgs;
	}
}
