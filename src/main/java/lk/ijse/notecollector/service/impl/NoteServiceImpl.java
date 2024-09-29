package lk.ijse.notecollector.service.impl;

import lk.ijse.notecollector.dto.NoteDTO;
import lk.ijse.notecollector.service.NoteService;
import lk.ijse.notecollector.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NoteServiceImpl implements NoteService {
    private static List<NoteDTO> noteDTOList=new ArrayList<>();
    NoteServiceImpl(){
        noteDTOList.add(new NoteDTO("6f535d44-fc2b-49b2-bc62-a017d99dbc4e","python","fist week","2023-10-19","1","u001"));
        noteDTOList.add(new NoteDTO("6f535d44-fc2b-49b2-bc62-a017d99dbc5e","java","second week","2023-10-26","2","u002"));
        noteDTOList.add(new NoteDTO("6f535d44-fc2b-49b2-bc62-a017d99dbc6e","spring","third week","2023-11-13","3","u001"));
        noteDTOList.add(new NoteDTO("6f535d44-fc2b-49b2-bc62-a017d99dbc7e","pascal","forth week","2023-10-20","4","u003"));
    }


    @Override
    public NoteDTO saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());
        return noteDTO;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteDTOList;
    }

    @Override
    public NoteDTO getNote(String noteId) {
        return null;
    }

    @Override
    public boolean updateNote(String noteId,NoteDTO noteDTO) {
        return false;
    }

    @Override
    public boolean deleteNote(String noteId) {
        return false;
    }
}
