package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EditInquiryReq {

    private Long Id;

    private String title;

    private String content;

}
