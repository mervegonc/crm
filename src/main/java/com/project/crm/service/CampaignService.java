package com.project.crm.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.project.crm.dto.CampaignDTO;
import com.project.crm.entity.Campaign;

public interface CampaignService {

	Optional<Campaign> findById(Long id);

	Optional<Campaign> findByName(String name);

	List<Campaign> findAll();

	List<CampaignDTO> getAllCampaigns(); // Yeni metod

	CampaignDTO getCampaignById(Long id); // Yeni metod

	CampaignDTO createCampaign(CampaignDTO campaignDTO); // Yeni metod

	CampaignDTO updateCampaign(Long id, CampaignDTO campaignDTO); // Yeni metod

	void deleteCampaign(Long id); // Yeni metod

	List<Campaign> findActiveCampaigns();

	Campaign save(Campaign campaign);

	void deleteById(Long id);

	List<Campaign> getActiveCampaigns();

	List<Campaign> getActiveCampaigns(LocalDate startDate, LocalDate endDate);
}
