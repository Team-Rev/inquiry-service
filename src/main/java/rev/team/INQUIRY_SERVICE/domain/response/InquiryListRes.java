package rev.team.INQUIRY_SERVICE.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InquiryListRes {

    private String title;

    private LocalDateTime postDate;

    private boolean processing;
}
