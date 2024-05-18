package com.knitwit.api.v1.controller;

import com.knitwit.model.Notification;
import com.knitwit.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "Добавление уведомления")
    @PostMapping("/save")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification savedNotification = notificationService.createNotification(notification);
        return ResponseEntity.ok(savedNotification);
    }

    @Operation(summary = "Получение всех уведомлений")
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @Operation(summary = "Получение уведомления по его ID")
    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable int notificationId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(notification);
    }

    @Operation(summary = "Удаление уведомления по его ID")
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<?> deleteNotificationById(@PathVariable int notificationId) {
        notificationService.deleteNotificationById(notificationId);
        return ResponseEntity.ok().build();
    }
}
