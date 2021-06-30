package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewReplyReq {

    private Long inquiryId;

    private String username;

    private String content;
}
