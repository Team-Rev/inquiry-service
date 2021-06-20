package rev.team.INQUIRY_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rev.team.INQUIRY_SERVICE.domain.entity.Inquiry;
import rev.team.INQUIRY_SERVICE.domain.entity.InquiryReply;
import rev.team.INQUIRY_SERVICE.domain.repository.InquiryReplyRepository;
import rev.team.INQUIRY_SERVICE.domain.repository.InquiryRepository;
import rev.team.INQUIRY_SERVICE.domain.request.EditInquiryReq;
import rev.team.INQUIRY_SERVICE.domain.request.EditReplyReq;
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
        inquiryReplyRepository.save(InquiryReply.builder()
                .userId(newReplyReq.getUserId())
                .content(newReplyReq.getContent())
                .inquiryReplyId(newReplyReq.getInquiry_id())
                .build());

        return "CREATE SUCCESS";
    }

    public String update(EditInquiryReq editReq) {
        inquiryRepository.update(editReq.getId(), editReq.getTitle(), editReq.getContent());
        return "EDIT SUCCESS";
    }


    public String update(EditReplyReq editReplyReq) {
        inquiryReplyRepository.update(editReplyReq.getId(), editReplyReq.getContent());
        return "EDIT SUCCESS";
    }
}
