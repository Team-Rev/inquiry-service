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

import java.util.List;


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
                .username(newInquiryReq.getUsername())
                .title(newInquiryReq.getTitle())
                .content(newInquiryReq.getContent())
                .parentId(newInquiryReq.getParentId())
                .build());

        return "CREATE SUCCESS";
    }

    public String insert(NewReplyReq newReplyReq) {
        inquiryReplyRepository.save(InquiryReply.builder()
                .username(newReplyReq.getUsername())
                .content(newReplyReq.getContent())
                .inquiryReplyId(newReplyReq.getInquiryId())
                .build());

        return "CREATE SUCCESS";
    }

    public String update(EditInquiryReq editReq) {
        inquiryRepository.updatePost(editReq.getInquiryId(), editReq.getTitle(), editReq.getContent());
        return "EDIT SUCCESS";
    }


    public String update(EditReplyReq editReplyReq) {
        inquiryReplyRepository.updatePost(editReplyReq.getInquiryReplyId(), editReplyReq.getContent());
        return "EDIT SUCCESS";
    }

    public String updateProcessing(Long inquiryId) {
        inquiryRepository.updateProcessing(inquiryId);
        return "UPDATE SUCCESS";
    }

    // 메인 문의면 이어지는 애들 다 삭제, 서브 문의면 그것만 삭제
    public String deleteInquiry(Long id) {

        int checkParentId = inquiryRepository.isParentId(id); // 메인 문의인지, 서브 문의인지 체크

        if (checkParentId == 0) { // 메인
            List<Inquiry> deleteInquiries = inquiryRepository.findInquiryById(id);

            for (Inquiry inquiry : deleteInquiries) {
                inquiryReplyRepository.delete(inquiry.getInquiryReplyId()); // 1-문의글의 답변글 삭제
                inquiryRepository.deleteById(inquiry.getInquiryId()); // 2-문의글 삭제
            }

        } else { // 서브
            inquiryRepository.deleteById(id);
        }

        return "DELETE SUCCESS";
    }

    public String deleteReply(Long id) {
        inquiryReplyRepository.deleteById(id);

        return "DELETE SUCCESS";
    }
}
