package com.oxak.kursach.views;

import com.oxak.kursach.components.GameEditor;
import com.oxak.kursach.models.Game;
import com.oxak.kursach.repo.GameRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class EditOrderView extends VerticalLayout {

    private final GameRepository gameRepo;

    private Grid<Game> grid = new Grid<>(Game.class);

    private final TextField filter = new TextField("", "Type to filter");
    private final Button addNewBtn = new Button("Add new");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn);
    private final GameEditor editor;

    public EditOrderView(GameRepository gameRepository, GameEditor editor) {
        this.gameRepo = gameRepository;
        this.editor = editor;
        add(toolbar, grid, editor);

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> showData(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editGame(e.getValue());
        });
        addNewBtn.addClickListener(e -> editor.editGame(new Game()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            showData(filter.getValue());
        });

        showData("");
    }

    private void showData(String title) {
        if (title.isBlank())
            grid.setItems(gameRepo.findAll());
        else
            grid.setItems(gameRepo.findByTitleContainingIgnoreCase(title));
    }
}