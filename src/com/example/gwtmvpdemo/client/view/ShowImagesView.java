package com.example.gwtmvpdemo.client.view;

import java.util.List;

import com.example.gwtmvpdemo.client.StorageService;
import com.example.gwtmvpdemo.client.presenter.ImagesPresenter.Display;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;

public class ShowImagesView extends Composite implements Display {

	private final Button addPrevButton = new Button("Poprzednie");
	private final Button addNextButton = new Button("Następne");
	private final Label lab = new Label("   ");
	HorizontalPanel hp2 = new HorizontalPanel();

	private static final String UPLOAD_ACTION_URL = GWT.getModuleBaseURL()
			+ "upload";

	VerticalPanel mainPanel = new VerticalPanel();
	final FormPanel form = new FormPanel();

	StorageService storage = new StorageService();
	private static int counter = 0;

	public ShowImagesView() {

		storage.addImage("/images/uploaded/cat.jpg");
		storage.addImage("/images/uploaded/cat2.gif");

		initWidget(mainPanel);

		addPrevButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hp2.remove(storage.getAllImages().get(
						counter % storage.getAllImages().size()));
				counter--;
				hp2.add(storage.getAllImages().get(
						counter % storage.getAllImages().size()));
			}
		});

		addNextButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hp2.remove(storage.getAllImages().get(
						counter % storage.getAllImages().size()));
				counter++;
				hp2.add(storage.getAllImages().get(
						counter % storage.getAllImages().size()));
			}
		});

		
//		mainPanel.add(lab);
		
		// <------------ UPLOADING FILES CODE --------------->

		form.setAction(UPLOAD_ACTION_URL);

		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		VerticalPanel panel = new VerticalPanel();
		form.setWidget(panel);

		final FileUpload upload = new FileUpload();
		upload.setName("uploadFormElement");
		panel.add(upload);

		panel.add(new Button("Dodaj", new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.submit();
			}
		}));

		// Add an event handler to the form.
		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				// if (tb.getText().length() == 0) {
				// Window.alert("The text box must not be empty");
				// event.cancel();,
				// }
			}
		});

		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				Window.alert(event.getResults());
				String name = "/images/uploaded/"
						+ upload.getFilename().substring(12,
								upload.getFilename().length());
				storage.addImage(name);
			}
		});

		HorizontalPanel hp = new HorizontalPanel();
		hp.add(addPrevButton);
		hp.add(lab);
		hp.add(addNextButton);
		
		hp2.add(storage.getAllImages().get(0));
		counter++;
		HorizontalPanel hp3 = new HorizontalPanel();
		hp3.add(form);
		
		
		mainPanel.add(hp);
		mainPanel.add(hp2);
		mainPanel.add(hp3);
	}

	@Override
	public HasClickHandlers getAddButton() {
		return addPrevButton;
	}

	@Override
	public void setData(List<String> data) {
		// TODO Auto-generated method stub
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getAddButton2() {
		return addNextButton;
	}
}
