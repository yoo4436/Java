package tw.brad.spring08.response;

import lombok.Data;

@Data
public class ChatMessage {

    private String email;
    private String content;
    private String time;
}
