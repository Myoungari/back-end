package myongari.backend.club.entity;

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
    @Column(name = "image_link")
    private String imageLink;
    private String introduce;
    private String activity;

    @Embedded
    private Apply apply;

    @Embedded
    private President president;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;

    public Club(String name, String location, String snsLink, String imageLink, String introduce, String activity, Apply apply, President president, Category category) {
        this.name = name;
        this.location = location;
        this.snsLink = snsLink;
        this.imageLink = imageLink;
        this.introduce = introduce;
        this.activity = activity;
        this.apply = apply;
        this.president = president;
        this.category = category;
    }
}
