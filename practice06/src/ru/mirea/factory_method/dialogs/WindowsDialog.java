package ru.mirea.factory_method.dialogs;

import ru.mirea.factory_method.buttons.Button;
import ru.mirea.factory_method.buttons.WindowsButton;

public class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
