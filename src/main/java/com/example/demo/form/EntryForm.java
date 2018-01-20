package com.example.demo.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EntryForm {
    String name;
    List<TagItem> tags;

}
