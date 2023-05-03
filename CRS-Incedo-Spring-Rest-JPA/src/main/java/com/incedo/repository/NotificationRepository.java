/**
 * 
 */
package com.incedo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.entity.Notification;

/**
 * @author David Masaka
 *
 */
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	List<Notification> findNotificationByStudentid(int studentid);
}
