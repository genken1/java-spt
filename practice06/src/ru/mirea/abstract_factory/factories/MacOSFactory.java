package ru.mirea.abstract_factory.factories;


import ru.mirea.abstract_factory.buttons.Button;
import ru.mirea.abstract_factory.buttons.MacOSButton;
import ru.mirea.abstract_factory.checkboxes.Checkbox;
import ru.mirea.abstract_factory.checkboxes.MacOSCheckbox;

public class MacOSFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
