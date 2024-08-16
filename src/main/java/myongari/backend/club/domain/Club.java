package myongari.backend.club.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clubs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String location;
    @Column(name = "sns_link")
    private String snsLink;
    private String introduce;
    private String activity;

    @Embedded
    private Image image;

    @Embedded
    private Apply apply;

    @Embedded
    private President president;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categories_id")
    private Category category;

    public Club(String name, String location, String snsLink, String introduce, String activity, Image image, Apply apply, President president, Category category) {
        this.name = name;
        this.location = location;
        this.snsLink = snsLink;
        this.introduce = introduce;
        this.activity = activity;
        this.image = image;
        this.apply = apply;
        this.president = president;
        this.category = category;
    }
}
