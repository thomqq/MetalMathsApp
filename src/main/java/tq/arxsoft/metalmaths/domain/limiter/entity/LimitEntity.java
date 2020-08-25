package tq.arxsoft.metalmaths.domain.limiter.entity;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity

@NoArgsConstructor
//@Builder <= this is problem with builder
@Getter
@Setter

@Table(name = "limit")
public class LimitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", unique = true, nullable = false)
    private LocalDate date;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "max_allowed", nullable = false)
    private int maxAllowed;

}
