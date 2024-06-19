package com.example.orderwise.repository;

import com.example.orderwise.entity.Order;
import com.example.orderwise.entity.enums.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller")
    List<Order> findBySellerUsername(@Param("seller") String sellerUsername);
    @Query("select o from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller and o.stage = :stage")
    List<Order> getAllByStageAndSeller(@Param("stage") Stage stage, @Param("seller") String sellerUsername);
    @Query("select o.confirmationDate from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller and o.stage = :stage order by o.confirmationDate desc")
    Date getDateOfLastConfirmation(@Param("stage") Stage stage, @Param("seller") String sellerUsername);
    @Query("select o.deliveredDate from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller and o.stage = :stage order by o.deliveredDate desc")
    Date getDateOfLastDelivered(@Param("stage") Stage stage, @Param("seller") String sellerUsername);
    @Query("select o.returnDate from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller and o.stage = :stage order by o.returnDate desc")
    Date getDateOfLastReturn(@Param("stage") Stage stage, @Param("seller") String sellerUsername);
}
