package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewInquiryReq {

    private String userId;

    private String content;

    private String title;

    private boolean checkInquiry;
}
