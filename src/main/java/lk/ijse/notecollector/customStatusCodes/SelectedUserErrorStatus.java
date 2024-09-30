package lk.ijse.notecollector.customStatusCodes;

import lk.ijse.notecollector.dto.NoteStatus;
import lk.ijse.notecollector.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserErrorStatus implements UserStatus, NoteStatus {
    private int statusCode;
    private String statusMessage;
}
