package com.example.demo.form;

import java.beans.PropertyEditorSupport;

public class TagItemEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new TagItem(Long.parseLong(text)));
    }
}
