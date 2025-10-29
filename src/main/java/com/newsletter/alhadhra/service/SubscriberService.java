package com.newsletter.alhadhra.service;
import com.newsletter.alhadhra.entity.SubscriberEntity;
import com.newsletter.alhadhra.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public SubscriberEntity saveSubscriber(SubscriberEntity subscriber){
        return subscriberRepository.save(subscriber);
    }

    public List<SubscriberEntity> getSubscribers(){
        return subscriberRepository.findAll();
    }
}
