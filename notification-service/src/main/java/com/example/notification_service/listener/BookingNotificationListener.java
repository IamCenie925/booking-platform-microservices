package com.example.notification_service.listener;

import com.example.notification_service.dto.BookingEventDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BookingNotificationListener {
    // Tên hàng đợi (phải khớp 100% với bên Booking Service)
    public static final String QUEUE_NAME = "booking.created.queue";

    /**
     * Bean này "tạo" (declare) queue trên RabbitMQ.
     * Cả bên Gửi và bên Nhận đều có Bean này để đảm bảo queue tồn tại.
     */
    @Bean
    public Queue bookingCreatedQueue() {
        return new Queue(QUEUE_NAME, true);
    }
    /**
     * Bean này cũng phải được thêm ở bên Nhận
     * để nó biết cách đọc (deserialize) tin nhắn JSON.
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    /**
     * Đây là hàm "Lắng nghe"
     * Spring sẽ tự động gọi hàm này khi có tin nhắn mới trong QUEUE_NAME
     */
    @RabbitListener(queues = QUEUE_NAME)
    public void onBookingCreated(BookingEventDto payload) { 
        System.out.println("==============================================");
        System.out.println(">>> [Notification Service] RECEIVED MESSAGE!");
        
        // Spring (với Jackson) sẽ tự động chuyển JSON -> object DTO
        System.out.println(">>> Payload (Object): " + payload.toString()); 
        
        System.out.println(">>> SIMULATING SENDING EMAIL.... to UserID: " + payload.userId());
        System.out.println(">>> For BookingID: " + payload.id());
        
        System.out.println(">>> Email sent successfully (simulation).");
        System.out.println("==============================================");
    }
}
