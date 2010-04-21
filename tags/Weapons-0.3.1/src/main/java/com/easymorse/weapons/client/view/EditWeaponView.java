package com.easymorse.weapons.client.view;

import com.easymorse.weapons.client.presenter.EditWeaponPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditWeaponView extends Composite implements
		EditWeaponPresenter.Display {

	private FlexTable detailsTable;
	private TextBox name;
	private TextArea description;
	private Button saveButton;
	private Button cancelButton;

	public EditWeaponView() {
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("15em");
		initWidget(contentDetailsDecorator);

		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		detailsTable = new FlexTable();
		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		detailsTable.addStyleName("contacts-ListContainer");
		detailsTable.getColumnFormatter().addStyleName(1, "add-contact-input");
		name = new TextBox();
		description = new TextArea();
		description.setWidth("12em");//TODO 权宜之计，要用formatter做细节布局处理。
		description.setVisibleLines(5);
		initDetailsTable();
		contentDetailsPanel.add(detailsTable);
		
		HorizontalPanel menuPanel = new HorizontalPanel();
	    saveButton = new Button("保存");
	    cancelButton = new Button("取消");
	    menuPanel.add(saveButton);
	    menuPanel.add(cancelButton);
	    contentDetailsPanel.add(menuPanel);

		contentDetailsDecorator.add(contentDetailsPanel);
	}

	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("名称"));
		detailsTable.setWidget(0, 1, name);
		detailsTable.setWidget(1, 0, new Label("介绍"));
		detailsTable.setWidget(1, 1, description);
		name.setFocus(true);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return this.cancelButton;
	}

	@Override
	public HasValue<String> getDescription() {
		return this.description;
	}

	@Override
	public HasValue<String> getName() {
		return this.name;
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return this.saveButton;
	}
}