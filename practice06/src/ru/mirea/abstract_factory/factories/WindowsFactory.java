package ru.mirea.abstract_factory.factories;


import ru.mirea.abstract_factory.buttons.Button;
import ru.mirea.abstract_factory.buttons.WindowsButton;
import ru.mirea.abstract_factory.checkboxes.Checkbox;
import ru.mirea.abstract_factory.checkboxes.WindowsCheckbox;

public class WindowsFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
