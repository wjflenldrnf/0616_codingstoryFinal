package org.spring.codingStory.pay.repository;

import org.spring.codingStory.pay.entity.PayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<PayEntity, Long> {
//    Page<PayEntity> findByPayMonContains(Pageable pageable, String search);
}
