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

import java.time.LocalDateTime;
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
                .postDate(LocalDateTime.now())
                .build());

        return "CREATE SUCCESS";
    }

    public String insert(NewReplyReq newReplyReq) {
        // 답변 글 생성
        inquiryReplyRepository.save(InquiryReply.builder()
                .username(newReplyReq.getUsername())
                .content(newReplyReq.getContent())
                .postDate(LocalDateTime.now())
                .build());

        // 최근에 삽입 한 답변 글의 id 가져오기
        Long inquiryReplyId = inquiryReplyRepository.findRecentData();

        // 답변 글이 바라보는 문의 글에 답변 글 id 넣어서 이어주기
        inquiryRepository.updateInquiryReplyId(inquiryReplyId, newReplyReq.getInquiryId());

        return "CREATE SUCCESS";
    }

    public String update(EditInquiryReq editReq) {
        int check = inquiryRepository.updatePost(editReq.getUsername(), editReq.getInquiryId(), editReq.getTitle(), editReq.getContent());

        if (check == 1) {
            return "EDIT SUCCESS";
        } else {
            return "EDIT FAILED : NOT MATCHED USERNAME";
        }
    }


    public String update(EditReplyReq editReplyReq) {
        int check = inquiryReplyRepository.updatePost(editReplyReq.getUsername(), editReplyReq.getInquiryReplyId(), editReplyReq.getContent());

        if (check == 1) {
            return "EDIT SUCCESS";
        } else {
            return "EDIT FAILED : NOT MATCHED USERNAME";
        }
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
