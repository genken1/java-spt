package ru.mirea.factory_method.dialogs;

import ru.mirea.factory_method.buttons.Button;
import ru.mirea.factory_method.buttons.HTMLButton;

public class HtmlDialog extends Dialog {
    @Override
    public Button createButton() {
        return new HTMLButton();
    }
}
