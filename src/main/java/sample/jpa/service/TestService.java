package sample.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.jpa.domain.Note;
import sample.jpa.repository.NoteRepository;
import sample.jpa.utils.Utils;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author panzx
 * @version 1.0.0
 * @ClassName TestService.java
 * @Description TODO
 * @createTime 2019年05月06日 18:50:00
 */
@Service
public class TestService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private Utils utils;

    public String[] testCpu(int num, String content) {
        String[] result = new String[num];
       for(int i=0;i<num;i++) {
           result[i] = Base64.getEncoder().encodeToString(utils.encrypt(content));
       }

       return result;
    }

    public List<Note> testIO(int num) {
        List<Note> notes = new LinkedList<>();

        for(int i = 0; i < num; i++) {
            Note note = new Note();
            note.setBody(utils.getRandomString());
            note.setTitle(utils.getRandomString());

            note = noteRepository.save(note);
            note = noteRepository.getOne(note.getId());

            notes.add(note);
        }

        return notes;
    }
}
