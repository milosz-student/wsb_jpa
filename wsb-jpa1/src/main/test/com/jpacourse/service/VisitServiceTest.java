package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;


    @Test
    @Transactional
    public void testFindByPatientId_shouldReturn2Visits() {
        // given
        final Long patientId = 1L;

        // when
        final List<VisitTO> visits = visitService.findByPatientId(patientId);

        // then
        assertThat(visits).isNotEmpty();
        assertThat(visits.size()).isEqualTo(2);
        assertThat(visits.get(0).getTime()).isEqualTo(
                LocalDateTime.of(2024, 11, 24, 10, 0, 0));
        assertThat(visits.get(1).getTime()).isEqualTo(
                LocalDateTime.of(2024, 12, 24, 10, 0, 0));
    }

    @Test
    @Transactional
    public void testFindByPatientId_shouldReturn4Visits() {
        // given
        final Long patientId = 2L;

        // when
        final List<VisitTO> visits = visitService.findByPatientId(patientId);

        // then
        assertThat(visits).isNotEmpty();
        assertThat(visits.size()).isEqualTo(4);
        assertThat(visits.get(0).getTime()).isEqualTo(
                LocalDateTime.of(2024, 11, 25, 14, 30, 0));
        assertThat(visits.get(1).getTime()).isEqualTo(
                LocalDateTime.of(2024, 12, 25, 14, 30, 0));
        assertThat(visits.get(2).getTime()).isEqualTo(
                LocalDateTime.of(2025, 12, 30, 10, 0, 0));
        assertThat(visits.get(3).getTime()).isEqualTo(
                LocalDateTime.of(2025, 12, 31, 14, 30, 0));

    }

    @Test
    @Transactional
    public void testFindByPatientId_shouldReturnEmptyList() {
        // given
        final Long patientId = 3L;

        // when
        final List<VisitTO> visits = visitService.findByPatientId(patientId);

        // then
        assertThat(visits).isEmpty();
    }

}
