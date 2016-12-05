package com.genedata.expressionist.ui;

import com.genedata.expressionist.data.License;
import com.genedata.expressionist.data.LicenseRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alessio Ceroni
 */

@SpringUI
@Theme("valo")
public class LicenseUI extends UI {

	private final Grid grid = new Grid();
	private final LicenseForm form;
	private final LicenseRepository repository;

// --------------------------- Constructors ---------------------------

	@Autowired
	public LicenseUI(LicenseRepository repository) {
		this.repository = repository;
		this.form = new LicenseForm(this, repository);
	}

// -------------------------- Public --------------------------

	public void updateList() {
		updateList("");
	}

// ------------------------- Protected -------------------------

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		TextField filterText = new TextField();
		filterText.setInputPrompt("filter by name...");
		filterText.addTextChangeListener(e -> updateList(e.getText()));

		Button clearFilter = new Button();
		clearFilter.setIcon(FontAwesome.TIMES);
		clearFilter.setDescription("Clear the current filter");
		clearFilter.addClickListener(e -> {
			filterText.clear();
			updateList();
		});

		Button addButton = new Button("Add new license");
		addButton.addClickListener(e -> {
			grid.deselectAll();
			form.setLicense(new License());
		});

		HorizontalLayout toolbar = new HorizontalLayout();
		toolbar.addComponents(filterText, clearFilter, addButton);
		toolbar.setSpacing(true);

		grid.setColumns("id", "recipient", "expiration");
		grid.setSizeFull();

		form.setVisible(false);

		HorizontalLayout main = new HorizontalLayout(grid, form);
		main.setSpacing(true);
		main.setSizeFull();
		main.setExpandRatio(grid, 1);

		VerticalLayout layout = new VerticalLayout();
		layout.addComponents(toolbar, main);
		layout.setSpacing(true);
		layout.setMargin(true);
		setContent(layout);

		grid.addSelectionListener(event -> {
			if (event.getSelected().isEmpty()) {
				form.setVisible(false);
			} else {
				form.setLicense((License) event.getSelected().iterator().next());
			}
		});

		updateList();
	}

// ------------------------- Private -------------------------

	private void updateList(String query) {
		grid.setContainerDataSource(new BeanItemContainer<>(License.class, repository.findByRecipientContainingIgnoreCase(query)));
	}
}
