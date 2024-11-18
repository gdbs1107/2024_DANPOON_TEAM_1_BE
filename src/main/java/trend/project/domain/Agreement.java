package trend.project.domain;

import jakarta.persistence.*;
import lombok.*;
import trend.project.domain.common.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 서비스 이용 약관
    @Column(nullable = false)
    private Boolean serviceAgreement;

    // 개인정보 수집 및 이용동의
    @Column(nullable = false)
    private Boolean personalDataAgree;

    // 제 3자 개인정보고 제공 동의
    @Column(nullable = false)
    private Boolean thirdPersonalDataAgree;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;
}
