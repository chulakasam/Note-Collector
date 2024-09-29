package lk.ijse.notecollector.service;

import lk.ijse.notecollector.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    NoteDTO saveNote(NoteDTO noteDTO);
    List<NoteDTO> getAllNotes();
    NoteDTO getNote(String noteId);
    boolean updateNote(String noteId,NoteDTO noteDTO);
    boolean deleteNote(String noteId);

}
