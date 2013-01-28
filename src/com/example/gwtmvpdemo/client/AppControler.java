package com.example.gwtmvpdemo.client;

import com.example.gwtmvpdemo.client.event.ShowImagesEvent;
import com.example.gwtmvpdemo.client.event.ShowImagesEventHandler;
import com.example.gwtmvpdemo.client.presenter.ImagesPresenter;
import com.example.gwtmvpdemo.client.presenter.Presenter;
import com.example.gwtmvpdemo.client.view.ShowImagesView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppControler implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final StorageService storage;
	private HasWidgets container;

	private final static String SHOW_IMAGES = "showImages";

	public AppControler(StorageService storage, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.storage = storage;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem(SHOW_IMAGES);
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals(SHOW_IMAGES)) {
				presenter = new ImagesPresenter(storage, eventBus,
						new ShowImagesView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(ShowImagesEvent.TYPE, new ShowImagesEventHandler() {
			@Override
			public void onShowPersons(ShowImagesEvent event) {
				doShowImages();
			}
		});
	}

	private void doShowImages() {
		History.newItem(SHOW_IMAGES);
	}

}
