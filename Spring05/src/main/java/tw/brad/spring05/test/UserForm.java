package tw.brad.spring05.test;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
    @NotBlank(message= "Email 不可為空")
    @Email(message="請輸入有效的Email")
    private String email;

    @Size(min=6, message="密碼長度 >=6")
    private String pwd;

    @NotBlank(message= "Name 不可為空")
    private String name;
}
