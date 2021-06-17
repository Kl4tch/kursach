package com.oxak.kursach.components;

import com.oxak.kursach.models.Game;
import com.oxak.kursach.repo.GameRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class GameEditor extends VerticalLayout implements KeyNotifier {
    private final GameRepository gameRepository;
    private Game game;

    private TextField title = new TextField("Title");
    private Checkbox rusText = new Checkbox("Русский текст");
    private Checkbox rusVoice = new Checkbox("Русский голос");
    private Binder<Game> binder = new Binder<>(Game.class);

    private Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Отмена");
    private Button delete = new Button("Удалить", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    @Setter
    private ChangeHandler changeHandler;
    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public GameEditor(GameRepository gameRepository) {
        this.gameRepository = gameRepository;

        add(title, rusText, rusVoice, actions);
        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editGame(game));
        setVisible(false);
    }

    private void save() {
        gameRepository.save(game);
        changeHandler.onChange();
    }

    private void delete() {
        gameRepository.delete(game);
        changeHandler.onChange();
    }

    public void editGame(Game newGame) {
        if (newGame == null) {
            setVisible(false);
            return;
        }

        if (newGame.getId() != null)
            this.game = gameRepository.findById(newGame.getId()).orElse(newGame);
        else
            this.game = newGame;

        binder.setBean(game);

        setVisible(true);

        title.focus();
    }
}
