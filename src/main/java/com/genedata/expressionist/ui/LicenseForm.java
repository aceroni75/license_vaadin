package com.genedata.expressionist.ui;

import com.genedata.expressionist.data.License;
import com.genedata.expressionist.data.LicenseRepository;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Alessio Ceroni
 */
public class LicenseForm extends FormLayout {

	private final TextField recipient = new TextField("Recipient");
	@SuppressWarnings("FieldCanBeLocal")
	private final PopupDateField expiration = new PopupDateField("Expiration");
	private final Button delete = new Button("Delete");

	private final LicenseUI ui;
	private final LicenseRepository repository;

	private License license;

// --------------------------- Constructors ---------------------------

	public LicenseForm(LicenseUI ui, LicenseRepository repository) {
		this.ui = ui;
		this.repository = repository;

		Button save = new Button("Save");
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		save.addClickListener(e -> this.save());

		delete.addClickListener(e -> this.delete());

		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		buttons.setSpacing(true);

		addComponents(recipient, expiration, buttons);
		setSizeUndefined();
	}

// -------------------------- Public --------------------------

	public void setLicense(License license) {
		this.license = license;
		BeanFieldGroup.bindFieldsUnbuffered(this.license, this);

		// Show delete button for only customers already in the database
		delete.setVisible(license.isPersisted());
		setVisible(true);
		recipient.selectAll();
	}

// ------------------------- Private -------------------------

	private void delete() {
		repository.delete(license);
		ui.updateList();
		setVisible(false);
	}

	private void save() {
		repository.save(license);
		ui.updateList();
		setVisible(false);
	}
}
