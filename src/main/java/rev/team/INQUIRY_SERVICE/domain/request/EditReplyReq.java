package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EditReplyReq {

    private Long inquiryReplyId;

    private String username;

    private String content;

}
