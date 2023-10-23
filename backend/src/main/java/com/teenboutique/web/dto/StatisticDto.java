package com.teenboutique.web.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public interface StatisticDto {
	public LocalDate getDate();
	public Long getSellPerDay();
}
