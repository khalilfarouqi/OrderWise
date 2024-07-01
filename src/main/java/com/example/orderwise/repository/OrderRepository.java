package com.example.orderwise.repository;

import com.example.orderwise.entity.Order;
import com.example.orderwise.entity.enums.City;
import com.example.orderwise.entity.enums.Stage;
import com.example.orderwise.entity.enums.Status;
import com.example.orderwise.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller")
    List<Order> findBySellerUsername(@Param("seller") String sellerUsername);
    @Query("select o from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller and o.stage = :stage")
    List<Order> getAllByStageAndSeller(@Param("stage") Stage stage, @Param("seller") String sellerUsername);
    List<Order> getAllByStage(Stage stage);

    @Query("select o.confirmationDate from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller and o.stage = :stage order by o.confirmationDate desc")
    Date getDateOfLastConfirmation(@Param("stage") Stage stage, @Param("seller") String sellerUsername);
    @Query("select o.confirmationDate from Order o where o.stage = :stage order by o.confirmationDate desc")
    Date getDateOfLastConfirmation(@Param("stage") Stage stage);

    @Query("select o.deliveredDate from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller and o.stage = :stage order by o.deliveredDate desc")
    Date getDateOfLastDelivered(@Param("stage") Stage stage, @Param("seller") String sellerUsername);
    @Query("select o.deliveredDate from Order o where o.stage = :stage order by o.deliveredDate desc")
    Date getDateOfLastDelivered(@Param("stage") Stage stage);

    @Query("select o.returnDate from Order o join o.cart c join c.items i join i.product p join p.seller s where s.username = :seller and o.stage = :stage order by o.returnDate desc")
    Date getDateOfLastReturn(@Param("stage") Stage stage, @Param("seller") String sellerUsername);
    @Query("select o.returnDate from Order o where o.stage = :stage order by o.returnDate desc")
    Date getDateOfLastReturn(@Param("stage") Stage stage);

    @Query("select o from Order o join o.cart c join c.items i join i.product p join p.seller s where s.userType = :userType and o.stage = :stage")
    List<Order> getAllByStageAndUserType(@Param("stage") Stage stage,@Param("userType") UserType userType);
    @Query("SELECT o FROM Order o JOIN o.cart c JOIN c.customer cu JOIN c.items i JOIN i.product p JOIN p.seller u WHERE o.status = 'WAITING' AND u.userType = 'DELIVERY_BOY' AND cu.city = :deliverCity")
    List<Order> findOrderToDeliver(@Param("deliverCity") City deliverCity);

    int countByStageAndStatus(Stage stage, Status status);
    int countByConfirmationBy(String confirmedName);
    @Query("select count(o) FROM Order o WHERE o.confirmationBy = :confirmationName AND year(o.confirmationDate) = :year AND month(o.confirmationDate) = :month AND day(o.confirmationDate) = :day")
    int countOrderTreatedThisDay(@Param("confirmationName") String confirmationName, @Param("year") int year, @Param("month") int month, @Param("day") int day);
    @Query("select count(o) FROM Order o WHERE o.confirmationBy = :confirmationName AND year(o.confirmationDate) = :year AND month(o.confirmationDate) = :month")
    int countOrderTreatedThisMonth(@Param("confirmationName") String confirmationName, @Param("year") int year, @Param("month") int month);
    int countByNoAnswerByAndStageOrStageAndStatus(String confirmationName,Stage stage1,Stage stage2, Status status);
    int countByNoAnswerByAndStageAndStatus(String confirmationName,Stage stage, Status status);
    int countByConfirmationByAndStageAndStatus(String confirmationName, Stage stage, Status status);
    int countByConfirmationByAndStageNotOrStageNot(String confirmationName, Stage stageP, Stage stageS);
    @Query("select o.confirmationDate from Order o where o.confirmationBy = :confirmationBy order by o.confirmationDate desc")
    Date getLastDateOrdersToConfirm(@Param("confirmationBy") String confirmationBy);
    @Query("select o.returnDate from Order o where o.confirmationBy = :confirmationBy order by o.returnDate desc")
    Date getLastDateOrdersToReturn(@Param("confirmationBy") String confirmationBy);

    List<Order> getAllByConfirmationByOrReturnedByOrNoAnswerBy(String confirmationBy, String deliveredBy, String returnedBy);

    List<Order> getAllByHoldToOrConfirmationByAndStage(String holdTo, String confirmationBy, Stage stage);
}
