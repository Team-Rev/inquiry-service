package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NewInquiryReq {

    private String userId;

    private String content;

    private String title;

    private boolean checkInquiry;
}
