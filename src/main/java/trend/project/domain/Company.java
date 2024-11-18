package trend.project.domain;

import jakarta.persistence.*;
import lombok.*;
import trend.project.domain.enumClass.Role;
import trend.project.domain.enumClass.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
        이메일 타입으로 username이 들어가야하지만, 그러면 로그인필터 하나로 두 개의 엔티티를 검증하는데에 들어가는 교체비용이 너무 크기 때문에
        일단 username으로 받았습니다

        추가로 기업의 username 검증하는 로직은 강하게 검증해야 할 것 같습니다
    */
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String companyName;

    // 회원 ACTIVE,INACTIVE 상태 추가
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    // 담당자 이름
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    // 회원 비활성화 상태 날짜를 기록하는 필드
    private LocalDateTime inactiveDate;






    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<CompanyProfileImage> companyProfileImages = new ArrayList<>();

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();





    /* 연관관계 메서드 */

    // status를 INACTIVE로 변경하는 메서드
    public void setInactive() {

        this.status = Status.INACTIVE;
        this.inactiveDate = LocalDateTime.now();  // 비활성화 날짜 기록

    }


    public void setPassword(String password) {
        this.password = password;
    }

}
