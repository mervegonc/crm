package com.project.crm.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.project.crm.dto.CampaignDTO;
import com.project.crm.service.CampaignService;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

	@Autowired
	private CampaignService campaignService;

	// Tüm kampanyaları listele
	@GetMapping
	public ResponseEntity<List<CampaignDTO>> getAllCampaigns() {
		List<CampaignDTO> campaigns = campaignService.getAllCampaigns();
		return ResponseEntity.ok(campaigns);
	}

	// ID'ye göre kampanya getir
	@GetMapping("/{id}")
	public ResponseEntity<CampaignDTO> getCampaignById(@PathVariable Long id) {
		CampaignDTO campaign = campaignService.getCampaignById(id);
		return ResponseEntity.ok(campaign);
	}

	// Yeni kampanya ekle
	@PostMapping
	public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CampaignDTO campaignDTO) {
		CampaignDTO createdCampaign = campaignService.createCampaign(campaignDTO);
		return ResponseEntity.ok(createdCampaign);
	}

	// Kampanyayı güncelle
	@PutMapping("/{id}")
	public ResponseEntity<CampaignDTO> updateCampaign(@PathVariable Long id, @RequestBody CampaignDTO campaignDTO) {
		CampaignDTO updatedCampaign = campaignService.updateCampaign(id, campaignDTO);
		return ResponseEntity.ok(updatedCampaign);
	}

	// Kampanyayı sil
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
		campaignService.deleteCampaign(id);
		return ResponseEntity.noContent().build();
	}
}
