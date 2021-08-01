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

        if (inquiryRepository.existsById(id)) {

            boolean checkParentId = inquiryRepository.existsById(id); // 메인 문의인지, 서브 문의인지 체크
            if (checkParentId) { // 메인

                // 삭제 할 메인 문의
                Inquiry deleteInquiry = inquiryRepository.findMainById(id);

                // 메인 문의에 연결된 답변 삭제
                if (deleteInquiry.getInquiryReplyId() != null) {
                    inquiryReplyRepository.delete(deleteInquiry.getInquiryReplyId());
                }

                // 메인 문의 삭제
                inquiryRepository.deleteById(id);

                // 메인 문의의 서브 문의들 삭제
                List<Inquiry> deleteInquiries = inquiryRepository.findInquiryByParentId(id);

                for (Inquiry deleteinquiry : deleteInquiries) {

                    // TODO : 서브 문의에 연결된 답변 삭제 안 됨
                    if (deleteInquiry.getInquiryReplyId() != null) {
                        inquiryReplyRepository.delete(deleteinquiry.getInquiryReplyId()); // 1-답변 삭제
                    }

                    inquiryRepository.deleteByParentId(id); // 2-서브 문의 삭제
                }

            } else { // 서브
                inquiryRepository.deleteById(id);
            }

            return "DELETE SUCCESS";

        } else {
            return "DELETE FAILED : NOT EXISTED DATA";
        }

    }

    public String deleteReply(Long id) {
        inquiryReplyRepository.deleteById(id);

        return "DELETE SUCCESS";
    }
}
