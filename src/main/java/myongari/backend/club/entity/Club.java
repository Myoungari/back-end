package myongari.backend.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myongari.backend.category.entity.Category;
import myongari.backend.president.entity.President;

@Entity
@Table(name = "clubs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String location;
    @Column(name = "sns_link")
    private String snsLink;
    @Column(nullable = false, name = "recruitment_status")
    @Enumerated(EnumType.STRING)
    private State recruitmentStatus;
    @Column(name = "apply_link")
    private String applyLink;
    @Column(name = "image_link")
    private String imageLink;
    private String introduce;
    private String activity;
    private String qualifications;

    @OneToOne
    @JoinColumn(name = "presidents_id")
    private President president;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;
}
