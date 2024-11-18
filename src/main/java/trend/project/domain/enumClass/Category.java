package trend.project.domain.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    MUSIC_PERFORMANCE("음악/공연"),
    ART_CRAFT("미술/공예"),
    LOCAL_CULTURE("지역문화"),
    FOOD_MARKET("음식/푸드마켓"),
    TRADITION_HISTORY("전통/역사"),
    NATURE_AGRICULTURE("자연환경/농업"),
    SPORTS("스포츠"),
    SEASONAL_EVENT("계절행사"),
    COMMUNITY_FAMILY("커뮤니티/가족");
    
    private final String description;
}
