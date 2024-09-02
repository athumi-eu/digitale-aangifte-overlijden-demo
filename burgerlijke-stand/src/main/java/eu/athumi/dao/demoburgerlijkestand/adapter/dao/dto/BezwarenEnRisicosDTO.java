package eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto.type.BezwarenDTOType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.dto.type.RisicoDTOType;

import java.util.List;

public record BezwarenEnRisicosDTO(List<BezwarenDTOType> bezwaren, List<RisicoDTOType> risicos) {}
