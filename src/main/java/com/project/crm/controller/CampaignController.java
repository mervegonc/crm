package com.project.crm.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.crm.dto.CampaignDTO;
import com.project.crm.entity.Campaign;
import com.project.crm.service.CampaignService;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

	private final CampaignService campaignService;

	@Autowired
	public CampaignController(CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	@GetMapping
	public ResponseEntity<List<CampaignDTO>> getAllCampaigns() {
		List<CampaignDTO> campaigns = campaignService.getAllCampaigns();
		return ResponseEntity.ok(campaigns);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CampaignDTO> getCampaignById(@PathVariable Long id) {
		CampaignDTO campaign = campaignService.getCampaignById(id);
		if (campaign != null) {
			return ResponseEntity.ok(campaign);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CampaignDTO campaignDTO) {
		CampaignDTO createdCampaign = campaignService.createCampaign(campaignDTO);
		return ResponseEntity.ok(createdCampaign);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CampaignDTO> updateCampaign(@PathVariable Long id, @RequestBody CampaignDTO campaignDTO) {
		CampaignDTO updatedCampaign = campaignService.updateCampaign(id, campaignDTO);
		if (updatedCampaign != null) {
			return ResponseEntity.ok(updatedCampaign);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
		campaignService.deleteCampaign(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/active")
	public ResponseEntity<List<Campaign>> getActiveCampaigns(@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate) {
		List<Campaign> campaigns;
		if (startDate != null && endDate != null) {
			try {
				LocalDate start = LocalDate.parse(startDate);
				LocalDate end = LocalDate.parse(endDate);
				campaigns = campaignService.getActiveCampaigns(start, end);
			} catch (DateTimeParseException e) {
				return ResponseEntity.badRequest().build(); // Geçersiz tarih formatı
			}
		} else {
			campaigns = campaignService.findActiveCampaigns();
		}
		return ResponseEntity.ok(campaigns);
	}

}
