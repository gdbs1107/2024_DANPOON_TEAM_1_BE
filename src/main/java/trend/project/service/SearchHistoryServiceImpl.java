package trend.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.domain.Member;
import trend.project.domain.SearchHistory;
import trend.project.repository.MemberRepository;
import trend.project.repository.SearchHistoryRepository;
import trend.project.web.dto.HistoryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchHistoryServiceImpl implements SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final MemberRepository memberRepository;



    @Override
    public List<HistoryDTO.HistoryResponseDTO> getHistory(String username){

        Member findMember = getMemberByUsername(username);

        List<SearchHistory> histories = searchHistoryRepository.findByMember(findMember);

        List<HistoryDTO.HistoryResponseDTO> historyList = histories.stream()
                .map(history -> HistoryDTO.HistoryResponseDTO.builder()
                        .content(history.getSearchContent())
                        .build())
                .collect(Collectors.toList());

        return historyList;

    }



    // 회원 찾는 메서드
    public Member getMemberByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(()-> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
