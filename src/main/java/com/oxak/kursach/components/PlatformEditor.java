package com.oxak.kursach.components;

import com.oxak.kursach.models.Platform;
import com.oxak.kursach.repo.PlatformRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
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
public class PlatformEditor extends VerticalLayout implements KeyNotifier {
    private final PlatformRepository repository;
    private Platform platform;

    private TextField title = new TextField("Title");
    private Binder<Platform> binder = new Binder<>(Platform.class);

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
    public PlatformEditor(PlatformRepository repository) {
        this.repository = repository;

        add(title, actions);
        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editGame(platform));
        setVisible(false);
    }

    private void save() {
        repository.save(platform);
        changeHandler.onChange();
    }

    private void delete() {
        repository.delete(platform);
        changeHandler.onChange();
    }

    public void editGame(Platform platform) {
        if (platform == null) {
            setVisible(false);
            return;
        }

        if (platform.getId() != null)
            this.platform = repository.findById(platform.getId()).orElse(platform);
        else
            this.platform = platform;

        binder.setBean(platform);

        setVisible(true);

        title.focus();
    }
}
