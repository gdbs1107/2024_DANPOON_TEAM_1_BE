package trend.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.domain.Plan;
import trend.project.domain.Ranking;
import trend.project.domain.enumClass.Category;
import trend.project.domain.enumClass.RankingCategory;
import trend.project.repository.RankingRepository;
import trend.project.repository.planRepository.PlanRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RankingService {

    private final PlanRepository planRepository;
    private final RankingRepository rankingRepository;



    @Scheduled(cron = "0 0 0 1 * *")
    public void calculateAndSaveMonthlyRanking() {
        // 한 달간 좋아요 수가 많은 5개의 Plan 가져오기
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        List<Plan> topPlans = planRepository.findTop5ByStartDateAfterOrderByLikesCountDesc(oneMonthAgo);

        // 기존 랭킹 데이터 삭제
        deleteRankingByCategory(RankingCategory.MAIN.name());

        // 새로운 랭킹 데이터 저장
        for (Plan plan : topPlans) {
            saveCategoryRanking("MAIN", plan);
        }
    }



    public void calculateAndSaveMonthlyRankingByCategory(String categoryName) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        // 카테고리별 Plan 검색
        List<Plan> topPlans = planRepository.findTop5ByCategoryAndStartDateAfterOrderByLikesCountDesc(
                Category.valueOf(categoryName.toUpperCase()), oneMonthAgo);

        // 기존 카테고리별 랭킹 데이터 삭제
        deleteRankingByCategory(categoryName);

        // 새로운 랭킹 데이터 저장
        for (Plan plan : topPlans) {
            saveCategoryRanking(categoryName, plan);
        }
    }

    private void deleteRankingByCategory(String categoryName) {
        RankingCategory rankingCategory = RankingCategory.valueOf(categoryName.toUpperCase());

        // 랭킹 카테고리별로 기존 데이터를 삭제
        rankingRepository.deleteByRankingCategory(rankingCategory);
    }



    // 카테고리별 랭킹 저장 로직 공통화
    private void saveCategoryRanking(String categoryName, Plan plan) {

        String imageLink = plan.getPlanPosterImage() != null && plan.getPlanPosterImage().getImageLink() != null
                ? plan.getPlanPosterImage().getImageLink()
                : "default-image-link"; // 기본 이미지 처리

        RankingCategory rankingCategory = RankingCategory.valueOf(categoryName.toUpperCase());  // 카테고리 enum 가져오기

        Ranking ranking = Ranking.builder()
                .planId(plan.getId())
                .title(plan.getTitle())
                .name(plan.getMember().getName())
                .likesCount(plan.getLikesCount())
                .commentsCount(plan.getCommentCount())
                .imageLink(imageLink)
                .rankingCategory(rankingCategory)  // 카테고리 설정
                .build();

        rankingRepository.save(ranking);
    }

}