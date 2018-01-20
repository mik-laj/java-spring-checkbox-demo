package com.example.demo.form;

import com.example.demo.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagItem {
    Long id;
    String name;

    public TagItem(Long id) {
        this.id = id;
    }

    public static TagItem fromTag(Tag tag){
        return new TagItem(tag.getId(), tag.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TagItem tagItem = (TagItem) o;
        return Objects.equals(id, tagItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
