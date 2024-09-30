package lk.ijse.notecollector.service;

import lk.ijse.notecollector.dto.NoteDTO;
import lk.ijse.notecollector.dto.NoteStatus;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDTO noteDTO);
    List<NoteDTO> getAllNotes();
    NoteStatus getNote(String noteId);
    boolean updateNote(String noteId,NoteDTO noteDTO);
    boolean deleteNote(String noteId);

}
