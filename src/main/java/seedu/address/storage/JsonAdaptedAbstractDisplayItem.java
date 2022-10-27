package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

abstract class JsonAdaptedAbstractDisplayItem {

    private final String name;
    private final String uid;
    private final List<JsonAdaptedAbstractAttribute> attributes = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();


    protected JsonAdaptedAbstractDisplayItem(String name, String uid, List<JsonAdaptedAbstractAttribute> attributes,
                                             List<JsonAdaptedTag> tags) {
        this.name = name;
        this.uid = uid;
        if (attributes != null) {
            this.attributes.addAll(attributes);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    protected String getName() {
        return name;
    }

    protected String getUid() {
        return uid;
    }

    protected List<JsonAdaptedAbstractAttribute> getAttributes() {
        return attributes;
    }

    protected List<JsonAdaptedTag> getTags() {
        return tags;
    }
}