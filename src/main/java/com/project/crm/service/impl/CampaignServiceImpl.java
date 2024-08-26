package com.project.crm.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crm.dto.CampaignDTO;
import com.project.crm.entity.Campaign;
import com.project.crm.repository.CampaignRepository;
import com.project.crm.service.CampaignService;

@Service
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	private CampaignRepository campaignRepository;

	@Override
	public Optional<Campaign> findById(Long id) {
		return campaignRepository.findById(id);
	}

	@Override
	public Optional<Campaign> findByName(String name) {
		return campaignRepository.findByName(name);
	}

	@Override
	public List<Campaign> findAll() {
		return campaignRepository.findAll();
	}

	@Override
	public List<CampaignDTO> getAllCampaigns() {
		List<Campaign> campaigns = campaignRepository.findAll();
		return campaigns.stream().map(campaign -> new CampaignDTO(campaign.getId(), campaign.getName(),
				campaign.getDescription(), campaign.isActive())).collect(Collectors.toList());
	}

	@Override
	public CampaignDTO getCampaignById(Long id) {
		Optional<Campaign> campaign = campaignRepository.findById(id);
		if (campaign.isPresent()) {
			return new CampaignDTO(campaign.get().getId(), campaign.get().getName(), campaign.get().getDescription(),
					campaign.get().isActive());
		}
		return null;
	}

	@Override
	public CampaignDTO createCampaign(CampaignDTO campaignDTO) {
		Campaign campaign = new Campaign();
		campaign.setName(campaignDTO.getName());
		campaign.setDescription(campaignDTO.getDescription());
		campaign.setActive(campaignDTO.isActive());
		Campaign savedCampaign = campaignRepository.save(campaign);
		return new CampaignDTO(savedCampaign.getId(), savedCampaign.getName(), savedCampaign.getDescription(),
				savedCampaign.isActive());
	}

	@Override
	public CampaignDTO updateCampaign(Long id, CampaignDTO campaignDTO) {
		Optional<Campaign> campaignOptional = campaignRepository.findById(id);
		if (campaignOptional.isPresent()) {
			Campaign campaign = campaignOptional.get();
			campaign.setName(campaignDTO.getName());
			campaign.setDescription(campaignDTO.getDescription());
			campaign.setActive(campaignDTO.isActive());
			Campaign updatedCampaign = campaignRepository.save(campaign);
			return new CampaignDTO(updatedCampaign.getId(), updatedCampaign.getName(), updatedCampaign.getDescription(),
					updatedCampaign.isActive());
		}
		return null;
	}

	@Override
	public void deleteCampaign(Long id) {
		campaignRepository.deleteById(id);
	}

	@Override
	public List<Campaign> findActiveCampaigns() {
		return campaignRepository.findByStartDateBeforeAndEndDateAfter(LocalDateTime.now());
	}

	@Override
	public Campaign save(Campaign campaign) {
		return campaignRepository.save(campaign);
	}

	@Override
	public void deleteById(Long id) {
		campaignRepository.deleteById(id);
	}
}
