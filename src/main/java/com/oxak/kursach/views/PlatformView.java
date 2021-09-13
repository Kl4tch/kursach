package com.oxak.kursach.views;

import com.oxak.kursach.components.PlatformEditor;
import com.oxak.kursach.models.Platform;
import com.oxak.kursach.repo.PlatformRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class PlatformView extends VerticalLayout {

    private final PlatformRepository repo;

    private Grid<Platform> grid = new Grid<>(Platform.class);

    private final TextField filter = new TextField("", "Type to filter");
    private final Button addNewBtn = new Button("Add new");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn);
    private final PlatformEditor editor;

    public PlatformView(PlatformRepository gameRepository, PlatformEditor editor) {
        this.repo = gameRepository;
        this.editor = editor;
        add(toolbar, grid, editor);

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> showData(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editGame(e.getValue());
        });
        addNewBtn.addClickListener(e -> editor.editGame(new Platform()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            showData(filter.getValue());
        });

        showData("");
    }

    private void showData(String title) {
        if (title.isBlank())
            grid.setItems(repo.findAll());
        else
            grid.setItems(repo.findByTitleContainingIgnoreCase(title));
    }
}