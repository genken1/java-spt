package ru.mirea.abstract_factory.factories;


import ru.mirea.abstract_factory.buttons.Button;
import ru.mirea.abstract_factory.checkboxes.Checkbox;

public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
