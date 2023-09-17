package org.dongguk.jjoin.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dongguk.jjoin.domain.type.DependentType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "club_name", nullable = false)
    private String name;

    @Column(name = "introduction")
    private String introduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    @Column(name = "dependent", nullable = false)
    @Enumerated(EnumType.STRING)
    private DependentType dependent;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @OneToOne
    @JoinColumn(name = "group_profile")
    private Image groupProfile;

    @OneToOne
    @JoinColumn(name = "background_image")
    private Image backgroundImage;

    //--------------------------------------------------------

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    List<ClubTag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    List<ClubMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    List<Plans> plans = new ArrayList<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    List<Album> albums = new ArrayList<>();

    @Builder
    public Club(String name, String introduction, User leader, DependentType dependent, Image groupProfile, Image backgroundImage) {
        this.name = name;
        this.introduction = introduction;
        this.leader = leader;
        this.dependent = dependent;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());;
        this.groupProfile = groupProfile;
        this.backgroundImage = backgroundImage;
    }
}