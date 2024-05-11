package pages.pageParent;

import action.ActionPage;
import com.codeborne.selenide.SelenideElement;
import context.IElementStorage;
import context.Storage;

public class Page implements ActionPage {
    private final IElementStorage<SelenideElement> storage;

    public Page() {
        this(new Storage());
    }

    public Page(IElementStorage<SelenideElement> storage) {
        this.storage = storage;
    }

    @Override
    public IElementStorage<SelenideElement> getLocatorStorage() {
        return storage;
    }

}
