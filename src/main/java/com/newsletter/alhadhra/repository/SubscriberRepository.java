package com.newsletter.alhadhra.repository;

import com.newsletter.alhadhra.entity.SubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity,Long> {
}
