package lk.ijse.notecollector.Dao;

import lk.ijse.notecollector.entity.impl.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDao  extends JpaRepository<NoteEntity,String> {
}
