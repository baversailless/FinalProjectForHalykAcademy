package kz.halykacademy.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.halykacademy.bookstore.entity.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;
    private List<Long> bookIds;
    private OrderStatus orderStatus;
    private LocalDateTime creationDate;
}
