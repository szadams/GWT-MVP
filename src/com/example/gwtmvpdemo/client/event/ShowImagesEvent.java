package com.example.gwtmvpdemo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowImagesEvent extends GwtEvent<ShowImagesEventHandler> {

	public static Type<ShowImagesEventHandler> TYPE = new Type<ShowImagesEventHandler>();

	@Override
	public Type<ShowImagesEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowImagesEventHandler handler) {
		handler.onShowPersons(this);
	}

}
