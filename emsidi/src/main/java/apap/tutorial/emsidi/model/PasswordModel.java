package apap.tutorial.emsidi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter

public class PasswordModel {
    String prevPassword;
    String confirmPassword;
    String newPassword;

}
