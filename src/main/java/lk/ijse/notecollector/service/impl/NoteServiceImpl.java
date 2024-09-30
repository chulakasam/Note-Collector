package lk.ijse.notecollector.service.impl;

import lk.ijse.notecollector.Dao.NoteDao;
import lk.ijse.notecollector.customStatusCodes.SelectedUserErrorStatus;
import lk.ijse.notecollector.dto.NoteDTO;
import lk.ijse.notecollector.dto.NoteStatus;
import lk.ijse.notecollector.entity.impl.NoteEntity;
import lk.ijse.notecollector.entity.impl.UserEntity;
import lk.ijse.notecollector.exceotion.DataPersistException;
import lk.ijse.notecollector.exceotion.UserNotFoundException;
import lk.ijse.notecollector.service.NoteService;
import lk.ijse.notecollector.util.AppUtil;
import lk.ijse.notecollector.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping noteMapping;



    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());
        NoteEntity savedNote =
                noteDao.save(noteMapping.toNoteEntity(noteDTO));
        if(savedNote == null){
            throw new DataPersistException("Note not saved");
        }
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteMapping.asNoteDtoList(noteDao.findAll());
    }

    @Override
    public NoteStatus getNote(String noteId) {
        if(noteDao.existsById(noteId)){
            var selectedUser = noteDao.getReferenceById(noteId);
            return noteMapping.toNoteDto(selectedUser);
        }else {
            return new SelectedUserErrorStatus(2,"Selected note not found");
        }
    }

    @Override
    public boolean updateNote(String noteId,NoteDTO noteDTO) {
        Optional<NoteEntity> tmpUser = noteDao.findById(noteId);
        if(tmpUser.isPresent()){
            tmpUser.get().setNoteTitle(noteDTO.getNoteTitle());
            tmpUser.get().setNoteDesc(noteDTO.getNoteDesc());
            tmpUser.get().setCreatedDate(noteDTO.getCreatedDate());
            tmpUser.get().setPriorityLevel(noteDTO.getPriorityLevel());
        }
        return false;
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> existedNote = noteDao.findById(noteId);
        if(!existedNote.isPresent()){
            throw new UserNotFoundException("Note with id " + noteId + " not found");
        }else {
            noteDao.deleteById(noteId);
        }
    }
}
