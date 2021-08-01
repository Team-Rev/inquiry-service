package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EditInquiryReq {

    private Long inquiryId;

    // 작성자만 수정 가능
    private String username;

    private String title;

    private String content;

}
