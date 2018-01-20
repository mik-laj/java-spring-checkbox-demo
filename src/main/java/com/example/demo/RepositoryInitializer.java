package com.example.demo;

import com.example.demo.entity.Entry;
import com.example.demo.entity.Tag;
import com.example.demo.repository.EntryRepository;
import com.example.demo.repository.TagRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryInitializer implements InitializingBean {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(entryRepository.count() == 0 && tagRepository.count() == 0){
            List<Tag> tags = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                Tag tag = new Tag("Tag #" + i);
                tagRepository.save(tag);
                tags.add(tag);
            }

            for(int i = 0; i < 5; i++) {
                Entry entry = new Entry("Entry #" + i);
                ArrayList<Tag> entry_tags = new ArrayList<>();
                for(int j = 0; j < Math.random() * 10; j++) {
                    entry_tags.add(tags.get((int) (Math.random() * tags.size())));
                }
                entry.setTagList(entry_tags);
                entryRepository.save(entry);
            }

        }
    }
}
