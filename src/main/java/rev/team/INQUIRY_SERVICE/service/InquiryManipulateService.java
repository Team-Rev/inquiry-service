package rev.team.INQUIRY_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rev.team.INQUIRY_SERVICE.domain.entity.Inquiry;
import rev.team.INQUIRY_SERVICE.domain.repository.InquiryReplyRepository;
import rev.team.INQUIRY_SERVICE.domain.repository.InquiryRepository;
import rev.team.INQUIRY_SERVICE.domain.request.NewInquiryReq;
import rev.team.INQUIRY_SERVICE.domain.request.NewReplyReq;

@Service
public class InquiryManipulateService {

    InquiryRepository inquiryRepository;
    InquiryReplyRepository inquiryReplyRepository;

    @Autowired
    public InquiryManipulateService(InquiryRepository inquiryRepository, InquiryReplyRepository inquiryReplyRepository) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryReplyRepository = inquiryReplyRepository;
    }

    public String insert(NewInquiryReq newInquiryReq) {
        inquiryRepository.save(Inquiry.builder()
                .userId(newInquiryReq.getUserId())
                .title(newInquiryReq.getTitle())
                .content(newInquiryReq.getContent())
                .checkInquiry(newInquiryReq.isCheckInquiry())
                .build());

        return "CREATE SUCCESS";
    }

    public String insert(NewReplyReq newReplyReq) {
        inquiryRepository.save(Inquiry.builder()
                .userId(newReplyReq.getUserId())
                .content(newReplyReq.getContent())
                .inquiryId(newReplyReq.getInquiry_id())
                .build());

        return "CREATE SUCCESS";
    }

}
